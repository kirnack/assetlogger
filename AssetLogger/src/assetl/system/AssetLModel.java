/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package assetl.system;

import java.util.Collection;
import java.util.Date;

/**
 * Provides a common interface for the model module in the
 * Model-View-Controller structure. Represents the data
 * used by the application with its getters and setters.
 * 
 * @author Bryon Rogers
 */
public interface AssetLModel
{
    /**
     * 
     * @param pID
     * @return
     */
    Person getPerson(String pID);
    
    /**
     * 
     * @param pPerson
     */
    void setPerson(Person pPerson);

    /**
     * 
     * @param pID
     * @return
     */
    Asset getAsset(String pID);

    /**
     * 
     * @param pAsset
     */
    void setAsset(Asset pAsset);

    /**
     * 
     * @param pID
     * @return
     */
    Request getRequest(String pID);

    /**
     * 
     * @param pRequest
     * @param pUserID
     */
    void setRequest(Request pRequest, String pUserID);

    /**
     * 
     * @param pID
     * @return
     */
    boolean isAdmin(String pID);

    /**
     * 
     * @param pID
     * @return
     */
    User getUser(String pID);

    /**
     * 
     * @param pID
     * @param pPwd
     * @return
     */
    boolean checkPwd(String pID, String pPwd);

    /**
     * 
     * @param pStart
     * @param pEnd
     * @return
     */
    Collection<Asset> getAvailAsset(Date pStart, Date pEnd);

    /**
     * 
     * @param pPerson
     * @return
     */
    Collection<Asset> getAssets(Person pPerson);

    /**
     * 
     * @return
     */
    int getNumRequests();

    /**
     *
     * @return
     */
    int getNumCheckouts();

    /**
     *
     * @return
     */
    int getNumLogs();

    void registerObserver(ModelObserver pObserver);
}
