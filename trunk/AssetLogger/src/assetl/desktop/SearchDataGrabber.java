package assetl.desktop;

/**
 * Grabs DataPackets for the SearchView
 * 
 * @author Devin Doman
 */
public abstract class SearchDataGrabber
   implements PacketGenerator
{
   /**
    * The SearchView to grab the packet from
    */
   protected SearchView mSearchView;


   /**
    * Constructor taking the SearchView as a parameter
    *
    * @param pSearchView The SearchView to get the packet from
    */
   public SearchDataGrabber(SearchView pSearchView)
   {
      mSearchView = pSearchView;
   }
}
