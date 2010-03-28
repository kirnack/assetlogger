package resources;

import java.util.Date;
import java.util.Calendar;

/**
 * Provides static methods to ease the conversion bewteen Date
 * int data types.
 *
 * @author Devin Doman
 */
public class DateConverter
{
   /**
    * Converts int month, day, and year variables into a Date object
    *
    * @param pMonth The month
    * @param pDay The day
    * @param pYear The year
    * @return The Date object with the values passed in
    */
   public static Date intsToDate(int pMonth, int pDay, int pYear)
   {
      //make a local calander and set the date
      Calendar cal = Calendar.getInstance();
      cal.set(pYear, pMonth - 1, pDay);
      return cal.getTime();
   }

   /**
    * Converts string month, day, and year variables to a Date object
    *
    * @param pMonth The month
    * @param pDay The day
    * @param pYear The year
    * @return The Date object with the values passed in
    */
   public static Date stringsToDate(String pMonth, String pDay, String pYear)
   {
      try
      {
         int month = Integer.parseInt(pMonth);
         int day = Integer.parseInt(pDay);
         int year = Integer.parseInt(pYear);
         return DateConverter.intsToDate(month, day, year);
      }
      catch (NumberFormatException ex)
      {
         System.err.println("Not a number");
         return null;
      }
   }
}
