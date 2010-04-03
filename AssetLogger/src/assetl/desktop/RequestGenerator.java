package assetl.desktop;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Date;

import assetl.system.AssetLControl;
import assetl.system.Request;
import assetl.system.Person;
import assetl.system.Checkout;
import assetl.system.Asset;

/**
 * Provides static methods that can be used by the views to create
 * new Request objects to be sent to the controller
 * 
 * @author Devin Doman
 */
public class RequestGenerator
{
   /**
    * The controller used to generate a request
    */
   private static AssetLControl cControl;

   /**
    * Creates an instance of a controller
    */
   static
   {
      cControl = new MapControl();
   }

   /**
    * Generates a new Request object with the parameters given
    *
    * @param pPersonID The person id
    * @param pAssetIDs A collection of strings holding asset ids
    * @param pStart The start date
    * @param pEnd The end date
    * @return The generated request
    */
   public static Request generateRequest(String pPersonID,
      Collection<Asset> pAssets, Date pStart, Date pEnd)
   {
      ArrayList<Checkout> checkouts = new ArrayList<Checkout>();
      Person recipient = cControl.getPerson(pPersonID);

      for (Asset asset : pAssets)
      {
         checkouts.add(new Checkout("", "", asset, recipient,
            pStart, pEnd));
      }

      return new Request("", new Date(), pStart, "Faculty Checkout", recipient,
         checkouts);
   }
}
