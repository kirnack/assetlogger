/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package assetl.system;

import assetl.service.DatabaseControl;

/**
 * Defines an abstract class that can be used by functions utilizing
 * the DBPacket class as its implementation of DataPacket
 *
 * @author Devin Doman
 */
public abstract class DBFunction
        extends Function
{
    /**
     * This function assumes a DBPacket
     */
    DBPacket mData;

    /**
     * Assumes the Controller is a DatabaseControl
     */
    DatabaseControl mDBControl;

    /**
     * Sets the DataPacket for this function
     *
     * @param pPacket The DataPacket sent from the view
     */
    @Override
    public void setPacket(DataPacket pPacket)
    {
        super.setPacket(pPacket);

        //convert to a DBPacket
        mData = (DBPacket) mPacket;
    }

    /**
     * Sets the controller calling the function
     *
     * @param pControl The controller calling the function
     */
    @Override
    public void setControl(AssetLControl pControl)
    {
       super.setControl(pControl);

       //convert to DatabaseControl
       mDBControl = (DatabaseControl) mControl;
    }

}
