package assetl.desktop;

/**
 * Grabs the DataPackets for the ServiceView
 *
 * @author Devin Doman
 */
public abstract class ServiceDataGrabber
   implements PacketGenerator
{
   /**
    * The ServiceView to grab the packet from
    */
   protected ServiceView mServView;

   /**
    * Default Constructor taking the current ServiceView as a parameter
    *
    * @param pServView The ServiceView to grab a DataPacket from
    */
   public ServiceDataGrabber(ServiceView pServView)
   {
      mServView = pServView;
   }
}
