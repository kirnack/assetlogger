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

        //
        // TODO: uncomment the if statement to enable validation of passwords
        // once there are actuall users in the database
        //

        // If the passwords match
        //if (mModel.checkPwd(mData.getUserName(), mData.getPassword()))
        //{
            test = "";
            // Change to the next view
            DatabaseControl tempControl = (DatabaseControl) mControl;
            tempControl.changeView("assetl.desktop.IDView");

            tempControl.setCurrentUser(mModel.getUser(mData.getUserName()));
        //}

        System.err.println("The passwords " + test + "match");
    }
}
