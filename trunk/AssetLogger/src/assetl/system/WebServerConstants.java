package assetl.system;

/**
 * Constants for a web server.
 *
 * Maintains a set of constants that aid with HTTP request handling.
 *
 * @author Bryon Rogers
 */
public class WebServerConstants
{
   /**
    * The default host the web server is running on.
    */
   public static final String DEFAULT_SERVER_HOST = "localhost";
   /**
    * The default port the web server is listening on.
    */
   public static final int DEFAULT_SERVER_PORT = 1987;
   /**
    * The default buffer size.
    */
   public static final int DEFAULT_BUFFER_SIZE = 8196;
   /**
    * Identifies a GET request
    */
   public static final int GET = 1;
   /**
    * Identifies a PUT request
    */
   public static final int PUT = 2;
   /**
    * Identifies a POST request
    */
   public static final int POST = 3;
   /**
    * Request GET
    */
   public static String REQ_GET = "GET";
   /**
    * Request PUT
    */
   public static String REQ_PUT = "PUT";
   /**
    * Request POST
    */
   public static String REQ_POST = "POST";
   /**
    * Server Response Status OK
    */
   public static String RESP_STATUS_OK = "HTTP/1.1 200 OK";
   /**
    * Server Response Status Created
    */
   public static String RESP_STATUS_CREATED = "HTTP/1.1 201 CREATED";
   /**
    * Server Response Status No Content
    */
   public static String RESP_STATUS_NO_CONTENT = "HTTP/1.1 204 NO CONTENT";
   /**
    * Server Response Status Bad Request
    */
   public static String RESP_STATUS_BADREQ = "HTTP/1.1 400 BAD REQUEST";
   /**
    * Server Response Status Not Found
    */
   public static String RESP_STATUS_NOTFOUND = "HTTP/1.1 404 NOT FOUND";
   /**
    * Server Response Status No Threads
    */
   public static String RESP_STATUS_ERROR = "HTTP/1.1 500 NO THREADS";
   /**
    * Server Response String
    */
   public static String RESP_SERVER = "Server: AssetLogger/0.1";
   /**
    * HTML Content Type
    */
   public static String RESP_CONTENT_TYPE_HTML = "Content-type: text/html";
   /**
    * Plain Text Content Type
    */
   public static String RESP_CONTENT_TYPE_TEXT = "Content-type: text/plain";
   /**
    * JPG Content Type
    */
   public static String RESP_CONTENT_TYPE_JPG = "Content-type: image/jpg";
   /**
    * JNLP Content Type
    */
   public static String RESP_CONTENT_TYPE_JNLP = "Content-type: application/x-java-jnlp-file";
   /**
    * Default Content Type
    */
   public static String RESP_CONTENT_TYPE = RESP_CONTENT_TYPE_HTML;
   /**
    * New line response
    */
   public static String RESP_NEWLINE = new String(new byte[]
      {
         0x0d, 0x0a
      });
   /**
    * Disable browser caching
    */
   public static String RESP_NOCACHE = "Pragma: no-cache";
   /**
    * Path to Root page
    */
   public static String PATH_ROOT = "/";
   /**
    * Path to page 1.
    */
   public static String PATH_PAGE1 = "/page1";
   /**
    * Path to page 1.
    /
   public static String PERSON_REQUEST = "/person";
   /**
    * Path to page 1.
    /
   public static String ASSET_REQUEST = "/asset";
   /**
    * Path to page 1.
    /
   public static String CHECKOUT_REQUEST = "/checkout";
   /**
    * Path to page 1.
    /
   public static String REQUEST_REQUEST = "/request";*/
   /**
    * Path to page 2.
    */
   public static String PATH_PAGE2 = "/page2";
   /**
    * Path to page 3.
    */
   public static String PATH_PAGE3 = "/page3";
   /**
    * Path to exit page.
    */
   public static String PATH_EXIT = "/exnihilo";
   /**
    * Root Web Page Title.
    */
   public static String PAGE_ROOT_TITLE = "Assetl Logger Server";
   /**
    * Root page body.
    */
   public static String PAGE_ROOT_BODY =
      "<CENTER><FONT SIZE=\"+2\">Assetl Logger Server</FONT><BR><BR><TABLE BORDER><TR><TD>Pages Served</TD><TD> ";
   /**
    * Root page body 2.
    */
   public static String PAGE_ROOT_BODY2 =
      "</TD></TR><TR><TD>Bytes Received</TD><TD>";
   /**
    * Root page body 3.
    */
   public static String PAGE_ROOT_BODY3 =
      "</TD></TR><TR><TD>Bytes Sent</TD><TD>";
   /**
    * Root page body 4.
    */
   public static String PAGE_ROOT_BODY4 =
      "</TD></TR></TABLE><BR><BR><A href=\"/page1\">Internal Page 1</A><BR>"
      + "<A href=\"/page2\">Internal Page 2</A><BR>"
      + "<A href=\"/page3\">Internal Page 3</A><BR>"
      + "<A href=\"/person\">Person Page</A><BR>"
      + "<A href=\"/asset\">Asset Page</A><BR>"
      + "<A href=\"/checkout\">Checkout Page</A><BR>"
      + "<A href=\"/request\">Request Page</A><BR>"
      + "<A href=\"/3c87449c\">death</A>";
   /**
    * Page 1 body.
    */
   public static String BODY_PAGE1 =
      "<HTML><BODY><BR><BR>PAGE 1</BODY></HTML>";
   /**
    * Page 2 body.
    */
   public static String BODY_PAGE2 =
      "<HTML><BODY><BR><BR>PAGE 2</BODY></HTML>";
   /**
    * Page 3 body.
    */
   public static String BODY_PAGE3 =
      "<HTML><BODY><BR><BR>PAGE 3</BODY></HTML>";
   /**
    * Page not found body.
    */
   public static String BODY_NOTFOUND =
      "<HTML><BODY><BR><BR>PAGE NOT FOUND</BODY></HTML>";
   /**
    * Opening HTML tag.
    */
   public static String HTML_HTML = "<HTML>";
   /**
    * Closing HTML tag.
    */
   public static String HTML_HTMLCLOSE = "</HTML>";
   /**
    * Opening TITLE tag.
    */
   public static String HTML_TITLE = "<TITLE>";
   /**
    * Closing TITLE tag.
    */
   public static String HTML_TITLECLOSE = "</TITLE>";
   /**
    * Opening BODY tag.
    */
   public static String HTML_BODY = "<BODY>";
   /**
    * Closing BODY tag.
    */
   public static String HTML_BODYCLOSE = "</BODY>";
   /**
    * Opening Table Row tag.
    */
   public static String HTML_TR = "<TR>";
   /**
    * Closing Table Row tag.
    */
   public static String HTML_TRCLOSE = "</TR>";
   /**
    * Opening Table Data tag.
    */
   public static String HTML_TD = "<TD>";
   /**
    * Closing Table Data tag.
    */
   public static String HTML_TDCLOSE = "</TD>";
   /**
    * Opening Table Header tag.
    */
   public static String HTML_TH = "<TH>";
   /**
    * Closing Table Header tag.
    */
   public static String HTML_THCLOSE = "</TH>";
}

