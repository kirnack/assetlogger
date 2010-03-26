package assetl.service;

import assetl.system.DataPacket;

/**
 * Locates data for person and loads it so the next view can
 * perform functions for that person.
 *
 * @author Devin Doman
 */
public class LoadPersonFunction
        extends Function
{
    /**
     * Sets the DataPacket for this function
     *
     * @param pPacket The DataPacket sent from the view
     */
    public void setPacket(DataPacket pPacket)
    {
        
    }
    
    /**
     * Gets the DataPacket currently set for this function
     *
     * @return The DataPacket
     */
    public DataPacket getPacket()
    {
        return null;
    }

    /**
     * Loads teacher data into the next view
     */
    public void performFunction()
    {
        mControl.setFunction("Schedule");
    }
}
