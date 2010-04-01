package assetl.desktop;

import assetl.system.DataPacket;

/**
 * Grabs the DataPackets for the ServiceView
 *
 * @author Devin Doman
 */
public abstract class ServiceDataGrabber
   implements PacketGenerator
{
   ServiceView mServView;

   /**
    * Default Constructor taking the current ServiceView as a parameter
    *
    * @param pServView The ServiceView to grab a DataPacket from
    */
   ServiceDataGrabber(ServiceView pServView)
   {
      mServView = pServView;
   }

   /**
    * Generates a DataPacket from the ServiceView and returns that packet
    *
    * @return The DataPacket generated
    */
   public abstract DataPacket grab();
}
