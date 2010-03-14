package assetl.system;

import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * An abstract class containing a JMenu
 * to switch between view functionality.
 *
 * @author Devin Doman
 */
public abstract class AssetView
        extends JFrame
        implements AssetLView
{
    /**
     * The Controller
     */
    protected AssetLControl mControl;

    /**
     * The current person
     */
    protected Person mPerson;

    /**
     * The current laptop
     */
    protected Asset mLaptop;

    /**
     * The desired start date
     */
    protected Date mStart;

    /**
     * The desired end date
     */
    protected Date mEnd;


    protected JMenuBar mBar;
    protected JMenu mMenu;
    protected JMenu mSub;
    protected JMenuItem mItem;

    /**
     * Constructor that takes the form title as a parameter
     *
     * @param pTitle The title to give the view
     */
    public AssetView(String pTitle)
    {
        super(pTitle);
        initMenu();
    }

    /**
     * Constructor that takes the view's controller as a parameter
     *
     * @param pControl The controller for this view
     */
    public AssetView(AssetLControl pControl)
    {
        this(pControl, "Asset Logger");
    }

    /**
     * Constructor that takes the form's title and controller as parameters
     *
     * @param pControl The controller for this view
     * @param pTitle The title to give the view
     */
    public AssetView(AssetLControl pControl, String pTitle)
    {
        super(pTitle);
        initMenu();
        mControl = pControl;
    }

    /**
     * Initialize a common menu for all subclasses
     */
    private void initMenu()
    {
        //menu bar
        mBar = new JMenuBar();
        mMenu = new JMenu("Type");
        mMenu.setMnemonic(KeyEvent.VK_A);
        mMenu.getAccessibleContext().setAccessibleDescription("The only menu");
        mBar.add(mMenu);

        mItem = new JMenuItem("Checkout", KeyEvent.VK_T);
        mItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        mItem.getAccessibleContext().setAccessibleDescription("None");
        mItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ev)
            {
                switchCheckout();
            }
        });
        mMenu.add(mItem);

        mItem = new JMenuItem("Checkin", KeyEvent.VK_A);
        mItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
        mItem.getAccessibleContext().setAccessibleDescription("None");
        mItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ev)
            {
                switchCheckin();
            }
        });
        mMenu.add(mItem);

        mItem = new JMenuItem("Schedule", KeyEvent.VK_A);
        mItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        mItem.getAccessibleContext().setAccessibleDescription("None");
        mItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ev)
            {
                switchSchedule();
            }
        });
        mMenu.add(mItem);

        mItem = new JMenuItem("Cancel", KeyEvent.VK_A);
        mItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        mItem.getAccessibleContext().setAccessibleDescription("None");
        mItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ev)
            {
                switchCancel();
            }
        });
        mMenu.add(mItem);
        
        this.setJMenuBar(mBar);
    }

    /**
     * Allow the Controller to show the View
     */
    public void showView()
    {
        setVisible(true);
    }

    /**
     * Allow the Controller to hide the View
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
     * Delagates to the controller a switch to checkout functionality
     */
    public void switchCheckout()
    {
        mControl.changeCheckout();
    }

    /**
     * Delagates to the controller a switch to checkin functionality
     */
    public void switchCheckin()
    {
        mControl.changeCheckin();
    }

    /**
     * Delagates to the controller a switch to schedule functionality
     */
    public void switchSchedule()
    {
        mControl.changeSchedule();
    }

    /**
     * Delagates to the controller a switch to cancel functionality
     */
    public void switchCancel()
    {
        mControl.changeCancel();
    }
}
