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
    * @param pControl
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
    * Enables functionality passed in for this view.
    *
    * @param pFunction The function to enable
    */
   void enable(String pFunction);

   /**
    * Enables ability to switch to a view with the given function enabled.
    *
    * @param pView The view to switch to
    * @param pFunction The function to enable
    * @param pItem The button to perform the switch
    */
   void enable(String pView, String pFunction, AbstractButton pItem);

   /**
    * Attaches a listener to the button given that performs the function given.
    * If the ActionListener indicated is a switch listener pFunction will
    * be the name of the view to switch to.
    *
    * @param pFunction The function to perform of name of view for listener
    *                  to switch to.
    * @param pItem     The button to perform the action
    * @param pListener The listener for an action
    */
   void enable(String pFunction, AbstractButton pItem, String pListener);

   /**
    * Allows control of the visibility of Admin components in the view
    *
    * @param pIsAdmin
    */
   public void setAdminComponents(int pIsAdmin);

   /**
    * Grabs all pertainent data from the fields and sets them in
    * a DataPacket object. This DataPacket is then returned by the
    * method.
    *
    * @param pFunction The function that needs the DataPacket
    * @return The DataPacket that has been set
    */
   DataPacket grabDataPacket(String pFunction);

   /**
    * Allows the view to receive a DataPacket from the controller
    *
    * @param pPacket The DataPacket to receive
    */
   void receiveDataPacket(DataPacket pPacket);

   /**
    * Prepopulates the fields with the data currently held
    * in the member variables or with those in the model.
    */
   void populateFields();
}
