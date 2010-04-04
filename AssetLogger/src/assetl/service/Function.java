package assetl.service;

import assetl.system.AssetLControl;
import assetl.system.AssetLModel;
import assetl.system.DataPacket;

/**
 * Represents a functionality that can be performed by the controller by
 * manipulating the model.
 *
 * @author Devin Doman
 */
public abstract class Function
{
   /**
    * The model to perform the function on
    */
   protected AssetLModel mModel;
   /**
    * Reference to the controller calling the function
    */
   protected AssetLControl mControl;

   /**
    * Sets the model to perform functions on
    *
    * @param pModel The model to perform functions on
    */
   protected void setModel(AssetLModel pModel)
   {
      mModel = pModel;
   }

   /**
    * Sets a reference to the Controller calling the function
    *
    * @param pControl The controller calling the function
    */
   protected void setControl(AssetLControl pControl)
   {
      mControl = pControl;
   }

   /**
    * Sets the model and controller used by this function
    *
    * @param pControl The controller calling the function
    * @param pModel The model to perform function on
    */
   public void setModules(AssetLControl pControl, AssetLModel pModel)
   {
      setControl(pControl);
      setModel(pModel);
   }

   /**
    * Sets the DataPacket for this function
    *
    * @param pPacket The DataPacket sent from the view
    */
   public abstract void setPacket(DataPacket pPacket);

   /**
    * Gets the DataPacket currently set for this function
    *
    * @return The DataPacket
    */
   public abstract DataPacket getPacket();

   /**
    * Peforms the defined function
    */
   public abstract void performFunction();
}
