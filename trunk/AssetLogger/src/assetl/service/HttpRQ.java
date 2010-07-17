package assetl.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Collection;
import java.util.Date;
import javax.xml.bind.*;
import assetl.system.*;

/**
 * This class is designed to marshal the parameters of the remote function call,
 * pass the function call and the data to the client for transmission, and
 * unmarshal the response XML.
 * @author Michael Hale
 * @author Bryon Rogers
 */
public class HttpRQ
   implements AssetLModel
{

   /**
    * The singleton instance of Client
    */
   private static Client cClient;
   /**
    * The singleton instance of HttpRQ
    */
   private static HttpRQ cInstance;
   /**
    * The XML Marshaller
    */
   private static Marshaller cMarshaller;
   /**
    * The XML Unmarshaller
    */
   private static Unmarshaller cUnmarshaller;

   /**
    * Gets the singleton HttpRQ instance
    *
    * @return the singleton instance of the HttpRQ
    */
   public static HttpRQ getInstance()
   {
      if (cInstance == null)
      {
         cInstance = new HttpRQ();
      }

      return cInstance;
   }

   /**
    * Constructs a new HttpRQ instance.
    *
    * (Private per the Singleton pattern.)
    */
   private HttpRQ()
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
      cClient = Client.getInstance();
   }

   /**
    * returns the marshalled object from the XML String
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
         if (obj instanceof String)
         {
            XML = (String) obj;
         }
         else
         {
         ByteArrayOutputStream BAOS = new ByteArrayOutputStream();
         cMarshaller.marshal(obj, BAOS);
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

   private Object sendRequest(String command, String string)
   {
      String response;
      if (!(string.equals("")))
      {
         String XML = string;
         if (!(string.contains("param0=")))
         {
            XML = "param0=" + string;
         }
         System.err.print(XML);
         response = cClient.postData(command, XML);
      }
      else
      {
         response = cClient.postData(command, null);
      }
       
      return response;
   }

   /**
    * Sends the marshalled XML to the Client
    *
    * @param command
    * @param param
    * @return
    */
   private Object sendRequest(String command, Object param)
   {
      Object[] objs = {param};
      return sendRequest(command, objs);
   }

    /**
    * Sends the marshalled XML to the Client
    *
    * @param command
    * @param param
    * @return
    */
   private Object sendRequest(String command, Object[] params)
   {
      String XML = new String();
      for (int i = 0; i < params.length; i++)
      {
         XML += ((i != 0) ? "&":"") + "param" + i + "="
           + params[i].getClass().toString().replace("class ", "")
           + ":";
         if (params[i].getClass().getPackage().equals(Asset.class.getPackage()))
         {
            XML += getXML(params[i]);
         }
         else if (params[i] instanceof Date)
         {
            XML += ((Date) params[i]).getTime();
         }
         else
         {
            XML+= params[i].toString();
         }
      }

      return sendRequest(command, XML);
   }

   /**
    * Overloaded AssetLModel method
    *
    * @param pID
    * @return
    */
   public Asset getAsset(String pID)
   {
      return (Asset)getObject(cClient.postData("getAsset", "param0=" + pID));
   }

   /**
    * Overloaded AssetLModel method
    *
    * @param pAsset
    * @return
    */
   public Checkout getCheckout(Asset pAsset)
   {
      return (Checkout)getObject((String) sendRequest("getCheckout", pAsset));
   }

   /**
    * Overloaded AssetLModel method
    *
    * @param pID
    * @return
    */
   public Checkout getCheckout(String pID)
   {
      return (Checkout)getObject(cClient.postData("getCheckout",
                                                  "param0=" + pID));
   }

   /**
    * Overloaded AssetLModel method
    *
    * @param pID
    * @return
    */
   public Person getPerson(String pID)
   {
      Person person = (Person)
         getObject((String) sendRequest("getPerson", pID));
      return person;
   }

   /**
    * Overloaded AssetLModel method
    *
    * @param pID
    * @return
    */
   public Request getRequest(String pID)
   {
      Request Request = (Request)
         getObject((String) sendRequest("getRequest", pID));
       return Request;
   }

   /**
    * Overloaded AssetLModel method
    *
    * @param pID
    * @return
    */
   public User getUser(String pID)
   {
      User user = (User)
         getObject((String) sendRequest("getUser", pID));
       return user;
   }

   /**
    * Overloaded AssetLModel method
    *
    * @param pPerson
    * @return
    */
   public Collection<Asset> getAssets(Person pPerson)
   {
      String xml = (String) sendRequest("getAssets", pPerson);
      System.out.println(xml);
      Collection<Asset> result = (Collection<Asset>)
         getObject(xml);
      return result;
   }

   /**
    * Overloaded AssetLModel method
    *
    * @param pStart
    * @param pEnd
    * @return
    */
   public Collection<Asset> getAvailAsset(Date pStart, Date pEnd)
   {
      Object[] array = {pStart, pEnd};
      String xml = (String) sendRequest("getAvailAsset", array);
      System.out.println(xml);
      Collection<Asset> result = (Collection<Asset>)
         getObject(xml);
      return result;
   }

   /**
    * Overloaded AssetLModel method
    *
    * @param pAsset
    * @return
    */
   public Collection<Person> getBorrowers(Asset pAsset)
   {
      String xml = (String) sendRequest("getBorrowers", pAsset);
      System.out.println(xml);
      Collection<Person> result = (Collection<Person>)
         getObject(xml);
      return result;

   }

   /**
    * Overloaded AssetLModel method
    *
    * @param pAsset
    * @return
    */
   public Collection<Checkout> getCheckedOutCheckouts(Asset pAsset)
   {
      String xml = (String) sendRequest("getCheckouts", pAsset);
      System.out.println(xml);
      Collection<Checkout> result = (Collection<Checkout>)
         getObject(xml);
      return result;
   }

   /**
    * Overloaded AssetLModel method
    *
    * @param pRequest
    * @return
    */
   public Collection<Checkout> getCheckouts(Request pRequest)
   {
      String xml = (String) sendRequest("getCheckouts", pRequest);
      System.out.println(xml);
      Collection<Checkout> result = (Collection<Checkout>)
         getObject(xml);
      return result;
   }

   /**
    * Overloaded AssetLModel method
    *
    * @return
    */
   public Integer getNumCheckouts()
   {
      String result = (String) this.sendRequest("getNumCheckouts", "");
      System.err.println(result);
      return new Integer (result);
   }

   /**
    * Overloaded AssetLModel method
    *
    * @return
    */
   public Integer getNumLogs()
   {
      String result = (String) this.sendRequest("getNumLogs", "");
      System.err.println(result);
      return new Integer (result);
   }

   /**
    * Overloaded AssetLModel method
    *
    * @return
    */
   public Integer getNumRequests()
   {
      String result = (String) this.sendRequest("getNumRequests", "");
      System.err.println(result);
      return new Integer (result);
   }

   /**
    * Overloaded AssetLModel method
    *
    * @return
    */
   public Collection<User> getUsers()
   {
      String xml = (String) sendRequest("getUsers", "");
      System.out.println(xml);
      Collection<User> result = (Collection<User>)
         getObject(xml);
      return result;
   }

   /**
    * Overloaded AssetLModel method
    *
    * @param pID
    * @return
    */
   public Integer getAccessLevel(String pID)
   {
      return getUser(pID).adminRights();
   }

   /**
    * Overloaded AssetLModel method
    *
    * @param pAsset
    */
   public void setAsset(Asset pAsset)
   {
      sendRequest("setAsset", pAsset);
   }

   /**
    * Overloaded AssetLModel method
    *
    * @param pCheckout
    * @deprecated
    */
   public void setCheckout(Checkout pCheckout)
   {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   /**
    * Overloaded AssetLModel method
    *
    * @param pCheckout
    * @param pUserID
    */
   public void setCheckout(Checkout pCheckout, String pUserID)
   {
      Object[] array = {pCheckout, pUserID};
      this.sendRequest("setCheckout", array);
   }

   /**
    * Overloaded AssetLModel method
    *
    * @param pPerson
    */
   public void setPerson(Person pPerson)
   {
      sendRequest("setPerson", pPerson);
   }

   /**
    * Overloaded AssetLModel method
    *
    * @param pRequest
    * @param pUserID
    */
   public void setRequest(Request pRequest, String pUserID)
   {
      Object[] array = {pRequest, pUserID};
      this.sendRequest("setRequest", array);
   }

   /**
    * Overloaded AssetLModel method
    *
    * @param pID
    * @param pPwd
    * @return
    */
   public Boolean checkPwd(String pID, String pPwd)
   {
       String result = 
          cClient.postData("checkPwd", "param0="+ pID + "&param1=" + pPwd);
       System.err.print(result);

       return new Boolean(result);
   }

   /**
    * Overloaded AssetLModel method
    *
    * @param pPerson
    * @return
    */
   public Collection<Request> getActiveRequests(Person pPerson)
   {
      String xml = (String) sendRequest("getActiveRequests", pPerson);
      System.out.println(xml);
      Collection<Request> result = (Collection<Request>)
         getObject(xml);
      return result;
   }
}
