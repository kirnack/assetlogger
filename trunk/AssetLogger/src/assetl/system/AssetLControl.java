package assetl.system;

import java.util.Collection;
import java.util.Date;

/**
 * Provides a common interface for the controller module in the 
 * Model-View-Controller structure. Defines AssetLogger behavior,
 * maps user actions to model updates.
 * 
 * @author Devin Doman
 */
public interface AssetLControl
{

   /**
    * Uses dynamic class loading to change the view.
    *
    * @param pView The name of the view to change to
    */
   void changeView(String pView);

   /**
    * Enables a function in the current view
    *
    * @param pFunction The function to enable
    */
   void enableFunction(String pFunction);

   /**
    * Sets the function from the collection, grabs the DataPacket from the view,
    * then performs the function.
    *
    * @param pFunction The function to perform
    */
   void doFunction(String pFunction);

   /**
    * Gets the current user of the application
    *
    * @return The current user
    */
   User getCurrentUser();

   /**
    * Sends a DataPacket to the controller's current view
    *
    * @param pPacket The packet to send
    */
   void sendViewPacket(DataPacket pPacket);

   //
   // The following methods allow the controller to server as Mediator
   // between the View and Model
   //
   /**
    * Returns the User represented by the ID given.
    *
    * @param pID The id of the user
    * @return The User identified
    */
   User getUser(String pID);

   /**
    * Returns the Person represented by the ID given.
    *
    * @param pID The unique identification number for this person
    * @return The Person identified
    */
   Person getPerson(String pID);

   /**
    * Returns the Asset represented by the ID given
    *
    * @param pID The unique identification number for this asset
    * @return The Asset identified
    */
   Asset getAsset(String pID);

   /**
    * Gets from the model the assets that are available to be
    * scheduled or checked out with the given start and end dates
    *
    * @param pStart The start date
    * @param pEnd The end date
    * @return The assets available between the dates
    */
   Collection<Asset> getAvailableAssets(Date pStart, Date pEnd);

   /**
    * Gets from the model the outstanding requests based on a Person.
    * The oustanding requests will not have a picked up date set yet for
    * its checkout collection and will not be past the requested pick up date.
    *
    * @param pPerson The person to get the requests for
    * @return The outstanding requests
    */
   Collection<Request> getOutstandingRequests(Person pPerson);
}
