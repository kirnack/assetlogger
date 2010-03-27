package assetl.service;

import assetl.system.DataPacket;
import assetl.system.LoginPacket;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Allows a User to log in to the application
 *
 * @author Devin Doman
 */
public class LogInFunction
   extends Function
{
   /**
    * The specific DataPacket needed for this function to operate
    */
   LoginPacket mData;

   /**
    * Sets the DataPacket for a Login
    *
    * @param pPacket The DataPacket sent from the view
    */
   public void setPacket(DataPacket pPacket)
   {
      mData = (LoginPacket) pPacket;
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
    * Logs in the user if they have a valid password
    */
   public void performFunction()
   {
      String test = "do not ";

      // If the passwords match
      if (mModel.checkPwd(mData.getUserName(), mData.getPassword()))
      {
         test = "";
         // Change to the next view
         DatabaseControl tempControl = (DatabaseControl) mControl;
         tempControl.changeView("ID");

         tempControl.setCurrentUser(mModel.getUser(mData.getUserName()));
      }
      else
      {
         /**
          * Displays a log in error, if the username and password don't match.
          */
         JOptionPane.showMessageDialog(new JFrame(),
            "Inccorect Username/Password"
            + " compination.",
            "Wrong Credentials",
            JOptionPane.ERROR_MESSAGE);

      }

      System.err.println("The passwords " + test + "match");
   }
}
