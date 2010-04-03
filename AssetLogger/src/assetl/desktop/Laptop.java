package assetl.desktop;

import assetl.service.AssetType;
import assetl.system.Asset;
import assetl.system.AssetMissMatchExecption;

/**
 * A Laptop Asset.
 * 
 * @author Bryon Rogers
 */
public class Laptop
   extends AssetType
{

   Asset mAsset;
   public Laptop(String pID, String pLaptopNumber, String pMake,
      String pModel, String pSerialNum, String pType,  boolean pMaintenance)
   {
      mAsset = new Asset(pID, pMake, pLaptopNumber,
                         pSerialNum, pType, pModel, false);
   }

   public Laptop(String pID, String pLaptopNumber, String pMake,
                 String pModel, String pSerialNum, String pType)
   {
      mAsset =
      new Asset(pID, pMake, pLaptopNumber, pSerialNum, pType, pModel, false);
   }

   public Laptop(String pID, String pLaptopNumber, String pType)
   {
      mAsset = new Asset(pID, "", pLaptopNumber, "", pType, "", false);
   }

   public Laptop(Asset pAsset)
      throws AssetMissMatchExecption
   {
      if(pAsset.getType().equalsIgnoreCase("Mac")
         || pAsset.getType().equalsIgnoreCase("PC"))
      {
         mAsset = pAsset;
      }
      else
      {
         throw new AssetMissMatchExecption("Not a Laptop asset.");
      }
   }

   public Laptop()
   {
   }

   @Override
   public String getModel()
   {
      return mAsset.getDescription();
   }

   @Override
   public void setModel(String pModel)
   {
      mAsset.setDescription(pModel);
   }

   /**
    * The Description property is used for the model information.
    * This will return the model information.
    *
    * @return The model information.
    * @deprecated
    */
   @Override
   public String getDescription()
   {
      return mAsset.getDescription();
   }

   /**
    * The Description property is used for the model information.
    * This will set the model information.
    * 
    * @deprecated 
    */
   @Override
   public void setDescription(String pDescription)
   {
      mAsset.setDescription(pDescription);
   }

   String getLaptopNumber()
   {
      return mAsset.getModel();
   }

   void setLaptopNumber(String pModel)
   {
      mAsset.setModel(pModel);
   }

   @Override
   public String toString()
   {
      return mAsset.getModel() + ": " + mAsset.getType();
   }
}
