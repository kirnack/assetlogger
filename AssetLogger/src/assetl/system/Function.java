package assetl.system;

/**
 * Represents a functionality that can be performed by the controller by
 * manipulating the model.
 *
 * @author Devin Doman
 */
public abstract class Function
{
    /**
     * The model to perform the function on
     */
    protected AssetLModel mModel;

    /**
     * The data used to manipulate tha model
     */
    protected DataPacket mPacket;
  
    /**
     * Sets the model to perform functions on
     *
     * @param mModel The model to set
     */
    public void setModel(AssetLModel pModel)
    {
        mModel = pModel;
    }

    public void setPacket(DataPacket pPacket)
    {
        mPacket = pPacket;
    }

    public DataPacket getPacket()
    {
        return mPacket;
    }
    
    public abstract void performFunction();
}
