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
   
   public AssetCollectionPacket()
   {
      this(null);
   }

   public AssetCollectionPacket(Collection<Asset> pAssets)
   {
      mAssets = pAssets;
   }

   public void addAsset(Asset pAsset)
   {
      mAssets.add(pAsset);
   }

   public void setAssets(Collection<Asset> pAssets)
   {
      mAssets = pAssets;
   }

   public Collection<Asset> getAssets()
   {
      return mAssets;
   }
}
