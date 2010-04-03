package assetl.system;

/**
 * A packet to send a single asset object
 *
 * @author Devin Doman
 */
public class AssetPacket
   implements DataPacket
{
   /**
    * The Asset to send in the packet
    */
   private Asset mAsset;

   /**
    * Default Constructor
    */
   public AssetPacket()
   {
      this(null);
   }

   /**
    * Constructor with parameters
    *
    * @param pAsset The Asset to send
    */
   public AssetPacket(Asset pAsset)
   {
      mAsset = pAsset;
   }

   /**
    * Sets a Asset
    *
    * @param pAsset The Asset to send
    */
   public void setAsset(Asset pAsset)
   {
      mAsset = pAsset;
   }

   /**
    * Gets a Asset
    *
    * @return The Asset
    */
   public Asset getAsset()
   {
      return mAsset;
   }
}
