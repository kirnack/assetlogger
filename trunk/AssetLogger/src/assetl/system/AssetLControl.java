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
     * Cancels a request for the asset indicated to the person indicated.
     *
     * @param pPerson The person to cancel the request for
     * @param pAsset The asset to cancel
     */
    void cancel(Person pPerson, Asset pAsset);

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
}
