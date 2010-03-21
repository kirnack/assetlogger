package assetl.system;

import java.util.Date;

/**
 * Provides a common interface for the controller module in the 
 * Model-View-Controller structure. Defines AssetLogger behavior,
 * maps user actions to model updates.
 * 
 * @author Devin Doman
 */
public interface AssetLControl
{
    /**
     * Change the function for the controller to perform for the view
     * 
     * @param pFunction The name of the function to set
     */
    void setFunction(String pFunction);
    
    /**
     * Performs the function the controller is currently set to.
     * 
     * @param pPacket The data packet needed for the function to perform
     */
    void doFunction(DataPacket pPacket);

    /**
     * Performs the function indicated by the string passed in.
     *
     * @param pFunction The function to perform
     * @param pPacket The data packet needed for the function to perform
     */
    void doFunction(String pFunction, DataPacket pPacket);

    //
    // The following methods allow the controller to server as Mediator
    // between the View and Model
    //

    /**
     * Returns the Person represented by the ID given.
     *
     * @param pID The unique identification number for this person
     * @return The Person identified
     */
    Person getPerson(String pID);

    /**
     * Returns the Asset represented by the ID given
     *
     * @param pID The unique identification number for this asset
     * @return The Asset identified
     */
    Asset getAsset(String pID);
}
