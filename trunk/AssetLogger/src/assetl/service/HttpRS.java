package assetl.service;

import java.io.*;
import java.util.*;
import javax.xml.bind.*;
import assetl.system.*;
import java.lang.reflect.*;
/**
 * This class is designed to unmarshal the incoming XML, call the appropriate
 * function, and return the marshalled XML for transmission.
 *
 * @author Michael Hale
 * @author Bryon Rogers
 */
public class HttpRS
{
   /**
    * The XML Marshaller
    */
   private static Marshaller cMarshaller;

   /**
    * The XML Unmarshaller
    */
   private static Unmarshaller cUnmarshaller;

   private static Server cServer;

   public static void setServer(Server pServer)
   {
      cServer = pServer;
   }

   public static Server getServer()
   {
      return cServer;
   }

   /**
    * Default Contructor
    */
   public HttpRS()
   {
      try
      {
         JAXBContext jaxbCtx = JAXBContext.newInstance("assetl.system");
         cMarshaller = jaxbCtx.createMarshaller();
         cUnmarshaller = jaxbCtx.createUnmarshaller();
      }
      catch (JAXBException jaxbEx)
      {
         System.err.println("JAXB Context Creation Error");
         jaxbEx.printStackTrace();
      }
   }

   /**
    * Unmarshalls the XML contained in a String object
    *
    * @param XML
    * @return
    */
   private Object getObject(String XML)
   {
      Object outputObj = new Object();
      try
      {
         //unmarshalling...
         outputObj =  cUnmarshaller.unmarshal
                      (new ByteArrayInputStream(XML.getBytes()));
      }
      catch(Exception ex)
      {
         ex.printStackTrace();
      }
      return outputObj;
   }

   /**
    * Returns the marshalled object parameter
    *
    * @params obj
    * @return
    */
   private String getXML(Object obj)
   {
      String XML = new String();
      try
      {
         ByteArrayOutputStream BAOS = new ByteArrayOutputStream();
         cMarshaller.marshal(obj, BAOS);
         XML = BAOS.toString();
         BAOS.flush();
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
      return XML;
   }

   /**
    * Returns the marshalled object collection into a single string that
    * concats each XML string with an '&' character between them
    *
    * @param objs
    * @return
    */
   private String getXML(Collection<?> objs)
   {
      String XML = new String();
      try
      {
         int numObjs = objs.size();
         Object[] response = new Object[numObjs];
         for (int i = 0; i < numObjs; i++)
         {
            ByteArrayOutputStream BAOS = new ByteArrayOutputStream();
            cMarshaller.marshal(response[i], BAOS);
            XML = BAOS.toString();
            BAOS.flush();
         }
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
      return XML;
   }

   public String getResponse(String command, String[] args)
   {
      Class[] classes = new Class[args.length];
      Object[] params = new Object[args.length];
      int i = 0;
      for(String arg : args)
      {
         String[] split = arg.split(":");
         if(split.length == 1)
         {
            classes[i] = String.class;
            params[i] = arg;
         }
         else
         {
            try
            {
               classes[i] = Class.forName(split[0]);
               System.err.print(Asset.class.getPackage());
               if (classes[i].getPackage().equals(Asset.class.getPackage()))
               {
                  params[i] = (classes[i].cast(getObject(split[1])));
               }
               else if (classes[i].isAssignableFrom(Date.class))
               {
                  params[i] = new Date(Long.parseLong(split[1]));
               }
               else
               {
                  params[i] =
                     classes[i].getConstructor(String.class)
                        .newInstance(split[0]);
               }
            }
            catch(Exception e)
            {
               e.printStackTrace();
               return e.toString();
            }
         }
         i++;
      }

      return getResponse(command, classes, params);
   }

   /**
    * Uses Reflection to determine the method and its parameters from the
    * unmarshalled XML objects, and then call the method.
    *
    * @param command
    * @param params
    * @return instanceof Date
    *
    */
   public String getResponse(String command, Class[] argClasses,
                             Object[] args)
   {
      //if (command.equals("getAvailAsset"))
      //{
      //   getAvailAsset(args);
      //}
      Object response= new Object();
      int numArgs = args.length;
      try
      {
         Method method = Server.class.getDeclaredMethod(command, argClasses);
         if (numArgs == 0)
         {
            response = method.invoke(cServer);
         }
         if (numArgs == 1)
         {
            response = method.invoke(cServer , argClasses[0].cast(args[0]));
         }
         if (numArgs == 2)
         {          
            response = method.invoke(cServer, argClasses[0].cast(args[0]),
               argClasses[1].cast(args[1]));
         }
         
         System.err.println(response);
         if (response instanceof Collection)
         {
            String xml = getXML(response);
            System.err.println(xml);
            return xml;
         }
         else if ((response instanceof Asset) || (response instanceof User)
            || (response instanceof Checkout) || (response instanceof Person)
            || (response instanceof Request))
         {
            return getXML(response);
         }
         else
         {
            return response.toString();
         }
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
         return "error:" + ex.toString();
      }
      
   }

   /**
    * Handles the special case where the inputs are Date objects- and cannot be
    * marshalled with the current schema
    *
    * @param args
    * @return
    */
   public Collection<Asset> getAvailAsset(Object[] args)
   {
      Date mStart = new Date((Long)args[0]);
      Date mEnd = new Date((Long)args[0]);

      Collection<Asset> assets = new ArrayList<Asset>();
      assets.add(new Asset(mStart.toString(), null));
      assets.add(new Asset(mEnd.toString(), null));
      return assets;
   }

}