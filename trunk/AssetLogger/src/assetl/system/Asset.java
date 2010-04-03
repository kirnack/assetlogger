package assetl.system;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

/**
 * A representation of an asset.
 *
 * @author Bryon Rogers
 */
@XmlRootElement(name = "asset")
@XmlAccessorType(XmlAccessType.FIELD)
public class Asset
{
   /**
    * The asset's ID
    */
   @XmlElement(name = "id")
   protected String mID;
   /**
    * The asset's make
    */
   @XmlElement(name = "make")
   protected String mMake;
   /**
    * The asset's model
    */
   @XmlElement(name = "model")
   protected String mModel;
   /**
    * The asset's serial number
    */
   @XmlElement(name = "serialnum")
   protected String mSerialNum;
   /**
    * The asset's type
    */
   @XmlElement(name = "type")
   protected String mType;
   /**
    * The asset's description
    */
   @XmlElement(name = "descrip")
   protected String mDescription;

   /**
    * Tells if the asset is in for matienance
    */
   @XmlElement(name = "inmaintance")
   protected boolean mMaintenance;

   /**
    * Constructs an asset with only the identifier and a type.
    *
    * @param pID The identifier
    * @param pType The type
    */
   public Asset()
   {
      this("", "", "", "", "", "", false);
   }

   /**
    * Constructs an asset with only the identifier and a type.
    *
    * @param pID The identifier
    * @param pType The type
    */
   public Asset(String pID, String pType)
   {
      this(pID, "", "", "", pType, "", false);
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
      this(pID, pMake, pModel, pSerialNum, pType, pDescription, false);
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
    * @param pMaintenance  If the assets is out for maintenance
    */
   public Asset(String pID, String pMake, String pModel, String pSerialNum,
      String pType, String pDescription, boolean pMaintenance)
   {
      mID = pID;
      mMake = pMake;
      mModel = pModel;
      mSerialNum = pSerialNum;
      mType = pType;
      mDescription = pDescription;
      mMaintenance = pMaintenance;
   }

   public Asset(Asset pAsset)
   {
      mID = pAsset.mID;
      mMake = pAsset.mMake;
      mModel = pAsset.mModel;
      mSerialNum = pAsset.mSerialNum;
      mType = pAsset.mType;
      mDescription = pAsset.mDescription;
      mMaintenance = pAsset.mMaintenance;
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
      mDescription = pDescription;
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
      mType = pType;
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
      mSerialNum = pSerialNum;
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
      mModel = pModel;
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
   public void setMake(String pMake)
   {
      mMake = pMake;
   }

   /**
    * Gets the identifier of the asset.
    *
    * @return The identifier of the asset.
    */
   public String getID()
   {
      return mID;
   }

   /**
    * Sets the identifier of the assets
    *
    * @param pID The new identifier of the asset.
    */
   public void setID(String pID)
   {
      mID = pID;
   }

   /**
    * Assets are considered equal if they have the same unique id.
    *
    * @param obj The object to compare equality with
    * @return True if the objects are equal
    */
   @Override
   public boolean equals(Object obj)
   {
      if (obj == null)
      {
         return false;
      }
      if (getClass() != obj.getClass())
      {
         return false;
      }
      final Asset other = (Asset) obj;
      if ((mID == null) ? (other.mID != null) : !mID.equals(other.mID))
      {
         return false;
      }
      return true;
   }

   /**
    * Overridden hashCode method for this object.
    *
    * @return The unique hash code for this object
    */
   @Override
   public int hashCode()
   {
      int hash = 7;
      hash = 13 * hash + (mID != null ? mID.hashCode() : 0);
      return hash;
   }

   /**
    *
    */
   @Override
   public String toString()
   {
      return mType + ": " + mModel;
   }

   /**
    * Sets the maintenance flag of the asset.
    *
    * @param pMaintenance The new maintenance status
    */
   public void setMaintenance(boolean pMaintenance)
   {
      mMaintenance = pMaintenance;
   }

   /**
    * Returns the maintenance flag of the asset.
    * 
    * @return Returns true if the asset is in for maintenance.
    */
   public boolean isInMaintenance()
   {
      return mMaintenance;
   }
}
