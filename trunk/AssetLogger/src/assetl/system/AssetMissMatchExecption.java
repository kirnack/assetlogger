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
   /**
    * Constructor with parameter for error message
    *
    * @param s The message for this exception
    */
   public AssetMissMatchExecption(String s)
   {
      super(s);
   }

   /**
    * Default Constructor
    */
   public AssetMissMatchExecption()
   {
      super();
   }
}
