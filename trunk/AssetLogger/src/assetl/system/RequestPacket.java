package assetl.system;

/**
 * Sends a Request in a DataPacket
 *
 * @author Devin Doman
 */
public class RequestPacket
   implements DataPacket
{
   /**
    * The request to be sent
    */
   Request mRequest;

   /**
    * Default Constructor
    */
   public RequestPacket()
   {
      this(null);
   }

   /**
    * Constructor taking the request as a parameter
    *
    * @param pRequest The request
    */
   public RequestPacket(Request pRequest)
   {
      mRequest = pRequest;
   }

   /**
    * Sets the Request
    *
    * @param pRequest The request
    */
   public void setRequest(Request pRequest)
   {
      mRequest = pRequest;
   }

   /**
    * Get the request
    *
    * @return The request
    */
   public Request getRequest()
   {
      return mRequest;
   }
}
