package assetl.desktop;

import assetl.service.DatabaseControl;

/**
 * A desktop level version of the controller that is able to choose
 * views in the desktop package.
 *
 * @author Devin Doman
 */
public class MapControl
   extends DatabaseControl
{
   /**
    * Default Constructor
    */
   public MapControl()
   {
      super();
   }

   /**
    * Sets the package for the class name needed to dynamically load the class
    * @param pPackage
    */
   protected void setViewPackage()
   {
      setClassPackage("assetl.desktop.");
   }

   /**
    * Appends a string to the end of the class name to dynamically load.
    *
    * @param mPostClass The string to append to the end of the class name
    */
   protected void setPostViewName()
   {
      setPostClassName("View");
   }
}
