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

   /**
    * Get the value of mReturnedDate
    *
    * @return the value of mReturnedDate
    */
   public Date getMReturnedDate()
   {
      return mReturnedDate;
   }

   /**
    * Set the value of mReturnedDate
    *
    * @param mReturnedDate new value of mReturnedDate
    */
   public void setMReturnedDate(Date mReturnedDate)
   {
      this.mReturnedDate = mReturnedDate;
   }

   /**
    * Get the value of mPickedupDate
    *
    * @return the value of mPickedupDate
    */
   public Date getMPickedupDate()
   {
      return mPickedupDate;
   }

   /**
    * Set the value of mPickedupDate
    *
    * @param mPickedupDate new value of mPickedupDate
    */
   public void setMPickedupDate(Date mPickedupDate)
   {
      this.mPickedupDate = mPickedupDate;
   }

   /**
    * Get the value of mRequestedEndDate
    *
    * @return the value of mRequestedEndDate
    */
   public Date getMRequestedEndDate() {
      return mRequestedEndDate;
   }

   /**
    * Set the value of mRequestedEndDate
    *
    * @param mRequestedEndDate new value of mRequestedEndDate
    */
   public void setMRequestedEndDate(Date mRequestedEndDate) {
      this.mRequestedEndDate = mRequestedEndDate;
   }

   /**
    * Get the value of mRequestedStartDate
    *
    * @return the value of mRequestedStartDate
    */
   public Date getMRequestedStartDate() {
      return mRequestedStartDate;
   }

   /**
    * Set the value of mRequestedStartDate
    *
    * @param mRequestedStartDate new value of mRequestedStartDate
    */
   public void setMRequestedStartDate(Date mRequestedStartDate) {
      this.mRequestedStartDate = mRequestedStartDate;
   }

   /**
    * Get the value of mRecipient
    *
    * @return the value of mRecipient
    */
   public Person getMRecipient() {
      return mRecipient;
   }

   /**
    * Set the value of mRecipient
    *
    * @param mRecipient new value of mRecipient
    */
   public void setMRecipient(Person mRecipient) {
      this.mRecipient = mRecipient;
   }

   /**
    * Get the value of mAsset
    *
    * @return the value of mAsset
    */
   public Asset getMAsset() {
      return mAsset;
   }

   /**
    * Set the value of mAsset
    *
    * @param mAsset new value of mAsset
    */
   public void setMAsset(Asset mAsset) {
      this.mAsset = mAsset;
   }

   /**
    * Get the value of mID
    *
    * @return the value of mID
    */
   public String getMID() {
      return mID;
   }

   /**
    * Set the value of mID
    *
    * @param mID new value of mID
    */
   public void setMID(String mID) {
      this.mID = mID;
   }
}
