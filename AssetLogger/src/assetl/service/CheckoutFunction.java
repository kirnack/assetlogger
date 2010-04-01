package assetl.service;

import java.util.Date;
import assetl.system.Asset;

/**
 * Peforms all the same functions as a schedule except that the pickup
 * and start date is today.
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
   protected boolean makeCheckout(Asset pAsset)
   {
      if (super.makeCheckout(pAsset))
      {
         //stamp picked up date with today's date
         mCurrCheckout.setPickedupDate(mData.getStart());
         return true;
      }
      else
      {
         return false;
      }
   }

   /**
    * Creates needed objects for the function using
    * data sent via the DataPacket
    */
   @Override
   public void initVariables()
   {
      super.initVariables();
      //make today the start date for the checkout
      mData.setStart(new Date());
   }
}
