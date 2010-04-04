package assetl.system;

import java.util.Collection;
import java.util.Date;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

/**
 * A class to represent a request from a person to borrow assets.
 *
 * @author Bryon Rogers
 */
@XmlRootElement(name = "request")
@XmlAccessorType(XmlAccessType.FIELD)
public class Request
{
   /**
    * The unique identifier for the request
    */
   @XmlElement(name = "id")
   protected String mID;
   /**
    * The date the request was made
    */
   @XmlElement(name = "made")
   protected Date mRequestMade;
   /**
    * The date the person requests to pick up the asset
    */
   @XmlElement(name = "reqpickeupdate")
   protected Date mRequestedPickup;
   /**
    * The type asset they would like to request
    */
   @XmlElement(name = "reqtype")
   protected String mRequstType;
   /**
    * The person requesting an asset
    */
   @XmlElement(name = "requestor")
   protected Person mRequestor;
   /**
    * Indicates whether the request data is valid
    */
   @XmlElement(name = "isactive")
   protected boolean mActive;
   /**
    * The checkouts to that have been requested
    */
   @XmlElement(name = "checkout")
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
      this(pID, pRequestMade, pRequestedPickup, pRequstType, pRequestor,
         new ArrayList<Checkout>());
   }

   /**
    * Constructor for a Request object
    *
    * @param pID The unique identifier of a Request
    * @param pRequestMade The Date the request was made
    * @param pRequestedPickup The Date they want to pick the Asset up
    * @param pRequstType
    * @param pRequestor The Person requesting the asset
    * @param pCheckouts
    */
   public Request(String pID, Date pRequestMade, Date pRequestedPickup,
      String pRequstType, Person pRequestor, Collection<Checkout> pCheckouts)
   {
      mID = pID;
      mRequestMade = pRequestMade;
      mRequestedPickup = pRequestedPickup;
      mRequstType = pRequstType;
      mRequestor = pRequestor;
      mActive = true;
      mCheckouts = pCheckouts;
      setCheckoutsRequestID();
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
      setCheckoutsRequestID();
   }

   /**
    * Allows for a Checkout to be added to the collection in this request
    *
    * @param pCheckout The checkout to add
    */
   public void addCheckout(Checkout pCheckout)
   {
      pCheckout.setRequestID(mID);
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
   public void setActive(boolean pActive)
   {
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
      setCheckoutsRequestID();
   }

   /**
    * Sets all the requests checkouts to have their RequestIDs match the
    * request's ID.
    */
   private void setCheckoutsRequestID()
   {
      for (Checkout checkout : mCheckouts)
      {
         checkout.setRequestID(mID);
      }
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
      final Request other = (Request) obj;
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
      hash = 17 * hash + (this.mID != null ? this.mID.hashCode() : 0);
      return hash;
   }

   @Override
   public String toString()
   {
      return "Request " + mID + " for " + mCheckouts.size() + " assets.";
   }


}
