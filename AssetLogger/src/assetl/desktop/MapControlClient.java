package assetl.desktop;

import assetl.service.DatabaseControlClient;

/**
 * A desktop level version of the controller that is able to choose
 * views in the desktop package.
 *
 * @author Devin Doman
 */
public class MapControlClient
   extends DatabaseControlClient
{
   /**
    * Default Constructor
    */
   public MapControlClient()
   {
      super();
   }

   /**
    * Sets the package for the class name needed to dynamically load the class
    */
   protected void setViewPackage()
   {
      setClassPackage("assetl.desktop.");
   }

   /**
    * Appends a string to the end of the class name to dynamically load.
    *
    */
   protected void setPostViewName()
   {
      setPostClassName("View");
   }
}
