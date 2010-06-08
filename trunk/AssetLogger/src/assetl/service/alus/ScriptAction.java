/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package assetl.service.alus;

import java.io.File;
import java.text.ParseException;

/**
 *
 * @author brogers3
 */
public interface ScriptAction
{
   public boolean update();
   public boolean setFile(File pFile)
      throws ParseException;
}
