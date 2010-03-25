package assetl.service;

import java.util.Date;
import assetl.system.Checkout;
import assetl.system.DataPacket;
import assetl.system.DBPacket;

/**
 * Defines the behavior for checking in an asset
 *
 * @author Devin Doman
 */
public class CheckinFunction 
        extends Function
{
    /**
     * The specific DataPacket needed for this function to operate
     */
    DBPacket mData;

    /**
     * Sets the DataPacket for a Login
     *
     * @param pPacket The DataPacket sent from the view
     */
    @Override
    public void setPacket(DataPacket pPacket)
    {
        super.setPacket(pPacket);
        mData = (DBPacket) mPacket;
    }
    /**
     * Checks in an asset: Retrieves the outstanding Checkout object for this
     * asset and sets today's date as the returned date.
     */
    public void performFunction()
    {
        //
        // Get the checkout and set the returned date to today's date
        //

        System.err.println("This check in was totally performed");
        Checkout outstanding = mModel.getCheckout(mData.getAsset());
        outstanding.setReturnedDate(new Date());
        mModel.setCheckout(outstanding);
    }
}
