package assetl.desktop;

import assetl.system.Asset;
import assetl.system.AssetMissMatchExecption;

/**
 * A LapTop Asset.
 * @author Bryon Rogers
 */
public class Laptop
   extends Asset
{

   public Laptop(String pID, String pLaptopNumber, String pMake,
      String pModel, String pSerialNum, String pType,  boolean pMaintenance)
   {
      super(pID, pMake, pLaptopNumber, pSerialNum, pType, pModel,
            pMaintenance);
   }

   public Laptop(String pID, String pLaptopNumber, String pMake,
                 String pModel, String pSerialNum, String pType)
   {
      super(pID, pMake, pLaptopNumber, pSerialNum, pType, pModel, false);
   }

   public Laptop(String pID, String pLaptopNumber, String pType)
   {
      super(pID, "", pLaptopNumber, "", pType, "", false);
   }

   public Laptop(Asset pAsset)
      throws AssetMissMatchExecption
   {
      if(pAsset.getType().equalsIgnoreCase("Mac")
         || pAsset.getType().equalsIgnoreCase("PC"))
      {
         super.mDescription = pAsset.getDescription();
         super.mID = pAsset.getID();
         super.mMaintenance = pAsset.isInMaintenance();
         super.mMake = pAsset.getMake();
         super.mModel = pAsset.getModel();
         super.mSerialNum = pAsset.getSerialNum();
         super.mType = pAsset.getType();
      }
      else
      {
         throw new AssetMissMatchExecption("Not a Laptiop asset.");
      }
   }

   public Laptop()
   {
   }

   @Override
   public String getModel()
   {
      return super.getDescription();
   }

   @Override
   public void setModel(String pModel)
   {
      super.setDescription(pModel);
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
      return super.getDescription();
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
      super.setDescription(pDescription);
   }

   String getLaptopNumber()
   {
      return mModel;
   }

   void setLaptopNumber(String pModel)
   {
      mModel = pModel;
   }

   @Override
   public String toString()
   {
      return mModel + ": " + mType;
   }

}