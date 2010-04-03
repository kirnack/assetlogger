/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package assetl.desktop;

import assetl.system.Asset;

/**
 *
 * @author brogers3
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
                 String pModel, String pSerialNum, String pType,
                 String pDescription)
   {
      super(pID, pMake, pLaptopNumber, pSerialNum, pType, pModel, false);
   }

   public Laptop(String pID, String pLaptopNumber, String pType)
   {
      super(pID, "", pLaptopNumber, "", pType, "", false);
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