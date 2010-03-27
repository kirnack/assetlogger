/*
 * AssetView.java
 *
 * Created on Mar 14, 2010, 4:49:04 PM
 */
package assetl.desktop;

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

      enableSwitch("Checkout", mChkOutMenuItem);
      enableSwitch("Checkin", mChkInMenuItem);
      enableSwitch("Schedule", mScheduleMenuItem);
      enableSwitch("FindAsset", mCancelMenuItem);
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
      mChkOutMenuItem = new javax.swing.JMenuItem();
      mChkInMenuItem = new javax.swing.JMenuItem();
      mScheduleMenuItem = new javax.swing.JMenuItem();
      mCancelMenuItem = new javax.swing.JMenuItem();
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

      mChkOutMenuItem.setText("Checkout");
      mChkOutMenuItem.setName("mChkOutMenuItem"); // NOI18N
      mViewMenu.add(mChkOutMenuItem);

      mChkInMenuItem.setText("Checkin");
      mChkInMenuItem.setName("mChkInMenuItem"); // NOI18N
      mViewMenu.add(mChkInMenuItem);

      mScheduleMenuItem.setText("Schedule");
      mScheduleMenuItem.setName("mScheduleMenuItem"); // NOI18N
      mViewMenu.add(mScheduleMenuItem);

      mCancelMenuItem.setText("Cancel");
      mCancelMenuItem.setName("mCancelMenuItem"); // NOI18N
      mViewMenu.add(mCancelMenuItem);

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
   private javax.swing.JMenu mBackBtn;
   private javax.swing.JMenuItem mCancelMenuItem;
   private javax.swing.JMenuItem mChkInMenuItem;
   private javax.swing.JMenuItem mChkOutMenuItem;
   private javax.swing.JMenuItem mLogoutMenuItem;
   private javax.swing.JMenuBar mMenuBar;
   private javax.swing.JMenuItem mScheduleMenuItem;
   private javax.swing.JMenu mUserMenu;
   private javax.swing.JMenu mViewMenu;
   // End of variables declaration//GEN-END:variables

   /**
    * Allows the controller to set the controller for the view
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
      // TODO: include the components that need to change for an admin

      mViewMenu.setVisible(pIsAdmin);
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
    * Allows the controller to switch to a view that has the functionality
    * provided
    */
   public void switchFunction(String pFunction)
   {
      mControl.setFunction(pFunction);
   }

   /**
    * Enables functionality passed in for this view.
    */
   public abstract void enable(String pFunction);

   /**
    * Sets the title
    * @param pLabels
    */
   public void setLabels(String pLabels)
   {
   }

   /**
    * Enables functionality on the AbstractButton passed in
    *
    * @param pFunction The function to enable
    * @param pItem The component to give functionality to
    */
   public void enable(String pFunction, AbstractButton pItem)
   {
      enable(pFunction, pItem, pFunction);
   }

   /**
    * Enables functionality on the AbstractButton passed in
    *
    * @param pFunction The function to enable
    * @param pItem The component to give functionality to
    * @param pLabel The label for the button performing the function
    */
   public void enable(String pFunction, AbstractButton pItem, String pLabel)
   {
      // TODO: make sure the function passed in is supported in this view
      // and make the labels of the button and title pretty

      setTitle(pLabel);
      pItem.setText(pLabel);

      //remove all previous action listeners
      removeActionListeners(pItem);

      //add a function action listener
      pItem.addActionListener(new FunctionListener(pFunction));
   }

   /**
    * Enables a component to listen for a request in functionality change.
    *
    * @param pFunction The function to switch for
    * @param pItem The component to add a listener for
    */
   public void enableSwitch(String pFunction, AbstractButton pItem)
   {
      //remove all previous action listeners
      removeActionListeners(pItem);

      //add a function action listener
      pItem.addActionListener(new SwitchListener(pFunction));
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
    * Listens for when the user needs to change the functionality of the view
    */
   public class SwitchListener
      implements ActionListener
   {
      /**
       * The function for the controller to switch to
       */
      String mFunction;

      /**
       * Sets the function to switch to
       *
       * @param pFunction The function to switch to
       */
      public SwitchListener(String pFunction)
      {
         mFunction = pFunction;
      }

      /**
       * Has the controller modify or switch to a view
       * with the functionality given.
       *
       * @param ev The causing action
       */
      public void actionPerformed(ActionEvent ev)
      {
         switchFunction(mFunction);
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
         mControl.setFunction(mFunction);
         mControl.doFunction();
         System.err.println("User pushed the " + mFunction + " button");
      }
   }
}
