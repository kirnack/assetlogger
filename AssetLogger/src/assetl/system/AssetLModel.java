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
    Person getPerson(String pID);
    void setPerson(Person pPerson);
    Asset getAsset(String pID);
    void setAsset(Asset pAsset);
    Request getRequest(String pID);
    void setRequest(Request pRequest);
    Boolean isAdmin(String pID);
    User getUser(String pID);
    Boolean checkPwd(String pID, String pPwd);
    Collection<Asset> getAvailAsset(Date pStart, Date pEnd);
    Collection<Asset> getAssets(Person pPerson);
}
