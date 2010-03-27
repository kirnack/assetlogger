package assetl.desktop;

import assetl.system.AssetLView;
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
    * Builds the map associations for the view choices the
    * controller will make
    */
   protected void constructMap()
   {
      // Clear the map then rebuild
      mHashViews.clear();

      AssetLView tempView = new ServiceView(this);
      addView("Schedule", tempView);
      addView("Checkout", tempView);
      addView("Cancel", tempView);

      tempView = new LoginView(this);
      addView("LogIn", tempView);

      tempView = new IDView(this);
      addView("Checkin", tempView);
      addView("LoadPerson", tempView);

      tempView = new FindAssetView(this);
      addView("FindAsset", tempView);

      tempView = new SureView(this);
      addView("Sure", tempView);
   }
}
