package assetl.service;

import java.util.Date;
import java.util.Collection;
import java.util.ArrayList;

import assetl.system.AssetLModel;
import assetl.system.Person;
import assetl.system.Asset;
import assetl.system.Request;
import assetl.system.Checkout;
import assetl.system.User;

/**
 * A class to use until the Server has completed its changes such that the
 * building of the controller can continue.
 *
 * @author Devin Doman
 */
public class DeleteMe
   implements AssetLModel
{
   /**
    * Gets a person by their id.
    *
    * @param pID The id of the person
    * @return The person with the id given
    */
   public Person getPerson(String pID)
   {
      return new Person("42", "Travis", "M", "Rice", "fake@byui.edu",
         "(608)449-4322");
   }

   /**
    * Sets a person.
    *
    * @param pPerson The person to set
    */
   public void setPerson(Person pPerson)
   {
   }

   /**
    * Gets an asset by its id.
    *
    * @param pID The id of the asset to get
    * @return The asset with the given id
    */
   public Asset getAsset(String pID)
   {
      return new Asset();
   }

   /**
    * Sets an asset.
    *
    * @param pAsset The asset to set.
    */
   public void setAsset(Asset pAsset)
   {
   }

   /**
    * Gets a request by its id.
    *
    * @param pID The id of the request.
    * @return The request with the id given.
    */
   public Request getRequest(String pID)
   {
      return new Request();
   }

   /**
    * Sets a Request.
    *
    * @param pRequest The request to set
    * @param pUserID The user id to register with the request
    */
   public void setRequest(Request pRequest, String pUserID)
   {
   }

   /**
    * Gets the Checkout object that has no returned date set
    * for the Asset given.
    *
    * @param pAsset The asset that needs to be returned
    * @return The Checkout object for this asset
    */
   public Checkout getCheckout(Asset pAsset)
   {
      return new Checkout();
   }

   /**
    * Sets a Checkout
    *
    * @param pCheckout The checkout to set
    */
   public void setCheckout(Checkout pCheckout)
   {
   }

   /**
    * Returns true if the person with the id given is an admin
    *
    * @param pID The id of the person
    * @return True if the person is an admin
    */
   public boolean isAdmin(String pID)
   {
      return true;
   }

   /**
    * Gets a user with the given id.
    *
    * @param pID The id of the user.
    * @return The user with the given id.
    */
   public User getUser(String pID)
   {
      return new User();
   }

   /**
    * Checks if the given password is valid for the person with the id given.
    *
    * @param pID The id of the password keeper
    * @param pPwd The password to be validated
    * @return True if the given password is valid
    */
   public boolean checkPwd(String pID, String pPwd)
   {
      return true;
   }

   /**
    * Returns a collection of all available assets in the time frame given.
    *
    * @param pStart The start date
    * @param pEnd The end date
    * @return The collection of available assets
    */
   public Collection<Asset> getAvailAsset(Date pStart, Date pEnd)
   {
      ArrayList<Asset> temp = new ArrayList<Asset>();
      temp.add(new Asset("676", "Apple", "#2", "asdfasf", "Mac", ""));
      temp.add(new Asset("967", "Dell", "#4", "cvxbcxb", "PC", ""));
      temp.add(new Asset("9787", "HP", "#88", "yiyuiy", "PC", ""));
      temp.add(new Asset("5677", "Dell", "#42", "qwewqeq", "PC", ""));
      return temp;
   }

   /**
    * Returns a collection of people who have borrowed the asset.
    *
    * @param pAsset The asset to get a borrow history for
    * @return The people who have borrowed this asset
    */
   public Collection<Person> getBorrowers(Asset pAsset)
   {
      return new ArrayList<Person>();
   }

   /**
    * Gets the active requests based on a Person.
    *
    * @param pPerson The person to get the requests for
    * @return The active requests
    */
   public Collection<Request> getActiveRequests(Person pPerson)
   {
      return new ArrayList<Request>();
   }

   /**
    * Returns a collection of all the assets this person has borrowed.
    *
    * @param pPerson The person to get an asset history for
    * @return The assets this person has borrowed
    */
   public Collection<Asset> getAssets(Person pPerson)
   {
      return new ArrayList<Asset>();
   }

   /**
    * The number of requests made in the database
    *
    * @return The number of requests
    */
   public int getNumRequests()
   {
      return 0;
   }

   /**
    * The number of checkouts made in the database
    *
    * @return The number of checkouts made
    */
   public int getNumCheckouts()
   {
      return 0;
   }

   /**
    * The number of logs made in the database
    *
    * @return The number of logs made
    */
   public int getNumLogs()
   {
      return 0;
   }

   /**
    * Takes the passed Checkout object and makes corresponding changes to
    * the database based on that object
    *
    * @param pCheckout The checkout to make changes based on.
    * @param pUserID The user who made the change.
    */
   public void setCheckout(Checkout pCheckout, String pUserID)
   {
   }

   /**
    * Creates a Checkout with the ID that is passed, based on the data in the
    * database.
    *
    * @param pID The ID of the checkout to create a checkout of.
    * @return The checkout with the passed ID.
    */
   public Checkout getCheckout(String pID)
   {
      return new Checkout();
   }

   /**
    * Creates a collection of checkouts that are owned by the passed request.
    *
    * @param pRequest The request which owns all the returned checkouts.
    * @return A collection of checkouts that are part of the passed request.
    */
   public Collection<Checkout> getCheckouts(Request pRequest)
   {
      return new ArrayList<Checkout>();
   }

   /**
    * Creates a collection of all users.
    *
    * @return A collection of all users.
    */
   public Collection<User> getUsers()
   {
      return new ArrayList<User>();
   }
}
