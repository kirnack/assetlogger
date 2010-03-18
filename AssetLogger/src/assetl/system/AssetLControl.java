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
     * Takes the objects passed from the view and schedules an asset
     * to the person for the indicated time
     *
     * @param pPerson The person scheduling the asset
     * @param pAsset The asset to schedule
     * @param pStart The date to pick up the asset
     * @param pEnd The date to return the asset
     */
    void schedule(Person pPerson, Asset pAsset, Date pStart, Date pEnd);

    /**
     * Takes the objects passed from the view and checks out an asset
     * to the person until the date indicated.
     *
     * @param pPerson The person checking out the asset
     * @param pAsset The asset to check out
     * @param pEnd The date to return the asset
     */
    void checkout(Person pPerson, Asset pAsset, Date pEnd);

    /**
     * Checks the given asset back in.
     *
     * @param pAsset The asset to check back in
     */
    void checkin(Asset pAsset);

    /**
     * Sets the request to invalid, canceling all assets in the checkout
     * collection.
     *
     * @param pRequest The request to cancel
     */
    void cancel(Request pRequest);

    /**
     * Sets the checkout to invalid, canceling a single asset.
     *
     * @param pCheckout The checkout to cancel
     */
    void cancel(Checkout pCheckout);

    /**
     * Changes the view to interface with checkout functionality
     */
    void changeCheckout();

    /**
     * Changes the view to interface with checkin functionality
     */
    void changeCheckin();

    /**
     * Changes the view to interface with schedule functionality
     */
    void changeSchedule();

    /**
     * Changes the view to interface with cancel functionality
     */
    void changeCancel();

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
