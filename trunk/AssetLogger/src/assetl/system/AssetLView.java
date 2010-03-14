package assetl.system;

/**
 * Provides a common interface for the view module in the
 * Model-View-Controller structure. Provides user-interface,
 * pulls data from the model and renders it, sends user gestures
 * to the controller.
 * 
 * @author Mike Hale
 */
public interface AssetLView
{
    /**
     * Allows the controller to show the view
     */
    void showView();

    /**
     * Allows the controller to hide the view
     */
    void hideView();

    /**
     * Allows the controller to close the view
     */
    void closeView();

    /**
     * Allows the controller to update the data displayed in the view
     */
    void updateData();

    /**
     * Delagates to the controller a switch to checkout functionality
     */
    void switchCheckout();

    /**
     * Delagates to the controller a switch to checkin functionality
     */
    void switchCheckin();

    /**
     * Delagates to the controller a switch to schedule functionality
     */
    void switchSchedule();

    /**
     * Delagates to the controller a switch to cancel functionality
     */
    void switchCancel();

    /**
     * Lets the controller enable checkout function in view if applicable
     */
    void enableCheckout();

    /**
     * Lets the controller enable checkout function in view if applicable
     */
    void enableCheckin();

    /**
     * Lets the controller enable checkout function in view if applicable
     */
    void enableSchedule();

    /**
     * Lets the controller enable checkout function in view if applicable
     */
    void enableCancel();
}
