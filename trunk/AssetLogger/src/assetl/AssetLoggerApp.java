/*
 * AssetLoggerApp.java
 */

package assetl;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

import assetl.system.AssetLControl;
import assetl.service.DatabaseControl;

/**
 * The main class of the application.
 */
public class AssetLoggerApp 
        extends SingleFrameApplication
{

    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup()
    {
        new DatabaseControl().run();
        //new DatabaseControl(this);
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     * @param root 
     */
    @Override protected void configureWindow(java.awt.Window root)
    {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of AssetLoggerApp
     */
    public static AssetLoggerApp getApplication()
    {
        return Application.getInstance(AssetLoggerApp.class);
    }

    /**
     * Main method launching the application.
     * @param args
     */
    public static void main(String[] args)
    {
        launch(AssetLoggerApp.class, args);
    }
}
