package assetl.system;

/**
 * A packet to hold and transmit a String
 *
 * @author Devin Doman
 */
public class StringPacket
   implements DataPacket
{
   /**
    * The string to send
    */
   String mString;

   /**
    * Default Constructor
    */
   public StringPacket()
   {
      this(null);
   }

   /**
    * Constructor with the string as a parameter
    *
    * @param pString The string
    */
   public StringPacket(String pString)
   {
      mString = pString;
   }

   /**
    * Sets the string
    *
    * @param pString The string
    */
   public void setString(String pString)
   {
      mString = pString;
   }

   /**
    * Gets the string
    *
    * @return The string
    */
   public String getString()
   {
      return mString;
   }
}
