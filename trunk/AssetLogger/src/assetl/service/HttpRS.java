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

   /**
    * Default Contructor
    */
   public HttpRS()
   {
      try
      {
         JAXBContext jaxbCtx = JAXBContext.newInstance("util");
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

   /**
    * Uses Reflection to determine the method and its parameters from the
    * unmarshalled XML objects, and then call the method.
    *
    * @param command
    * @param params
    * @return
    */
   public String getResponse(String command, String[] args)
   {
      if (command.equals("getAvailAsset"))
         getAvailAsset(args);
      Object response= new Object();
      int numArgs = args.length;
      try
      {
         Class thisClass = Server.getInstance().getClass();
         Method method = thisClass.getMethod(command);
//         Class<?>[] argTypes = method.getParameterTypes();
         if (numArgs == 0)
            response = method.invoke(command);
         if (numArgs == 1)
            response = method.invoke(args[0]);
         if (numArgs == 2)
            response = method.invoke(args[0], args[1]);

         if ("Collection".equals(response.getClass().getName()))
            return getXML((Collection<?>)response);
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
      return getXML(response);
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