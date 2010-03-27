package assetl.system;

/**
 * Carrier for data that needs to be sent from the view to the controller
 * in order to login
 *
 * @author Devin Doman
 */
public class LoginPacket
   implements DataPacket
{
   private String mUserName;
   private String mPassword;

   /**
    * Default Constructor
    */
   public LoginPacket()
   {
      this(null, null);
   }

   /**
    * Contructor with all parameters.
    *
    * @param pUserName The user name to be sent
    * @param pPassword The password to be sent
    */
   public LoginPacket(String pUserName, String pPassword)
   {
      mUserName = pUserName;
      mPassword = pPassword;
   }

   /**
    * Sets the user name.
    *
    * @param pUserName The user name to set
    */
   public void setUserName(String pUserName)
   {
      mUserName = pUserName;
   }

   /**
    * Sets the password
    *
    * @param pPassword The password to set
    */
   public void setPassword(String pPassword)
   {
      mPassword = pPassword;
   }

   /**
    * Gets the user name
    *
    * @return The user name
    */
   public String getUserName()
   {
      return mUserName;
   }

   /**
    * Gets the password
    *
    * @return The password
    */
   public String getPassword()
   {
      return mPassword;
   }
}
