/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package assetl.system;

import java.util.Date;

/**
 * A class that represents the borrowing on an asset by a person.
 *
 * @author Bryon Rogers
 */
public class Checkout
{
   protected String mID;
   protected Asset mAsset;
   protected Person mRecipient;
   protected Date mRequestedStartDate;
   protected Date mRequestedEndDate;
   protected Date mPickedupDate;
   protected Date mReturnedDate;
   protected boolean mActive;

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
    * @param mActive new value of mActive
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
    * @param mReturnedDate new value of mReturnedDate
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
    * @param mPickedupDate new value of mPickedupDate
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
    * @param mRequestedEndDate new value of mRequestedEndDate
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
    * @param mRequestedStartDate new value of mRequestedStartDate
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
    * @param mRecipient new value of mRecipient
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
    * @param mAsset new value of mAsset
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
    * @param mID new value of mID
    */
   public void setID(String pID)
   {
      this.mID = pID;
   }
}
