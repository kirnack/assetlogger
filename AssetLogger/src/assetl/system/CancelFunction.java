package assetl.system;

/**
 *
 * @author Devin Doman
 */
public class CancelFunction 
        extends Function
{
    /**
     * This function assumes a DBPacket
     */
    DBPacket mData;

    /**
     * Default Constructor
     */
    public CancelFunction()
    {
        // converts to a DBPacket
        mData = (DBPacket) mPacket;
    }

    /**
     * Sets the request to invalid, canceling all assets in the checkout
     * collection.
     *
     * @param pRequest The request to cancel
     */
    public void cancel(Request pRequest)
    {
        pRequest.setActive(false);
        mModel.setRequest(pRequest, mData.getUserID());
    }

    /**
     * Sets the checkout to invalid, canceling a single asset.
     *
     * @param pCheckout The checkout to cancel
     */
    public void cancel(Checkout pCheckout)
    {
        //make sure we don't cancel a checkout if they've picked it up already
        if (pCheckout.getPickedupDate() != null)
        {
            pCheckout.setActive(false);
            mModel.setCheckout(pCheckout);
        }
    }

    /**
     * Cancels a request
     */
    public void performFunction()
    {

    }
}
