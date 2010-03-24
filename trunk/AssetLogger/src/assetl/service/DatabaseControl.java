package assetl.service;

import assetl.desktop.CheckinView;
import java.util.Collection;
import java.util.AbstractMap;
import java.util.HashMap;

import assetl.system.AssetLControl;
import assetl.system.AssetLModel;
import assetl.system.AssetLView;
import assetl.system.ModelObserver;
import assetl.system.DataPacket;
import assetl.system.DBPacket;
import assetl.system.Request;
import assetl.system.Person;
import assetl.system.Asset;
import assetl.system.User;
import assetl.desktop.LoginView;
import assetl.desktop.ServiceView;
import assetl.desktop.IDView;
import assetl.desktop.FindAssetView;
import assetl.desktop.SureView;

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
public class DatabaseControl 
        implements AssetLControl, ModelObserver, Runnable
{
    /**
     * The Model
     */
    private AssetLModel mModel;

    /**
     * The View
     */
    private AssetLView mView;

    /**
     * A data packet that can be used to set the model and can be retrieved
     * from the view
     */
    private DataPacket mPacket;

    /**
     * Stores the behavior the controller can currently perform with
     * the DataPacket.
     */
    private Function mFunction;

    /**
    * Map storing the choices for views the controller will make
    */
    private AbstractMap<String, AssetLView> mHashViews;

    /**
     * Default Contructor for the controller. Gets the model and creates
     * the view.
     */
    public DatabaseControl()
    {
        //get the model and register this controller as a listener
        mModel = Server.getInstance();
        mModel.registerObserver(this);

        mPacket = new DBPacket();
        //The default admin
        DBPacket packet = (DBPacket) mPacket;
        packet.setUserID("Doctor");
        packet.setIsAdmin(mModel.isAdmin(packet.getUserID()));
        mPacket = packet;

        mPacket = new DBPacket();

        // construct the map
        mHashViews = new HashMap<String, AssetLView>();
        AssetLView tempView = new ServiceView(this);
        addView("Schedule", tempView);
        addView("Checkout", tempView);
        addView("Cancel", tempView);

        tempView = new LoginView(this);
        addView("LogIn", tempView);

        tempView = new IDView(this);
        addView("Checkin", tempView);
        addView("IDTeacher", tempView);

        tempView = new FindAssetView(this);
        addView("FindAsset", tempView);
        
        tempView = new SureView(this);
        addView("Sure", tempView);

        //Use a netbeans generated gui
        mView = mHashViews.get("LogIn");

        // hide the admin components
        mView.setAdminComponents(false);
    }

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
    private Object loadObj(String pClass)
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
     * Change the function for the controller to perform for the view
     * 
     * @param pFunction The name of the function to set
     */
    public void setFunction(String pFunction)
    {
        //switch to a view that can perform this function
        if (switchFunction(pFunction))
        {
            String functionObj = "assetl.service." + pFunction + "Function";
            mFunction = (Function) loadObj(functionObj);

            if (mFunction != null)
            {
                //
                // Set the model, data, and controller
                // for the function to work with
                //
                
                mFunction.setModules(this, mModel);
                mFunction.setPacket(mPacket);
            }
            
            mView.enable(pFunction);
        }
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
     * Performs the function the controller is currently set to.
     * 
     * @param pPacket The data packet needed for the function to perform
     */
    public void doFunction(DataPacket pPacket)
    {
        mView.grabDataPacket();
        mFunction.setPacket(pPacket);
        mFunction.performFunction();
    }

    /**
     * Performs the function indicated by the string passed in.
     *
     * @param pFunction The function to perform
     * @param pPacket The data packet needed for the function to perform
     */
    public void doFunction(String pFunction, DataPacket pPacket)
    {
        setFunction(pFunction);
        doFunction(pPacket);
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
