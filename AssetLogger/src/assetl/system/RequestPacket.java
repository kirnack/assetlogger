package assetl.system;

/**
 * Sends a Request and Checkout object
 *
 * @author Devin Doman
 */
public class RequestPacket
   implements DataPacket
{

   public Request getRequest()
   {
      return new Request();
   }

   public Checkout getCheckout()
   {
      return new Checkout();
   }
}
