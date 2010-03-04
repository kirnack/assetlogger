package assetl.system;

import java.util.Collection;
import java.util.Date;

/**
 * A class to represent a request from a person to borrow assets.
 *
 * @author Bryon Rogers
 */
public class Request
{

   protected String mID;
   protected Date mRequestMade;
   protected String mRequstType;
   protected Person mRequestor;
   protected Boolean mActive;
   protected Collection<Checkout> mCheckouts;

   /**
    * Get the value of mCheckouts
    *
    * @return the value of mCheckouts
    */
   public Collection<Checkout> getMCheckouts() {
      return mCheckouts;
   }

   /**
    * Set the value of mCheckouts
    *
    * @param mCheckouts new value of mCheckouts
    */
   public void setMCheckouts(Collection<Checkout> mCheckouts) {
      this.mCheckouts = mCheckouts;
   }

   /**
    * Get the value of mActive
    *
    * @return the value of mActive
    */
   public Boolean getMActive() {
      return mActive;
   }

   /**
    * Set the value of mActive
    *
    * @param mActive new value of mActive
    */
   public void setMActive(Boolean mActive) {
      this.mActive = mActive;
   }

   /**
    * Get the value of mRequestor
    *
    * @return the value of mRequestor
    */
   public Person getMRequestor() {
      return mRequestor;
   }

   /**
    * Set the value of mRequestor
    *
    * @param mRequestor new value of mRequestor
    */
   public void setMRequestor(Person mRequestor) {
      this.mRequestor = mRequestor;
   }

   /**
    * Get the value of mRequstType
    *
    * @return the value of mRequstType
    */
   public String getMRequstType() {
      return mRequstType;
   }

   /**
    * Set the value of mRequstType
    *
    * @param mRequstType new value of mRequstType
    */
   public void setMRequstType(String mRequstType) {
      this.mRequstType = mRequstType;
   }

   /**
    * Get the value of mRequestMade
    *
    * @return the value of mRequestMade
    */
   public Date getMRequestMade() {
      return mRequestMade;
   }

   /**
    * Set the value of mRequestMade
    *
    * @param mRequestMade new value of mRequestMade
    */
   public void setMRequestMade(Date mRequestMade) {
      this.mRequestMade = mRequestMade;
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
