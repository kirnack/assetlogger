package assetl.desktop;

import assetl.system.AssetLControl;

import assetl.system.DataPacket;
import assetl.system.StringPacket;

/**
 * A handwritten Swing gui.
 *
 * @author Gordon Hale
 */
public class HandwrittenView
   extends AssetView
{
   /**
    * Constructor for the user interface
    *
    * @param pControl The controller for this view
    */
   public HandwrittenView(AssetLControl pControl)
   {
      super(pControl);
      initComponents();
   }

   /**
    * Initializes all the swing components for the gui
    */
   public void initComponents()
   {
   }

   /**
    * Updates the views display of the model
    */
   public void updateData()
   {
   }

   /**
    * Grabs all pertainent data from the fields and sets them in
    * a DataPacket object. This DataPacket is then returned by the
    * method.
    *
    * @param pFunction The function that needs the DataPacket
    * @return The DataPacket that has been set
    */
   public DataPacket grabDataPacket(String pFunction)
   {
      return new StringPacket();
   }

   /**
    * Enables functionality passed in for this view.
    */
   public void enable(String pFunction)
   {
   }

   /**
    * The entry point for this view
    */
   public void run()
   {
   }
}
