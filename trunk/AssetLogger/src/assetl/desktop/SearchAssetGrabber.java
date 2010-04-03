package assetl.desktop;

import assetl.system.DataPacket;
import assetl.system.AssetPacket;

/**
 * Creates an AssetPacket for the SearchView to send to the controller
 * @author Devin Doman
 */
public class SearchAssetGrabber
   extends SearchDataGrabber
{
   /**
    * Constructor taking the SearchView as a parameter
    *
    * @param pSearchView The SearchView to get the packet from
    */
   public SearchAssetGrabber(SearchView pSearchView)
   {
      super(pSearchView);
   }

   /**
    * Generates a DataPacket from the view and returns that packet
    *
    * @return The DataPacket generated
    */
   public DataPacket grab()
   {
      return new AssetPacket();
   }
}
