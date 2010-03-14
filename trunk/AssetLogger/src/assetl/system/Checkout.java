package assetl.system;

import java.util.Date;

/**
 * A class that represents the borrowing on an asset by a person.
 *
 * @author Bryon Rogers
 */
public class Checkout
{
    /**
     * The unique identification of a checkout object
     */
    protected String mID;

    /**
     * The asset that needs checkout
     */
    protected Asset mAsset;

   /**
    * The person receiving the asset
    */
   protected Person mRecipient;

   /**
    * The date to start reserving an asset for the person
    */
   protected Date mRequestedStartDate;

   /**
    * The date the person will return the asset
    */
   protected Date mRequestedEndDate;

   /**
    * The date the asset was actually picked up
    */
   protected Date mPickedupDate;

   /**
    * The date the asset was actually returned
    */
   protected Date mReturnedDate;

   /**
    * Indicates whether the data in this object is valid
    */
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
    * @param pAsset The asset to checkout
    * @param pRecipient The recipient of the asset
    * @param pRequestedStartDate The requested start date
    * @param pRequestedEndDate The requested end date
    */
   public Checkout(String pID, Asset pAsset, Person pRecipient, 
                   Date pRequestedStartDate, Date pRequestedEndDate)
   {
       this(pID, pAsset, pRecipient, pRequestedStartDate, pRequestedEndDate,
            null, null);
   }

   /**
    * Contructor for Checkout object
    *
    * @param pID The unique identifier of a checkout
    * @param pAsset The asset to checkout
    * @param pRecipient The recipient of the asset
    * @param pRequestedStartDate The requested start date
    * @param pRequestedEndDate The requested end date
    */
   public Checkout(String pID, Asset pAsset, Person pRecipient,
                   Date pRequestedStartDate, Date pRequestedEndDate,
                   Date pPickedupDate, Date pReturnedDate)
   {
       mID = pID;
       mAsset = pAsset;
       mRecipient = pRecipient;
       mRequestedStartDate = pRequestedStartDate;
       mRequestedStartDate = pRequestedEndDate;
       mPickedupDate = pPickedupDate;
       mReturnedDate = pReturnedDate;
       mActive = true;
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
}
