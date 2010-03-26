package assetl.service;

import java.util.Calendar;
import java.util.Date;

import assetl.system.Asset;
import assetl.system.Checkout;
import assetl.system.DataPacket;
import assetl.system.SchedulePacket;
import assetl.system.Person;
import assetl.system.Request;



/**
 * Schedules an asset
 *
 * @author Devin Doman
 */
public class ScheduleFunction
        extends Function
{

    /**
     * The specific DataPacket needed for this function to operate
     */
    SchedulePacket mData;

    /**
     * The current request
     */
    Request mCurrRequest;

    /**
     * The current checkout
     */
    Checkout mCurrCheckout;

    /**
     * The person who will receive the asset
     */
    Person mRecipient;
    
    /**
     * The asset the person will receive
     */
    Asset mAsset;

    /**
     * The start Date
     */
    Date mStart;

    /**
     * The end Date
     */
    Date mEnd;

    /**
     * Sets the DataPacket for this function
     *
     * @param pPacket The DataPacket sent from the view
     */
    public void setPacket(DataPacket pPacket)
    {
        mData = (SchedulePacket) pPacket;
    }

    /**
     * Gets the DataPacket currently set for this function
     *
     * @return The DataPacket
     */
    public DataPacket getPacket()
    {
        return mData;
    }

    /**
     * Creates needed objects for the function using
     * data sent via the DataPacket
     */
    public void initVariables()
    {
       mCurrRequest = null;
       mCurrCheckout = null;

       mRecipient = mModel.getPerson(mData.getPersonID());
       mAsset = mModel.getAsset(mData.getAssetID());
       mStart = makeDate(mData.getStartMon(), mData.getStartDay(),
                         mData.getStartYear());
       mEnd = makeDate(mData.getEndMon(), mData.getEndDay(),
                       mData.getEndYear());
    }

    /**
     * Converts a month, day, and year into a Date object
     *
     * @param pMonth The month
     * @param pDay The day
     * @param pYear The year
     * @return The Date object with the values passed in
     */
    protected Date makeDate(int pMonth, int pDay, int pYear)
    {
        //make a local calander and set the date
        Calendar cal = Calendar.getInstance();
        cal.set(pYear, pMonth + 1, pDay);
        return cal.getTime();
    }

    /**
     * Makes a new request using objects sent from the view.
     * Expects the Person, start Date, and Asset fields to be set in the packet.
     * Sets the Request made in the Request field of the data packet.
     */
    protected void makeRequest()
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

        if (mCurrRequest == null || mRecipient != mCurrRequest.getRequestor() ||
            mStart != mCurrRequest.getRequestedPickup())
        {
            // Make the request object, stamp it with today's date
            mCurrRequest = new Request("", new Date(), mStart,
                                       mAsset.getType(), mRecipient);
        }
    }

    /**
     * Makes a new checkout using data obtained from the view and adds
     * it to the current request. Expects the Person, Asset, start Date,
     * and end Date to be set in the data packet. Sets the checkout data
     * in the Checkout field of the data packet.
     *
     * @return true if the checkout can be made
     */
    protected boolean makeCheckout()
    {
        if (mCurrRequest == null)
        {
            // Can't add a checkout object
            return false;
        }
        else
        {
            // Make the checkout and add it to the current request
            mCurrCheckout = new Checkout("", "", mAsset, mRecipient,
                                               mStart, mEnd);
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
     */
    public void performFunction()
    {
        initVariables();
        
        //make a new request and checkout
        makeRequest();
        makeCheckout();

        //
        // TODO: make sure the checkout isn't already in the collection
        //

        //add the checkout to the request
        mCurrRequest.addCheckout(mCurrCheckout);

        //send the request to the model
        mModel.setRequest(mCurrRequest, mControl.getCurrentUser().getID());

        //TODO: remove the following line of test code for view changing
        //mDBControl.changeView(new assetl.service.HandwrittenView(mControl));

    }
}
