/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package assetl.service;

import java.util.Date;
import assetl.system.AssetLControl;
import assetl.system.AssetLModel;
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
        //make a new request;
        Request testRequest = new Request();

        testRequest.setID("random num");
        testRequest.setActive(true);
        testRequest.setRequestMade(new Date());
        testRequest.setRequstType("checkout");
        testRequest.setRequestor(pPerson);
        testRequest.setCheckouts(null);


        mModel.setPerson(pPerson);
        mModel.setAsset(pAsset);
        mModel.setRequest(testRequest);
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

    public Person getPerson(String pID)
    {
        return mModel.getPerson(pID);
    }
}
