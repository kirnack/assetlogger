package resources;

/**
 * Provides static methods to allow an easy way to dynamically
 * load objects
 *
 * @author Devin Doman
 */
public class ObjectLoader
{
   /**
    * Uses dynamic class loading to create a new instance of an object.
    *
    * @param pDynObj The name of the class to get an instance of
    * @return The dynamically created object
    */
   public static Object loadObj(String pClass)
   {
      Object obj = null;

      try
      {
         //load into a class object
         Class<?> clazz = Class.forName(pClass);

         //Dynamically instantiate the object
         obj = clazz.newInstance();

      }
      catch (ClassNotFoundException ex)
      {
         System.err.println("Class " + pClass + " could not be found.");
         ex.printStackTrace();
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
      return obj;
   }
}
