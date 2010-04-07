package assetl.system;

/**
 * A Decorator for different types of assets.
 * @author Bryon Rogers
 */
public class AssetType
   extends Asset
{
   /**
    * Provides an Asset to be decorated by subclasses
    */
   protected Asset mAsset;

   /**
    * Default Constructor
    */
   public AssetType()
   {
   }

   /**
    * Constructor that takes an Asset as a parameter
    *
    * @param pAsset The asset to decorate
    */
   public AssetType(Asset pAsset)
   {
      mAsset = pAsset;
   }

   /**
    * Constructor with parameters
    *
    * @param pID The asset id
    * @param pMake The make
    * @param pModel The model
    * @param pSerialNum The serial number
    * @param pType The type
    * @param pDescription The description
    * @param pMaintenance True if in maintenance
    * @param mAsset The asset
    */
   public AssetType(String pID, String pMake, String pModel, String pSerialNum,
      String pType, String pDescription, boolean pMaintenance, Asset mAsset)
   {
      mAsset = new Asset(pID, pMake, pModel, pSerialNum,
         pType, pDescription, pMaintenance);
   }

   /**
    * Constructor with parameters
    *
    * @param pID The asset id
    * @param pMake The make
    * @param pModel The model
    * @param pSerialNum The serial number
    * @param pType The type
    * @param pDescription The description
    * @param mAsset The asset
    */
   public AssetType(String pID, String pMake, String pModel, String pSerialNum,
      String pType, String pDescription, Asset mAsset)
   {
      mAsset = new Asset(pID, pMake, pModel, pSerialNum, pType, pDescription);
   }

   /**
    * Constructor with parameters
    *
    * @param pID The asset id
    * @param pType The type
    * @param mAsset The asset
    */
   public AssetType(String pID, String pType, Asset mAsset)
   {
      mAsset = new Asset(pID, pType);
   }

   /**
    * Returns true if the objects are equal
    *
    * @param obj The object to compare with
    * @return True if equal
    */
   @Override
   public boolean equals(Object obj)
   {
      return (obj instanceof AssetType) && ((AssetType) obj).equals(mAsset);
   }

   /**
    * Generates a unique hash code for the object
    * @return The hash code
    */
   @Override
   public int hashCode()
   {
      return (this.mAsset != null ? this.mAsset.hashCode() : 0);
   }

   /**
    * Returns the asset's description
    *
    * @return The description
    */
   @Override
   public String getDescription()
   {
      return mAsset.getDescription();
   }

   /**
    * Returns the asset id
    *
    * @return The asset id
    */
   @Override
   public String getID()
   {
      return mAsset.getID();
   }

   /**
    * Returns the asset's make
    *
    * @return The make
    */
   @Override
   public String getMake()
   {
      return mAsset.getMake();
   }

   /**
    * Returns the asset's model
    *
    * @return The model
    */
   @Override
   public String getModel()
   {
      return mAsset.getModel();
   }

   /**
    * Returns the asset's serial number
    *
    * @return The serial number
    */
   @Override
   public String getSerialNum()
   {
      return mAsset.getSerialNum();
   }

   /**
    * Returns the asset's type
    *
    * @return The type
    */
   @Override
   public String getType()
   {
      return mAsset.getType();
   }

   /**
    * Returns true if the asset is in maintenance
    *
    * @return True if in maintenance
    */
   @Override
   public boolean isInMaintenance()
   {
      return mAsset.isInMaintenance();
   }

   /**
    * Sets the description
    *
    * @param pDescription The description
    */
   @Override
   public void setDescription(String pDescription)
   {
      mAsset.setDescription(pDescription);
   }

   /**
    * Sets the id
    *
    * @param pID The id
    */
   @Override
   public void setID(String pID)
   {
      mAsset.setID(pID);
   }

   /**
    * Sets whether the asset is in maintenance
    *
    * @param pMaintenance True if in maintenance
    */
   @Override
   public void setMaintenance(boolean pMaintenance)
   {
      mAsset.setMaintenance(pMaintenance);
   }

   /**
    * Sets the make
    *
    * @param pMake The make
    */
   @Override
   public void setMake(String pMake)
   {
      mAsset.setMake(pMake);
   }

   /**
    * Sets the model
    *
    * @param pModel The model
    */
   @Override
   public void setModel(String pModel)
   {
      mAsset.setModel(pModel);
   }

   /**
    * Sets the serial number
    *
    * @param pSerialNum The serial number
    */
   @Override
   public void setSerialNum(String pSerialNum)
   {
      mAsset.setSerialNum(pSerialNum);
   }

   /**
    * Sets the type
    *
    * @param pType The type
    */
   @Override
   public void setType(String pType)
   {
      mAsset.setType(pType);
   }

   /**
    * Returns the asset being decorated
    *
    * @return The asset being decorated
    */
   public Asset getAsset()
   {
      return mAsset;
   }
}
