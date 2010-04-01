package assetl.desktop;

import assetl.system.DataPacket;
import assetl.system.SchedulePacket;

/**
 * Generates a SchedulePacket for the ServiceView
 *
 * @author Devin Doman
 */
public class ServiceScheduleGrabber
   extends ServiceDataGrabber
{
   /**
    * Default Constructor taking the current ServiceView as a parameter
    *
    * @param pServView The ServiceView to grab a DataPacket from
    */
   ServiceScheduleGrabber(ServiceView pServView)
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
      SchedulePacket packet = new SchedulePacket();
      return packet;
   }

}