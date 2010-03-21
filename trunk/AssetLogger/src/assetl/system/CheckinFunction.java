package assetl.system;

import java.util.Date;

/**
 * Defines the behavior for checking in an asset
 *
 * @author Devin Doman
 */
public class CheckinFunction 
        extends DBFunction
{
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
