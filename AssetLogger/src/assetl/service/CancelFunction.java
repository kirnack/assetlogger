package assetl.service;

import assetl.system.Checkout;
import assetl.system.Request;
import assetl.system.DataPacket;
import assetl.system.RequestPacket;
import assetl.system.CheckoutPacket;

/**
 * Defines behavior for canceling requests
 *
 * @author Devin Doman
 */
public class CancelFunction
   extends Function
{
   /**
    * The specific DataPacket needed for this function to operate
    */
   private DataPacket mData;
   /**
    * The request sent
    */
   private Request mCurrRequest;
   /**
    * The checkout sent
    */
   private Checkout mCurrCheckout;

   /**
    * Sets the DataPacket for a Login
    *
    * @param pPacket The DataPacket sent from the view
    */
   public void setPacket(DataPacket pPacket)
   {
      mData = pPacket;
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
    * Sets the checkout or request member variable depending on the
    * DataPacket sent
    */
   public void getData()
   {
      mCurrRequest = null;
      mCurrCheckout = null;
      RequestPacket reqPack;
      CheckoutPacket checkPack;

      if (RequestPacket.class.isAssignableFrom(mData.getClass()))
      {
         //If a RequestPacket was sent
         reqPack = (RequestPacket) mData;
         mCurrRequest = reqPack.getRequest();
      }
      else if (CheckoutPacket.class.isAssignableFrom(mData.getClass()))
      {
         checkPack = (CheckoutPacket) mData;
         mCurrCheckout = checkPack.getCheckout();
      }
   }

   /**
    * Cancels a request or individual checkout
    */
   public void performFunction()
   {
      // TODO: Remove if statements by extending class
      // and using Composite pattern

      getData();

      if (mCurrRequest != null)
      {
         // if a request was sent set it to inactive
         mCurrRequest.setActive(false);
         for (Checkout out : mCurrRequest.getCheckouts())
         {
            out.setActive(false);
         }
         mModel.setRequest(mCurrRequest, mControl.getCurrentUser().getID());
      }
      else if (mCurrCheckout != null)
      {
         // if a checkout was sent set it to inactive

         //don't cancel a checkout if they've picked it up already
         if (mCurrCheckout.getPickedupDate() != null)
         {
            mCurrCheckout.setActive(false);
            mModel.setCheckout(mCurrCheckout,
                               mControl.getCurrentUser().getID());
         }
      }
      else
      {
         System.out.println("Nothing to cancel");
      }
   }
}
