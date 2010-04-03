package assetl.service;

import assetl.system.DataPacket;
import assetl.system.LaptopPacket;
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
   private LaptopPacket mData;

   /**
    * Sets the DataPacket for this function
    *
    * @param pPacket The DataPacket sent from the view
    */
   public void setPacket(DataPacket pPacket)
   {
      mData = (LaptopPacket) pPacket;
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
    * Take the data packet and make an asset
    *
    * @return The asset to set in the model
    */
   public Asset generateLaptop()
   {
      Asset laptop = null;

      if (!("".equals(mData.getBarCode())))
      {
         laptop = new Asset();
         laptop.setID(mData.getBarCode());
         laptop.setMake(mData.getMake());
         laptop.setModel(mData.getLaptopNumber());
         laptop.setSerialNum(mData.getSerialNubmer());

         String type = "PC";
         if ("Apple".equalsIgnoreCase(mData.getMake()))
         {
            type = "Mac";
         }

         laptop.setType(type);
         laptop.setMaintenance(mData.getMaintenance());
      }
      
      return laptop;
   }

   /**
    * Adds an asset to the model
    */
   public void performFunction()
   {
     Asset asset = generateLaptop();
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
