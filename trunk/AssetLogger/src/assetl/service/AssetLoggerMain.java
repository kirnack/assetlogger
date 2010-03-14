package assetl.service;

/**
 * The entry point for the application.
 *
 * @author Devin Doman
 * @author Bryon Rogers
 * @author Mike Hale
 */
public class AssetLoggerMain
{
    public static void main(String[] args)
    {
        new Thread(new DatabaseControl()).start();
    }
}
