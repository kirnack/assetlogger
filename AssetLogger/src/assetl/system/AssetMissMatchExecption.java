package assetl.system;

/**
 * Denotes trying to make a Asset Sub class from an asset that is not of
 * the same sub class.
 *
 * @author Bryon Rogers
 */
public class AssetMissMatchExecption
   extends ClassCastException
{

   public AssetMissMatchExecption(String s)
   {
      super(s);
   }

   public AssetMissMatchExecption()
   {
      super();
   }

}
