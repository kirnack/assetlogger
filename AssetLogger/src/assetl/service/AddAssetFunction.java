package assetl.service;

import assetl.system.DataPacket;
import assetl.system.LaptopPacket;
import assetl.system.Asset;

/**
 * Adds an Asset to the model
 *
 * @author Devin Doman
 */
public class AddAssetFunction
   extends Function
{
   /**
    * Sends the data needed to make a laptop asset
    */
   LaptopPacket mData;

   /**
    * Sets the DataPacket for this function
    *
    * @param pPacket The DataPacket sent from the view
    */
   public void setPacket(DataPacket pPacket)
   {
      mData = (LaptopPacket) pPacket;
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
    * Take the data packet and make an asset
    *
    * @return The asset to set in the model
    */
   public Asset generateLaptop()
   {
      Asset laptop = new Asset();
      
      laptop.setID(mData.getBarCode());
      laptop.setMake(mData.getMake());
      laptop.setModel(mData.getLaptopNumber());
      laptop.setSerialNum(mData.getSerialNubmer());

      String type = "PC";
      if ("Apple".equals(mData.getMake()))
      {
         type = "Mac";
      }

      laptop.setType(type);
      laptop.setMaintenance(mData.getMaintenance());
      
      return laptop;
   }

   /**
    * Adds an asset to the model
    */
   public void performFunction()
   {
      mModel.setAsset(generateLaptop());
   }
}
