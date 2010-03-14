package assetl.system;

/**
 * Provides an inteface for any object that wishes to observer the model
 * 
 * @author Devin Doman
 */
public interface ModelObserver
{
    /**
     * This method is called by the model to indicate
     * there has been a state change in the model.
     * The controller will reflect these changes in
     * the view. The controller then makes a pull
     * on the data it might want to care about.
     */
    void updateData();

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
    void updateData(Person pPerson, Asset pAsset, Request pRequest);
}
