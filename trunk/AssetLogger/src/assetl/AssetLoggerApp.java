/*
 * AssetLoggerApp.java
 */

package assetl;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

import assetl.service.DatabaseControl;

/**
 * The main class of the application.
 */
public class AssetLoggerApp extends SingleFrameApplication {

    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup()
    {
        show(new AssetLoggerView(this, new DatabaseControl()));
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
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
     */
    public static void main(String[] args)
    {
        launch(AssetLoggerApp.class, args);
    }
}
