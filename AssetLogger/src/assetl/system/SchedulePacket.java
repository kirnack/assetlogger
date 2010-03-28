package assetl.system;

import java.util.Date;

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
    * The start date
    */
   Date mStart;
   /**
    * The end date
    */
   Date mEnd;

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
    * Sets the start date
    *
    * @param pStart The start date
    */
   public void setStart(Date pStart)
   {
      mStart = pStart;
   }

   /**
    * Sets the end date
    *
    * @param pStart The start date
    */
   public void setEnd(Date pEnd)
   {
      mEnd = pEnd;
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
    * @return The start date
    */
   public Date getEnd()
   {
      return mEnd;
   }
}
