package assetl.system;

import javax.swing.AbstractButton;

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
     *
     * @param pFunction The function to switch to
     */
    void switchFunction(String pFunction);

    /**
     * Enables functionality passed in for this view.
     *
     * @param pFunction The function to enable
     */
    void enable(String pFunction);

    /**
     * Enables functionality on the AbstractButton passed in
     *
     * @param pFunction The function to enable
     * @param pItem The component to give functionality to
     */
    void enable(String pFunction, AbstractButton pItem);

    /**
     * Enables a component to listen for a request in functionality change.
     *
     * @param pFunction The function to switch for
     * @param pItem The component to add a listener for
     */
    void enableSwitch(String pFunction, AbstractButton pItem);

    /**
     * Allows the controller to turn on admin abilities in a view
     */
    void enableAdmin();

    /**
     * Allows the controller to turn off admin abilities in a view
     */
    void disableAdmin();

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
