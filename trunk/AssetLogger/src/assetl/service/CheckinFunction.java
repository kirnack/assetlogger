package assetl.service;

import java.util.Date;
import assetl.system.Checkout;
import assetl.system.DataPacket;
import assetl.system.StringPacket;

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
   private StringPacket mData;

   /**
    * Sets the DataPacket for a Checkin
    *
    * @param pPacket The DataPacket sent from the view
    */
   public void setPacket(DataPacket pPacket)
   {
      mData = (StringPacket) pPacket;
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
    * Checks in an asset: Retrieves the outstanding Checkout object for this
    * asset and sets today's date as the returned date.
    */
   public void performFunction()
   {
      //
      // Get the checkout and set the returned date to today's date
      //

      //
      // TODO: handle when no outstanding checkout exists for this asset
      //

      Checkout outstanding = mModel.getCheckout(
         mModel.getAsset(mData.getString()));
      outstanding.setReturnedDate(new Date());
      mModel.setCheckout(outstanding);
   }
}
