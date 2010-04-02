package assetl.desktop;

import assetl.system.DataPacket;
import assetl.system.RequestPacket;
import assetl.system.Request;

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
      //Send the request selected in the scheduled list
      Request req = (Request) mServView.getScheduledList().getSelectedValue();
      return new RequestPacket(req);
   }
}
