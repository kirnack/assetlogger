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
 * Client to the Server
 */
public class Client
{
   /**
    * The singleton instance of Client
    */
   private static Client cInstance;
   /**
    * The host name or IP address of the WebServer
    * (used to build the base URL).
    */
   private String mHost;
   /**
    * The port on which the WebServer is listening
    */
   private int mPort;
   /**
    * The URL where the WebServer can be found
    */
   private String mBaseURL;
   /**
    * The buffer size for reading data.
    */
   private int mBufferSize;
   private SSLSocketFactory factory;

   /**
    * Gets the singleton Client instance
    *
    * @return the singleton instance of the Client
    */
   public static Client getInstance()
   {
      if (cInstance == null)
      {
         cInstance = new Client();
      }

      return cInstance;
   }

   /**
    * Constructs a new Client instance.
    *
    * (Private per the Singleton pattern.)
    */
   private Client()
   {
      try
      {
         mBufferSize = getInteger("bufferSize", DEFAULT_BUFFER_SIZE);
         mHost = getString("serverHost", DEFAULT_SERVER_HOST);
         mPort = getInteger("serverPort", DEFAULT_SERVER_PORT);
         mBaseURL = "http://" + mHost + ":" + mPort + "/";
         TrustManager[] trustAllCerts = new TrustManager[]
         {
            new X509TrustManager()
            {
               public java.security.cert.X509Certificate[] getAcceptedIssuers()
               {
                  return null;
               }

               public void checkClientTrusted(
                  java.security.cert.X509Certificate[] certs, String authType)
               {
               }

               public void checkServerTrusted(
                  java.security.cert.X509Certificate[] certs, String authType)
               {
               }
            }
         };

         Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
         SSLContext sc = SSLContext.getInstance("TLS");
         sc.init(null, trustAllCerts, null);
         factory = (SSLSocketFactory) sc.getSocketFactory();
         Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
         HttpsURLConnection.setDefaultSSLSocketFactory(factory);
      }
      catch (Exception e)
      {
         e.printStackTrace();
         System.exit(1);
      }
   }

   /**
    * Gets data from the web server.
    *
    * @param pDataID a String identifying the data
    *
    * @return a byte array containing the data
    * @throws java.io.IOException
    */
   public byte[] getData(String pDataID)
      throws IOException
   {
      HttpsURLConnection httpURLConnection =
         (HttpsURLConnection) new URL(mBaseURL + pDataID).openConnection();
      httpURLConnection.setReadTimeout(300);
      ((HttpsURLConnection) httpURLConnection).setHostnameVerifier( new HostnameVerifier(){
              public boolean verify(String hostname, SSLSession session) {
                  return true;
              }
          });
      httpURLConnection.connect();
      InputStream in = httpURLConnection.getInputStream();

      if (in == null)
      {
         throw new IOException("Could not get " + pDataID + " from server.");
      }

      byte[] buffer = new byte[mBufferSize];
      int amountRead;
      int totalRead = 0;

      while ((amountRead =
         in.read(buffer, totalRead, mBufferSize - totalRead)) != -1
         && totalRead < mBufferSize)
      {
         totalRead += amountRead;
      }

      return buffer;
   }

   /**
    * Puts (sends) the named data (stored in a file) to the server,
    * replacing the old data (if any).
    *
    * @param pDataID the name of the local data (file).
    */
   public void putData(String pDataID)
   {
      try
      {
         File fileToPut = new File(pDataID);

         if (fileToPut.exists() && !mHost.equals("localhost"))
         {
            SSLSocket socket = (SSLSocket) factory.createSocket(mHost, mPort);

            OutputStream out = socket.getOutputStream();
            byte[] buffer = new byte[mBufferSize];

            out.write(("PUT /" + pDataID + " http/1.1\n").getBytes());
            out.write(("Content-Length: " + fileToPut.length() + "\n\n").
               getBytes());

            if (fileToPut.exists())
            {
               RandomAccessFile fin = new RandomAccessFile(fileToPut, "r");

               while (fin.read(buffer, 0, mBufferSize) != -1)
               {
                  out.write(buffer);
                  out.flush();
               }

               fin.close();
            }

            ((Socket) socket).shutdownOutput();

            InputStream in = socket.getInputStream();

            buffer = new byte[100];
            int amountRead;
            int totalRead = 0;

            while ((amountRead = in.read(buffer, totalRead, 100 - totalRead)) != -1
               && totalRead < 100)
            {
               totalRead += amountRead;
            }

            String line = new String(buffer);

            if (!line.contains("20"))
            {
               throw new IOException("Unexpected response from server: " + line);
            }
         }
      }
      catch (Exception e)
      {
         System.out.println(e);
      }
   }

   /**
    * Posts data to the server. Includes the capability to transfer XML
    *
    * @param pDataID identifies the data to post.
    * @param pData the data to post.
    */
   public String postData(final String pDataID, final String pData)
   {
      try
      {

         String body = new String();
         if (pDataID.toLowerCase().equals("message")
            || pDataID.toLowerCase().equals("msg"))
         {
            body = "Message=" + httpURLencode(pData);
         }
         else
         {
            body = "command=" + pDataID + "&" + pData;
         }
         SSLSocket socket = (SSLSocket) factory.createSocket(mHost, mPort);
         BufferedWriter writer =
            new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

         writer.write("POST /" + pDataID + " http/1.1\n");
         writer.write("Content-Length: " + body.length() + "\n\n");
         writer.write(body + "\n\n");

         writer.flush();

         BufferedReader reader =
            new BufferedReader(new InputStreamReader(socket.getInputStream()));
         String line = reader.readLine();


         if (line.indexOf("204") == -1 && line.indexOf("200") == -1)
         {
            throw new Exception("Failed to post to server: " + line);
         }
         /*
          * TODO:  Fix the client and the Request Handler so that they don't
          *        quit before the request has actually been sent, not the header.
          */
         String str;
         while ((str = reader.readLine()) != null)
         {
            line = str;
            System.out.println(str);
         }

         System.err.println(line);
         socket.close();
         return line;
      }
      catch (Exception e)
      {
         System.err.println("Error - could not log to server: " + e);
      }
      return null;
   }

   private String httpURLencode(final String pSource)
   {
      StringBuilder builder = new StringBuilder();

      for (char c : pSource.toCharArray())
      {
         if (Character.isLetterOrDigit(c))
         {
            builder.append(c);
         }
         else
         {
            builder.append(httpURLencode(c));
         }
      }
      return builder.toString();
   }

   private String httpURLencode(final char pSource)
   {
      String hex = Integer.toHexString((int) pSource);
      if (hex.length() > 2)
      {
         hex = hex.substring(hex.length() - 2);
      }
      else if (hex.length() < 2)
      {
         hex = "0" + hex;
      }

      return "%" + hex;
   }
}
