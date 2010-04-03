/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package assetl.system;

import assetl.system.Asset;

/**
 * A Decorator for different types of assets.
 * @author Bryon Rogers
 */
public class AssetType
   extends Asset
{
   Asset mAsset;

   public AssetType(Asset pAsset)
   {
      this.mAsset = pAsset;
   }

   public AssetType(String pID, String pMake, String pModel, String pSerialNum,
      String pType, String pDescription, boolean pMaintenance, Asset mAsset)
   {
      mAsset = new Asset(pID, pMake, pModel, pSerialNum,
         pType, pDescription, pMaintenance);
   }

   public AssetType(String pID, String pMake, String pModel, String pSerialNum,
      String pType, String pDescription, Asset mAsset)
   {
      mAsset = new Asset(pID, pMake, pModel, pSerialNum, pType, pDescription);
   }

   public AssetType(String pID, String pType, Asset mAsset)
   {
      mAsset = new Asset(pID, pType);
   }

   public AssetType()
   {
   }

   @Override
   public boolean equals(Object obj)
   {
      return (obj instanceof AssetType) && ((AssetType) obj).equals(mAsset);
   }

   @Override
   public int hashCode()
   {
      return (this.mAsset != null ? this.mAsset.hashCode() : 0);
   }

   @Override
   public String getDescription()
   {
      return mAsset.getDescription();
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
   public String getModel()
   {
      return mAsset.getModel();
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
   public boolean isInMaintenance()
   {
      return mAsset.isInMaintenance();
   }

   @Override
   public void setDescription(String pDescription)
   {
      mAsset.setDescription(pDescription);
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
   public void setModel(String pModel)
   {
      mAsset.setModel(pModel);
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

   public Asset getAsset()
   {
      return mAsset;
   }
}
