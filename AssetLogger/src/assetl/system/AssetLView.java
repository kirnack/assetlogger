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
    public void showView();

    /**
     * Allows the controller to hide the view
     */
    public void hideView();
}
