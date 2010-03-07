/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package assetl.service;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Date;
import assetl.system.AssetLModel;
import assetl.system.Person;
import assetl.system.Asset;
import assetl.system.Request;
import assetl.system.User;

/**
 *
 * @author Bryon Rogers
 */
public class Server 
        implements AssetLModel
{
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
        return new Person("");
    }

    public void setPerson(Person pPerson)
    {
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

    public void setRequest(Request pRequest)
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
