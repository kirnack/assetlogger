package assetl.service;

/**
 * Enables scheduling when in a view to find an asset with.
 * 
 * @author Devin Doman
 */
public class ScheduleAssetFunction
   extends LoadFinderFunction
{
   /**
    * Enables scheduling when finding an asset
    */
   @Override
   public void performFunction()
   {
      super.performFunction();
      mControl.enableFunction("Schedule");
   }
}
