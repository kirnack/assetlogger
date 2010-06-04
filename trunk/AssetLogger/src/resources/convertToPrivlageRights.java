/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package resources;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author brogers3
 */
public class convertToPrivlageRights
{
   File mFile;

   public static void main(String[] args)
   {
      convertToPrivlageRights crypto;
      for (String arg : args)
      {
         try
         {
            crypto = new convertToPrivlageRights(arg);
            if (!crypto.update())
            {
               System.err.println("An error occurced when updating'"
                  + arg + "'.");
            }
         }
         catch (ParseException e)
         {
            System.err.println("'" + arg + "' is not a valid AssetLogger File");
         }

      }
   }

   public convertToPrivlageRights(String fileName)
      throws ParseException
   {
      if (fileName.endsWith(".aldb"))
      {
         mFile = new File(fileName);
      }
      else
      {
         throw new ParseException("wrong file type.", -42);
      }
   }

   public convertToPrivlageRights(File fileName)
      throws ParseException
   {
      if (fileName.getName().endsWith(".aldb"))
      {
         mFile = fileName;
      }
      else
      {
         throw new ParseException("wrong file type.", -42);
      }
   }

   public boolean update()
   {
      loadDatabaseDriver("org.sqlite.JDBC");
      Connection conn = null;
      PreparedStatement prepReq = null;
      try
      {
         conn = DriverManager.getConnection("jdbc:sqlite:"
            + mFile.getName());
         java.sql.ResultSet rs = conn.createStatement().executeQuery(
            "select * from users");
         Map<String, Integer> users = new HashMap<String, Integer>();
         while (rs.next())
         {
            users.put(rs.getString("UserID"), (rs.getInt("isAdmin") + 2) % 3);
         }

         prepReq = conn.prepareStatement("update Users set " +
                                         "isAdmin=? where UserID=?");

         int mapsize = users.size();

         Iterator keyValuePairs1 = users.entrySet().iterator();
         for (int i = 0; i < mapsize; i++)
         {
            Map.Entry entry = (Map.Entry) keyValuePairs1.next();
            prepReq.setInt(1, ((Integer) entry.getValue()));
            prepReq.setString(2, (String) entry.getKey());
            prepReq.addBatch();
         }
         conn.setAutoCommit(false);
         prepReq.executeBatch();
         conn.commit();
      }
      catch (Exception e)
      {
         try
         {
            prepReq.close();
         }
         catch(Exception ey)
         {
            ey.printStackTrace();
         }

         try
         {
            conn.close();
         }
         catch(Exception ex)
         {
            ex.printStackTrace();
         }

         e.printStackTrace();
      }

      return true;
   }

   /**
    * Loads the class for the database driver.
    *
    * @param driverName The name of the driver class.
    */
   private void loadDatabaseDriver(String driverName)
   {
      try
      {
         Class.forName(driverName);

      }
      catch (ClassNotFoundException ex)
      {
         ex.printStackTrace();
         System.exit(-1);
      }
   }

}
