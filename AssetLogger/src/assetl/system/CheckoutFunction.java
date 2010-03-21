package assetl.system;

import java.util.Date;

/**
 *
 * @author Devin Doman
 */
public class CheckoutFunction 
        extends ScheduleFunction
{    
    /**
     * Makes a new checkout using data obtained from the view and adds
     * it to the current request. Expects the Person, Asset, start Date,
     * and end Date to be set in the data packet. Sets the checkout data
     * in the Checkout field of the data packet.
     *
     * @return true if the checkout can be made
     */
    @Override
    protected boolean makeCheckout()
    {
        if(super.makeCheckout())
        {
            //stamp picked up date with today's date
            mData.getCheckout().setPickedupDate(mData.getStart());
            return true;
        }
        else
        {
            return false;
        }

    }

    /**
     * Checks out an asset. Performs all the same functions as a schedule
     * except that it uses today's date as the starting date for scheduling
     * the asset and indicates today's date as the date of pickup.
     */
    @Override
    public void performFunction()
    {
        //make today the start date for the checkout
        mData.setStart(new Date());

        super.performFunction();
    }
}
