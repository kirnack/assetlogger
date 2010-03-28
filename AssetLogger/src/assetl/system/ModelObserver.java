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
}
