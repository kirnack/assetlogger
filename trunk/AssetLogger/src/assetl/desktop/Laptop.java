package assetl.desktop;

import assetl.system.AssetType;
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
   /**
    * Default Constructor
    *
    */
   public Laptop()
   {
   }

   /**
    * Constructor with all parameters
    *
    * @param pID The laptop unique identification
    * @param pLaptopNumber The laptop number assigned
    * @param pMake The make
    * @param pModel The model
    * @param pSerialNum The serial number
    * @param pType The type (PC or Mac)
    * @param pMaintenance True if the laptop is in maintenance
    */
   public Laptop(String pID, String pLaptopNumber, String pMake,
      String pModel, String pSerialNum, String pType, boolean pMaintenance)
   {
      mAsset = new Asset(pID, pMake, pLaptopNumber,
         pSerialNum, pType, pModel, false);
   }

   /**
    * Constructor with parameters without in maintenance field
    *
    * @param pID The laptop unique identification
    * @param pLaptopNumber The laptop number assigned
    * @param pMake The make
    * @param pModel The model
    * @param pSerialNum The serial number
    * @param pType The type (PC or Mac)
    */
   public Laptop(String pID, String pLaptopNumber, String pMake,
      String pModel, String pSerialNum, String pType)
   {
      mAsset =
         new Asset(pID, pMake, pLaptopNumber, pSerialNum, pType, pModel, false);
   }

   /**
    * Constructor with parameters
    *
    * @param pID The laptop unique identification
    * @param pLaptopNumber The laptop number assigned
    * @param pType The type (PC or Mac)
    */
   public Laptop(String pID, String pLaptopNumber, String pType)
   {
      mAsset = new Asset(pID, "", pLaptopNumber, "", pType, "", false);
   }

   /**
    * Converts the parent Asset class to a Laptop iff the Asset passed
    * in is a Laptop
    *
    * @param pAsset The laptop to convert to a Laptop
    * @throws AssetMissMatchExecption Exception thrown if the Asset is not
    *                                 a Laptop
    */
   public Laptop(Asset pAsset)
      throws AssetMissMatchExecption
   {
      if (pAsset.getType().equalsIgnoreCase("Mac")
         || pAsset.getType().equalsIgnoreCase("PC"))
      {
         mAsset = pAsset;
      }
      else
      {
         throw new AssetMissMatchExecption("Not a Laptop asset.");
      }
   }

   /**
    * Returns the laptop model
    *
    * @return The laptop model
    */
   @Override
   public String getModel()
   {
      return mAsset.getDescription();
   }

   /**
    * Sets the laptop model
    *
    * @param pModel The laptop model
    */
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
    * @deprecated Laptop objects use the description field to hold the model
    *             and the model field to hold to laptop number so that laptop
    *             numbers will be easily searchable
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
    * @deprecated Laptop objects use the description field to hold the model
    *             and the model field to hold to laptop number so that laptop
    *             numbers will be easily searchable
    */
   @Override
   public void setDescription(String pDescription)
   {
      mAsset.setDescription(pDescription);
   }

   /**
    * Returns the laptop number
    *
    * @return The laptop number
    */
   String getLaptopNumber()
   {
      return mAsset.getModel();
   }

   /**
    * Sets the laptop number
    *
    * @param pModel The laptop number
    */
   void setLaptopNumber(String pLaptopNum)
   {
      mAsset.setModel(pLaptopNum);
   }

   /**
    * Converts a laptop object to a string representation
    *
    * @return The Laptop as a string
    */
   @Override
   public String toString()
   {
      return mAsset.getModel() + ": " + mAsset.getType();
   }
}
