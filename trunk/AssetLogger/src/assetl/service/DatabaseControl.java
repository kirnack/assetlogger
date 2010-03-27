package assetl.service;

import java.util.Collection;
import java.util.ArrayList;
import java.util.AbstractMap;
import java.util.HashMap;

import assetl.system.AssetLControl;
import assetl.system.AssetLModel;
import assetl.system.AssetLView;
import assetl.system.ModelObserver;
import assetl.system.DataPacket;
import assetl.system.Request;
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
   ModelObserver,
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
    * A data packet that can be used to set the model and can be retrieved
    * from the view
    */
   protected DataPacket mPacket;
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
    * Map storing the choices for views the controller will make
    */
   protected AbstractMap<String, AssetLView> mHashViews;
   /**
    * The current user of the application
    */
   protected User mUser;

   /**
    * Default Contructor for the controller. Gets the model and creates
    * the view.
    */
   public DatabaseControl()
   {
      //get the model and register this controller as a listener
      mModel = Server.getInstance();
      mModel.registerObserver(this);

      mFunctions = new ArrayList<Function>();

      //The default admin
      mUser = new User();
      mUser.setID("Doctor");
      mUser.setAdmin(mModel.isAdmin("Doctor"));

      // construct the map
      mHashViews = new HashMap<String, AssetLView>();
      constructMap();

      //Use a netbeans generated gui
      mView = mHashViews.get("LogIn");

      // hide the admin components
      mView.setAdminComponents(false);
   }

   /**
    * Builds the map associations for the view choices the
    * controller will make
    */
   protected abstract void constructMap();

   /**
    * Associates multiple keys with a single view
    *
    * @param pKeys The keys to which the view will be associated
    * @param pView The view associated with the given keys
    */
   public void addViews(Collection<String> pKeys, AssetLView pView)
   {
      // associate each key with the view
      for (String key : pKeys)
      {
         mHashViews.put(key, pView);
      }
   }

   /**
    * Adds a new view to the map for the given key
    *
    * @param pKey The key to which the view will be associated
    * @param pView The view associated with the given key
    */
   public void addView(String pKey, AssetLView pView)
   {
      mHashViews.put(pKey, pView);
   }

   /**
    * Uses dynamic class loading to create a new instance of an object.
    *
    * @param pDynObj The name of the class to get an instance of
    * @return The dynamically created object
    */
   protected Object loadObj(String pClass)
   {
      Object obj = null;

      try
      {
         //load into a class object
         Class<?> clazz = Class.forName(pClass);

         //Dynamically instantiate the object
         obj = clazz.newInstance();

      }
      catch (ClassNotFoundException ex)
      {
         System.err.println("Class " + pClass + " could not be found.");
         ex.printStackTrace();
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
      return obj;
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
      Function tempFunction = getFunction(pFunction);
      //switch to a view that can perform this function
      if (switchFunction(pFunction) && (tempFunction == null))
      {
         String functionObj = "assetl.service." + pFunction + "Function";
         tempFunction = (Function) loadObj(functionObj);

         if (tempFunction != null)
         {
            // Set the model and controller for the function to work with
            tempFunction.setModules(this, mModel);
         }

         // Enable this function in the view
         mView.enable(pFunction);

         // Map the function to the name passed in to create it
         mFunctions.add(tempFunction);
      }

      return tempFunction;
   }

   /**
    * Removes all functions from the controller
    */
   public void clearFunctions()
   {
      mFunctions.clear();
   }

   /**
    * Changes to a view that can perform the function passed
    *
    * @param pFunction The function to find a pairing view for
    * @return True if there is a view that can perform the function
    */
   public boolean switchFunction(String pFunction)
   {
      // Get from the hash map a possible view
      AssetLView tempView = mHashViews.get(pFunction);
      boolean ableToChange = false;

      // if there is a mapped view change to it
      if (tempView != null)
      {
         changeView(tempView);
         ableToChange = true;
      }
      return ableToChange;
   }

   /**
    * Grabs the data packet and performs the last loaded function
    */
   public void doFunction()
   {
      String className = mFunction.getClass().getCanonicalName();
      mFunction.setPacket(mView.grabDataPacket(className));
      mFunction.performFunction();
   }

   /**
    * Performs the function the controller is currently set to.
    *
    * @param pPacket The data packet needed for the function to perform
    */
   public void doFunction(DataPacket pPacket)
   {
      mFunction.setPacket(pPacket);
      mFunction.performFunction();
   }

   /**
    * Finds the function in the collection, loads it, then performs
    * the function
    *
    * @param pFunction The function to perform
    */
   public void doFunction(String pFunction)
   {
      pFunction = "assetl.service." + pFunction + "Function";

      mFunction = getFunction(pFunction);

      // If the function was found perform the function
      if (mFunction != null)
      {
         doFunction();
      }
   }

   /**
    * Uses dynamic class loading to change the view.
    *
    * @param pView The name of the view to change to
    */
   public void changeView(String pView)
   {
      AssetLView view = (AssetLView) loadObj(pView);
      changeView(view);
   }

   /**
    * Allows the controller to change which view is currently being used
    *
    * @param pView The view to change to
    */
   public void changeView(AssetLView pView)
   {
      //if the views are not the same type change the view
      if (!pView.getClass().equals(mView.getClass()))
      {
         mView.closeView();
         mView = pView;
         //make sure this controller is the controller for the view
         mView.setControl(this);

         mView.showView();

         //reset the functions
         mFunctions.clear();

         //set admin controls
         mView.setAdminComponents(mUser.isAdmin());
      }
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
    * This method is called by the model to indicate
    * there has been a state change in the model.
    * The controller will reflect the changes in the view.
    * The model pushes all data that may have been changed.
    *
    * @param pPerson The Person object that may have changed
    * @param pAsset The Asset object that may have changed
    * @param pRequest The Request object that may have changed
    */
   public void updateData(Person pPerson, Asset pAsset, Request pRequest)
   {
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
    * Gets from the model the outstanding requests based on a Person.
    * The oustanding requests will not have a picked up date set yet for
    * its checkout collection and will not be past the requested pick up date.
    *
    * @param pPerson The person to get the requests for
    * @return The outstanding requests
    */
   public Collection<Request> getOutstandingRequests(Person pPerson)
   {
      // TODO: Have controller filter results to give to the view

      return mModel.getActiveRequests(pPerson);
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
