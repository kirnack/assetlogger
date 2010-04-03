package assetl.desktop;

import assetl.system.DataPacket;

/**
 * Generates a DataPacket for a view
 * @author Devin Doman
 */
public interface PacketGenerator
{
   /**
    * Generates a DataPacket from the view and returns that packet
    *
    * @return The DataPacket generated
    */
   DataPacket grab();
}

