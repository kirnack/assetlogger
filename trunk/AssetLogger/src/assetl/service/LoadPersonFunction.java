package assetl.service;

import assetl.system.DataPacket;
import assetl.system.StringPacket;
import assetl.system.PersonPacket;
import assetl.system.Person;

/**
 * Locates data for person and loads it so the next view can
 * perform functions for that person.
 *
 * @author Devin Doman
 */
public class LoadPersonFunction
   extends Function
{
   /**
    * A packet to store a Person's identification
    */
   StringPacket mData;

   /**
    * Sets the DataPacket for this function
    *
    * @param pPacket The DataPacket sent from the view
    */
   public void setPacket(DataPacket pPacket)
   {
      mData = (StringPacket) pPacket;
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
    * Loads teacher data into the next view
    */
   public void performFunction()
   {
      Person tempPerson = mModel.getPerson(mData.getString());
      
      if (tempPerson != null)
      {
         //
         // TODO: find out why the view doesn't always 
         // load properly ever since this
         // if statement was added
         //

         mControl.setFunction("Schedule");
         mControl.getView().receiveDataPacket(new PersonPacket(tempPerson));
      }
      else
      {
         System.err.println("Person does not exist");
      }
   }
}
