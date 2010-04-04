package assetl.system;

import java.util.Collection;

/**
 * A DataPacket to represent a collection of assets
 *
 * @author Devin Doman
 */
public class AssetCollectionPacket 
   implements DataPacket
{
   /**
    * The assets to be sent
    */
   Collection<Asset> mAssets;
   
   /**
    * Default Constructor
    */
   public AssetCollectionPacket()
   {
      this(null);
   }

   /**
    * Constructor taking a collection of assets as a parameter
    *
    * @param pAssets The collection of assets to send
    */
   public AssetCollectionPacket(Collection<Asset> pAssets)
   {
      mAssets = pAssets;
   }

   /**
    * Adds a single asset to the collection being sent in the packet
    *
    * @param pAsset The asset to add
    */
   public void addAsset(Asset pAsset)
   {
      mAssets.add(pAsset);
   }

   /**
    * Sets the collection of asssets to be sent
    *
    * @param pAssets The collection of assets to send
    */
   public void setAssets(Collection<Asset> pAssets)
   {
      mAssets = pAssets;
   }

   /**
    * Returns the collection of assets
    *
    * @return The collection of assets
    */
   public Collection<Asset> getAssets()
   {
      return mAssets;
   }
}
