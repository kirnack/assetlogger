package assetl.service;

import java.util.Date;
import java.util.Collection;
import java.util.ArrayList;
import assetl.system.DataPacket;
import assetl.system.AssetCollectionPacket;
import assetl.system.StringPacket;
import assetl.system.Asset;
import assetl.system.Checkout;

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
   private DataPacket mData;

   /**
    * Sets the DataPacket for a Checkin
    *
    * @param pPacket The DataPacket sent from the view
    */
   public void setPacket(DataPacket pPacket)
   {
      mData = pPacket;
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
    * Gets from the DataPacket the assets that need to be checked in
    *
    * @return The collection of assests that need to be checked in
    */
   public Collection<Asset> getAssets()
   {
      ArrayList<Asset> checkins = new ArrayList<Asset>();

      //Handle different DataPackets
      checkins = new ArrayList<Asset>();
      if (StringPacket.class.isAssignableFrom(mData.getClass()))
      {
         StringPacket strPack = (StringPacket) mData;
         checkins.add(mModel.getAsset(strPack.getString()));
      }
      else
      {
         AssetCollectionPacket colPack = (AssetCollectionPacket) mData;
         checkins = (ArrayList<Asset>) colPack.getAssets();
      }

      return checkins;
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

      for (Asset asset : getAssets())
      {
         Checkout outstanding = mModel.getCheckout(asset);
         if (outstanding != null)
         {
            outstanding.setReturnedDate(new Date());
            mModel.setCheckout(outstanding);
         }
         else
         {
            System.err.println("Item not out.");
         }
      }
   }
}
