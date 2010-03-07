/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package assetl.system;

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
    void schedule(Person pPerson, Asset pAsset, Date pStart, Date pEnd);
    void checkout(Request pRequest);
    void checkin(Request pRequest);
    void cancel(Request pRequest);
}
