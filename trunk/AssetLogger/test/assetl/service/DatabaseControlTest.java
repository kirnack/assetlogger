/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assetl.service;

import assetl.system.Asset;
import assetl.system.AssetLView;
import assetl.system.DataPacket;
import assetl.system.Person;
import assetl.system.User;
import java.util.Collection;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the functionality of DatabaseControl
 * @author Devin Doman
 */
public class DatabaseControlTest
{
   /**
    * Test of setViewPackage method, of class DatabaseControl.
    */
   @Test
   public void testSetViewPackage()
   {
      System.out.println("setViewPackage");
      DatabaseControl instance = new DatabaseControlImpl();
      instance.setViewPackage();
      assertEquals("assetl.desktop.", instance.mPackage);
   }

   /**
    * Test of setPostViewName method, of class DatabaseControl.
    */
   @Test
   public void testSetPostViewName()
   {
      System.out.println("setPostViewName");
      DatabaseControl instance = new DatabaseControlImpl();
      instance.setPostViewName();
      assertEquals("View", instance.mPostClass);
   }

   /**
    * Test of setClassPackage method, of class DatabaseControl.
    */
   @Test
   public void testSetClassPackage()
   {
      System.out.println("setClassPackage");
      String pPackage = "assetl.desktop.";
      DatabaseControl instance = new DatabaseControlImpl();
      instance.setClassPackage(pPackage);
      assertEquals(pPackage, instance.mPackage);
   }

   /**
    * Test of setPostClassName method, of class DatabaseControl.
    */
   @Test
   public void testSetPostClassName()
   {
      System.out.println("setPostClassName");
      String pPostClass = "View";
      DatabaseControl instance = new DatabaseControlImpl();
      instance.setPostClassName(pPostClass);
      assertEquals(pPostClass, instance.mPostClass);
   }

   /**
    * Test of setViewAmbles method, of class DatabaseControl.
    */
   @Test
   public void testSetViewAmbles()
   {
      System.out.println("setViewAmbles");
      DatabaseControl instance = new DatabaseControlImpl();
      instance.setViewAmbles();
      assertEquals("assetl.desktop.", instance.mPackage);
      assertEquals("View", instance.mPostClass);
   }

   /**
    * Test of setFunctionAmbles method, of class DatabaseControl.
    */
   @Test
   public void testSetFunctionAmbles()
   {
      System.out.println("setFunctionAmbles");
      DatabaseControl instance = new DatabaseControlImpl();
      instance.setFunctionAmbles();
      assertEquals("assetl.service.", instance.mPackage);
      assertEquals("Function", instance.mPostClass);
   }

   /**
    * Test of addAmbles method, of class DatabaseControl.
    */
   @Test
   public void testAddAmbles()
   {
      System.out.println("addAmbles");
      String pName = "Class";
      DatabaseControl instance = new DatabaseControlImpl();
      String expResult = instance.mPackage + "Class" + instance.mPostClass;
      String result = instance.addAmbles(pName);
      assertEquals(expResult, result);
   }

   /**
    * Test of setClassAmbles method, of class DatabaseControl.
    */
   @Test
   public void testSetClassAmbles()
   {
      System.out.println("setClassAmbles");
      String pPackage = "assetl.system.";
      String pPostClass = "Post";
      DatabaseControl instance = new DatabaseControlImpl();
      instance.setClassAmbles(pPackage, pPostClass);

      assertEquals(instance.mPackage, pPackage);
      assertEquals(instance.mPostClass, pPostClass);
   }

   /**
    * Test of setFunction method, of class DatabaseControl.
    */
   @Test
   public void testSetFunction()
   {
      System.out.println("setFunction");
      String pFunction = "Checkout";
      DatabaseControl instance = new DatabaseControlImpl();
      instance.setFunction(pFunction);
      assertEquals(instance.mPackage + pFunction + instance.mPostClass,
         instance.mFunction.getClass().getCanonicalName());
   }

   /**
    * Test of getFunction method, of class DatabaseControl.
    */
   @Test
   public void testGetFunction()
   {
      System.out.println("getFunction");
      String pFunction = "Checkout";
      DatabaseControl instance = new DatabaseControlImpl();
      instance.setFunction(pFunction);
      Function expResult = instance.mFunction;
      Function result = instance.getFunction(pFunction);
      assertEquals(expResult, result);
   }

   /**
    * Test of addFunction method, of class DatabaseControl.
    */
   @Test
   public void testAddFunction()
   {
      System.out.println("addFunction");
      String pFunction = "LogIn";
      DatabaseControl instance = new DatabaseControlImpl();
      Function result = instance.addFunction(pFunction);
      Function expResult = instance.getFunction("LogIn");
      assertEquals(expResult, result);
   }

   /**
    * Test of enableFunction method, of class DatabaseControl.
    */
   @Test
   public void testEnableFunction()
   {
      System.out.println("enableFunction");
      String pFunction = "LogIn";
      DatabaseControl instance = new DatabaseControlImpl();
      instance.enableFunction(pFunction);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of doFunction method, of class DatabaseControl.
    */
   @Test
   public void testDoFunction_String()
   {
      System.out.println("doFunction");
      String pFunction = "";
      DatabaseControl instance = new DatabaseControlImpl();
      instance.doFunction(pFunction);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of changeView method, of class DatabaseControl.
    */
   @Test
   public void testChangeView_String()
   {
      System.out.println("changeView");
      String pView = "";
      DatabaseControl instance = new DatabaseControlImpl();
      instance.changeView(pView);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of changeView method, of class DatabaseControl.
    */
   @Test
   public void testChangeView_AssetLView()
   {
      System.out.println("changeView");
      AssetLView pView = null;
      DatabaseControl instance = new DatabaseControlImpl();
      instance.changeView(pView);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of updateData method, of class DatabaseControl.
    */
   @Test
   public void testUpdateData()
   {
      System.out.println("updateData");
      DatabaseControl instance = new DatabaseControlImpl();
      instance.updateData();
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of sendViewPacket method, of class DatabaseControl.
    */
   @Test
   public void testSendViewPacket()
   {
      System.out.println("sendViewPacket");
      DataPacket pPacket = null;
      DatabaseControl instance = new DatabaseControlImpl();
      instance.sendViewPacket(pPacket);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of getUser method, of class DatabaseControl.
    */
   @Test
   public void testGetUser()
   {
      System.out.println("getUser");
      String pID = "";
      DatabaseControl instance = new DatabaseControlImpl();
      User expResult = null;
      User result = instance.getUser(pID);
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of getPerson method, of class DatabaseControl.
    */
   @Test
   public void testGetPerson()
   {
      System.out.println("getPerson");
      String pID = "";
      DatabaseControl instance = new DatabaseControlImpl();
      Person expResult = null;
      Person result = instance.getPerson(pID);
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of getAsset method, of class DatabaseControl.
    */
   @Test
   public void testGetAsset()
   {
      System.out.println("getAsset");
      String pID = "";
      DatabaseControl instance = new DatabaseControlImpl();
      Asset expResult = null;
      Asset result = instance.getAsset(pID);
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of getAvailableAssets method, of class DatabaseControl.
    */
   @Test
   public void testGetAvailableAssets()
   {
      System.out.println("getAvailableAssets");
      Date pStart = null;
      Date pEnd = null;
      DatabaseControl instance = new DatabaseControlImpl();
      Collection expResult = null;
      Collection result = instance.getAvailableAssets(pStart, pEnd);
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of getOutstandingRequests method, of class DatabaseControl.
    */
   @Test
   public void testGetOutstandingRequests()
   {
      System.out.println("getOutstandingRequests");
      Person pPerson = null;
      DatabaseControl instance = new DatabaseControlImpl();
      Collection expResult = null;
      Collection result = instance.getOutstandingRequests(pPerson);
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of setCurrentUser method, of class DatabaseControl.
    */
   @Test
   public void testSetCurrentUser()
   {
      System.out.println("setCurrentUser");
      User pUser = null;
      DatabaseControl instance = new DatabaseControlImpl();
      instance.setCurrentUser(pUser);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of getCurrentUser method, of class DatabaseControl.
    */
   @Test
   public void testGetCurrentUser()
   {
      System.out.println("getCurrentUser");
      DatabaseControl instance = new DatabaseControlImpl();
      User expResult = null;
      User result = instance.getCurrentUser();
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of getView method, of class DatabaseControl.
    */
   @Test
   public void testGetView()
   {
      System.out.println("getView");
      DatabaseControl instance = new DatabaseControlImpl();
      AssetLView expResult = null;
      AssetLView result = instance.getView();
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of run method, of class DatabaseControl.
    */
   @Test
   public void testRun()
   {
      System.out.println("run");
      DatabaseControl instance = new DatabaseControlImpl();
      instance.run();
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   public class DatabaseControlImpl
      extends DatabaseControl
   {
      public void setViewPackage()
      {
         setClassPackage("assetl.desktop.");
      }

      public void setPostViewName()
      {
         setPostClassName("View");
      }
   }
}
