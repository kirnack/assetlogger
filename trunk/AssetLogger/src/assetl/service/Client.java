package assetl.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.net.Socket;
import java.net.URL;
import java.security.Security;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import static resources.Config.*;
import static assetl.system.WebServerConstants.*;

/**
 * A Singleton ALClient to the Server.
 * The host is defualted to the host from the WebServerConstants and Config
 * classes.
 */
public class Client extends DefualtALClient
{

   private static Client cClient;

   public static Client getInstance()
   {
      if(cClient == null)
      {
         cClient = new Client(getString("serverHost", DEFAULT_SERVER_HOST),
                              getInteger("serverPort", DEFAULT_SERVER_PORT));
      }
      return cClient;
   }

   /**
    * Constructs a new Client instance.
    */
   private Client(String pHost, int pPort)
   {

      super(getInteger("bufferSize", DEFAULT_BUFFER_SIZE),
              pHost, pPort, "http://" + pHost + ":" + pPort + "/");
   }

}
