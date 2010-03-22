package assetl.system;

/**
 * Defines behavior for canceling requests
 *
 * @author Devin Doman
 */
public class CancelFunction 
        extends DBFunction
{
    /**
     * Cancels a request or individual checkout
     */
    public void performFunction()
    {
        // TODO: Remove if statements by extending class 
        // and using Composite pattern

        Request request = mData.getRequest();
        Checkout checkout = mData.getCheckout();

        if (request != null)
        {
            // if a request was sent set it to inactive
            request.setActive(false);
            mModel.setRequest(request, mData.getUserID());
        }
        else if (checkout != null)
        {
            // if a checkout was sent set it to inactive

            //don't cancel a checkout if they've picked it up already
            if (checkout.getPickedupDate() != null)
            {
                checkout.setActive(false);
                mModel.setCheckout(checkout);
            }
        }
        else
        {
            System.out.println("Nothing to cancel");
        }
    }
}
