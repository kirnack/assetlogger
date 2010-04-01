package assetl.desktop;

import assetl.system.DataPacket;
import assetl.system.SchedulePacket;
import assetl.system.PersonPacket;
import assetl.system.Request;
import assetl.system.Checkout;
import assetl.system.Asset;

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
      PersonPacket tempPacket = (PersonPacket) mServView.getDataPacket();

      packet.setPersonID(tempPacket.getPerson().getID());
      Request selected = (Request) mServView.getScheduledList().getSelectedValue();

      for (Checkout checkout : selected.getCheckouts())
      {
         Asset asset = checkout.getAsset();
         packet.addAssetID(asset.getID());
         if (packet.getEnd() == null)
         {
            packet.setEnd(checkout.getRequestedEndDate());
         }
      }
      packet.setStart(selected.getRequestedPickup());
      return packet;
   }
}
