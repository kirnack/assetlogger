/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package assetl.system;

import java.util.Date;

/**
 * Contains all the data that the view can send to the controller so that
 * the controller can change the model. In this case the data reflects
 * that stored in the Server object.
 *
 * @author Devin Doman
 */
public class DBPacket 
        implements DataPacket
{
    /**
     * The id of the user
     */
    private String mUserID;

    /**
     * True if the user is an admin
     */
    private boolean mAdmin;

    /**
     * The current person
     */
    protected Person mPerson;

    /**
     * The current laptop
     */
    protected Asset mAsset;

    /**
     * The desired start date
     */
    protected Date mStart;

    /**
     * The desired end date
     */
    protected Date mEnd;

    /**
     * The current request
     */
    protected Request mRequest;

    /**
     * The current checkout
     */
    protected Checkout mCheckout;

    /**
     * Default Constructor
     */
    public DBPacket()
    {
        setData(null, null, null, null, null, null);
    }

    /**
     * Constructor with parameters for all member variables.
     *
     * @param pPerson The person data
     * @param pAsset The asset data
     * @param pStart The start date
     * @param pEnd The end date
     * @param pRequest The request data
     * @param pCheckout The checkout data
     */
    public DBPacket(Person pPerson, Asset pAsset, Date pStart, Date pEnd, Request pRequest, Checkout pCheckout)
    {
        setData(pPerson, pAsset, pStart, pEnd, pRequest, pCheckout);
    }

    /**
     * Sets all the data in the packet
     *
     * @param pPerson The person data
     * @param pAsset The asset data
     * @param pStart The start date
     * @param pEnd The end date
     * @param pRequest The request data
     * @param pCheckout The checkout data
     */
    public void setData(Person pPerson, Asset pAsset, Date pStart, Date pEnd, Request pRequest, Checkout pCheckout)
    {
        mPerson = pPerson;
        mAsset = pAsset;
        mStart = pStart;
        mEnd = pEnd;
        mRequest = pRequest;
        mCheckout = pCheckout;
    }

    /**
     * Sets the user identification
     *
     * @param pUserID The user id
     */
    public void setUserID(String pUserID)
    {
        mUserID = pUserID;
    }

    /**
     * Sets whether the user is an admin or not
     *
     * @param pIsAdmin True if the user is an admin
     */
    public void setIsAdmin(boolean pIsAdmin)
    {
        mAdmin = pIsAdmin;
    }

    /**
     * Sets a person
     *
     * @param pPerson The person data
     */
    public void setPerson(Person pPerson)
    {
        mPerson = pPerson;
    }

    /**
     * Sets an asset
     *
     * @param pAsset The asset data
     */
    public void setAsset(Asset pAsset)
    {
        mAsset = pAsset;
    }

    /**
     * Sets a start date
     *
     * @param pStart The start date
     */
    public void setStart(Date pStart)
    {
        mStart = pStart;
    }

    /**
     * Sets an end date
     *
     * @param pEnd The end date
     */
    public void setEnd(Date pEnd)
    {
        mEnd = pEnd;
    }

    /**
     * Sets the request
     *
     * @param pRequest The request data
     */
    public void setRequest(Request pRequest)
    {
        mRequest = pRequest;
    }

    /**
     * Sets the checkout
     *
     * @param pCheckout The checkout data
     */
    public void setCheckout(Checkout pCheckout)
    {
        mCheckout = pCheckout;
    }

    /**
     * Gets the user identification
     *
     * @return The user id
     */
    public String getUserID()
    {
        return mUserID;
    }

    /**
     * Gets whether the user is an admin or not
     *
     * @return True if the user is an admin
     */
    public boolean getIsAdmin()
    {
        return mAdmin;
    }

    /**
     * Gets the person data
     *
     * @return The person data
     */
    public Person getPerson()
    {
        return mPerson;
    }

    /**
     * Gets the asset data
     *
     * @return The asset data
     */
    public Asset getAsset()
    {
        return mAsset;
    }

    /**
     * Gets the start date
     *
     * @return The start date
     */
    public Date getStart()
    {
        return mStart;
    }

    /**
     * Gets the end date
     *
     * @return The end date
     */
    public Date getEnd()
    {
        return mEnd;
    }

    /**
     * Gets the request data
     *
     * @return The request data
     */
    public Request getRequest()
    {
        return mRequest;
    }

    /**
     * Gets the checkout data
     *
     * @return The checkout data
     */
    public Checkout getCheckout()
    {
        return mCheckout;
    }
}
