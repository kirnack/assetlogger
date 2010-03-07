/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package assetl.service;

import java.util.Date;
import assetl.system.AssetLControl;
import assetl.system.AssetLModel;
import assetl.system.AssetLView;
import assetl.system.Request;
import assetl.system.Person;
import assetl.system.Asset;

/**
 *
 * @author Devin Doman
 */
public class DatabaseControl 
        implements AssetLControl
{
    private AssetLModel mModel;
    /**
     * Constructor uses a singleton instance of the model Server.
     * It takes the view as a parameter
     * 
     * @param pView The view tied to this controller
     */
    public DatabaseControl()
    {
        mModel = Server.getInstance();
    }
    public void schedule(Person pPerson, Asset pAsset, Date pStart, Date pEnd)
    {

    }

    public void checkout(Request pRequest)
    {

    }

    public void checkin(Request pRequest)
    {

    }

    public void cancel(Request pRequest)
    {

    }
}
