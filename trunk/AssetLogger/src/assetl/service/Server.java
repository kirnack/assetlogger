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

import java.sql.Connection;
import java.sql.DriverManager;
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

   public int getNumCheckouts() {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   public int getNumLogs() {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   public int getNumRequests() {
      throw new UnsupportedOperationException("Not supported yet.");
   }
    private Person test;
    /**
     * Variable to hold a singleton of Server
     */
    private static final AssetLModel INSTANCE;

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

        try
        {
            mFile = new File ("LaptopChecker.aldb");
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
            File name = new File ((ClassLoader.getSystemResource(
             Server.class.getPackage().getName().replace(".","/") + "/test.sql")
                   .toString()).replace("file:", ""));
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
    public static AssetLModel getInstance()
    {
        return INSTANCE;
    }

    public Person getPerson(String pID)
    {
        return test;
    }

    public void setPerson(Person pPerson)
    {
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

    public Boolean isAdmin(String pID)
    {
        return true;
    }

    public User getUser(String pID)
    {
        return new User();
    }

    public Boolean checkPwd(String pID, String pPwd)
    {
        return true;
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
