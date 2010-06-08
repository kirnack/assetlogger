/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assetl.service.alus;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import resources.SecurityFunctions;

/**
 *
 * @author brogers3
 */
public class encryptCurrentUsers
   implements ScriptAction
{
   File mFile;

   public static void main(String[] args)
   {
      encryptCurrentUsers crypto;
      for (String arg : args)
      {
         try
         {
            crypto = new encryptCurrentUsers(arg);
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

   public encryptCurrentUsers()
   {
      mFile = null;
   }

   public encryptCurrentUsers(String fileName)
      throws ParseException
   {
      this(new File(fileName));
   }

   public boolean setFile(File pFile)
      throws ParseException
   {
      if (pFile.getName().endsWith(".aldb"))
      {
         mFile = pFile;
         return true;
      }
      return false;
   }

   public encryptCurrentUsers(File fileName)
      throws ParseException
   {
      if (!setFile(fileName))
      {
         throw new ParseException("wrong file type.", -42);
      }
   }

   public boolean update()
   {
      if (mFile != null)
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
            Map<String, String> users = new HashMap<String, String>();
            while (rs.next())
            {
               users.put(rs.getString("UserID"),
                  SecurityFunctions.byteArrayToHexString(
                  SecurityFunctions.computeHash(
                  rs.getString("Password"))));
            }

            prepReq = conn.prepareStatement("update Users set "
               + "Password=? where UserID=?");

            int mapsize = users.size();

            Iterator keyValuePairs1 = users.entrySet().iterator();
            for (int i = 0; i < mapsize; i++)
            {
               Map.Entry entry = (Map.Entry) keyValuePairs1.next();
               prepReq.setString(1, (String) entry.getValue());
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
            catch (Exception ey)
            {
               ey.printStackTrace();
            }

            try
            {
               conn.close();
            }
            catch (Exception ex)
            {
               ex.printStackTrace();
            }

            e.printStackTrace();
            return false;
         }

         return true;
      }
      return false;
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
