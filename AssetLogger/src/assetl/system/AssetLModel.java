package assetl.system;

import java.util.Collection;
import java.util.Date;

/**
 * Provides a common interface for the model module in the
 * Model-View-Controller structure. Represents the data
 * used by the application with its getters and setters.
 * 
 * @author Bryon Rogers
 */
public interface AssetLModel
{
   /**
    * Gets a person by their id.
    *
    * @param pID The id of the person
    * @return The person with the id given
    */
   Person getPerson(String pID);

   /**
    * Sets a person.
    *
    * @param pPerson The person to set
    */
   void setPerson(Person pPerson);

   /**
    * Gets an asset by its id.
    *
    * @param pID The id of the asset to get
    * @return The asset with the given id
    */
   Asset getAsset(String pID);

   /**
    * Sets an asset.
    *
    * @param pAsset The asset to set.
    */
   void setAsset(Asset pAsset);

   /**
    * Gets a request by its id.
    *
    * @param pID The id of the request.
    * @return The request with the id given.
    */
   Request getRequest(String pID);

   /**
    * Sets a Request.
    *
    * @param pRequest The request to set
    * @param pUserID The user id to register with the request
    */
   void setRequest(Request pRequest, String pUserID);

   /**
    * Gets the Checkout object that has no returned date set
    * for the Asset given.
    *
    * @param pAsset The asset that needs to be returned
    * @return The Checkout object for this asset
    */
   Checkout getCheckout(Asset pAsset);

   /**
    * Sets a Checkout
    *
    * @param pCheckout The checkout to set
    */
   void setCheckout(Checkout pCheckout);

   /**
    * Returns true if the person with the id given is an admin
    *
    * @param pID The id of the person
    * @return True if the person is an admin
    */
   boolean isAdmin(String pID);

   /**
    * Gets a user with the given id.
    *
    * @param pID The id of the user.
    * @return The user with the given id.
    */
   User getUser(String pID);

   /**
    * Checks if the given password is valid for the person with the id given.
    *
    * @param pID The id of the password keeper
    * @param pPwd The password to be validated
    * @return True if the given password is valid
    */
   boolean checkPwd(String pID, String pPwd);

   /**
    * Returns a collection of all available assets in the time frame given.
    *
    * @param pStart The start date
    * @param pEnd The end date
    * @return The collection of available assets
    */
   Collection<Asset> getAvailAsset(Date pStart, Date pEnd);

   /**
    * Returns a collection of people who have borrowed the asset.
    *
    * @param pAsset The asset to get a borrow history for
    * @return The people who have borrowed this asset
    */
   Collection<Person> getBorrowers(Asset pAsset);

   /**
    * Gets the active requests based on a Person.
    *
    * @param pPerson The person to get the requests for
    * @return The active requests
    */
   Collection<Request> getActiveRequests(Person pPerson);

   /**
    * Returns a collection of all the assets this person has borrowed.
    *
    * @param pPerson The person to get an asset history for
    * @return The assets this person has borrowed
    */
   Collection<Asset> getAssets(Person pPerson);

   /**
    * The number of requests made in the database
    *
    * @return The number of requests
    */
   int getNumRequests();

   /**
    * The number of checkouts made in the database
    *
    * @return The number of checkouts made
    */
   int getNumCheckouts();

   /**
    * The number of logs made in the database
    *
    * @return The number of logs made
    */
   int getNumLogs();

   /**
    * Allows for any interested listerners for state change in to model
    * to register with this model.
    *
    * @param pObserver The interested listener
    */
   void registerObserver(ModelObserver pObserver);
}
