package resources;

/**
 * A class to provide a Configuration Service through static
 * methods (use import static util.Config.*;).
 */
public class Config
{
   /**
    * Gets a string configured by name.
    *
    * @param pName the name.
    * @param pDefaultValue the default value if no configurable by
    *        that name exists.
    */
   public static String getString(final String pName, final String pDefaultValue)
   {
      return System.getProperty(pName, pDefaultValue);
   }

   /**
    * Gets an integer configured by name.
    *
    * @param pName the name.
    * @param pDefaultValue the default value if no configurable by
    *        that name exists.
    */
   public static int getInteger(final String pName, final int pDefaultValue)
   {
      int value = 0;
      try
      {
         value = Integer.parseInt(System.getProperty(pName));
      }
      catch (Exception e)
      {
         value = pDefaultValue;
      }

      return value;
   }

   /**
    * Gets a boolean configured by name.
    *
    * @param pName the name.
    * @param pDefaultValue the default value if no configurable by
    *        that name exists.
    */
   public static boolean getBoolean(final String pName, final boolean pDefaultValue)
   {
      String value = System.getProperty(pName);
      return ((value == null) ? pDefaultValue :
              value.equalsIgnoreCase("true"));
   }
}
