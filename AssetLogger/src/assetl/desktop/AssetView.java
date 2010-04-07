package assetl.desktop;

import assetl.system.AssetLControl;
import assetl.system.AssetLView;
import assetl.system.DataPacket;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.AbstractButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * An abstract class containing a JMenu
 * common to all view subclasses
 *
 * @author Devin Doman
 */
public abstract class AssetView
   extends JFrame
   implements AssetLView,
   Runnable
{
   /**
    * The Controller
    */
   protected AssetLControl mControl;
   /**
    * The data packet that can be sent to the controller
    */
   protected DataPacket mPacket;
   /**
    * The Menu Bar common to all views extending the class
    */
   private JMenuBar mMenuBar;
   /**
    * The View menu
    */
   private JMenu mViewMenu;
   /**
    * A menu item that returns to the main screen
    */
   private JMenuItem mScreenMenuItem;
   /**
    * A menu item that goes to a view that can check in an asset
    */
   private JMenuItem mCheckinMenuItem;
   /**
    * A menu item that goes to a view that can add an asset to the model
    */
   private JMenuItem mAddAssetMenuItem;
   /**
    * A menu item that goes to a view that can add a person to the model
    */
   private JMenuItem mAddPersonMenuItem;
   /**
    * A menu item that goes to a view that can search for past borrowers
    * of an asset
    */
   private JMenuItem mSearchMenuItem;
   /**
    * A menu item that goes to a view that can display a history of
    * assets borrowed by a person
    */
   private JMenuItem mHistoryMenuItem;
   /**
    * The User menu
    */
   private JMenu mUserMenu;
   /**
    * Logs the user out of the application
    */
   private JMenuItem mLogoutMenuItem;

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
      initComponents();
      mControl = pControl;

      try
      {
         UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
      }
      catch (Exception e)
      {

      }
      //
      // add action listeners to the menu items
      //

      enable("AddAsset", mAddAssetMenuItem, "Switch");
      enable("AddPerson", mAddPersonMenuItem, "Switch");
      enable("Login", mLogoutMenuItem, "Switch");
      enable("ID", "LoadPerson", mScreenMenuItem);
      enable("ID", "Checkin", mCheckinMenuItem);
      enable("Search", "SearchBorrowers", mSearchMenuItem);
      enable("Search", "SearchAssetHistory", mHistoryMenuItem);
   }

   /**
    * Initialized components that will be common for all subclasses
    */
   private void initComponents()
   {
      mMenuBar = new JMenuBar();
      mViewMenu = new JMenu();
      mScreenMenuItem = new JMenuItem();
      mCheckinMenuItem = new JMenuItem();
      mAddAssetMenuItem = new JMenuItem();
      mAddPersonMenuItem = new JMenuItem();
      mSearchMenuItem = new JMenuItem();
      mHistoryMenuItem = new JMenuItem();
      mUserMenu = new JMenu();
      mLogoutMenuItem = new JMenuItem();

      setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

      //Set up the View Menu
      mViewMenu.setText("View");
      mScreenMenuItem.setText("Main screen");
      mViewMenu.add(mScreenMenuItem);
      mCheckinMenuItem.setText("Checkin");
      mViewMenu.add(mCheckinMenuItem);
      mAddAssetMenuItem.setText("Add Asset");
      mViewMenu.add(mAddAssetMenuItem);
      mAddPersonMenuItem.setText("Add Person");
      mViewMenu.add(mAddPersonMenuItem);
      mSearchMenuItem.setText("Search Borrowers");
      mViewMenu.add(mSearchMenuItem);
      mHistoryMenuItem.setText("Search Asset History");
      mViewMenu.add(mHistoryMenuItem);
      mMenuBar.add(mViewMenu);

      //Set up the User Menu
      mUserMenu.setText("User");
      mLogoutMenuItem.setText("Log Out");
      mUserMenu.add(mLogoutMenuItem);
      mMenuBar.add(mUserMenu);

      //Set the JMenuBar in the frame
      setJMenuBar(mMenuBar);
   }

   /**
    * Allows control of the visibility of Admin components in the view
    *
    * @param pIsAdmin True if the admin components are to be set to visible
    */
   public void setAdminComponents(boolean pIsAdmin)
   {
      mAddAssetMenuItem.setVisible(pIsAdmin);
      mAddPersonMenuItem.setVisible(pIsAdmin);
      mSearchMenuItem.setVisible(pIsAdmin);
      mCheckinMenuItem.setVisible(pIsAdmin);
   }

   /**
    * Sets the label on the button passed in
    *
    * @param pLabel The label to give the button
    * @param pItem The button to change the label for
    */
   public void setLabel(String pLabel, AbstractButton pItem)
   {
      pItem.setText(pLabel);
   }

   /**
    * Allows the controller to set the controller for the view
    *
    * @param pControl The controller to set
    */
   public void setControl(AssetLControl pControl)
   {
      mControl = pControl;
   }

   /**
    * Allow the Controller to show the View
    */
   public void showView()
   {
      run();
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
    * Shows the menu bar
    */
   public void showMenuBar()
   {
      mMenuBar.setVisible(true);
   }

   /**
    * Hides the menu bar
    */
   public void hideMenuBar()
   {
      mMenuBar.setVisible(false);
   }

   /**
    * Allows the view to receive a DataPacket from the controller
    *
    * @param pPacket The DataPacket to receive
    */
   public void receiveDataPacket(DataPacket pPacket)
   {
      mPacket = pPacket;
      updateData();
   }

   /**
    * Prepopulates the fields with the data currently held
    * in the member variables or with those in the model.
    * To enable this subclasses must override.
    * @throws UnsupportedOperationException
    */
   public void populateFields()
      throws UnsupportedOperationException
   {
      throw new UnsupportedOperationException("Prepopulate not supported");
   }

   /**
    * Enables functionality passed in for this view.
    */
   public abstract void enable(String pFunction);

   /**
    * Enables ability to switch to a view with the given function enabled.
    *
    * @param pView The view to switch to
    * @param pFunction The function to enable
    * @param pItem The button to perform the switch
    */
   public void enable(String pView, String pFunction, AbstractButton pItem)
   {
      // TODO: change to remove switch listeners

      //remove all previous action listeners
      removeActionListeners(pItem);

      //Enable view switching, with a function enabled
      pItem.addActionListener(new SwitchListener(pView, pFunction));
   }

   /**
    * Attaches a listener to the button given that performs the function given.
    * If the ActionListener indicated is a switch listener pFunction will
    * be the name of the view to switch to.
    *
    * @param pFunction The function to perform or name of view for listener
    *                  to switch to.
    * @param pItem     The button to perform the action
    * @param pListener The listener for an action
    */
   public void enable(String pFunction, AbstractButton pItem, String pListener)
   {
      //remove all previous action listeners
      removeActionListeners(pItem);

      ActionListener listen = new SwitchListener(pFunction);
      if ("Function".equals(pListener))
      {
         listen = new FunctionListener(pFunction);
      }

      //add the action listener indicated
      pItem.addActionListener(listen);
   }

   /**
    * Removes all event listeners from a button
    *
    * @param pButton The button to remove listeners from
    */
   public void removeActionListeners(AbstractButton pButton)
   {
      //
      // Get all listeners and then remove each one
      //

      ActionListener[] actions = pButton.getActionListeners();

      for (ActionListener listen : actions)
      {
         pButton.removeActionListener(listen);
      }
   }

   /**
    * Listens for when the user needs to change the view
    */
   public class SwitchListener
      implements ActionListener
   {
      /**
       * The view for the controller to switch to
       */
      String mView;
      /**
       * The function to enable in this view
       */
      String mFunction;

      /**
       * Sets the view to switch to
       *
       * @param pView The view to switch to
       */
      public SwitchListener(String pView)
      {
         mView = pView;
         mFunction = null;
      }

      /**
       * Sets the view to switch to and the function for the view
       * to start performing
       *
       * @param pView The view to switch to
       * @param pFunction The function to enable in the view
       */
      public SwitchListener(String pView, String pFunction)
      {
         mView = pView;
         mFunction = pFunction;
      }

      /**
       * Has the controller modify or switch to the view given.
       *
       * @param ev The causing action
       */
      public void actionPerformed(ActionEvent ev)
      {
         if (mView != null)
         {
            mControl.changeView(mView);
         }
         if (mFunction != null)
         {
            mControl.enableFunction(mFunction);
         }
      }
   }

   /**
    * Listens for when the user pushed the button with the given function.
    * It then sends a DataPacket to the controller and delegates to the
    * controller perform the specified function.
    */
   public class FunctionListener
      implements ActionListener
   {
      /**
       * The function this button is to perform
       */
      String mFunction;

      /**
       * Sets the function for this button
       *
       * @param pFunction The function to set the button with
       */
      public FunctionListener(String pFunction)
      {
         mFunction = pFunction;
      }

      /**
       * Has controller change the functionality to perform, sends
       * the controller a DataPacket, then delegates to the controller
       * to perform the function.
       *
       * @param ev The causing action
       */
      public void actionPerformed(ActionEvent ev)
      {
         mControl.doFunction(mFunction);
         updateData();
      }
   }
}
