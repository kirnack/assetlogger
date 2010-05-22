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
      String XML = "param0=" + getXML(params[0]);
      for (int i = 1; i < params.length; i++)
      {
         XML += "&param" + i + "=" + getXML(params[i]);
      }

      String response = cClient.postData(command, XML);      
      return getObject(response);
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
      return (Checkout)sendRequest("getCheckout", pAsset);
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
      throw new UnsupportedOperationException("Not supported yet.");
   }

   /**
    * Overloaded AssetLModel method
    *
    * @param pID
    * @return
    */
   public Request getRequest(String pID)
   {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   /**
    * Overloaded AssetLModel method
    *
    * @param pID
    * @return
    */
   public User getUser(String pID)
   {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   /**
    * Overloaded AssetLModel method
    *
    * @param pPerson
    * @return
    */
   public Collection<Asset> getAssets(Person pPerson)
   {
      throw new UnsupportedOperationException("Not supported yet.");
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
      throw new UnsupportedOperationException("Not supported yet.");
   }

   /**
    * Overloaded AssetLModel method
    *
    * @param pAsset
    * @return
    */
   public Collection<Person> getBorrowers(Asset pAsset)
   {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   /**
    * Overloaded AssetLModel method
    *
    * @param pAsset
    * @return
    */
   public Collection<Checkout> getCheckedOutCheckouts(Asset pAsset)
   {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   /**
    * Overloaded AssetLModel method
    *
    * @param pRequest
    * @return
    */
   public Collection<Checkout> getCheckouts(Request pRequest)
   {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   /**
    * Overloaded AssetLModel method
    *
    * @return
    */
   public int getNumCheckouts()
   {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   /**
    * Overloaded AssetLModel method
    *
    * @return
    */
   public int getNumLogs()
   {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   /**
    * Overloaded AssetLModel method
    *
    * @return
    */
   public int getNumRequests()
   {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   /**
    * Overloaded AssetLModel method
    *
    * @return
    */
   public Collection<User> getUsers()
   {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   /**
    * Overloaded AssetLModel method
    *
    * @param pID
    * @return
    */
   public int getAccessLevel(String pID)
   {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   /**
    * Overloaded AssetLModel method
    *
    * @param pAsset
    */
   public void setAsset(Asset pAsset)
   {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   /**
    * Overloaded AssetLModel method
    *
    * @param pCheckout
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
      throw new UnsupportedOperationException("Not supported yet.");
   }

   /**
    * Overloaded AssetLModel method
    *
    * @param pPerson
    */
   public void setPerson(Person pPerson)
   {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   /**
    * Overloaded AssetLModel method
    *
    * @param pRequest
    * @param pUserID
    */
   public void setRequest(Request pRequest, String pUserID)
   {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   /**
    * Overloaded AssetLModel method
    *
    * @param pID
    * @param pPwd
    * @return
    */
   public boolean checkPwd(String pID, String pPwd)
   {
      Object[] params = { pID, pPwd};
      return Boolean.getBoolean(cClient.postData("checkPwd", "param0="+ pID
         + "&param1=" + pPwd));
   }

   /**
    * Overloaded AssetLModel method
    *
    * @param pPerson
    * @return
    */
   public Collection<Request> getActiveRequests(Person pPerson)
   {
      throw new UnsupportedOperationException("Not supported yet.");
   }
}