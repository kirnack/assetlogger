package assetl.system;

/**
 * Sends a Checkout in a DataPacket
 *
 * @author Devin Doman
 */
public class CheckoutPacket
   implements DataPacket
{
   /**
    * The checkout to be sent
    */
   Checkout mCheckout;

   /**
    * Default Constructor
    */
   public CheckoutPacket()
   {
      this(null);
   }

   /**
    * Constructor taking the Checkout as a parameter
    *
    * @param pCheckout The Checkout
    */
   public CheckoutPacket(Checkout pCheckout)
   {
      mCheckout = pCheckout;
   }

   /**
    * Sets the Checkout
    *
    * @param pCheckout The Checkout
    */
   public void setCheckout(Checkout pCheckout)
   {
      mCheckout = pCheckout;
   }

   /**
    * Get the Checkout
    *
    * @return The Checkout
    */
   public Checkout getCheckout()
   {
      return mCheckout;
   }
}
