package assetl.system;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

import java.util.Date;

/**
 * A class that represents the borrowing on an asset by a person.
 *
 * @author Bryon Rogers
 */
@XmlRootElement(name = "checkout")
@XmlAccessorType(XmlAccessType.FIELD)
public class Checkout
{
   /**
    * The unique identification of a checkout object
    */
   @XmlElement(name = "id")
   protected String mID;
   /**
    * The ID of the request that owns this checkout.
    */
   @XmlElement(name = "asset")
   protected String mRequestID;
   /**
    * The asset that needs checkout
    */
   protected Asset mAsset;
   /**
    * The person receiving the asset
    */
   @XmlElement(name = "receiver")
   protected Person mRecipient;
   /**
    * The date to start reserving an asset for the person
    */
   @XmlElement(name = "requestedstart")
   protected Date mRequestedStartDate;
   /**
    * The date the person will return the asset
    */
   @XmlElement(name = "requestedend")
   protected Date mRequestedEndDate;
   /**
    * The date the asset was actually picked up
    */
   @XmlElement(name = "pickedupon")
   protected Date mPickedupDate;
   /**
    * The date the asset was actually returned
    */
   @XmlElement(name = "returnedon")
   protected Date mReturnedDate;
   /**
    * Indicates whether the data in this object is valid
    */
   @XmlElement(name = "isactive")
   protected boolean mActive;

   /**
    * Default Constructor for Checkout object.
    */
   public Checkout()
   {
   }

   /**
    * Contructor for Checkout object
    *
    * @param pID The unique identifier of a checkout
    * @param pRequestID The ID of the request that owns this checkout.
    * @param pAsset The asset to checkout
    * @param pRecipient The recipient of the asset
    * @param pRequestedStartDate The requested start date
    * @param pRequestedEndDate The requested end date
    */
   public Checkout(String pID, String pRequestID, Asset pAsset,
      Person pRecipient, Date pRequestedStartDate,
      Date pRequestedEndDate)
   {
      this(pID, pRequestID, pAsset, pRecipient, pRequestedStartDate,
         pRequestedEndDate, null, null, true);
   }

   /**
    * Contructor for Checkout object
    *
    * @param pID The unique identifier of a checkout
    * @param pRequestID The ID of the request that owns this checkout.
    * @param pAsset The asset to checkout
    * @param pRecipient The recipient of the asset
    * @param pRequestedStartDate The requested start date
    * @param pRequestedEndDate The requested end date
    * @param pPickedupDate
    * @param pReturnedDate
    * @param pActive
    */
   public Checkout(String pID, String pRequestID, Asset pAsset,
      Person pRecipient, Date pRequestedStartDate,
      Date pRequestedEndDate, Date pPickedupDate,
      Date pReturnedDate, boolean pActive)
   {
      mID = pID;
      mAsset = pAsset;
      mRecipient = pRecipient;
      mRequestedStartDate = pRequestedStartDate;
      mRequestedStartDate = pRequestedEndDate;
      mRequestID = pRequestID;
      mPickedupDate = pPickedupDate;
      mReturnedDate = pReturnedDate;
      mActive = pActive;
   }

   /**
    * Returns if the checkout is still active. Meaning that the asset has/will
    * be picked up and has not been returned yet.
    *
    * @return the value of mActive
    */
   public boolean isActive()
   {
      return mActive;
   }

   /**
    * Set the active state for the
    *
    * @param pActive
    */
   public void setActive(boolean pActive)
   {
      this.mActive = pActive;
   }

   /**
    * Get the value of mReturnedDate
    *
    * @return the value of mReturnedDate
    */
   public Date getReturnedDate()
   {
      return mReturnedDate;
   }

   /**
    * Set the value of mReturnedDate
    *
    * @param pReturnedDate
    */
   public void setReturnedDate(Date pReturnedDate)
   {
      this.mReturnedDate = pReturnedDate;
   }

   /**
    * Get the value of mPickedupDate
    *
    * @return the value of mPickedupDate
    */
   public Date getPickedupDate()
   {
      return mPickedupDate;
   }

   /**
    * Set the value of mPickedupDate
    *
    * @param pPickedupDate
    */
   public void setPickedupDate(Date pPickedupDate)
   {
      this.mPickedupDate = pPickedupDate;
   }

   /**
    * Get the value of mRequestedEndDate
    *
    * @return the value of mRequestedEndDate
    */
   public Date getRequestedEndDate()
   {
      return mRequestedEndDate;
   }

   /**
    * Set the value of mRequestedEndDate
    *
    * @param pRequestedEndDate
    */
   public void setRequestedEndDate(Date pRequestedEndDate)
   {
      this.mRequestedEndDate = pRequestedEndDate;
   }

   /**
    * Get the value of mRequestedStartDate
    *
    * @return the value of mRequestedStartDate
    */
   public Date getRequestedStartDate()
   {
      return mRequestedStartDate;
   }

   /**
    * Set the value of mRequestedStartDate
    *
    * @param pRequestedStartDate
    */
   public void setRequestedStartDate(Date pRequestedStartDate)
   {
      this.mRequestedStartDate = pRequestedStartDate;
   }

   /**
    * Get the value of mRecipient
    *
    * @return the value of mRecipient
    */
   public Person getRecipient()
   {
      return mRecipient;
   }

   /**
    * Set the value of mRecipient
    *
    * @param pRecipient
    */
   public void setRecipient(Person pRecipient)
   {
      this.mRecipient = pRecipient;
   }

   /**
    * Get the value of mAsset
    *
    * @return the value of mAsset
    */
   public Asset getAsset()
   {
      return mAsset;
   }

   /**
    * Set the value of mAsset
    *
    * @param pAsset
    */
   public void setAsset(Asset pAsset)
   {
      this.mAsset = pAsset;
   }

   /**
    * Get the value of mID
    *
    * @return the value of mID
    */
   public String getID()
   {
      return mID;
   }

   /**
    * Set the value of mID
    *
    * @param pID
    */
   public void setID(String pID)
   {
      this.mID = pID;
   }

   /**
    * Returns the ID of the request that owns this checkout.
    *
    * @return The owning request ID.
    */
   public String getRequestID()
   {
      return mRequestID;
   }

   /**
    * Sets the ID of the owning request for this checkout.
    *
    * @param pRequestID
    */
   public void setRequestID(String pRequestID)
   {
      this.mRequestID = pRequestID;
   }

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
      final Checkout other = (Checkout) obj;
      if ((this.mID == null) ? (other.mID != null)
         : !this.mID.equals(other.mID))
      {
         return false;
      }
      return true;
   }

   @Override
   public int hashCode()
   {
      int hash = 7;
      hash = 23 * hash + (this.mID != null ? this.mID.hashCode() : 0);
      return hash;
   }
}
