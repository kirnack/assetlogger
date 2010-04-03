package assetl.service;

import java.util.Date;
import assetl.system.Checkout;

/**
 * Peforms all the same functions as a schedule except that the pickup
 * and start date is today.
 * 
 * @author Devin Doman
 */
public class CheckoutFunction
   extends ScheduleFunction
{
   /**
    * Stamps each checkout in the Request with today as the pickedup date
    */
   @Override
   public void performFunction()
   {
      for (Checkout out : mData.getRequest().getCheckouts())
      {
         // Set today as the picked up data for all checkouts
         out.setPickedupDate(new Date());
      }

      super.performFunction();
   }
}
