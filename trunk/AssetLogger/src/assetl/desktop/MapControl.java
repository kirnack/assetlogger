package assetl.desktop;

import assetl.service.DatabaseControl;
import assetl.system.AssetLModel;

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
    * Default Constructor
    */
   public MapControl(AssetLModel pModel)
   {
      super(pModel);
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
