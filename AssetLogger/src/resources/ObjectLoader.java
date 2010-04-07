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
    * @param pClass
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

   /**
    * Dynamically loads an object via its constructor for objects
    * with one parameter.
    *
    * @param pClass The class to dynamically load
    * @param pParam The one parameter to the objects constructor
    * @return The dynamically loaded object
    */
   public static Object loadObj(String pClass, Object pParam)
   {
      Object obj = null;

      try
      {
         //Get all constructors
         for (Constructor<?> con : Class.forName(pClass).getDeclaredConstructors())
         {
            //Get the parameter types of the constructors
            Class<?>[] paramTypes = con.getParameterTypes();
            //If ther is one parameter that is a superclass of the parameter
            if (paramTypes.length == 1
               && paramTypes[0].isAssignableFrom(pParam.getClass()))
            {
               //dynamically pass the parameter to the constructor
               obj = con.newInstance(pParam);
            }
         }
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
