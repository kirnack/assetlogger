package resources;

import java.lang.reflect.Constructor;

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

   public static Object loadObj(String pClass, Object pParam)
   {
      Object obj = null;

      try
      {
         pClass = "resources.DeleteMe";
         //load into a class object
         Class<?> clazz = Class.forName(pClass);

         Constructor<?> con = Class.forName(pClass).getDeclaredConstructor(pParam.getClass());

         //Dynamically instantiate the object
         obj = con.newInstance(pParam);

         System.out.println("obj: " + obj);

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
