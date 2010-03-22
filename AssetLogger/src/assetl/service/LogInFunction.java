package assetl.service;

/**
 * Allows a User to log in to the application
 *
 * @author Devin Doman
 */
public class LogInFunction
        extends DBFunction
{
    /**
     * Logs in the user if they have a valid password
     */
    public void performFunction()
    {
        mDBControl.changeView("assetl.desktop.IDView");
    }
}
