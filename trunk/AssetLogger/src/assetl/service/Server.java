package assetl.service;

import assetl.service.alus.ScriptAction;
import assetl.system.*;

import java.io.*;
import java.net.*;
import java.security.KeyStore;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

import static assetl.service.alus.UpdateScriptKeywords.*;
import static resources.Config.*;
import static assetl.system.WebServerConstants.*;

/**
 * A singleton Server that will take assets, people, and request classes
 * and add or modify the database to reflect the changes to the objects, based
 * on the objects passed as parameters. Sends notfications of state changes
 * to any interested listeners using the Observer pattern.
 *
 * @author Bryon Rogers
 * @author Michael Hale
 * @see <a href=""> Database design </a>
 */
public class Server
   extends Thread
   implements AssetLModel
{
   /**
    * Default Pool Size
    */
   public final static int DEFAULT_POOL_SIZE = 20;
   /**
    * The ServerSocket
    */
   private ServerSocket mServerSocket = null;
   /**
    * A count of connections
    */
   private int mConnectionCount = 0;
   /**
    * Holds an array of RequestHandlers
    */
   private RequestHandler[] mHandlerPool;
   /**
    * The size of the RequestHandler array
    */
   private int mPoolSize;
   /**
    * The number of the last request
    */
   private int mLastRequestNumber = 0;
   /**
    * A File object to represent the database.
    */
   private File mFile;
   /**
    * The database connection.
    */
   private Connection mConn;
   /**
    * A sql statement.
    */
   private Statement mStat;
   /**
    * Variable to hold a singleton of Server
    */
   private static final Server cInstance;

   /**
    * Initialize a Server instance
    */
   static
   {
      cInstance = new Server();
      //new Thread(cInstance).run();
   }

   /**
    * Constructor is deliberately private to create a singleton.
    * It will create a connection to the database, if the database does not
    * exist are
    */
   private Server()
   {
      //Used to close the database when the server exits.
      Runtime.getRuntime().addShutdownHook(new Thread()
      {
         @Override
         public void run()
         {
            try
            {
               mStat.close();
               mConn.close();
            }
            catch (Exception e)
            {
            }
         }
      });

      mFile = new File(System.getProperty("dbfilename", "LaptopChecker")
         + "." + System.getProperty("dbfileext", "aldb"));

      try
      {

         if (!mFile.exists() || !mFile.isFile())
         {
            System.err.println("Making Database");
            createDB();
         }
         else
         {
            loadDatabaseDriver("org.sqlite.JDBC");
            mConn = DriverManager.getConnection("jdbc:sqlite:"
               + mFile.getName());

            mStat = mConn.createStatement();
            BufferedReader in = new BufferedReader(
               new InputStreamReader(
               Server.class.getResourceAsStream("Update.alus")));
            Connection conn = DriverManager.getConnection("jdbc:sqlite:"
               + mFile.getName());
            ResultSet rs = conn.createStatement().executeQuery("Select "
               + "ChangeString from DatabaseInfo");
            String prevVersion = rs.getString(1);
            rs.close();
            System.err.println(prevVersion);
            int compare = prevVersion.compareTo(in.readLine());
            System.err.println(compare);
            if (compare < 0)
            {
               update(in, prevVersion);
            }
            mConn.setAutoCommit(false);
         }
      }
      catch (Exception e)
      {
         //TODO: Fix the Null pointer exception that occurs in the try block above
         System.out.println(e);
         System.exit(-1);
      }
      HttpRS.setServer(this);
   }

   private boolean update(BufferedReader pIn, String prevVersion)
   {
      try
      {
         String str;
         boolean startUpdate = false;
         backup();
         int i = 1;
         while ((str = pIn.readLine()) != null)
         {
            if (str.startsWith("/") && !startUpdate)
            {
               startUpdate = prevVersion.equals(str.split("/")[1]);
               System.err.println(str);
            }
            else if (startUpdate && !str.startsWith("/"))
            {
               System.err.println(str);
               if (KEYWORDS.containsKey(str))
               {
                  if (!(((ScriptAction) KEYWORDS.get(str).
                     getConstructor(File.class)
                     .newInstance(mFile)).update()))
                  {
                     System.err.println("Could not run '" + str + "' on line "
                        + i + ".");
                     return false;
                  }
               }
               else
               {
                  try
                  {
                     mConn.prepareStatement(str).execute();
                  }
                  catch (Exception e)
                  {
                     System.err.println("Error on line " + i +
                        " with '" + str + "'.");
                     e.printStackTrace();
                  }
               }
            }
            i++;
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
         return false;
      }
      return true;
   }

   private

    void backup()
   {
      try
      {
         DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
         java.util.Date date = new java.util.Date();

         FileInputStream fin = new FileInputStream(mFile);
         String backup = mFile.getAbsolutePath()
            + ".backup_" + dateFormat.format(date);
         FileOutputStream fout = new FileOutputStream(backup);
         byte[] buffer = new byte[1024];
         int bytesRead;
         while ((bytesRead = fin.read(buffer)) > 0)
         {
            fout.write(buffer, 0, bytesRead);
         }
         fin.close();
         fout.close();
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   /**
    * Creates the needed Database for the Asset Logger to store information
    * in.
    * @throws Exception
    */
   private void createDB()
   {
      try
      {
         loadDatabaseDriver("org.sqlite.JDBC");
         mConn = DriverManager.getConnection("jdbc:sqlite:"
            + mFile.getName());
         mStat = mConn.createStatement();
         BufferedReader in = new BufferedReader(
            new InputStreamReader(
            Server.class.getResourceAsStream("AssetLoggerSetup.sql")));
         String str;
         while ((str = in.readLine()) != null)
         {
            System.err.println(str);
            mStat.execute(str);
         }
         in.close();
      }
      catch (SQLException e)
      {
         e.printStackTrace();
      }
      catch (FileNotFoundException e)
      {
      }
      catch (IOException e)
      {
      }
   }

   /**
    * Loads the class for the database driver.
    *
    * @param driverName The name of the driver class.
    */
   private void loadDatabaseDriver(String driverName)
   {
      try
      {
         Class.forName(driverName);

      }
      catch (ClassNotFoundException ex)
      {
         ex.printStackTrace();
         System.exit(-1);
      }
   }

   /**
    * Returns the Server instance
    *
    * @return The singleton instance of Server
    */
   public static Server getInstance()
   {
      return cInstance;
   }

   /**
    * Returns a person object to represent the person in the database based on
    * the passed ID.
    *
    * @param pID The id of the person to be returned.
    * @return The person object representing the person with the passed ID.
    */
   public Person getPerson(String pID)
   {
      Person person = null;
      Connection conn = null;
      try
      {
         conn = DriverManager.getConnection("jdbc:sqlite:"
            + mFile.getName());
         ResultSet rs = conn.createStatement().executeQuery(
            "select * from requestors where"
            + " RequestorID='" + pID + "';");
         if (rs.next())
         {
            person = new Person(rs.getString("RequestorID"),
               rs.getString("FirstName"), rs.getString("MI"),
               rs.getString("LastName"), rs.getString("Email"),
               rs.getString("Phone"));
         }
         rs.close();
      }
      catch (Exception e)
      {
         System.err.println(e);
      }
      finally
      {
         try
         {
            conn.close();
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }
      }
      return person;
   }

   /**
    * Takes the person object and makes changes to the database based on
    * the object.
    *
    * @param pPerson The person to have information added/changed.
    */
   public synchronized void setPerson(Person pPerson)
   {
      //
      // Retrieves data from database to see if the person needs
      // to be added, and also to check to see if there is an actual
      // need to update the data
      //
      Connection conn = null;
      try
      {
         Person temp = getPerson(pPerson.getID());
         PreparedStatement prep = null;
         conn = DriverManager.getConnection("jdbc:sqlite:"
            + mFile.getName());
         if (temp == null)
         {
            //System.err.println("Adding " + pPerson.getFirstName());
            prep = conn.prepareStatement(
               "insert into Requestors values (?, ?, ?, ?, ?, ?);");
         }
         else if (!(temp.getFirstName().equals(pPerson.getFirstName()))
            || !(temp.getMiddleName().equals(pPerson.getMiddleName()))
            || !(temp.getLastName().equals(pPerson.getLastName()))
            || !(temp.getEmail().equals(pPerson.getEmail()))
            || !(temp.getPhoneNumber().equals(pPerson.getPhoneNumber())))
         {
            //System.err.println("Updating " + pPerson.getFirstName());
            prep = conn.prepareStatement(
               "update Requestors set RequestorID = ?, FirstName= ?, "
               + "MI = ?, LastName =?, Phone = ?, Email = ?"
               + " where RequestorID ='" + pPerson.getID() + "';");
         }

         if (prep != null)
         {
            prep.setString(1, pPerson.getID());
            prep.setString(2, pPerson.getFirstName());
            prep.setString(3, pPerson.getMiddleName());
            prep.setString(4, pPerson.getLastName());
            prep.setString(5, pPerson.getPhoneNumber());
            prep.setString(6, pPerson.getEmail());
            prep.execute();
         }
      }
      catch (SQLException e)
      {
         e.printStackTrace(System.err);
      }
      finally
      {
         try
         {
            conn.close();
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }
      }
   }

   /**
    * Returns an asset object that represents the the asset in the database
    * with the passed ID.
    *
    * @param pID The ID of the asset to get an object of.
    * @return The asset with that has the passed ID.
    */
   public Asset getAsset(String pID)
   {
      Asset asset = null;
      Connection conn = null;
      try
      {
         conn = DriverManager.getConnection("jdbc:sqlite:"
            + mFile.getName());
         ResultSet rs = conn.createStatement().executeQuery("select * from assets where"
            + " AssetID='" + pID + "';");
         if (rs.next())
         {
            asset = new Asset(rs.getString("AssetID"),
               rs.getString("Make"), rs.getString("Model"),
               rs.getString("SerialNumber"),
               rs.getString("AssetType"),
               rs.getString("Description"),
               rs.getBoolean("inMaintenance"));
         }
         rs.close();
      }
      catch (Exception e)
      {
         System.err.println(e);
      }
      finally
      {
         try
         {
            conn.close();
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }
      }
      return asset;
   }

   /**
    * Sets asset information in the database
    *
    * @param pAsset The asset to set
    */
   public synchronized void setAsset(Asset pAsset)
   {
      System.err.println("Retreivinng " + pAsset.getID());
      PreparedStatement prep = null;
      Connection conn = null;
      try
      {
         conn = DriverManager.getConnection("jdbc:sqlite:"
            + mFile.getName());
         //Retrieves data from database to see if the person needs to be added,
         //and also to check to see if there is an actual need to update te data.
         Asset temp = getAsset(pAsset.getID());

         if (temp == null)
         {
            System.err.println("Adding " + pAsset.getID());
            prep = conn.prepareStatement(
               "insert into Assets values (?, ?, ?, ?, ?, ?, ?);");
         }
         else if (!(temp.getMake().equals(pAsset.getMake()))
            || !(temp.getModel().equals(pAsset.getModel()))
            || !(temp.getSerialNum().equals(pAsset.getSerialNum()))
            || !(temp.getType().equals(pAsset.getType()))
            || !(temp.getDescription().equals(pAsset.getDescription()))
            || (temp.isInMaintenance() != pAsset.isInMaintenance()))
         {
            System.err.println("Updating " + pAsset.getID());
            prep = conn.prepareStatement(
               "update Assets set AssetID = ?, Make = ?, "
               + "Model = ?, SerialNumber =?, AssetType = ?, "
               + "Description = ?, inMaintenance = ? where AssetID ='" + pAsset.
               getID()
               + "';");
         }

         if (prep != null)
         {
            prep.setString(1, pAsset.getID());
            prep.setString(2, pAsset.getMake());
            prep.setString(3, pAsset.getModel());
            prep.setString(4, pAsset.getSerialNum());
            prep.setString(5, pAsset.getType());
            prep.setString(6, pAsset.getDescription());
            prep.setBoolean(7, pAsset.isInMaintenance());
            prep.execute();
         }
      }
      catch (SQLException e)
      {
         System.err.println(e.getSQLState());
         e.printStackTrace(System.err);
      }
      finally
      {
         try
         {
            conn.close();
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }
      }
   }

   /**
    * Returns a request object that represents the request in the database with
    * the passed ID.
    *
    * @param pID The ID of the request to retrieve from the database.
    * @return The request object that represents the request in the database
    *  that has the passed ID.
    */
   public Request getRequest(String pID)
   {
      Request request = null;
      Connection conn = null;
      try
      {
         conn = DriverManager.getConnection("jdbc:sqlite:"
            + mFile.getName());
         ResultSet requestSet = conn.createStatement().executeQuery(
            "select * from Requests where" + " RequestID='" + pID + "';");
         if (requestSet.next())
         {
            request = new Request(requestSet.getString("RequestID"),
               requestSet.getDate("RequestedMadeDate"),
               requestSet.getDate("RequestedPickupDate"),
               requestSet.getString("RequestedType"),
               getPerson(requestSet.getString("RequestorID")));
         }
         requestSet.close();

         if (request != null)
         {
            request.setCheckouts(getCheckouts(request));
         }
      }
      catch (Exception e)
      {
         System.err.println(e);
      }
      finally
      {
         try
         {
            conn.close();
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }
      }

      return request;
   }

   /**
    * Creates a collection of checkouts that are owned by the passed request.
    *
    * @param pRequest The request which owns all the returned checkouts.
    * @return A collection of checkouts that are part of the passed request.
    */
   public Collection<Checkout> getCheckouts(Request pRequest)
   {
      Collection<Checkout> checkouts = new ALArrayList<Checkout>();
      Connection conn = null;
      try
      {

         conn = DriverManager.getConnection("jdbc:sqlite:"
            + mFile.getName());
         ResultSet rs = conn.createStatement().executeQuery(
            "select * from Checkouts where" + " RequestID='"
            + pRequest.getID() + "';");

         System.out.println(rs.isBeforeFirst());

         while (rs.next())
         {
            checkouts.add(new Checkout(rs.getString("CheckoutID"),
               rs.getString("RequestID"),
               getAsset(rs.getString("AssetID")),
               getPerson(rs.getString("RecipeantID")),
               rs.getDate("RequestedStartDate"),
               rs.getDate("RequestedEndDate"),
               rs.getDate("PickupDate"),
               rs.getDate("ReturnDate"),
               rs.getBoolean("Active")));
         }
         rs.close();
      }
      catch (Exception e)
      {
         System.err.println(e);
         e.printStackTrace();
      }
      finally
      {
         try
         {
            conn.close();
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }
      }
      return checkouts;
   }

   /**
    * Creates a Checkout with the ID that is passed, based on the data in the
    * database.
    *
    * @param pID The ID of the checkout to create a checkout of.
    * @return The checkout with the passed ID.
    */
   public Checkout getCheckout(String pID)
   {
      Checkout checkout = null;
      Connection conn = null;
      try
      {
         conn = DriverManager.getConnection("jdbc:sqlite:"
            + mFile.getName());
         ResultSet rs = conn.createStatement().executeQuery(
            "select * from Checkouts where"
            + " CheckoutID='" + pID + "';");
         if (rs.next())
         {
            checkout = new Checkout(rs.getString("CheckoutID"),
               rs.getString("RequestID"),
               getAsset(rs.getString("AssetID")),
               getPerson(rs.getString("RecipeantID")),
               rs.getDate("RequestedStartDate"),
               rs.getDate("RequestedEndDate"),
               rs.getDate("PickupDate"),
               rs.getDate("ReturnDate"),
               rs.getBoolean("Active"));
         }
         rs.close();
      }
      catch (Exception e)
      {
         System.err.println(e);
      }
      finally
      {
         try
         {
            conn.close();
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }
      }
      return checkout;
   }

   /**
    * Takes the passed Checkout object and makes corresponding changes to
    * the database based on that object
    *
    * @param pCheckout The checkout to make changes based on.
    * @param pUserID The user who made the change.
    */
   public synchronized void setCheckout(Checkout pCheckout, String pUserID)
   {
      if (pCheckout != null)
      {
         Connection conn = null;
         try
         {
            Checkout temp = null;
            conn = DriverManager.getConnection("jdbc:sqlite:"
               + mFile.getName());
            System.err.println("Retreivinng " + pCheckout.getID());
            //Retrieves data from database to see if the person needs to be added,
            //and also to check to see if there is an actual need to update te data.

            PreparedStatement prepReq = null;
            if (("".equals(pCheckout.getID()))
               || (((temp = getCheckout(pCheckout.getID())) == null)))
            {
               pCheckout.setID(Integer.toString(getNumCheckouts() + 1));
               System.err.println("Adding " + pCheckout.getID());
               prepReq = conn.prepareStatement(
                  "insert into Checkouts values (?, ?, ?, ?, ?, ?, ?, ?, '"
                  + pUserID + "', ?);");
            }
            else
            {
               if (!(temp.getAsset() != null
                  ? temp.getAsset().equals(pCheckout.getAsset())
                  : pCheckout.getAsset() == null)
                  || !((temp.getPickedupDate() != null)
                  ? temp.getPickedupDate().equals(pCheckout.getPickedupDate())
                  : pCheckout.getPickedupDate() == null)
                  || !((temp.getRecipient() != null)
                  ? temp.getRecipient().equals(pCheckout.getRecipient())
                  : pCheckout.getRecipient() == null)
                  || !((temp.getRequestedEndDate() != null)
                  ? temp.getRequestedEndDate().equals(pCheckout.
                  getRequestedEndDate())
                  : pCheckout.getRequestedEndDate() == null)
                  || !((temp.getRequestedEndDate() != null)
                  ? temp.getRequestedStartDate().equals(pCheckout.
                  getRequestedStartDate())
                  : pCheckout.getRequestedStartDate() == null)
                  || !((temp.getReturnedDate() != null)
                  ? temp.getReturnedDate().equals(pCheckout.getReturnedDate())
                  : pCheckout.getReturnedDate() == null)
                  || temp.isActive() != pCheckout.isActive())
               {

                  System.err.println("Updating " + pCheckout.getID());
                  prepReq = conn.prepareStatement(
                     "update Checkouts set CheckoutID = ?, RequestID = ?,"
                     + " RecipeantID = ?, AssetID = ?,"
                     + " RequestedStartDate = ?, RequestedEndDate = ?,"
                     + " PickupDate = ?, ReturnDate = ?, " + "UserID ='"
                     + pUserID + "', Active = ? where RequestID ='"
                     + pCheckout.getID() + "';");
               }
            }
            if (prepReq != null)
            {
               prepReq.setString(1, pCheckout.getID());
               prepReq.setString(2, pCheckout.getRequestID());
               prepReq.setString(3, pCheckout.getRecipient().getID());
               prepReq.setString(4, ((pCheckout.getAsset() == null)
                  ? null : pCheckout.getAsset().getID()));
               prepReq.setTimestamp(5, ((pCheckout.getRequestedStartDate() != null)
                  ? new Timestamp(pCheckout.getRequestedStartDate().getTime())
                  : null));
               prepReq.setTimestamp(6, ((pCheckout.getRequestedEndDate() != null)
                  ? new Timestamp(pCheckout.getRequestedEndDate().getTime())
                  : null));
               prepReq.setTimestamp(7, ((pCheckout.getPickedupDate() != null)
                  ? new Timestamp(pCheckout.getPickedupDate().getTime())
                  : null));
               prepReq.setTimestamp(8, ((pCheckout.getReturnedDate() != null)
                  ? new Timestamp(pCheckout.getReturnedDate().getTime())
                  : null));
               prepReq.setBoolean(9, pCheckout.isActive());
               prepReq.execute();
            }
         }
         catch (Exception e)
         {
            e.printStackTrace(System.err);
         }
         finally
         {
            try
            {
               conn.close();
            }
            catch (Exception e)
            {
               e.printStackTrace();
            }
         }
      }
   }

   /**
    * Makes changes to the database based on the request that was passed in.
    *
    * @param pRequest The request to make changes to in the database.
    * @param pUserID The user who is making the change
    */
   public synchronized void setRequest(Request pRequest, String pUserID)
   {
       Connection conn = null;
      try
      {
         //System.err.println("Retreivinng " + pRequest.getID());
         //Retrieves data from database to see if the person needs to be added,
         //and also to check to see if there is an actual need to update te data.
         Request temp = null;
         PreparedStatement prepReq = null;
         conn =  DriverManager.getConnection("jdbc:sqlite:"
               + mFile.getName());
         if (("".equals(pRequest.getID()))
            || ((temp = getRequest(pRequest.getID())) == null))
         {
            pRequest.setID(Integer.toString(getNumRequests() + 1));
            System.err.println("Adding " + pRequest.getID());
            prepReq = conn.prepareStatement(
               "insert into Requests values (?, ?, ?, ?, ?, '"
               + pUserID + "');");
         }
         else
         {
            if (!pRequest.getRequestMade().equals(temp.getRequestMade())
               || !pRequest.getRequestedPickup().equals(
               temp.getRequestedPickup())
               || !pRequest.getRequestor().equals(temp.getRequestor())
               || pRequest.getRequstType().equals(temp.getRequstType()))
            {
               System.err.println("Updating " + pRequest.getID());
               prepReq = conn.prepareStatement(
                  "update Requests set RequestID = ?, RequestedMadeDate = ?,"
                  + " RequestedPickupDate = ?, RequestedType = ?,"
                  + " RequestorID = ?, UserID ='" + pUserID
                  + "' where RequestID ='" + pRequest.getID() + "';");
            }
         }

         if (prepReq != null)
         {
            prepReq.setString(1, pRequest.getID());
            prepReq.setTimestamp(2,
               new Timestamp(pRequest.getRequestMade().getTime()));
            prepReq.setTimestamp(3,
               new Timestamp(pRequest.getRequestedPickup().getTime()));
            prepReq.setString(4, pRequest.getRequstType());
            prepReq.setString(5, pRequest.getRequestor().getID());
            prepReq.execute();
         }
         //conn.commit();
         prepReq.close();
         for (Checkout checkout : pRequest.getCheckouts())
         {
            setCheckout(checkout, pUserID);
         }
      }
      catch (SQLException e)
      {
         e.printStackTrace(System.err);
         try
         {
            conn.close();
         }
         catch (Exception ex)
         {
         }
      }
      catch (NullPointerException e)
      {
      }
   }

   /**
    * Gets the Checkout object that has no returned date set
    * for the Asset given.
    *
    * @param pAsset The asset that needs to be returned
    * @return The Checkout object for this asset
    */
   public Checkout getCheckout(Asset pAsset)
   {
      return getActiveCheckouts(pAsset).iterator().next();
   }

   /**
    * Gets the checked out Checkouts for this asset. If more than one checkout
    * is returned in the collection there is likely a problem to be resolved.
    *
    * @param pAsset The asset to get checked out Checkouts
    * @return The Checkouts that are checked out for this asset
    */
   public Collection<Checkout> getCheckedOutCheckouts(Asset pAsset)
   {
      Collection<Checkout> checkouts = new ALArrayList<Checkout>();
      Connection conn = null;
      try
      {
         conn = DriverManager.getConnection("jdbc:sqlite:"
            + mFile.getName());
         ResultSet rs = conn.createStatement().executeQuery(
            "select * from Checkouts where"
            + " AssetID='" + pAsset.getID()
            + "' and Active=1 and PickupDate is not null;");
         while (rs.next())
         {
            checkouts.add(new Checkout(rs.getString("CheckoutID"),
               rs.getString("RequestID"),
               getAsset(rs.getString("AssetID")),
               getPerson(rs.getString("RecipeantID")),
               rs.getDate("RequestedStartDate"),
               rs.getDate("RequestedEndDate"),
               rs.getDate("PickupDate"),
               rs.getDate("ReturnDate"),
               rs.getBoolean("Active")));
         }
         rs.close();
      }
      catch (Exception e)
      {
         System.err.println(e);
         e.printStackTrace();
      }
      finally
      {
         try
         {
            conn.close();
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }
      }
      return checkouts;
   }

   /**
    * Gets the active checkouts in the database for an asset.
    *
    * @param pAsset The asset to find active checkouts for
    * @return The checkouts that are active for this asset
    */
   public Collection<Checkout> getActiveCheckouts(Asset pAsset)
   {
      Collection<Checkout> checkouts = new ALArrayList<Checkout>();
      Connection conn = null;
      try
      {
         conn = DriverManager.getConnection("jdbc:sqlite:"
            + mFile.getName());
         ResultSet rs = conn.createStatement().executeQuery(
            "select * from Checkouts where"
            + " AssetID='" + pAsset.getID()
            + "' and Active=1 order by "
            + "RequestedStartDate, PickupDate DESC"
            + ";");
         while (rs.next())
         {
            checkouts.add(new Checkout(rs.getString("CheckoutID"),
               rs.getString("RequestID"),
               getAsset(rs.getString("AssetID")),
               getPerson(rs.getString("RecipeantID")),
               rs.getDate("RequestedStartDate"),
               rs.getDate("RequestedEndDate"),
               rs.getDate("PickupDate"),
               rs.getDate("ReturnDate"),
               rs.getBoolean("Active")));
         }
         rs.close();
      }
      catch (Exception e)
      {
         System.err.println(e);
         e.printStackTrace();
      }
      finally
      {
         try
         {
            conn.close();
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }
      }
      return checkouts;
   }

   /**
    * Sets a Checkout
    *
    * @param pCheckout The checkout to set
    * @deprecated
    */
   public synchronized void setCheckout(Checkout pCheckout)
   {
      /*  Only if we want to allow updates without user info.
       * But we schould not in my opion.  This should not be used.
      setCheckout(pCheckout, "user");

       */
   }

   /**
    * Creates a collection of all users.
    *
    * @return A collection of all users.
    */
   public Collection<User> getUsers()
   {
      Collection<User> users = new ALArrayList<User>();
      Connection conn = null;
      try
      {
         conn = DriverManager.getConnection("jdbc:sqlite:"
            + mFile.getName());
         ResultSet rs = conn.createStatement().executeQuery(
            "select * from users;");
         while (rs.next())
         {
            users.add(new User(rs.getString("UserID"),
               rs.getString("Password"),
               rs.getInt("isAdmin")));
         }
         rs.close();
      }
      catch (Exception e)
      {
         System.err.println(e);
      }
      finally
      {
         try
         {
            conn.close();
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }
      }
      return users;
   }

   /**
    * Tells if the user, with the passed ID, is an admin.
    *
    * @param pID The ID of the user to check the admin status of.
    * @return True if the user is an admin.
    */
   public Integer getAccessLevel(String pID)
   {
      try
      {
         return getUser(pID).adminRights();
      }
      catch (NullPointerException e)
      {
         return User.NONEXISTANT;
      }
   }

   /**
    * Creates a user object to represent the user in the database who has the
    * passed ID. A null reference is returned if the use dose not exist.
    *
    * @param pID The ID of the user in the database to create an object of.
    * @return A user object that has the ID that was passed in.
    */
   public User getUser(String pID)
   {
      User user = null;
      Connection conn = null;
      try
      {
         conn = DriverManager.getConnection("jdbc:sqlite:"
            + mFile.getName());
         ResultSet rs = conn.createStatement().executeQuery(
            "select * from users where"
            + " UserID='" + pID + "';");
         if (rs.next())
         {
            user = new User(rs.getString("UserID"), rs.getString("Password"),
               rs.getInt("isAdmin"));
         }
         rs.close();
      }
      catch (Exception e)
      {
         System.err.println(e);
      }
      finally
      {
         try
         {
            conn.close();
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }
      }
      return user;
   }

   /**
    * Checks the users password.
    *
    * @param pID The user to check the password of.
    * @param pPwd The password to verify.
    * @return Returns true if the passed password is the user's password.
    */
   public Boolean checkPwd(String pID, String pPwd)
   {
      try
      {
         return ((pPwd != null)
            && pPwd.equals(getUser(pID).getPassword()));
      }
      catch (NullPointerException e)
      {
         return false;
      }
   }

   /**
    * Returns a collection of assets that are available at the specify time
    * frame.
    * @param pStart The start time availability of the assets
    * @param pEnd The end time availability of the assets
    * @return A collection of assets are available at the specifies time.
    */
   public Collection<Asset> getAvailAsset(Date pStart, Date pEnd)
   {
      Collection<Asset> assets = new ALArrayList<Asset>();
      ArrayList<String> assetIDs = new ArrayList<String>();
      Connection conn = null;
      try
      {
         conn = DriverManager.getConnection("jdbc:sqlite:"
            + mFile.getName());
         ResultSet rs = conn.createStatement().executeQuery(
            "Select AssetID, RequestedStartDate, RequestedEndDate "
            + "from checkouts where active=1;");

         try
         {
            while (rs.next())
            {



               Date qStartDate = rs.getDate("RequestedStartDate");
               Date qEndDate = rs.getDate("RequestedEndDate");
               if ((qStartDate.getTime() <= pStart.getTime())
                  && (qEndDate.getTime() >= pStart.getTime()))
               {
                  String id = rs.getString("AssetID");
                  if (!assetIDs.contains(id))
                  {
                     assetIDs.add(id);
                  }
               }
               else if (qStartDate.getTime() <= pEnd.getTime()
                  && (qEndDate.getTime() >= pEnd.getTime()))
               {
                  String id = rs.getString("AssetID");
                  if (!assetIDs.contains(id))
                  {
                     assetIDs.add(id);
                  }
               }
               else
               {
               }
            }
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }

         rs.close();

         String ids = "";

         for (String id : assetIDs)
         {
            ids += (("".equals(ids)) ? "" : ", ") + "\"" + id + "\"";
         }

         rs = conn.createStatement().executeQuery(
            "select * from assets where Assets.inMaintenance=0 and"
            + " assetid not in (" + ids + ");");/* +
         "or not exists (select assetid " +
         "from checkouts where checkouts.assetid=assets.assetid and active=1) " +
         ");");*/

         try
         {
            while (rs.next())
            {
               assets.add(new Asset(rs.getString("AssetID"),
                  rs.getString("Make"),
                  rs.getString("Model"),
                  rs.getString("SerialNumber"),
                  rs.getString("AssetType"),
                  rs.getString("Description"),
                  rs.getBoolean("inMaintenance")));
            }
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }

         rs.close();
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      finally
      {
         try
         {
            conn.close();
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }
      }
      return assets;
   }

   /**
    * Returns a collection of people who have borrowed the asset.
    *
    * @param pAsset The asset to get a borrow history for
    * @return The people who have borrowed this asset
    */
   public Collection<Person> getBorrowers(Asset pAsset)
   {
      Collection<Person> borrowers = new ALArrayList<Person>();
      Connection conn = null;
      try
      {
         conn = DriverManager.getConnection("jdbc:sqlite:"
            + mFile.getName());
         ResultSet rs = conn.createStatement().executeQuery(
            "select * from Requestors where exists (select requestorid Requests"
            + " where exists (select requestid from Checkouts where "
            + "Checkouts.AssetID=" + pAsset.getID() + "));");
         while (rs.next())
         {
            borrowers.add(new Person(rs.getString("RequestorID"),
               rs.getString("FirstName"), rs.getString("MI"),
               rs.getString("LastName"), rs.getString("Email"),
               rs.getString("Phone")));
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      finally
      {
         try
         {
            conn.close();
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }
      }
      return borrowers;
   }

   /**
    * Gets the active requests based on a Person.
    *
    * @param pPerson The person to get the requests for
    * @return The active requests
    */
   public Collection<Request> getActiveRequests(Person pPerson)
   {
      Collection<Request> requests = new ALArrayList<Request>();
      Connection conn = null;
      try
      {
         conn = DriverManager.getConnection("jdbc:sqlite:"
            + mFile.getName());
         ResultSet rs = conn.createStatement().executeQuery(
            "Select * from Requests where requestorID=\"" + pPerson.getID()
            + "\" and Exists (select requestID from checkouts where active=1 and "
            + "checkouts.requestID=requests.requestID);");
         while (rs.next())
         {
            requests.add(new Request(rs.getString("RequestID"),
               rs.getDate("RequestedMadeDate"),
               rs.getDate("RequestedPickupDate"),
               rs.getString("RequestedType"),
               getPerson(rs.getString("RequestorID"))));
         }

         rs.close();

         for (Request request : requests)
         {
            request.setCheckouts(getCheckouts(request));
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      finally
      {
         try
         {
            conn.close();
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }
      }
      return requests;
   }

   /**
    * Returns a collection of all the assets this person has borrowed.
    *
    * @param pPerson The person to get an asset history for
    * @return The assets this person has borrowed
    */
   public Collection<Asset> getAssets(Person pPerson)
   {
      Collection<Asset> assets = new ALArrayList<Asset>();
      Connection conn = null;

      try
      {
         conn = DriverManager.getConnection("jdbc:sqlite:"
            + mFile.getName());
         ResultSet rs = conn.createStatement().executeQuery(
            "select * from assets where exists(select assetid from checkouts"
            + " where exists (select requestid from requests where "
            + "requestorID=\""
            + pPerson.getID() + "\"));");

         while (rs.next())
         {
            assets.add(new Asset(rs.getString("AssetID"),
               rs.getString("Make"), rs.getString("Model"),
               rs.getString("SerialNumber"),
               rs.getString("AssetType"),
               rs.getString("Description"),
               rs.getBoolean("inMaintenance")));
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      finally
      {
         try
         {
            conn.close();
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }
      }
      return assets;
   }

   /**
    * Returns the number of checkouts in the database so when a new checkout is
    * made the appropriate ID can be given.
    * @return The number of checkouts in the database.
    */
   public Integer getNumCheckouts()
   {
      int numCheckouts = 0;
      Connection conn = null;
      try
      {
         conn = DriverManager.getConnection("jdbc:sqlite:"
            + mFile.getName());
         ResultSet rs = conn.createStatement().executeQuery(
            "select count(*) from checkouts;");
         numCheckouts = rs.getInt(1);
      }
      catch (SQLException e)
      {
         e.printStackTrace(System.err);
      }
      finally
      {
         try
         {
            conn.close();
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }
      }
      return numCheckouts;
   }

   /**
    * Returns the number of logs that are in the database.  This can be
    * used to create the appropriate log ID when a log is created.
    * @return The number of logs in the database.
    */
   public Integer getNumLogs()
   {
      int numLogs = 0;
      Connection conn = null;
      try
      {
         conn = DriverManager.getConnection("jdbc:sqlite:"
            + mFile.getName());
         ResultSet rs = conn.createStatement().executeQuery(
            "select count(*) from CheckoutLog;");
         numLogs = rs.getInt(1);
         rs.close();
      }
      catch (SQLException e)
      {
         e.printStackTrace(System.err);
      }
      finally
      {
         try
         {
            conn.close();
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }
      }
      return numLogs;
   }

   /**
    * Returns the number of requests that are in the database.  This can be
    * used to create the appropriate request ID when a log is created.
    * @return The number of request in the database.
    */
   public Integer getNumRequests()
   {
      int numCheckouts = 0;
      Connection conn = null;
      ResultSet rs = null;
      try
      {
         conn = DriverManager.getConnection("jdbc:sqlite:"
            + mFile.getName());
         rs = conn.createStatement().executeQuery(
            "select count(*) from Requests;");
         numCheckouts = rs.getInt(1);
      }
      catch (SQLException e)
      {
         e.printStackTrace(System.err);
      }
      finally
      {
         try
         {
            conn.close();
            rs.close();
         }
         catch (Exception e)
         {
         }
      }
      return numCheckouts;
   }

   /**
    * Entry point for the Server to run as a stand alone server.
    *
    * @param args Command line arguments
    */
   public static void main(String[] args)
   {
      System.out.println("AssetLogger Serve is now running");
      Server.getInstance().run();
   }

   /**
    * Entry point for the Server
    */
   @Override
   public void run()
   {
      String ksName = System.getProperty("keystore", "al.jks");
       char ksPass[] = System.getProperty("keyPassword", "AssetLogger")
                                 .toCharArray();
       char ctPass[] = System.getProperty("certificatePassword",
                              "AssetLogger").toCharArray();
       try
       {
           KeyStore ks = KeyStore.getInstance("JKS");
           ks.load(Server.class.getResourceAsStream(ksName), ksPass);
           KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
           kmf.init(ks,ctPass);

          mPoolSize = getInteger("poolSize", DEFAULT_POOL_SIZE);

          SSLContext sc = SSLContext.getInstance("TLS");
          sc.init(kmf.getKeyManagers(), null, null);
          sc.init(kmf.getKeyManagers(), null, null);
         // initialize the request pool
         initPool();

         // setup listener
         int serverPort = getInteger("serverPort", DEFAULT_SERVER_PORT);
         SSLServerSocketFactory fact = sc.getServerSocketFactory();
         mServerSocket = (SSLServerSocket) fact.createServerSocket(serverPort);
         while (RequestHandler.isConnected())
         {
            SSLSocket incoming = (SSLSocket) mServerSocket.accept();
            try
            {
                incoming.setSoTimeout(2000);
                System.err.println("Handing Off connection");
                runPool((SSLSocket) incoming);
                mConnectionCount++;
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
         }
      }
      catch (Exception e)
      {
         System.err.println("Error - " + e);
         e.printStackTrace();
      }
      finally
      {
         try
         {
            mServerSocket.close();
         }
         catch (Exception e)
         {
         }
      }
   }

   /**
    * Initializes the pool
    */
   private void initPool()
   {
      mHandlerPool = new RequestHandler[mPoolSize];
      for (int i = 0; i < mPoolSize; i++)
      {
         mHandlerPool[i] = new RequestHandler(i);
         mHandlerPool[i].start();
      }
      mLastRequestNumber = 0;
   }

   /**
    * Runs the pool on the socket given
    *
    * @param socket The socket to run the pool with
    * @throws Exception Throws all exceptions
    */
   private void runPool(Socket socket)
      throws Exception
   {
      for (int i = 0; i < mPoolSize; i++)
      {
         mLastRequestNumber++;
         if (mLastRequestNumber >= mPoolSize)
         {
            mLastRequestNumber = 0;
         }

         if (mHandlerPool[mLastRequestNumber].isAvailable())
         {
            mHandlerPool[mLastRequestNumber].init(socket);
            return;
         }
      }

      RequestHandler.writeHeader(socket.getOutputStream(),
         RESP_STATUS_ERROR);
      socket.close();
   }
}
