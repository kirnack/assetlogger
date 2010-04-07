package assetl.service;

import assetl.system.DataPacket;
import assetl.system.StringPacket;

/**
 * Changes to the FindAsset view to either
 * a schedule funtion or a checkout function.
 * 
 * @author Devin Doman
 */
public abstract class LoadFinderFunction
   extends Function
{
   /**
    * Holds the String to send to the next view
    */
   StringPacket mData;

   /**
    * Sets the DataPacket for this function
    *
    * @param pPacket The DataPacket
    */
   public void setPacket(DataPacket pPacket)
   {
      mData = (StringPacket) pPacket;
   }

   /**
    * Returns the DataPacket
    *
    * @return The DataPacket
    */
   public DataPacket getPacket()
   {
      return mData;
   }

   /**
    * Changes to the view to find assets, sending the DataPacket
    */
   public void performFunction()
   {
      mControl.changeView("FindAsset");
      mControl.sendViewPacket(mData);
   }
}
