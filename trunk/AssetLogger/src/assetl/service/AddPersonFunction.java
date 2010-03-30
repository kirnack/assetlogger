package assetl.service;

import assetl.system.DataPacket;
import assetl.system.PersonPacket;

/**
 * Adds the Person to the database sent in the DataPacket
 *
 * @author Devin Doman
 */
public class AddPersonFunction
   extends Function
{
   /**
    * Sends the data needed to make a laptop asset
    */
   private PersonPacket mData;

   /**
    * Sets the DataPacket for this function
    *
    * @param pPacket The DataPacket sent from the view
    */
   public void setPacket(DataPacket pPacket)
   {
      mData = (PersonPacket) pPacket;
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
    * Adds the person to the model
    */
   public void performFunction()
   {
      mModel.setPerson(mData.getPerson());
      mControl.changeView("ID");
   }
}
