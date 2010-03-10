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
import assetl.system.Checkout;

/**
 *
 * @author Devin Doman
 */
public class DatabaseControl 
        implements AssetLControl
{
    private AssetLModel mModel;
    private String mUserID;
    private boolean mAdmin;

    private Request mCurrRequest;

    /**
     * Constructor uses a singleton instance of the model Server.
     * It takes the view as a parameter
     * 
     * @param pView The view tied to this controller
     */
    public DatabaseControl()
    {
        mModel = Server.getInstance();
        //The default admin
        mUserID = "Doctor";
        mAdmin = mModel.isAdmin(mUserID);
    }

    /**
     * Makes a new request using strings obtained from the view.
     *
     * @param pPerson
     * @param pPickMon
     * @param pPickDay
     * @param pPickYear
     */
    private void makeRequest(String pPerson, String pPickMon, String pPickDay,
                             String pPickYear)
    {
        // Initialize the objects needed to make a request
        Date pickup = new Date(Integer.parseInt(pPickYear),
                Integer.parseInt(pPickMon), Integer.parseInt(pPickDay));
        Person requestor = new Person(pPerson);


        // Make the request object
        mCurrRequest = new Request("0", new Date(), pickup,
                                   "Checkout", requestor);
        mModel.setPerson(requestor);
    }

    /**
     * Makes a new checkout using strings obtained from the view and adds
     * it to the current request.
     *
     * @param pPerson
     * @param pAsset
     * @param pStrtMon
     * @param pStrtDay
     * @param pStrtYear
     * @param pEndMon
     * @param pEndDay
     * @param pEndYear
     * @return
     */
    private boolean makeCheckout(String pPerson, String pAsset,
                                 String pStrtMon,String pStrtDay,
                                 String pStrtYear, String pEndMon,
                                 String pEndDay, String pEndYear)
    {
        if (mCurrRequest == null)
        {
            // Can't add a checkout object
            return false;
        }
        else
        {
            // Initialize the objects needed to make a checkout
            Date start = new Date(Integer.parseInt(pStrtYear),
                                  Integer.parseInt(pStrtMon),
                                  Integer.parseInt(pStrtDay));
            Date end = new Date(Integer.parseInt(pEndYear),
                                Integer.parseInt(pEndMon),
                                Integer.parseInt(pEndDay));
            Person recipient = new Person(pPerson);
            Asset currAsset = new Asset(pAsset, "Checkout");

            // Make the checkout and add it to the current request
            Checkout currChkOut = new Checkout("0", currAsset, recipient,
                                               start, end);
            mModel.setAsset(currAsset);
            mCurrRequest.addCheckout(currChkOut);
            return true;
        }
    }


    public void schedule(String pPerson, String pAsset,
                  String pStrtMon, String pStrtDay, String pStrtYear,
                  String pEndMon, String pEndDay, String pEndYear)
    {
        //make a new request and add a checkout to it
        makeRequest(pPerson, pEndMon, pEndDay, pEndYear);
        makeCheckout(pPerson, pAsset, pStrtMon, pStrtDay, pStrtYear,
                     pEndMon, pEndDay, pEndYear);

        mModel.setRequest(mCurrRequest, mUserID);
    }

    public void checkout(String pPerson, String pAsset,
                  String pStrtMon, String pStrtDay, String pStrtYear,
                  String pEndMon, String pEndDay, String pEndYear)
    {

    }

    public void checkin(String pPerson, String pAsset,
                  String pStrtMon, String pStrtDay, String pStrtYear,
                  String pEndMon, String pEndDay, String pEndYear)
    {

    }

    public void cancel(String pPerson, String pAsset,
                  String pStrtMon, String pStrtDay, String pStrtYear,
                  String pEndMon, String pEndDay, String pEndYear)
    {

    }

    public String getPersonFirst(String pID)
    {
        return mModel.getPerson(pID).getFirstName();
    }
}
