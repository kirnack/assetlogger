/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package assetl.system;

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
     *
     * @param pPerson
     * @param pAsset
     * @param pStrtMon
     * @param pStrtDay
     * @param pStrtYear
     * @param pEndMon
     * @param pEndDay
     * @param pEndYear
     */
    void schedule(String pPerson, String pAsset,
                  String pStrtMon, String pStrtDay, String pStrtYear,
                  String pEndMon, String pEndDay, String pEndYear);
    /**
     *
     * @param pPerson
     * @param pAsset
     * @param pEndMon
     * @param pEndDay
     * @param pEndYear
     */
    void checkout(String pPerson, String pAsset,
                  String pEndMon, String pEndDay, String pEndYear);
    /**
     *
     * @param pPerson
     * @param pAsset
     */
    void checkin(String pPerson, String pAsset);
    /**
     * 
     * @param pPerson
     * @param pAsset
     */
    void cancel(String pPerson, String pAsset);
}