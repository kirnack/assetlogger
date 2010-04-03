package assetl.service;

import assetl.system.DataPacket;
import assetl.system.AssetPacket;
import assetl.system.Asset;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Adds an Asset to the model
 *
 * @author Devin Doman
 */
public class AddAssetFunction
   extends Function
{
   /**
    * Sends the data needed to make a laptop asset
    */
   private AssetPacket mData;

   /**
    * Sets the DataPacket for this function
    *
    * @param pPacket The DataPacket sent from the view
    */
   public void setPacket(DataPacket pPacket)
   {
      mData = (AssetPacket) pPacket;
   }

   /**
    * Gets the DataPacket currently set for this function
    *
    * @return The DataPacket
    */
   public DataPacket getPacket()
   {
      return mData;
   }

   /**
    * Adds an asset to the model
    */
   public void performFunction()
   {
     Asset asset = mData.getAsset();
     if (asset != null)
     {
         mModel.setAsset(asset);
     }
     else
     {
        Object[] options = {"Add Asset",
                    "Return to Main Screen."};
        int n = JOptionPane.showOptionDialog(new JFrame(),
                "No Asset informaiton was added.\nWould you like to add an "
                + "asset or go back to the main screen?",
                "No Asset info added",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,     //do not use a custom Icon
                options,  //the titles of buttons
                options[0]); //default button title
        if (n ==  JOptionPane.YES_OPTION)
        {
           return;
        }
     }
     mControl.changeView("ID");
     mControl.enableFunction("LoadPerson");
   }
}
