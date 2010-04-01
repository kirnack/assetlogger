package assetl.desktop;

import assetl.system.DataPacket;
import assetl.system.StringPacket;
import assetl.system.PersonPacket;

/**
 * Generates a StringPacket for the ServiceView.
 *
 * @author Devin Doman
 */
public class ServiceStringGrabber
   extends ServiceDataGrabber
{
   /**
    * Default Constructor taking the current ServiceView as a parameter
    *
    * @param pServView The ServiceView to grab a DataPacket from
    */
   ServiceStringGrabber(ServiceView pServView)
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
      PersonPacket tempPacket = (PersonPacket) mServView.getDataPacket();
      return new StringPacket(tempPacket.getPerson().getID());
   }
}
