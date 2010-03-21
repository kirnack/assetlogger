package assetl.system;

import java.util.Date;

/**
 * Defines the behavior for checking in an asset
 *
 * @author Devin Doman
 */
public class CheckinFunction 
        extends Function
{
        /**
     * Retrieves the outstanding Checkout object for this asset and sets
     * today's date as the returned date.
     * 
     * @param pAsset The asset being returned
     */
    public void checkin(Asset pAsset)
    {
        //
        // Get the checkout and set the returned date to today's date
        //

        Checkout outstanding = mModel.getCheckout(pAsset);
        outstanding.setReturnedDate(new Date());
        mModel.setCheckout(outstanding);
    }

    /**
     * Checks in an asset
     */
    public void performFunction()
    {

    }
}
