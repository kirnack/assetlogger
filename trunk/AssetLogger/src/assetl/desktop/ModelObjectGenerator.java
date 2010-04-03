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
public class ModelObjectGenerator
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

   /**
    * Fetch the asset from the model with the given id
    *
    * @param pID The id of the asset
    * @return The asset from the model
    */
   public static Asset fetchAsset(String pID)
   {
      return cControl.getAsset(pID);
   }

   /**
    * Fetch the person from the model with the given id
    *
    * @param pID The id of the person
    * @return The person from the model
    */
   public static Person fetchPerson(String pID)
   {
      return cControl.getPerson(pID);
   }

   /**
    * Creates an Asset object containing laptop data
    *
    * @param pBarCode The bar code
    * @param pLaptopNumber The laptop number
    * @param pMake The make
    * @param pModel The model
    * @param pSerialNum The serial number
    * @param pMaintenance Whether the laptop is in maintenance
    * @return The asset object representing the laptop
    */
   public static Asset generateLaptop(String pBarCode, String pLaptopNumber,
      String pMake, String pModel, String pSerialNum, boolean pMaintenance)
   {
      Asset laptop = null;

      if (!("".equals(pBarCode)))
      {
         String type = "PC";
         if ("Apple".equalsIgnoreCase(pMake))
         {
            type = "Mac";
         }
         laptop = new Asset(pBarCode, pMake, pLaptopNumber, pSerialNum, type,
            pModel, pMaintenance);
      }

      return laptop;
   }
}
