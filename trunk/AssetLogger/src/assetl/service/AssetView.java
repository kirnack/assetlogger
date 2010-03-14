package assetl.service;

import javax.swing.JFrame;

import assetl.system.AssetLView;
import assetl.system.AssetLControl;

/**
 * A handwritten Swing gui.
 *
 * @author Gordon Hale
 */
public class AssetView
        extends JFrame
        implements AssetLView
{
    AssetLControl mControl;

    /**
     * Constructor for the user interface
     *
     * @param pControl The controller for this view
     */
    public AssetView(AssetLControl pControl)
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
     * Updates the views display of the model
     */
    public void updateData()
    {

    }
}
