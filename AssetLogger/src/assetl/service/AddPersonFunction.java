package assetl.service;

import assetl.system.DataPacket;
import assetl.system.PersonPacket;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Adds the Person to the database sent in the DataPacket
 *
 * @author Devin Doman
 */
public class AddPersonFunction
   extends Function
{
   /**
    * Sends the data needed to make a laptop asset
    */
   private PersonPacket mData;

   /**
    * Sets the DataPacket for this function
    *
    * @param pPacket The DataPacket sent from the view
    */
   public void setPacket(DataPacket pPacket)
   {
      mData = (PersonPacket) pPacket;
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
    * Adds the person to the model
    */
   public void performFunction()
   {
      if (mData.getPerson() != null)
      {
         mModel.setPerson(mData.getPerson());
      }
      else
      {
          Object[] options = {"Add Person",
                    "Return to Main Screen."};
        int n = JOptionPane.showOptionDialog(new JFrame(),
                "No Person informaiton was added.\nWould you like to add an "
                + "person or go back to the main screen?",
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
