package assetl.service;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Date;

import resources.ObjectLoader;
import assetl.system.AssetLControl;
import assetl.system.AssetLModel;
import assetl.system.AssetLView;
import assetl.system.DataPacket;
import assetl.system.Request;
import assetl.system.Checkout;
import assetl.system.Person;
import assetl.system.Asset;
import assetl.system.User;

/**
 * The Database Controller is used to modify the Server model in response
 * to user gestures from the view. Specifically it can send Request objects
 * containing an array of Checkout objects to the Server to be added to the
 * database. It listens for any state changes in the model made by other
 * controller instances and then updates the view accordingly. It creates the
 * view and modifies the components displayed to the user. The view can call
 * its schedule, checkout, checkin, and cancel functions to indicate the
 * user wishes those events to occur.
 *
 * @author Devin Doman
 */
public abstract class DatabaseControl
   implements AssetLControl,
   Runnable
{
   /**
    * The Model
    */
   protected AssetLModel mModel;
   /**
    * The View
    */
   protected AssetLView mView;
   /**
    * Holds the last function performed by the controller
    */
   protected Function mFunction;
   /**
    * Stores the behaviors the controller can currently perform, mapped
    * with the name of the function
    */
   protected Collection<Function> mFunctions;
   /**
    * The current user of the application
    */
   protected User mUser;
   /**
    * Stores the package the controller will dynamically load a class from
    */
   protected String mPackage;
   /**
    * Stores any part of the class name that needs to be appended to the
    * end of the object for dynamic loading.
    */
   protected String mPostClass;

   /**
    * Default Contructor for the controller. Gets the model and creates
    * the view.
    */
   public DatabaseControl()
   {
      //get the model
      mModel = Server.getInstance();

      // TODO: delete the following line of test code
      //mModel = new Mimick();
      mFunctions = new ArrayList<Function>();
      mPostClass = "";

      //default user
      mUser = new User();
      mUser.setAdmin(false);

      //Use a netbeans generated gui
      changeView("Login");
   }

   /**
    * Sets the package for the class name needed to dynamically load the class
    */
   protected abstract void setViewPackage();

   /**
    * Appends a string to the end of the class name to dynamically load.
    */
   protected abstract void setPostViewName();

   /**
    * Sets the package for the class name needed to dynamically load the class
    * @param pPackage
    */
   protected void setClassPackage(String pPackage)
   {
      mPackage = pPackage;
   }

   /**
    * Appends a string to the end of the class name to dynamically load.
    * 
    * @param pPostClass
    */
   protected void setPostClassName(String pPostClass)
   {
      mPostClass = pPostClass;
   }

   /**
    * Sets the pre and post ambles of the class name for views that
    * need to by dynamically loaded.
    */
   protected void setViewAmbles()
   {
      setViewPackage();
      setPostViewName();
   }

   /**
    * Sets the pre and post ambles of the class name for function classes
    * that need to by dynamically loaded.
    */
   protected void setFunctionAmbles()
   {
      setClassPackage("assetl.service.");
      setPostClassName("Function");
   }

   /**
    * Addes the pre and post ambles of the class name to the string passed in
    * to prepare for dynamic class loading.
    *
    * @param pName The name of the class to add the ambles to
    * @return The cannonical class name
    */
   protected String addAmbles(String pName)
   {
      return mPackage + pName + mPostClass;
   }

   /**
    * Appends string before and after a class name to dynamically load.
    *
    * @param pPackage The package portion of the class name
    * @param pPostClass Any string needed to append at the end of the class name
    */
   protected void setClassAmbles(String pPackage, String pPostClass)
   {
      setClassPackage(pPackage);
      setPostClassName(pPostClass);
   }

   /**
    * Loads and then sets the passed function as the controllers current
    * function
    *
    * @param pFunction The function to set
    */
   public void setFunction(String pFunction)
   {
      mFunction = addFunction(pFunction);
   }

   /**
    * Gets the function specified out of the collection
    *
    * @param pFunction The function to look for
    * @return The function, null if not found
    */
   public Function getFunction(String pFunction)
   {
      setFunctionAmbles();
      pFunction = addAmbles(pFunction);

      Function findMe = null;

      for (Function funct : mFunctions)
      {
         Class<?> clazz = funct.getClass();

         // If the function is found
         if (clazz != null && pFunction.equals(clazz.getCanonicalName()))
         {
            findMe = funct;
            break;
         }
      }

      return findMe;
   }

   /**
    * Change the function for the controller to perform for the view
    *
    * @param pFunction The name of the function to set
    * @return The function object just added to the controller
    */
   public Function addFunction(String pFunction)
   {
      // Check the function collection for this function
      Function tempFunction = getFunction(pFunction);

      // Add the function to the collection if it is not
      if (tempFunction == null)
      {
         setFunctionAmbles();
         tempFunction = (Function) ObjectLoader.loadObj(addAmbles(pFunction));

         // If we were able to dynamically load the function
         if (tempFunction != null)
         {
            // Set the model and controller for the function to work with
            tempFunction.setModules(this, mModel);
            // Map the function to the name passed in to create it
            mFunctions.add(tempFunction);
         }
      }
      return tempFunction;
   }

   /**
    * Enables the function in the current view
    *
    * @param pFunction The function to enable
    */
   public void enableFunction(String pFunction)
   {
      mView.enable(pFunction);
   }

   /**
    * Sets the function, grabs the DataPacket from the view, then
    * performs the function.
    *
    * @param pFunction The function to perform
    */
   public void doFunction(String pFunction)
   {
      try
      {
         setFunction(pFunction);
         mFunction.setPacket(mView.grabDataPacket(pFunction));
         mFunction.performFunction();
      }
      catch (Exception ex)
      {
         System.err.println("The function could not be performed");
         ex.printStackTrace();
      }
   }

   /**
    * Uses dynamic class loading to change the view.
    *
    * @param pView The name of the view to change to
    */
   public void changeView(String pView)
   {
      setViewAmbles();
      AssetLView view = (AssetLView) ObjectLoader.loadObj(addAmbles(pView), this);
      changeView(view);
   }

   /**
    * Allows the controller to change which view is currently being used
    *
    * @param pView The view to change to
    */
   public void changeView(AssetLView pView)
   {
      // If the controller has no view load it
      if (mView == null)
      {
         mView = pView;
      }
      else if (!pView.getClass().equals(mView.getClass()))
      {
         //
         // If the views are not the same type change the view
         //

         mView.closeView();
         mView = pView;
         mView.showView();

         //reset the functions
         mFunctions.clear();
      }
      mView.setAdminComponents(mUser.isAdmin());
   }

   /**
    * This method is called by the model to indicate
    * there has been a state change in the model.
    * The controller will reflect these changes in
    * the view. The controller then makes a pull
    * on the data it might want to care about.
    */
   public void updateData()
   {
      //tell the view to update its display
      mView.updateData();
   }

   /**
    * Sends a DataPacket to the controller's current view
    *
    * @param pPacket The packet to send
    */
   public void sendViewPacket(DataPacket pPacket)
   {
      mView.receiveDataPacket(pPacket);
   }

   /**
    * Returns the User represented by the ID given.
    *
    * @param pID The id of the user
    * @return The User identified
    */
   public User getUser(String pID)
   {
      return mModel.getUser(pID);
   }

   /**
    * Returns the Person represented by the ID given.
    *
    * @param pID The unique identification number for this person
    * @return The Person identified
    */
   public Person getPerson(String pID)
   {
      return mModel.getPerson(pID);
   }

   /**
    * Returns the Asset represented by the ID given
    *
    * @param pID The unique identification number for this asset
    * @return The Asset identified
    */
   public Asset getAsset(String pID)
   {
      return mModel.getAsset(pID);
   }

   /**
    * Gets from the model the assets that are available to be
    * scheduled or checked out with the given start and end dates
    *
    * @param pStart The start date
    * @param pEnd The end date
    * @return The assets available between the dates
    */
   public Collection<Asset> getAvailableAssets(Date pStart, Date pEnd)
   {
      return mModel.getAvailAsset(pStart, pEnd);
   }

   /**
    * Gets the collection of active requests from the model based on the
    * person and then splits the active requests into a collection of
    * those that are scheduled and those that are checked out. The collection
    * returned is based on the string passed in indicating what is needed
    *
    * @param pNeeded The needed collection to get from the split
    * @param pPerson The person to get the collection for
    * @return The needed collection
    */
   protected Collection<Request> splitRequests(String pNeeded, Person pPerson)
   {
      ArrayList<Request> active = new ArrayList<Request>();
      ArrayList<Checkout> checkouts = new ArrayList<Checkout>();
      ArrayList<Request> scheduled = new ArrayList<Request>();
      ArrayList<Request> checkedout = new ArrayList<Request>();


      //Get the active requests from the model
      active = (ArrayList<Request>) mModel.getActiveRequests(pPerson);

      for (Request request : active)
      {
         //
         // Get the checkouts contained in a request, if the checkouts don't
         // have a pickedup date add it to the list of scheduled requests
         // otherwise put it in the list of checked out requests if
         // it does not have a returned date set.
         //

         checkouts = (ArrayList<Checkout>) request.getCheckouts();
         if (!checkouts.isEmpty())
         {
            if (checkouts.get(0).getPickedupDate() == null)
            {
               //The active checkouts that have not been picked up

               scheduled.add(request);
            }
            else
            {
               //The active checkouts that have been picked up

               checkedout.add(request);
            }
         }
      }
      if ("Scheduled".equals(pNeeded))
      {
         return scheduled;
      }
      else if ("CheckedOut".equals(pNeeded))
      {
         return checkedout;
      }
      return null;
   }

   /**
    * Gets from the model the the scheduled requests based on a Person.
    * The scheduled requests will not have a picked up date set yet for
    * its checkout collection and will not be past the requested pick up date-
    * they will not have been checked out yet.
    *
    * @param pPerson The person to get the requests for
    * @return The scheduled requests
    */
   public Collection<Request> getScheduledRequests(Person pPerson)
   {
      return splitRequests("Scheduled", pPerson);
   }

   /**
    * Returns the people who have recently borrowed the given asset
    *
    * @param pAsset The asset borrowed
    * @return The people who have borrowed the asset
    */
   public Collection<Person> getPastBorrowers(Asset pAsset)
   {
      return mModel.getBorrowers(pAsset);
   }

   /**
    * Returns the assets recently borrowed by the person given
    *
    * @param pPerson The person who has borrowed the assets
    * @return The assets borrowed by the person
    */
   public Collection<Asset> getAssetHistory(Person pPerson)
   {
      return mModel.getAssets(pPerson);
   }

   /**
    * Gets from the model the checked out requests based on a Person.
    * These will be those requests that have been checked out to a person
    * but not yet returned.
    *
    * @param pPerson The person to get the requests for
    * @return The checked out requests
    */
   public Collection<Request> getCheckedOutRequests(Person pPerson)
   {
      return splitRequests("CheckedOut", pPerson);
   }

   /**
    * Sets the current user of the application
    *
    * @param pUser The user to set
    */
   public void setCurrentUser(User pUser)
   {
      mUser = pUser;
      mView.setAdminComponents(mUser.isAdmin());
   }

   /**
    * Gets the current user of the application
    *
    * @return The current user
    */
   public User getCurrentUser()
   {
      return mUser;
   }

   /**
    * Returns the current view
    *
    * @return The current view
    */
   public AssetLView getView()
   {
      return mView;
   }

   /**
    * Starts the controller.
    */
   public void run()
   {
      mView.showView();
   }
}
