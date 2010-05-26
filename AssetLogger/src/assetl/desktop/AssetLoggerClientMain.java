package assetl.desktop;

import assetl.service.HttpRQ;

/**
 * The entry point for the application.
 *
 * @author Devin Doman
 * @author Bryon Rogers
 * @author Mike Hale
 */
public class AssetLoggerClientMain
{
   /**
    * The main method for this application.
    * Starts a new instance of a Controller thread.
    *
    * @param args Command line arguments
    */
   public static void main(String[] args)
   {
      
      new Thread(new MapControl(HttpRQ.getInstance())).start();
   }
}
