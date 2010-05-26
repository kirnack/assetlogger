/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package assetl.service;

/**
 *
 * @author brogers3
 */
public class ServerStarter
{
   public static void main(String[] args)
   {
      System.setProperty("dbfilename", "TestingGUI");
      new Thread(Server.getInstance()).run();
   }

}
