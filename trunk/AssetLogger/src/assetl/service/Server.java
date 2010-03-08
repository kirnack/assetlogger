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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

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
