package assetl.service;

import assetl.system.DataPacket;
import assetl.system.RequestPacket;
import assetl.system.PersonPacket;

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
   RequestPacket mData;

   /**
    * Sets the DataPacket for this function
    *
    * @param pPacket The DataPacket sent from the view
    */
   public void setPacket(DataPacket pPacket)
   {
      mData = (RequestPacket) pPacket;
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
    * An idea of a function that makes sure that a request doesn't already exist,
    * and if an asset needs to be changed the user can be prompted to select
    * another one by switching to the FindAssetView and resending the request.
    */
   public void validateRequest()
   {
      
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
      validateRequest();

      //send the request to the model
      mModel.setRequest(mData.getRequest(), mControl.getCurrentUser().getID());

      // TODO: find a better way to do this: separate view changing from
      // functions more, perhaps a keyed singleton that needs person
      // set only once

      mControl.changeView("Service");
      mControl.sendViewPacket(
         new PersonPacket(mData.getRequest().getRequestor()));
   }
}
