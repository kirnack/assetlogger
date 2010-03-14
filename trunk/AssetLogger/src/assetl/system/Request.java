package assetl.system;

import java.util.Collection;
import java.util.Date;
import java.util.ArrayList;

/**
 * A class to represent a request from a person to borrow assets.
 *
 * @author Bryon Rogers
 */
public class Request
{

    /**
     * 
     */
    protected String mID;
   /**
    *
    */
   protected Date mRequestMade;
   /**
    *
    */
   protected Date mRequestedPickup;
   /**
    *
    */
   protected String mRequstType;
   /**
    *
    */
   protected Person mRequestor;
   /**
    *
    */
   protected boolean mActive;
   /**
    *
    */
   protected Collection<Checkout> mCheckouts;

   /**
    * Default Constructor
    */
   public Request()
   {
   }

   /**
    * Constructor for a Request object
    *
    * @param pID The unique identifier of a Request
    * @param pRequestMade The Date the request was made
    * @param pRequestedPickup The Date they want to pick the Asset up
    * @param pRequstType
    * @param pRequestor The Person requesting the asset
    */
   public Request(String pID, Date pRequestMade, Date pRequestedPickup,
                  String pRequstType, Person pRequestor)
   {
       mID = pID;
       mRequestMade = pRequestMade;
       mRequestedPickup = pRequestedPickup;
       mRequstType = pRequstType;
       mRequestor = pRequestor;
       mActive = true;
       mCheckouts = new ArrayList<Checkout>();
   }

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
    * @param pCheckouts new value of mCheckouts
    */
   public void setCheckouts(Collection<Checkout> pCheckouts)
   {
      mCheckouts = pCheckouts;
   }

   /**
    * Allows for a Checkout to be added to the collection in this request
    *
    * @param pCheckout The checkout to add
    */
   public void addCheckout(Checkout pCheckout)
   {
       mCheckouts.add(pCheckout);
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
    * @param pActive new value of mActive
    */
   public void setActive(boolean pActive) {
      mActive = pActive;
   }

   /**
    * Get the value of mRequestor
    *
    * @return the value of pRequestor
    */
   public Person getRequestor()
   {
      return mRequestor;
   }

   /**
    * Set the value of mRequestor
    *
    * @param pRequestor new value of mRequestor
    */
   public void setRequestor(Person pRequestor)
   {
      mRequestor = pRequestor;
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
    * @param pRequstType new value of mRequstType
    */
   public void setRequstType(String pRequstType)
   {
      mRequstType = pRequstType;
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
    * @param pRequestMade new value of mRequestMade
    */
   public void setRequestMade(Date pRequestMade)
   {
      mRequestMade = pRequestMade;
   }

   /**
    * Get the value of mRequestedPickup
    *
    * @return the value of mRequestedPickup
    */
   public Date getRequestedPickup()
   {
       return mRequestedPickup;
   }

   /**
    * Set the value of mRequestedPickup
    *
    * @param pRequestedPickup The requested pickup date
    */
   public void setRequestedPickup(Date pRequestedPickup)
   {
       mRequestedPickup = pRequestedPickup;
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
    * @param pID new value of mID
    */
   public void setID(String pID)
   {
      mID = pID;
   }
}