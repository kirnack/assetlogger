package assetl.system;

import java.util.Collection;

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
     * Grabs the data packet and performs the last loaded function
     */
    public void doFunction();

    /**
     * Finds the function in the collection, loads it, then performs
     * the function
     *
     * @param pFunction The function to perform
     */
    void doFunction(String pFunction);

    //
    // The following methods allow the controller to server as Mediator
    // between the View and Model
    //

    /**
     * Returns the User represented by the ID given.
     *
     * @param pID The id of the user
     * @return The User identified
     */
    User getUser(String pID);

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

    /**
     * Gets from the model the outstanding requests based on a Person.
     * The oustanding requests will not have a picked up date set yet for
     * its checkout collection and will not be past the requested pick up date.
     *
     * @param pPerson The person to get the requests for
     * @return The outstanding requests
     */
    Collection<Request> getOutstandingRequests(Person pPerson);
}
