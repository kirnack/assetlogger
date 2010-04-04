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
