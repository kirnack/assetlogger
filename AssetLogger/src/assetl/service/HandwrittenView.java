package assetl.service;

import javax.swing.JFrame;

import assetl.system.AssetLView;
import assetl.system.AssetLControl;

/**
 * A handwritten Swing gui.
 *
 * @author Gordon Hale
 */
public class HandwrittenView
        extends JFrame
        implements AssetLView
{
    AssetLControl mControl;

    /**
     * Constructor for the user interface
     *
     * @param pControl The controller for this view
     */
    public HandwrittenView(AssetLControl pControl)
    {
        super("Asset Logger");
        mControl = pControl;
        initConponents();
    }

    /**
     * Initializes all the swing components for the gui
     */
    public void initConponents()
    {
    }

    /**
     * Shows this, the JFrame
     */
    public void showView()
    {
        setVisible(true);
    }

    /**
     * Hides the JFrame
     */
    public void hideView()
    {
        setVisible(false);
    }

    /**
     * Allow the Controller to close the View
     */
    public void closeView()
    {
        dispose();
    }

    /**
     * Updates the views display of the model
     */
    public void updateData()
    {

    }

    /**
     * Delagates to the controller a switch to checkout functionality
     */
    public void switchCheckout()
    {

    }

    /**
     * Delagates to the controller a switch to checkin functionality
     */
    public void switchCheckin()
    {

    }

    /**
     * Delagates to the controller a switch to schedule functionality
     */
    public void switchSchedule()
    {

    }

    /**
     * Delagates to the controller a switch to cancel functionality
     */
    public void switchCancel()
    {

    }

    /**
     * Lets the controller enable checkout function in view if applicable
     */
    public void enableCheckout()
    {

    }

    /**
     * Lets the controller enable checkout function in view if applicable
     */
    public void enableCheckin()
    {

    }

    /**
     * Lets the controller enable checkout function in view if applicable
     */
    public void enableSchedule()
    {

    }

    /**
     * Lets the controller enable checkout function in view if applicable
     */
    public void enableCancel()
    {

    }
}
