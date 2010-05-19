package assetl.service;

import assetl.system.*;

import java.io.*;
import java.net.*;

import java.sql.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import static resources.Config.*;
import static resources.WebServerConstants.*;
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
      cInstance.start();
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
      mFile = new File(System.getProperty(
         "dbfilename", "LaptopChecker") + "."
         + System.getProperty("dbfileext", "aldb"));
      try
      {

         if (!mFile.exists() || !mFile.isFile())
         {
            //System.err.println("Making Database");
            createDB();
         }
         else
         {
            loadDatabaseDriver("org.sqlite.JDBC");
            mConn = DriverManager.getConnection("jdbc:sqlite:"
               + mFile.getName());
            mStat = mConn.createStatement();
            URL setupSQL = ClassLoader.getSystemResource(
               Server.class.getPackage().getName().replace(".", "/")
               + "/Update.sql");
            File updateScript = new File(
               setupSQL.toString().replace("file:", ""));
            BufferedReader in = new BufferedReader(
               new FileReader(updateScript));
            ResultSet rs = mConn.createStatement().executeQuery("Select "
               + "ChangeString from DatabaseInfo");
            String str = rs.getString(1);
            rs.close();
            System.err.println(str);
            int compare = str.compareTo(in.readLine());
            System.err.println(compare);
            if (compare < 0)
            {
               while ((str = in.readLine()) != null)
               {
                  System.err.println(str);
                  mConn.prepareStatement(str).execute();
               }
            }
         }
      }
      catch (Exception e)
      {
         //TODO: Fix the Null pointer exception that occurs in the try block above
         System.out.println(e);
         System.exit(-1);
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
         URL setupSQL = ClassLoader.getSystemResource(
            Server.class.getPackage().getName().replace(".", "/")
            + "/AssetLoggerSetup.sql");
         //System.err.println(setupSQL);
         File name = new File(setupSQL.toString().replace("file:", ""));
         //System.err.println(name);
         BufferedReader in = new BufferedReader(new FileReader(name));
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
      try
      {
         ResultSet rs = mConn.createStatement().executeQuery("select * from requestors where"
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
      try
      {
         Person temp = getPerson(pPerson.getID());
         PreparedStatement prep = null;
         if (temp == null)
         {
            //System.err.println("Adding " + pPerson.getFirstName());
            prep = mConn.prepareStatement(
               "insert into Requestors values (?, ?, ?, ?, ?, ?);");
         }
         else if (!(temp.getFirstName().equals(pPerson.getFirstName()))
            || !(temp.getMiddleName().equals(pPerson.getMiddleName()))
            || !(temp.getLastName().equals(pPerson.getLastName()))
            || !(temp.getEmail().equals(pPerson.getEmail()))
            || !(temp.getPhoneNumber().equals(pPerson.getPhoneNumber())))
         {
            //System.err.println("Updating " + pPerson.getFirstName());
            prep = mConn.prepareStatement(
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
      try
      {
         ResultSet rs = mConn.createStatement().executeQuery("select * from assets where"
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
      try
      {
         //Retrieves data from database to see if the person needs to be added,
         //and also to check to see if there is an actual need to update te data.
         Asset temp = getAsset(pAsset.getID());

         if (temp == null)
         {
            System.err.println("Adding " + pAsset.getID());
            prep = mConn.prepareStatement(
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
            prep = mConn.prepareStatement(
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
      try
      {
         ResultSet requestSet = mConn.createStatement().executeQuery(
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
      Collection<Checkout> checkouts = new ArrayList<Checkout>();

      try
      {

         ResultSet rs = mConn.createStatement().executeQuery(
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
      try
      {
         ResultSet rs = mConn.createStatement().executeQuery("select * from Checkouts where"
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
         try
         {
            Checkout temp = null;
            System.err.println("Retreivinng " + pCheckout.getID());
            //Retrieves data from database to see if the person needs to be added,
            //and also to check to see if there is an actual need to update te data.

            PreparedStatement prepReq = null;
            if (("".equals(pCheckout.getID()))
               || (((temp = getCheckout(pCheckout.getID())) == null)))
            {
               pCheckout.setID(Integer.toString(getNumCheckouts() + 1));
               System.err.println("Adding " + pCheckout.getID());
               prepReq = mConn.prepareStatement(
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
                  prepReq = mConn.prepareStatement(
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
      try
      {
         //System.err.println("Retreivinng " + pRequest.getID());
         //Retrieves data from database to see if the person needs to be added,
         //and also to check to see if there is an actual need to update te data.
         Request temp = null;
         PreparedStatement prepReq = null;

         if (("".equals(pRequest.getID()))
            || ((temp = getRequest(pRequest.getID())) == null))
         {
            pRequest.setID(Integer.toString(getNumRequests() + 1));
            System.err.println("Adding " + pRequest.getID());
            prepReq = mConn.prepareStatement(
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
               prepReq = mConn.prepareStatement(
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

         prepReq.close();
         for (Checkout checkout : pRequest.getCheckouts())
         {
            setCheckout(checkout, pUserID);
         }
      }
      catch (SQLException e)
      {
         e.printStackTrace(System.err);
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
      Collection<Checkout> checkouts = new ArrayList<Checkout>();

      try
      {
         ResultSet rs = mConn.createStatement().executeQuery(
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
      Collection<Checkout> checkouts = new ArrayList<Checkout>();

      try
      {
         ResultSet rs = mConn.createStatement().executeQuery(
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

      return checkouts;
   }

   /**
    * Sets a Checkout
    *
    * @param pCheckout The checkout to set
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
      Collection<User> users = new ArrayList<User>();
      try
      {
         ResultSet rs = mConn.createStatement().executeQuery(
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
      return users;
   }

   /**
    * Tells if the user, with the passed ID, is an admin.
    *
    * @param pID The ID of the user to check the admin status of.
    * @return True if the user is an admin.
    */
   public int getAccessLevel(String pID)
   {
      try
      {
         return getUser(pID).isAdmin();
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
      try
      {
         ResultSet rs = mConn.createStatement().executeQuery("select * from users where"
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
      return user;
   }

   /**
    * Checks the users password.
    *
    * @param pID The user to check the password of.
    * @param pPwd The password to verify.
    * @return Returns true if the passed password is the user's password.
    */
   public boolean checkPwd(String pID, String pPwd)
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
      Collection<Asset> assets = new ArrayList<Asset>();
      ArrayList<String> assetIDs = new ArrayList<String>();

      try
      {
         ResultSet rs = mConn.createStatement().executeQuery(
            "Select AssetID, RequestedStartDate, RequestedEndDate " +
            "from checkouts where active=1;");

         try
         {
            while (rs.next())
            {
               

               Date qStartDate = rs.getDate("RequestedStartDate");
               Date qEndDate = rs.getDate("RequestedEndDate");
               if ((qStartDate.getTime() <= pStart.getTime())
                      && (qEndDate.getTime()>= pStart.getTime()))
               {
                  String id = rs.getString("AssetID");
                  if (!assetIDs.contains(id))
                  {
                     assetIDs.add(id);
                  }
               }
               else if(qStartDate.getTime() <= pEnd.getTime()
                  && (qEndDate.getTime()>= pEnd.getTime()))
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
            ids += (("".equals(ids)) ? "":", ") + "\"" + id + "\"";
         }

         rs =  mConn.createStatement().executeQuery(
            "select * from assets where Assets.inMaintenance=0 and" +
            " assetid not in (" + ids + ");");/* +
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
      Collection<Person> borrowers = new ArrayList<Person>();
      try
      {
         ResultSet rs = mConn.createStatement().executeQuery(
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
      Collection<Request> requests = new ArrayList<Request>();
      try
      {
         ResultSet rs = mConn.createStatement().executeQuery(
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
      Collection<Asset> assets = new ArrayList<Asset>();
      try
      {
         ResultSet rs = mConn.createStatement().executeQuery(
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
      return assets;
   }

   /**
    * Returns the number of checkouts in the database so when a new checkout is
    * made the appropriate ID can be given.
    * @return The number of checkouts in the database.
    */
   public int getNumCheckouts()
   {
      int numCheckouts = 0;
      try
      {
         ResultSet rs = mConn.createStatement().executeQuery(
            "select count(*) from checkouts;");
         numCheckouts = rs.getInt(1);
      }
      catch (SQLException e)
      {
         e.printStackTrace(System.err);
      }
      return numCheckouts;
   }

   /**
    * Returns the number of logs that are in the database.  This can be
    * used to create the appropriate log ID when a log is created.
    * @return The number of logs in the database.
    */
   public int getNumLogs()
   {
      int numLogs = 0;

      try
      {
         ResultSet rs = mConn.createStatement().executeQuery(
            "select count(*) from CheckoutLog;");
         numLogs = rs.getInt(1);
         rs.close();
      }
      catch (SQLException e)
      {
         e.printStackTrace(System.err);
      }

      return numLogs;
   }

   /**
    * Returns the number of requests that are in the database.  This can be
    * used to create the appropriate request ID when a log is created.
    * @return The number of request in the database.
    */
   public int getNumRequests()
   {
      int numCheckouts = 0;
      try
      {
         ResultSet rs = mConn.createStatement().executeQuery(
            "select count(*) from Requests;");
         numCheckouts = rs.getInt(1);
      }
      catch (SQLException e)
      {
         e.printStackTrace(System.err);
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
      try
      {
         mPoolSize = getInteger("poolSize", DEFAULT_POOL_SIZE);

         initPool();

         int serverPort = getInteger("serverPort", DEFAULT_SERVER_PORT);

         mServerSocket = new ServerSocket(serverPort);

         //accept connections and process them
         while (RequestHandler.isConnected())
         {
            Socket incoming = mServerSocket.accept();
            incoming.setSoTimeout(2000);
            runPool(incoming);
            mConnectionCount++;
         }
      }
      catch (Exception e)
      {
         System.out.println("Error - " + e);
         //e.printStackTrace();
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
