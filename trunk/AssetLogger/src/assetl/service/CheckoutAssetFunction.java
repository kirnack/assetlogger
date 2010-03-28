package assetl.service;

/**
 * Enables checkout in the FindAsset view
 * 
 * @author Devin Doman
 */
public class CheckoutAssetFunction
   extends LoadFinderFunction
{
   /**
    * Enables scheduling in the when finding an asset
    */
   @Override
   public void performFunction()
   {
      super.performFunction();
      mControl.enableFunction("Checkout");
   }
}
