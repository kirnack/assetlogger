/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package assetl.service;

import assetl.system.AssetLModel;
import assetl.system.Person;
import assetl.system.Asset;
import assetl.system.Request;
import assetl.system.User;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Bryon Rogers
 */
public class Server 
        implements AssetLModel
{
    private File mFile;
    private Connection mConn;
    private Statement mStat;

   public int getNumCheckouts()
   {
      int numCheckouts = 0;
      try
      {
        ResultSet rs = mStat.executeQuery("select count(*) from checkouts;");
         numCheckouts = rs.getInt(1);
      }
      catch (SQLException e)
      {
          e.printStackTrace(System.err);
      }
      return numCheckouts;
   }

   public int getNumLogs()
   {
      int numLogs = 0;

      try
      {
        ResultSet rs = mStat.executeQuery("select count(*) from CheckoutLog;");
         numLogs = rs.getInt(1);
         rs.close();
      }
      catch (SQLException e)
      {
          e.printStackTrace(System.err);
      }

      return numLogs;
   }

   public int getNumRequests()
   {
      return 0;
   }
    private Person test;
    /**
     * Variable to hold a singleton of Server
     */
    private static final Server INSTANCE;

    /**
     * Initialize a Server instance
     */
    static
    {
        INSTANCE = new Server();
    }
    //
    // TODO: actually implement these functions
    //
    
    /**
     * Constructor is deliberately private to create a singleton
     */
    private Server()
    {
        mFile = new File ("LaptopChecker.aldb");
        try
        {
            
            if (!mFile.exists() || !mFile.isFile())
            {
                System.err.println("Making Database");
                createDB();
            }
            else
            {
                Class.forName("org.sqlite.JDBC");
                mConn = DriverManager.getConnection("jdbc:sqlite:"
                    + mFile.getName());
                mStat = mConn.createStatement();
            }
        }
        catch(Exception e)
        {
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
             Server.class.getPackage().getName().replace(".","/") +
                 "/AssetLoggerSetup.sql");
            System.err.println(setupSQL);
            File name = new File (setupSQL.toString().replace("file:", ""));
            System.err.println(name);
            BufferedReader in = new BufferedReader(new FileReader(name));
            String str;
            while ((str = in.readLine()) != null)
            {
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
        return INSTANCE;
    }

    public Person getPerson(String pID)
    {
        // TODO: return a person based on their id

        return test;
    }

    public void setPerson(Person pPerson)
    {
        // TODO: set a person
        
        test = pPerson;
    }

    public Asset getAsset(String pID)
    {
        return new Asset("", "");
    }

    public void setAsset(Asset pAsset)
    {

    }

    public Request getRequest(String pID)
    {
        return new Request();
    }

    public void setRequest(Request pRequest, String pUserID)
    {

    }

    public Collection<User> getUsers()
    {
        Collection<User> users = new ArrayList<User>();
        try
        {
            ResultSet rs = mStat.executeQuery("select * from users;");
            while (rs.next())
            {
                Boolean admin =rs.getBoolean("isAdmin");
                boolean primAdmin = admin.booleanValue();
                users.add(new User(rs.getString("UserID"),
                        rs.getString("Password"),
                        primAdmin));
            }
            rs.close();
        }
        catch (Exception e)
        {
            System.err.println(e);
        }
        return users;
    }

    public boolean isAdmin(String pID)
    {
        try
        {
            return getUser(pID).isAdmin();
        }
        catch (NullPointerException e)
        {
            return false;
        }
    }

    public User getUser(String pID)
    {
        User user = null;
        try
        {
            ResultSet rs = mStat.executeQuery("select * from users where" +
                                              " UserID='" + pID + "';");
            if (rs.next())
            {
                Boolean admin =rs.getBoolean("isAdmin");
                boolean primAdmin = admin.booleanValue();
                user = new User(rs.getString("UserID"), rs.getString("Password")
                        , primAdmin);
            }
            rs.close();
        }
        catch (Exception e)
        {
            System.err.println(e);
        }
        return user;
    }

    public boolean checkPwd(String pID, String pPwd)
    {
        try
        {
            return ((pPwd != null) &&
                    pPwd.equals(getUser(pID).getPassword()));
        }
        catch (NullPointerException e)
        {
            return false;
        }
    }

    public Collection<Asset> getAvailAsset(Date pStart, Date pEnd)
    {
        return new ArrayList<Asset>();
    }

    public Collection<Asset> getAssets(Person pPerson)
    {
        return new ArrayList<Asset>();
    }
}
