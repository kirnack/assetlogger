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
     * Allows the controller to set the controller for the view
     */
    void setControl(AssetLControl pControl);

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
     * Allows the controller to switch to a view that has the functionality
     * provided
     */
    void switchFunction(String pFunction);

    /**
     * Enables functionality passed in for this view.
     */
    void enable(String pFunction);

    /**
     * Grabs all pertainent data from the fields and sets them in
     * a DataPacket object
     */
    void grabDataPacket();

    /**
     * Prepopulates the fields with the data currently held
     * in the member variables or with those in the model.
     */
    void populateFields();
}
