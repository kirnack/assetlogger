package assetl.service;

import java.util.Date;

import assetl.system.Asset;
import assetl.system.Checkout;
import assetl.system.DataPacket;
import assetl.system.SchedulePacket;
import assetl.system.PersonPacket;
import assetl.system.Person;
import assetl.system.Request;

/**
 * Schedules an asset
 *
 * @author Devin Doman
 */
public class ScheduleFunction
   extends Function
{
   /**
    * The specific DataPacket needed for this function to operate
    */
   SchedulePacket mData;
   /**
    * The current request
    */
   Request mCurrRequest;
   /**
    * The current checkout
    */
   Checkout mCurrCheckout;
   /**
    * The person who will receive the asset
    */
   Person mRecipient;
   /**
    * The asset the person will receive
    */
   Asset mAsset;

   /**
    * Sets the DataPacket for this function
    *
    * @param pPacket The DataPacket sent from the view
    */
   public void setPacket(DataPacket pPacket)
   {
      mData = (SchedulePacket) pPacket;
   }

   /**
    * Gets the DataPacket currently set for this function
    *
    * @return The DataPacket
    */
   public DataPacket getPacket()
   {
      return mData;
   }

   /**
    * Creates needed objects for the function using
    * data sent via the DataPacket
    */
   public void initVariables()
   {
      mCurrRequest = null;
      mCurrCheckout = null;

      mRecipient = mModel.getPerson(mData.getPersonID());
      mAsset = mModel.getAsset(mData.getAssetID());

      // TODO: remove this test code until get asset works again
      mAsset = new Asset();
      mAsset.setID(mData.getAssetID());
      mAsset.setType("Mac");
   }

   /**
    * Makes a new request using objects sent from the view.
    * Expects the Person, start Date, and Asset fields to be set in the packet.
    * Sets the Request made in the Request field of the data packet.
    */
   protected void makeRequest()
   {
      //
      // TODO: find a request by its requestor and pickup date
      // and see if it already exists
      // (rather than just checking the last request made)
      //

      //
      // If the pickup dates are the same for the same person
      // there is no reason to make a new request
      //

      if (mCurrRequest == null || mRecipient != mCurrRequest.getRequestor()
         || mData.getStart() != mCurrRequest.getRequestedPickup())
      {
         System.err.println("findprob: " + mAsset);
         // Make the request object, stamp it with today's date
         mCurrRequest = new Request("", new Date(), mData.getStart(),
            mAsset.getType(), mRecipient);
      }
   }

   /**
    * Makes a new checkout using data obtained from the view and adds
    * it to the current request. Expects the Person, Asset, start Date,
    * and end Date to be set in the data packet. Sets the checkout data
    * in the Checkout field of the data packet.
    *
    * @return true if the checkout can be made
    */
   protected boolean makeCheckout()
   {
      if (mCurrRequest == null)
      {
         // Can't add a checkout object
         return false;
      }
      else
      {
         // Make the checkout and add it to the current request
         mCurrCheckout = new Checkout("", "", mAsset, mRecipient,
            mData.getStart(), mData.getEnd());
         mCurrCheckout.setActive(true);
         return true;
      }
   }

   /**
    * Takes the objects sent from the view and schedules an asset to the
    * person indicated. If a Request object already exists for the person
    * with the same pickup date the same Request object is retrieved and used.
    * Otherwise a new Request object is made. A new Checkout object is created
    * and added to the Request object if the Checkout doesn't already exist.
    * The Request is then sent to the model.
    */
   public void performFunction()
   {
      initVariables();

      //make a new request and checkout
      makeRequest();
      makeCheckout();

      //
      // TODO: make sure the checkout isn't already in the collection
      //

      //add the checkout to the request
      mCurrRequest.addCheckout(mCurrCheckout);

      //send the request to the model
      mModel.setRequest(mCurrRequest, mControl.getCurrentUser().getID());

      // TODO: find a better way to do this: separate view changing from
      // functions more

      mControl.changeView("Service");
      mControl.sendViewPacket(new PersonPacket(mRecipient));
   }
}
