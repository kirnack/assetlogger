package assetl.service;

import java.util.Date;
import assetl.system.DataPacket;
import assetl.system.StringPacket;
import assetl.system.Asset;
import assetl.system.Checkout;
import java.util.Collection;

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

      Checkout outstanding = null;
      Asset asset = mModel.getAsset(mData.getString());

      if (asset != null)
      {
         Collection<Checkout> outstandings
            = mModel.getCheckedOutCheckouts(asset);

         System.err.println("asset found");

         if (outstandings.size() != 1)
         {
            System.err.println("Too many standing checkouts, "
               + "or no standing chekcouts");
            return;
         }
         outstanding = (Checkout) outstandings.toArray()[0];
         if (outstanding != null)
         {
            // TODO: prompt if the power cord is missing
            // show the barcode and ask for number on bag, if
            // different don't check it in

            outstanding.setReturnedDate(new Date());
            outstanding.setActive(false);
            mModel.setCheckout(outstanding, mControl.getCurrentUser().getID());
            System.err.println("works");
         }
         else
         {
            System.err.println("Item not out.");
         }
      }
      else
      {
         System.err.println("Not an asset");
      }
   }
}
