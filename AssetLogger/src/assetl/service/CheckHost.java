/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package assetl.service;

import java.io.IOException;
import static resources.Config.*;
import static assetl.system.WebServerConstants.*;

/**
 * Uses the DefualtALClient to post, put, and get data from any
 * host.
 * @author Bryon Rogers
 */
public class CheckHost
{
   private int mPort;
   private int mBufferSize;

   CheckHost()
   {
      mPort = getInteger("serverPort", DEFAULT_SERVER_PORT);
      mBufferSize = getInteger("bufferSize", DEFAULT_BUFFER_SIZE);
   }

    public byte[] getData(String pDataID, String pHost)
      throws IOException
    {
       return new DefualtALClient(mBufferSize, pHost, mPort, 
                                  "http://" + pHost + ":" + mPort + "/")
                                  .getData(pDataID);
    }

    public void putData(String pDataID, String pHost)
    {
       new DefualtALClient(mBufferSize, pHost, mPort, "http://" + pHost + ":"
          + mPort + "/").putData(pDataID);
    }

    public String postData(final String pDataID, final String pData,
       String pHost)
    {
       return new DefualtALClient(mBufferSize, pHost, mPort, "http://" + pHost 
          + ":" + mPort + "/").postData(pDataID, pData);
    }
}
