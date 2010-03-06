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
   protected boolean mActive;
   protected Collection<Checkout> mCheckouts;

   /**
    * Get the value of mCheckouts
    *
    * @return the value of mCheckouts
    */
   public Collection<Checkout> getCheckouts()
   {
      return mCheckouts;
   }

   /**
    * Set the value of mCheckouts
    *
    * @param mCheckouts new value of mCheckouts
    */
   public void setCheckouts(Collection<Checkout> pCheckouts)
   {
      this.mCheckouts = pCheckouts;
   }

   /**
    * Get the value of mActive
    *
    * @return the value of mActive
    */
   public boolean isActive()
   {
      return mActive;
   }

   /**
    * Set the value of mActive
    *
    * @param mActive new value of mActive
    */
   public void setActive(boolean pActive) {
      this.mActive = pActive;
   }

   /**
    * Get the value of mRequestor
    *
    * @return the value of mRequestor
    */
   public Person getRequestor()
   {
      return mRequestor;
   }

   /**
    * Set the value of mRequestor
    *
    * @param mRequestor new value of mRequestor
    */
   public void setRequestor(Person pRequestor)
   {
      this.mRequestor = pRequestor;
   }

   /**
    * Get the value of mRequstType
    *
    * @return the value of mRequstType
    */
   public String getRequstType()
   {
      return mRequstType;
   }

   /**
    * Set the value of mRequstType
    *
    * @param mRequstType new value of mRequstType
    */
   public void setRequstType(String pRequstType)
   {
      this.mRequstType = pRequstType;
   }

   /**
    * Get the value of mRequestMade
    *
    * @return the value of mRequestMade
    */
   public Date getRequestMade()
   {
      return mRequestMade;
   }

   /**
    * Set the value of mRequestMade
    *
    * @param mRequestMade new value of mRequestMade
    */
   public void setRequestMade(Date pRequestMade)
   {
      this.mRequestMade = pRequestMade;
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
