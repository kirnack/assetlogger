package assetl.system;

/**
 * Encapsulates the data needed to be sent by the view to the controller
 * to schedule an asset.
 *
 * @author Devin Doman
 */
public class SchedulePacket
   implements DataPacket
{
   /**
    * The id needed to get a person from the model
    */
   String mPersonID;
   /**
    * The id needed to get an asset from the model
    */
   String mAssetID;
   /**
    * The starting month
    */
   int mStartMon;
   /**
    * The starting day
    */
   int mStartDay;
   /**
    * The starting year
    */
   int mStartYear;
   /**
    * The ending month
    */
   int mEndMon;
   /**
    * The ending day
    */
   int mEndDay;
   /**
    * The ending day
    */
   int mEndYear;

   /**
    * Sets the person id
    *
    * @param pPersonID The person id
    */
   public void setPersonID(String pPersonID)
   {
      mPersonID = pPersonID;
   }

   /**
    * Sets the asset id
    *
    * @param pAssetID The asset id
    */
   public void setAssetID(String pAssetID)
   {
      mAssetID = pAssetID;
   }

   /**
    * Sets the start month
    *
    * @param pStartMon The start month
    */
   public void setStartMon(int pStartMon)
   {
      mStartMon = pStartMon;
   }

   /**
    * Sets the start day
    *
    * @param pStartDay The start day
    */
   public void setStartDay(int pStartDay)
   {
      mStartDay = pStartDay;
   }

   /**
    * Sets the start year
    *
    * @param pStartYear The start year
    */
   public void setStartYear(int pStartYear)
   {
      mStartYear = pStartYear;
   }

   /**
    * Sets the end month
    *
    * @param pEndMon The end month
    */
   public void setEndMon(int pEndMon)
   {
      mEndMon = pEndMon;
   }

   /**
    * Sets the end day
    *
    * @param pEndDay The end day
    */
   public void setEndDay(int pEndDay)
   {
      mEndDay = pEndDay;
   }

   /**
    * Sets the end year
    *
    * @param pEndYear The end year
    */
   public void setEndYear(int pEndYear)
   {
      mEndYear = pEndYear;
   }

   /**
    * Gets the person id
    *
    * @return The person id
    */
   public String getPersonID()
   {
      return mPersonID;
   }

   /**
    * Gets the asset id
    *
    * @return The asset id
    */
   public String getAssetID()
   {
      return mAssetID;
   }

   /**
    * Gets the start month
    *
    * @return The start month
    */
   public int getStartMon()
   {
      return mStartMon;
   }

   /**
    * Gets the start day
    *
    * @return The start day
    */
   public int getStartDay()
   {
      return mStartDay;
   }

   /**
    * Gets the start year
    *
    * @return The start year
    */
   public int getStartYear()
   {
      return mStartYear;
   }

   /**
    * Gets the end month
    *
    * @return The end month
    */
   public int getEndMon()
   {
      return mEndMon;
   }

   /**
    * Gets the end day
    *
    * @return The end day
    */
   public int getEndDay()
   {
      return mEndDay;
   }

   /**
    * Gets the end year
    *
    * @return The end year
    */
   public int getEndYear()
   {
      return mEndYear;
   }
}
