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

   Asset mAsset;
   public Laptop(String pID, String pLaptopNumber, String pMake,
      String pModel, String pSerialNum, String pType,  boolean pMaintenance)
   {
      mAsset = new Asset(pID, pMake, pLaptopNumber,
                         pSerialNum, pType, pModel, false);
   }

   @Override
   public boolean equals(Object obj)
   {
      return (obj instanceof Laptop) && mAsset.equals(((Laptop) obj).mAsset);
   }

   @Override
   public String getID()
   {
      return mAsset.getID();
   }

   @Override
   public String getMake()
   {
      return mAsset.getMake();
   }

   @Override
   public String getSerialNum()
   {
      return mAsset.getSerialNum();
   }

   @Override
   public String getType()
   {
      return mAsset.getType();
   }

   @Override
   public int hashCode()
   {
      return mAsset.hashCode();
   }

   @Override
   public boolean isInMaintenance()
   {
      return mAsset.isInMaintenance();
   }

   @Override
   public void setID(String pID)
   {
      mAsset.setID(pID);
   }

   @Override
   public void setMaintenance(boolean pMaintenance)
   {
      mAsset.setMaintenance(pMaintenance);
   }

   @Override
   public void setMake(String pMake)
   {
      mAsset.setMake(pMake);
   }

   @Override
   public void setSerialNum(String pSerialNum)
   {
      mAsset.setSerialNum(pSerialNum);
   }

   @Override
   public void setType(String pType)
   {
      mAsset.setType(pType);
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

   
   public String toString()
   {
      return mAsset.getModel() + ": " + mAsset.getType();
   }

   public Asset getAsset()
   {
      return mAsset;
   }

}
