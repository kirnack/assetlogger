package assetl.service;

import assetl.system.DataPacket;
import assetl.system.LoginPacket;
import assetl.system.User;

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
    @Override
    public void setPacket(DataPacket pPacket)
    {
        super.setPacket(pPacket);
        mData = (LoginPacket) mPacket;
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
            tempControl.changeView("assetl.desktop.IDView");

            tempControl.setCurrentUser(mModel.getUser(mData.getUserName()));
        }

        System.err.println("The passwords " + test + "match");
    }
}
