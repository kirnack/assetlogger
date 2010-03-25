package assetl.system;

/**
 * Allows the view to transmit data needed for the controller to get and
 * use an asset.
 *
 * @author Devin Doman
 */
public class AssetPacket
        implements DataPacket
{
    String mAssetID;

    public AssetPacket()
    {
        this(null);
    }

    public AssetPacket(String pAssetID)
    {
        mAssetID = pAssetID;
    }

    public void setAssetID(String pAssetID)
    {
        mAssetID = pAssetID;
    }

    public String getAssetID()
    {
        return mAssetID;
    }
}
