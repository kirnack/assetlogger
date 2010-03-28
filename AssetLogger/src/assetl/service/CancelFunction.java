package assetl.service;

import assetl.system.Checkout;
import assetl.system.Request;
import assetl.system.DataPacket;
import assetl.system.RequestPacket;

/**
 * Defines behavior for canceling requests
 *
 * @author Devin Doman
 */
public class CancelFunction
   extends Function
{
   /**
    * The specific DataPacket needed for this function to operate
    */
   private RequestPacket mData;

   /**
    * Sets the DataPacket for a Login
    *
    * @param pPacket The DataPacket sent from the view
    */
   public void setPacket(DataPacket pPacket)
   {
      mData = (RequestPacket) pPacket;
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
         mModel.setRequest(request, mControl.getCurrentUser().getID());
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
