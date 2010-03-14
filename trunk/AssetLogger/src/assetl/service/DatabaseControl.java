package assetl.service;

import java.util.Date;

import assetl.AssetLoggerView;
import assetl.AssetLoggerApp;
import assetl.system.AssetLControl;
import assetl.system.AssetLModel;
import assetl.system.AssetLView;
import assetl.system.ModelObserver;
import assetl.system.Request;
import assetl.system.Person;
import assetl.system.Asset;
import assetl.system.Checkout;

/**
 * The Database Controller is used to modify the Server model in response
 * to user gestures from the view. Specifically it can send Request objects
 * containing an array of Checkout objects to the Server to be added to the
 * database. It listens for any state changes in the model made by other
 * controller instances and then updates the view accordingly. It creates the
 * view and modifies the components displayed to the user. The view can call
 * its schedule, checkout, checkin, and cancel functions to indicate the
 * user wishes those events to occur.
 *
 * @author Devin Doman
 */
public class DatabaseControl 
        implements AssetLControl, ModelObserver, Runnable
{
    /**
     * The Model
     */
    private AssetLModel mModel;

    /**
     * The View
     */
    private AssetLView mView;

    /**
     * The id of the user
     */
    private String mUserID;

    /**
     * True if the user is an admin
     */
    private boolean mAdmin;

    /**
     * The current request being handled
     */
    private Request mCurrRequest;

    /**
     * Default Contructor for the controller. Gets the model and creates
     * the view.
     */
    public DatabaseControl()
    {
        //get the model and register this controller as a listener
        mModel = Server.getInstance();
        mModel.registerObserver(this);
        
        //The default admin
        mUserID = "Doctor";
        mAdmin = mModel.isAdmin(mUserID);

        /*
         Commented out by Devin Doman 3/13/10 replaced by code following.
         Delete after 2 weeks if still not applicable. Uses the handwritten
         view instead of netbeans generated.
         
         mView = new AssetView(this);
        */

        //Use the netbeans generated gui
        mView = new AssetLoggerView(AssetLoggerApp.getApplication(), this);
    }

    /**
     * Makes a new request using objects sent from the view.
     *
     * @param pRequestor The person making the request
     * @param pPickup The date the asset will be picked up
     */
    private void makeRequest(Person pRequestor, Date pPickup)
    {
        //
        // TODO: find a request by its requestor and pickup date
        // and see if it already exists
        // (rather than just checking the last request made)
        //

        //
        // If the pickup dates are the same for the same person
        // there is no reason to make a new request
        //

        if (mCurrRequest == null || pRequestor != mCurrRequest.getRequestor() ||
                pPickup != mCurrRequest.getRequestedPickup())
        {
            // Make the request object, stamp it with today's date
            mCurrRequest = new Request("0", new Date(), pPickup,
                                       "Checkout", pRequestor);
            mModel.setPerson(pRequestor);
        }
    }

    /**
     * Makes a new checkout using strings obtained from the view and adds
     * it to the current request.
     *
     * @param pRecipient The person receiving the asset
     * @param pAsset The asset to checkout
     * @param pStart The starting date to schedule the asset for
     * @param pEnd The end date of the assets scheduled checkout time
     * @param pPickedUp boolean indicating whether the item has been picked up
     *                  yet or not
     * @return true if the checkout can be made
     */
    private boolean makeCheckout(Person pRecipient, Asset pAsset,
            Date pStart, Date pEnd, boolean pPickedUp)
    {
        if (mCurrRequest == null)
        {
            // Can't add a checkout object
            return false;
        }
        else
        {
            // Make the checkout and add it to the current request
            Checkout currChkOut = new Checkout("0", pAsset, pRecipient,
                                               pStart, pEnd);

            //
            // TODO: make sure the checkout isn't already in the collection
            //

            if (pPickedUp)
            {
                //stamp picked up date with today's date
                currChkOut.setPickedupDate(pStart);
            }
            
            mModel.setAsset(pAsset);
            mCurrRequest.addCheckout(currChkOut);
            return true;
        }
    }

    /**
     * Takes the objects sent from the view and schedules an asset to the
     * person indicated. If a Request object already exists for the person
     * with the same pickup date the same Request object is retrieved and used.
     * Otherwise a new Request object is made. A new Checkout object is created
     * and added to the Request object if the Checkout doesn't already exist.
     * The Request is then sent to the model.
     * 
     * @param pPerson
     * @param pAsset
     * @param pStart
     * @param pEnd
     */
    public void schedule(Person pPerson, Asset pAsset, Date pStart, Date pEnd)
    {
        //make a new request and add a checkout to it
        makeRequest(pPerson, pStart);
        makeCheckout(pPerson, pAsset, pStart, pEnd, false);
        mModel.setRequest(mCurrRequest, mUserID);
    }

    /**
     * A checkout performs all the same functions as a schedule except that
     * it uses todays date as the starting date for scheduling the asset
     * and indicates today's date as the date of pickup.
     *
     * @param pPerson The person checking out the asset
     * @param pAsset The asset to checkout
     * @param pEnd When the asset needs to be returned
     */
    public void checkout(Person pPerson, Asset pAsset, Date pEnd)
    {
        //make today the start date for the checkout
        Date today = new Date();
        makeRequest(pPerson, today);
        makeCheckout(pPerson, pAsset, today, pEnd, true);
        mModel.setRequest(mCurrRequest, mUserID);
    }

    /**
     * Retrieves the outstanding Checkout object for this asset and sets
     * today's date as the returned date.
     * 
     * @param pAsset The asset being returned
     */
    public void checkin(Asset pAsset)
    {

    }

    /**
     * Retrieves the outstanding Request object for this asset and cancels
     * the request.
     * 
     * @param pPerson
     * @param pAsset The asset to cancel a request for
     */
    public void cancel(Person pPerson, Asset pAsset)
    {

    }

    /**
     * This method is called by the model to indicate
     * there has been a state change in the model.
     * The controller will reflect these changes in
     * the view. The controller then makes a pull
     * on the data it might want to care about.
     */
    public void updateData()
    {
        //tell the view to update its display
        mView.updateData();
    }

    /**
     * This method is called by the model to indicate
     * there has been a state change in the model.
     * The controller will reflect the changes in the view.
     * The model pushes all data that may have been changed.
     *
     * @param pPerson The Person object that may have changed
     * @param pAsset The Asset object that may have changed
     * @param pRequest The Request object that may have changed
     */
    public void updateData(Person pPerson, Asset pAsset, Request pRequest)
    {

    }

    /**
     * Returns the Person represented by the ID given.
     *
     * @param pID The unique identification number for this person
     * @return The Person identified
     */
    public Person getPerson(String pID)
    {
        return mModel.getPerson(pID);
    }

    /**
     * Starts the controller.
     */
    public void run()
    {
        mView.showView();
    }
}
