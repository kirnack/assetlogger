package assetl.service;

import assetl.service.DBFunction;
import assetl.system.Asset;
import assetl.system.Checkout;
import assetl.system.DataPacket;
import assetl.system.Person;
import assetl.system.Request;
import java.util.Date;


/**
 * Schedules an asset
 *
 * @author Devin Doman
 */
public class ScheduleFunction
        extends DBFunction
{

    /**
     * Sets the DataPacket for this function
     *
     * @param pPacket The DataPacket sent from the view
     */
    @Override
    public void setPacket(DataPacket pPacket)
    {
        super.setPacket(pPacket);

        //resets the request and checkout data
        mData.setRequest(null);
        mData.setCheckout(null);
    }

    /**
     * Makes a new request using objects sent from the view.
     * Expects the Person, start Date, and Asset fields to be set in the packet.
     * Sets the Request made in the Request field of the data packet.
     */
    protected void makeRequest()
    {
        Request curr = mData.getRequest();
        Person requestor = mData.getPerson();
        Date pickup = mData.getStart();
        String type = mData.getAsset().getType();

        //
        // TODO: find a request by its requestor and pickup date
        // and see if it already exists
        // (rather than just checking the last request made)
        //

        //
        // If the pickup dates are the same for the same person
        // there is no reason to make a new request
        //

        if (curr == null || requestor != curr.getRequestor() ||
                pickup != curr.getRequestedPickup())
        {
            // Make the request object, stamp it with today's date
            curr = new Request("0", new Date(), pickup,
                                        type, requestor);
            mData.setRequest(curr);
            mModel.setPerson(requestor);
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
        Request curr = mData.getRequest();
        Person recipient = mData.getPerson();
        Asset asset = mData.getAsset();
        Date start = mData.getStart();
        Date end = mData.getEnd();

        if (curr == null)
        {
            // Can't add a checkout object
            return false;
        }
        else
        {
            // Make the checkout and add it to the current request
            Checkout currChkOut = new Checkout("0", "",asset, recipient,
                                               start, end);

            //
            // TODO: make sure the checkout isn't already in the collection
            //
            
            mModel.setAsset(asset);
            mData.setCheckout(currChkOut);
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
        //make a new request and checkout
        makeRequest();
        makeCheckout();

        //add the checkout to the request
        mData.getRequest().addCheckout(mData.getCheckout());

        //send the request to the model
        mModel.setRequest(mData.getRequest(), mData.getUserID());

        //TODO: remove the following line of test code for view changing
        //mDBControl.changeView(new assetl.service.HandwrittenView(mControl));

    }
}
