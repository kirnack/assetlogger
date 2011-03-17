/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package assetl.service;

import java.io.IOException;

/**
 *
 * @author brogers3
 */
public interface ALClient
{
   public byte[] getData(String pDataID)
      throws IOException;

   public void putData(String pDataID);

    public String postData(final String pDataID, final String pData);
}
