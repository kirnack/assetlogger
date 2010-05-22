package assetl.service;


import java.io.*;

import java.net.*;

import java.util.*;

import static resources.Config.*;
import static assetl.system.WebServerConstants.*;

/**
 * Handles requests for the Server
 *
 */
public class RequestHandler
   extends Thread
{
   /**
    * The default buffer size.
    */
   private static final int DEFAULT_BUFFER_SIZE = 8196;

   /**
    * Keeps track of number of pages served.
    */
   private static int cPagesServed = 0;

   /**
    * Keeps track of number of bytes read in.
    */
   private static int cBytesIn = 0;

   /**
    * Keeps track of number of bytes written out.
    */
   private static int cBytesOut = 0;

   /**
    * Has a disconnect command been received?
    */
   private static boolean cDisconnectReceived;

   /**
    * Can we handle requests?
    */
   private static boolean cCanHandleRequests;

   /**
    * Initializes static variables.
    */
   static
   {
      cPagesServed = 0;
      cBytesIn = 0;
      cBytesOut = 0;
      cDisconnectReceived = false;
      cCanHandleRequests = true;
   }

   /**
    * Incoming Socket
    */
   private Socket mSocket;

   /**
    * Socket input stream
    */
   private InputStream mInputStream;

   /**
    * Socket output stream
    */
   private OutputStream mOutputStream;

   /**
    * Thread ID
    */
   private int mID;

   /**
    * In Use flag
    */
   private boolean mInUse;

   /**
    * Thread lock
    */
   private final Object mLockObj;

   /**
    * Command Request Line -- the first line of the request
    */
   private String mRequest;

   /**
    * Parsed command data
    */
   private String mCommand;

   /**
    * Tracks the type of Http command that has been received
    */
   private int mCommandType;

   /**
    * General Buffered Reader
    */
   private BufferedReader mBufferedReader;

   /**
    * Request properties.
    */
   private Map<String, String> mRequestProps;

   /**
    * Request length.
    */
   private int mRequestLength;

   /**
    * Request timeout.
    */
   private int mRequestTimeout;

   /**
    * Digit Display Buffer
    */
   private byte[] mDigits;

   /**
    * Constructs a new RequestHandler instance.
    *
    * @param id Thread ID
    */
   public RequestHandler(int id)
   {
      mID = id;
      mLockObj = new Object();
      mInUse = false;
      setDaemon(true);
      mRequestTimeout = getInteger("requestTimeout", 300);
      mDigits = new byte[32];
   }

   /**
    * Setup and run to handle an incoming connection
    *
    * @param socket Incoming socket
    */
   public void init(Socket socket)
   {
      mSocket = socket;
      mRequestProps = new HashMap<String, String>();

      mRequestLength = 0;  // no existing request

      try
      {
         mInputStream = new BufferedInputStream(mSocket.getInputStream(), 10000);
         mInputStream.mark(100000);
         mBufferedReader = new BufferedReader(
            new InputStreamReader(mInputStream), 1024);

         synchronized (mLockObj)
         {
            mInUse = true;
            mLockObj.notify();
         }
      }
      catch (IOException e)
      {
         System.err.println("Error - " + e);
      }
   }

   /**
    * Check if handler is available.
    *
    * @return boolean false if in use, true if available
    */
   boolean isAvailable()
   {
      return (!mInUse);
   }

   /**
    * Whether or not the server has received a disconnect command.
    * (Static so it can be called from the WebServer object.)
    *
    * @return whether or not the server has received a disconnect command.
    */
   public static boolean isConnected()
   {
      return (!cDisconnectReceived);
   }

   /**
    * Run method for handling web requests.
    */
   @Override
   public void run()
   {
      while (true)
      {
         // wait for object
         synchronized (mLockObj)
         {
            mInUse = false;

            try
            {
               mLockObj.wait();
            }
            catch (Exception e)
            {
               System.err.println("Wait object exception: " + e);
            }
         }

         // check for exit condition (null socket)
         if (mSocket == null)
         {
            // "exiting"
            return;
         }

         // handle socket
         cPagesServed++;

         try
         {
            mInputStream = mSocket.getInputStream();
            mOutputStream = mSocket.getOutputStream();

            try
            {
               parseHeader();

               if (parseRequest())
               {
                  handleRequest();

                  if (mCommand.equals("/" + Integer.toHexString
                     ("DISCONNECT".hashCode())))
                  {
                     cDisconnectReceived = true;
                  }
               }
               else
               {
                  // invalid request
                  writeHeader(RESP_STATUS_BADREQ);
               }
            }
            catch (Exception e)
            {
               System.err.println(
                  "Request Handler " + mID + " " + e.getMessage());
            }
            mSocket.close();
         }
         catch (Exception e)
         {
            System.err.println("Request Handler " + mID + " " + e);
         }

         // some day the garbage collector will come around
         mSocket = null;
         mInputStream = null;
         mOutputStream = null;
      }
   }

   /**
    * Parses the received header
    */
   private void parseHeader()
      throws IOException
   {
      mRequest = mBufferedReader.readLine();

      String line;
      while ((line = mBufferedReader.readLine()) != null && !line.equals(""))
      {
         if (line.indexOf(": ") != -1)
         {
            String[] prop = line.split(": ");
            mRequestProps.put(prop[0], prop[1]);
         }
      }
   }

   /**
    * Parses the request into component pieces
    *
    * @return true if valid, false if invalid
    */
   private boolean parseRequest()
   {
      String[] reqParse = mRequest.split(" ");

      mCommandType = 0;

      if (reqParse[0].equals(REQ_GET))
      {
         mCommandType = GET;
      }

      else if (reqParse[0].equals(REQ_PUT))
      {
         mCommandType = PUT;
      }

      else if (reqParse[0].equals(REQ_POST))
      {
         mCommandType = POST;
      }

      else
      {
         return false;
      }

      mCommand = reqParse[1];

      return true;
   }

   /**
    * Can requests be handled? -- STATIC so we can call from WebServer
    *
    * @return True if requests can be handled
    */
   public static boolean canHandleRequests()
   {
      return cCanHandleRequests;
   }

   /**
    * Handles the request.
    */
   private void handleRequest()
      throws Exception
   {
      if (mCommandType == GET)
      {
         handleGetRequest();
      }
      else if (mCommandType == PUT)
      {
         handlePutRequest();
      }
      else if (mCommandType == POST)
      {
         handlePostRequest();
      }
      else
      {
         throw new RuntimeException("Invalid command: " + mCommand);
      }

      mSocket.shutdownOutput();
   }

   /**
    * Handles Get request.
    */
   private void handleGetRequest()
      throws IOException
   {
      String dataID = new String(mCommand).substring(1);
      String contentType = RESP_CONTENT_TYPE;
      String cDataSaveDir = "."; // but should be a static configurable variable!

      File dataFile = new File(cDataSaveDir + File.separator + dataID);

      if (dataFile.exists() && ! dataFile.isDirectory())
      {
         if (dataID.endsWith(".jnlp"))
         {
            contentType = RESP_CONTENT_TYPE_JNLP;
         }
         FileInputStream fileIn = new FileInputStream(dataFile);
         byte[] data = new byte[(int) dataFile.length()];
         fileIn.read(data);

         writeResponse(data, contentType);
      }
      // Handle page requests
      else if (mCommand.equals(PATH_ROOT))
      {
         writeRootPage();
      }
      else if (mCommand.equals(PATH_PAGE1))
      {
         // "/page1"
         writeResponse(BODY_PAGE1);
      }
      else if (mCommand.equals(PATH_PAGE2))
      {
         // "/page2"
         writeResponse(BODY_PAGE2);
      }
      else if (mCommand.equals(PATH_PAGE3))
      {
         // "/page3"
         writeResponse(BODY_PAGE3);
      }
      else if (mCommand.equals(PATH_EXIT))
      {
         cCanHandleRequests = false;
      }
      else // unknown page or data
      {
         writeHeader(RESP_STATUS_NOTFOUND);
         mOutputStream.write(BODY_NOTFOUND.getBytes());
      }
   }

   /**
    * Handles Put request.
    */
   private void handlePutRequest()
      throws IOException
   {
      String dataID = new String(mCommand).substring(1);

      System.out.println("PUT request received for " + dataID);

      int size = Integer.parseInt(mRequestProps.get("Content-Length"));

      boolean modified = false; // some dataFile.exists();

      if (size == 0)
      {
         writeHeader(RESP_STATUS_OK);
         return;
      }

      int bufferSize = DEFAULT_BUFFER_SIZE;

      byte[] bytes = new byte[bufferSize];
      int numRead = 0;

      mInputStream.reset();

      if (! findStartOfData(mInputStream))
      {
         throw new IOException("Could not find end of header");
      }

      while ((numRead = mInputStream.read(bytes, 0, bufferSize)) != -1)
      {
         System.out.println("Num read: " + numRead);
      }

      if (modified)
      {
         writeHeader(RESP_STATUS_OK);
      }
      else
      {
         writeHeader(RESP_STATUS_CREATED);
      }
   }

   /**
    * Handles Post request.
    */
   private void handlePostRequest()
      throws IOException
   {
      String name = mCommand.substring(1);

      if (! mBufferedReader.ready())
      {
         writeHeader(RESP_STATUS_NO_CONTENT);
         return;
      }

      String line;
      while (! (line = mBufferedReader.readLine()).equals(""))
      {
         for (String s : line.split("&"))
         {
            String[] props = s.split("=", 2);
            if (props.length > 1)
            {
               if (props[0].contains("param"))
               {
                  mRequestProps.put((props[0]),(props[1]));
               }
               else
               {
                  mRequestProps.put(httpURLencode(props[0]),
                                    httpURLencode(props[1]));
               }
            }
         }
      }

      if (mRequestProps.containsKey("Message"))
      {
         System.out.println("From client " + getIPHash() + " received:\n" +
                            name + " with message " +
                            mRequestProps.get("Message"));
         writeHeader(RESP_STATUS_OK);
         return;
      }

      if (mRequestProps.containsKey("command"))
      {
         int numParams = mRequestProps.size();
         String[] params = new String[numParams - 2];
         for (int i = 0; i < numParams - 2; i++)
            params[i] = mRequestProps.get("param" + i +"");

         writeHeader(RESP_STATUS_OK);
         HttpRS RS = new HttpRS();
         String response = RS.getResponse(mRequestProps.get("command"), params);
         writeData(response);

      }
   }

   /**
    * Gets the hash of the socket's IP address as a String.
    *
    * @return the hash of the socket's IP address as a String.
    */
   private String getIPHash()
   {
      return ("" + mSocket.getInetAddress().hashCode());
   }

   /**
    * URL encodes strings for HTTP.
    *
    * @param pSource the string to URL encode
    */
   private String httpURLencode(String pSource)
   {
      StringBuilder sb = new StringBuilder();
      int indexStart = 0;
      int indexEnd;

      while (indexStart < pSource.length())
      {
         indexEnd = pSource.indexOf("%", indexStart);

         if (indexEnd == -1)
         {
            indexEnd = pSource.length();
         }

         sb.append(pSource.substring(indexStart, indexEnd));

         indexStart = indexEnd + 3;

         if (indexStart < pSource.length())
         {
            sb.append((char) Integer.valueOf(
                pSource.substring(indexEnd + 1, indexStart), 16).intValue());
         }
      }

      return sb.toString();
   }

   /**
    * Finds where the actual data starts.
    *
    * @param pIn the input stream to look for data in
    */
   private boolean findStartOfData(InputStream pIn)
      throws IOException
   {
      int read;
      while ((read = pIn.read()) != -1)
      {
         if ((char) read == '\n' && (char) pIn.read() == '\n')
         {
            return true;
         }
      }

      return false;
   }

   /**
    * Writes the response
    *
    * @param body Body of String to write
    */
   private void writeResponse(String body)
   {
      writeResponse(body.getBytes(), RESP_CONTENT_TYPE);
   }

   /**
    * Writes the response
    *
    * @param body Body of bytes to write
    * @param contentType content type of data
    */
   private void writeResponse(byte[] body, String contentType)
   {
      writeHeader(RESP_STATUS_OK, contentType);
      writeData(body);
   }

   /**
    * Writes out data
    *
    * @param out the output stream to write to
    * @param data Data to write
    */
   private static void writeData(OutputStream out, String data)
   {
      writeData(out, data.getBytes());
   }

   /**
    * Writes out data
    *
    * @param data Data to write
    */
   private void writeData(byte[] data)
   {
      writeData(mOutputStream, data);
   }

   /**
    * Writes out data
    *
    * @param data Data to write
    */
   private void writeData(String data)
   {
      writeData(data.getBytes());
   }

   /**
    * Writes out data
    *
    * @param out Output stream to write to
    * @param data Data to write
    */
   public static void writeData(OutputStream out, byte[] data)
   {
      try
      {
         out.write(data);
         cBytesOut += data.length;
      }
      catch (Exception e)
      {
      }
   }

   /**
    * Writes out data
    *
    * @param out Output stream to write to
    * @param data Data to write
    * @param offset Offset in array
    * @param len Length to write
    */
   public static void writeData(OutputStream out, byte[] data,
                                int offset, int len)
   {
      try
      {
         out.write(data, offset, len);
         cBytesOut += len;
      }
      catch (Exception e)
      {
      }
   }

   /**
    * Writes out the header
    *
    * @param status Status byte array
    */
   void writeHeader(byte[] status)
   {
      writeHeader(mOutputStream, status, RESP_CONTENT_TYPE);
   }

   /**
    * Writes out the header
    *
    * @param status Status String
    */
   void writeHeader(String status)
   {
      writeHeader(mOutputStream, status, RESP_CONTENT_TYPE);
   }
   /**
    * Writes out the header
    *
    * @param status Status byte array
    * @param contentType content type of data
    */
   void writeHeader(String status, String contentType)
   {
      writeHeader(mOutputStream, status, contentType);
   }

   /**
    * Writes out the header information -- STATIC so we can call from WebServer
    *
    * @param out Output stream to write to
    * @param status Status String
    */
   static void writeHeader(OutputStream out, String status)
   {
      writeHeader(out, status.getBytes(), RESP_CONTENT_TYPE);
   }

   /**
    * Writes out the header information -- STATIC so we can call from WebServer
    *
    * @param out Output stream to write to
    * @param status Status String
    * @param contentType content type of data
    */
   static void writeHeader(OutputStream out, String status, String contentType)
   {
      writeHeader(out, status.getBytes(), contentType);
   }

   /**
    * Writes out the header information -- STATIC so we can call from WebServer
    *
    * @param out Output stream to write to
    * @param status Status byte array
    * @param contentType content type of data
    */
   static void writeHeader(OutputStream out, byte[] status, String contentType)
   {
      try
      {
         writeData(out, status);
         writeData(out, RESP_NEWLINE);
         writeData(out, RESP_SERVER);
         writeData(out, RESP_NEWLINE);
         writeData(out, contentType);
         writeData(out, RESP_NEWLINE);
         writeData(out, RESP_NOCACHE);
         writeData(out, RESP_NEWLINE);
         writeData(out, RESP_NEWLINE);
      }
      catch (Exception e)
      {
      }
   }

   /**
    * Writes root page ("/")
    */
   private void writeRootPage()
   {
      writeHeader(RESP_STATUS_OK);
      writeData(HTML_HTML);
      writeData(HTML_TITLE);
      writeData(PAGE_ROOT_TITLE);
      writeData(HTML_TITLECLOSE);
      writeData(HTML_BODY);
      writeData(PAGE_ROOT_BODY);
      writeNumber(cPagesServed, 10, (byte) 0x20);
      writeData(PAGE_ROOT_BODY2);
      writeNumber(cBytesIn, 10, (byte) 0x20);
      writeData(PAGE_ROOT_BODY3);
      writeNumber(cBytesOut, 10, (byte) 0x20);
      writeData(PAGE_ROOT_BODY4);
      writeData(HTML_BODYCLOSE);
      writeData(HTML_HTMLCLOSE);
   }

   /**
    * Outputs a base 10 number
    *
    * @param val Value
    * @param digits Number of digits to display
    * @param fill Fill character
    */
   private void writeNumber(int val, int digits, byte fill)
   {
      for (int i = 0; i < digits; i++)
      {
         mDigits[31 - i] = (byte)((val % 10) + 0x30);
         val /= 10;
      }
      writeData(mOutputStream, mDigits, (31 - digits + 1), digits);
   }
}
