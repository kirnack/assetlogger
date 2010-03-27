package assetl.service;

/**
 *
 * @author Devin Doman
 */
public class ScheduleAssetFunction
   extends FindAssetFunction
{
   /**
    * Enables scheduling in the when finding an asset
    */
   @Override
   public void performFunction()
   {
      super.performFunction();
      mControl.enableFunction("Schedule");
   }
}
