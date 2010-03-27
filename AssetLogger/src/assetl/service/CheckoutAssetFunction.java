package assetl.service;

/**
 *
 * @author Devo
 */
public class CheckoutAssetFunction
   extends FindAssetFunction
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
