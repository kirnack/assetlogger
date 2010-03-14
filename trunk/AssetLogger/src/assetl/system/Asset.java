package assetl.system;

/**
 * A representation of an asset.
 *
 * @author Bryon Rogers
 */
public class Asset
{
    /**
     * The asset's ID
     */
    protected String mID;

   /**
    * The assest's make
    */
   protected String mMake;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Asset other = (Asset) obj;
        if ((this.mID == null) ? (other.mID != null) : !this.mID.equals(other.mID)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + (this.mID != null ? this.mID.hashCode() : 0);
        return hash;
    }

   /**
    * The asset's model
    */
   protected String mModel;

   /**
    * The asset's serial number
    */
   protected String mSerialNum;

   /**
    * The asset's type
    */
   protected String mType;

   /**
    * The asset's description
    */
   protected String mDescription;

   /**
    * Constructs an asset with only the identifier and a type.
    *
    * @param pID The identifier
    * @param pType The type
    */
   public Asset(String pID, String pType)
   {
      this(pID,"","","",pType,"");
   }

   /**
    * Constructs an asset with all fields.
    *
    * @param pID The identifier
    * @param pMake The make
    * @param pModel The model
    * @param pSerialNum The serial number
    * @param pType The type
    * @param pDescription The description
    */
   public Asset(String pID, String pMake, String pModel, String pSerialNum,
                String pType, String pDescription)
   {
      this.mID = pID;
      this.mMake = pMake;
      this.mModel = pModel;
      this.mSerialNum = pSerialNum;
      this.mType = pType;
      this.mDescription = pDescription;
   }

   /**
    * Gets the description of this asset.
    *
    * @return The description of the asset.
    */
   public String getDescription()
   {
      return mDescription;
   }

   /**
    * Sets the description of the asset.
    *
    * @param pDescription The new description
    */
   public void setDescription(String pDescription)
   {
      this.mDescription = pDescription;
   }


   /**
    * Gets the type of the asset.
    *
    * @return The assists type.
    */
   public String getType()
   {
      return mType;
   }

   /**
    * Sets the type of the asset.
    *
    * @param pType The new asset type.
    */
   public void setType(String pType)
   {
      this.mType = pType;
   }


   /**
    * Gets the serial number of the asset.
    *
    * @return The serial number of the asset
    */
   public String getSerialNum()
   {
      return mSerialNum;
   }

   /**
    * Sets the serial number of the asset.
    *
    * @param pSerialNum The new serial number for the asset.
    */
   public void setSerialNum(String pSerialNum)
   {
      this.mSerialNum = pSerialNum;
   }


   /**
    * Gets the model of the asset.
    *
    * @return The model of the asset.
    */
   public String getModel()
   {
      return mModel;
   }

   /**
    * Sets the model of the asset.
    *
    * @param pModel The new model of the asset.
    */
   public void setModel(String pModel)
   {
      this.mModel = pModel;
   }

   /**
    * Gets the make of the asset.
    *
    * @return The make of the asset.
    */
   public String getMake()
   {
      return mMake;
   }

   /**
    * Sets the value of the asset.
    *
    * @param pMake The new make of the asset
    */
   public void setMake(String pMake) {
      this.mMake = pMake;
   }

   /**
    * Gets the identifier of the asset.
    *
    * @return The identifier of the asset.
    */
   public String getID() {
      return mID;
   }

   /**
    * Sets the identifier of the assets
    *
    * @param pID The new identifier of the asset.
    */
   public void setID(String pID) {
      this.mID = pID;
   }
}
