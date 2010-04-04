/*
 * AssetView.java
 *
 * Created on Mar 14, 2010, 4:49:04 PM
 */
package assetl.desktop;

import resources.ObjectLoader;
import assetl.system.AssetLControl;
import assetl.system.AssetLView;
import assetl.system.DataPacket;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.AbstractButton;

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
    * In place for dynamic class loading. Assumes the controller
    * will immediately be set by the controller that dynamically loaded
    * the view
    */
   public AssetView()
   {
      this(null, "Asset Logger");
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
      initComponents();
      mControl = pControl;

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

      // TODO: enable and show the back button
      mBackBtn.setVisible(false);
   }

   /** This method is called from within the constructor to
    * initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is
    * always regenerated by the Form Editor.
    */
   @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mMenuBar = new javax.swing.JMenuBar();
        mBackBtn = new javax.swing.JMenu();
        mViewMenu = new javax.swing.JMenu();
        mScreenMenuItem = new javax.swing.JMenuItem();
        mCheckinMenuItem = new javax.swing.JMenuItem();
        mAddAssetMenuItem = new javax.swing.JMenuItem();
        mAddPersonMenuItem = new javax.swing.JMenuItem();
        mSearchMenuItem = new javax.swing.JMenuItem();
        mHistoryMenuItem = new javax.swing.JMenuItem();
        mUserMenu = new javax.swing.JMenu();
        mLogoutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mMenuBar.setName("mMenuBar"); // NOI18N

        mBackBtn.setForeground(new java.awt.Color(0, 0, 204));
        mBackBtn.setText("Back");
        mBackBtn.setName("mBackBtn"); // NOI18N
        mMenuBar.add(mBackBtn);

        mViewMenu.setText("View");
        mViewMenu.setName("mViewMenu"); // NOI18N

        mScreenMenuItem.setText("Main screen");
        mScreenMenuItem.setName("mScreenMenuItem"); // NOI18N
        mViewMenu.add(mScreenMenuItem);

        mCheckinMenuItem.setText("Checkin");
        mCheckinMenuItem.setName("mCheckinMenuItem"); // NOI18N
        mViewMenu.add(mCheckinMenuItem);

        mAddAssetMenuItem.setText("Add Asset");
        mAddAssetMenuItem.setName("mAddAssetMenuItem"); // NOI18N
        mViewMenu.add(mAddAssetMenuItem);

        mAddPersonMenuItem.setText("Add Person");
        mAddPersonMenuItem.setName("mAddPersonMenuItem"); // NOI18N
        mViewMenu.add(mAddPersonMenuItem);

        mSearchMenuItem.setText("Search Borrowers");
        mSearchMenuItem.setName("mSearchMenuItem"); // NOI18N
        mViewMenu.add(mSearchMenuItem);

        mHistoryMenuItem.setText("Search Asset History");
        mHistoryMenuItem.setName("mHistoryMenuItem"); // NOI18N
        mViewMenu.add(mHistoryMenuItem);

        mMenuBar.add(mViewMenu);

        mUserMenu.setText("User");
        mUserMenu.setName("mUserMenu"); // NOI18N

        mLogoutMenuItem.setText("Log Out");
        mLogoutMenuItem.setName("mLogoutMenuItem"); // NOI18N
        mUserMenu.add(mLogoutMenuItem);

        mMenuBar.add(mUserMenu);

        setJMenuBar(mMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 283, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem mAddAssetMenuItem;
    private javax.swing.JMenuItem mAddPersonMenuItem;
    private javax.swing.JMenu mBackBtn;
    private javax.swing.JMenuItem mCheckinMenuItem;
    private javax.swing.JMenuItem mHistoryMenuItem;
    private javax.swing.JMenuItem mLogoutMenuItem;
    private javax.swing.JMenuBar mMenuBar;
    private javax.swing.JMenuItem mScreenMenuItem;
    private javax.swing.JMenuItem mSearchMenuItem;
    private javax.swing.JMenu mUserMenu;
    private javax.swing.JMenu mViewMenu;
    // End of variables declaration//GEN-END:variables

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
    * Allows control of the visibility of Admin components in the view
    *
    * @param pIsAdmin True if the admin components are to be set to visible
    */
   public void setAdminComponents(boolean pIsAdmin)
   {
      mAddAssetMenuItem.setVisible(pIsAdmin);
      mAddPersonMenuItem.setVisible(pIsAdmin);
      mSearchMenuItem.setVisible(pIsAdmin);
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

      //
      // Load the listener with pFunction as a
      // parameter to the dynamic constructor
      //

      /* Ask brother neff how to get dynamic constructing with parameters
       * working
      pListener += "Listener";
      pListener = "assetl.desktop.AssetView$" + pListener;
      ActionListener listen = (ActionListener) ObjectLoader.loadObj(pListener,
      pFunction);

      pListener += "Listener";
      pListener = "assetl.desktop.AssetView$" + pListener;
      ActionListener listen = (ActionListener) ObjectLoader.loadObj(pListener);
       */

      // KLUGE: would like to use dynamic class loading as soon as
      // some unanswered questions on how to do it are resolved


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
