package assetl.service;

import assetl.system.DataPacket;
import assetl.system.StringPacket;

/**
 * Changes to the FindAsset view and enables either
 * a schedule funtion or a checkout function.
 * 
 * @author Devin Doman
 */
public abstract class FindAssetFunction
   extends Function
{
   StringPacket mData;

   public void setPacket(DataPacket pPacket)
   {
      mData = (StringPacket) pPacket;
   }

   public DataPacket getPacket()
   {
      return mData;
   }

   public void performFunction()
   {
      mControl.changeView("FindAsset");
      mControl.sendViewPacket(mData);
   }
}
