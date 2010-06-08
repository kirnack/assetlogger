/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package assetl.service.alus;

import java.util.HashMap;

/**
 *
 * @author brogers3
 */
public class UpdateScriptKeywords
{
   public static HashMap<String, Class> KEYWORDS;

   static
   {
      try
      {
      KEYWORDS = new HashMap<String, Class>();
      //ScriptAction scripta = new encryptCurrentUsers("*.aldb");
      KEYWORDS.put("EncryptPasswords", encryptCurrentUsers.class);
     //  scripta = new convertToPrivlageRights("*.aldb");
      KEYWORDS.put("ImplementPrivliageRights", convertToPrivlageRights.class);
      }
      catch (Exception e)
      {
         e.printStackTrace();
         System.exit(1);
      }
   }

}