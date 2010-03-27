package assetl.system;

/**
 * Contains data needed to make a new laptop asset
 *
 * @author Devin Doman
 */
public class LaptopPacket
   implements DataPacket
{
   String mBarCode;
   String mMake;
   String mLaptopNumber;
   String mSerialNumber;
   boolean mIsMaintenance;

   public void setBarCode(String pBarCode)
   {
      mBarCode = pBarCode;
   }

   public void setMake(String pMake)
   {
      mMake = pMake;
   }

   public void setLaptopNumber(String pLaptopNumber)
   {
      mLaptopNumber = pLaptopNumber;
   }

   public void setSerialNubmer(String pSerialNumber)
   {
      mSerialNumber = pSerialNumber;
   }

   public void setMaintenance(boolean pIsMaintenance)
   {
      mIsMaintenance = pIsMaintenance;
   }

   public String getBarCode()
   {
      return mBarCode;
   }

   public String getMake()
   {
      return mMake;
   }

   public String getLaptopNumber()
   {
      return mLaptopNumber;
   }

   public String getSerialNubmer()
   {
      return mSerialNumber;
   }

   public boolean getMaintenance()
   {
      return mIsMaintenance;
   }
}
