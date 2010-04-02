package assetl.desktop;

import assetl.system.DataPacket;
import assetl.system.RequestPacket;

/**
 * Gets a RequestPacket from the ServiceView
 *
 * @author Devin Doman
 */
public class ServiceRequestGrabber
   extends ServiceDataGrabber
{
   /**
    * Default Constructor taking the current ServiceView as a parameter
    *
    * @param pServView The ServiceView to grab a DataPacket from
    */
   ServiceRequestGrabber(ServiceView pServView)
   {
      super(pServView);
   }

   /**
    * Generates a DataPacket from the ServiceView and returns that packet
    *
    * @return The DataPacket generated
    */
   public DataPacket grab()
   {
      RequestPacket packet = new RequestPacket();

      return packet;
   }
}
