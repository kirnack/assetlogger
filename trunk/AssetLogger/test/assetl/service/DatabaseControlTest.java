/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assetl.service;

import assetl.system.Asset;
import assetl.system.Person;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Devo
 */
public class DatabaseControlTest
{
   /**
    * 
    */
   public DatabaseControlTest()
   {
   }

   /**
    *
    * @throws Exception
    */
   @BeforeClass
   public static void setUpClass()
      throws Exception
   {
   }

   /**
    *
    * @throws Exception
    */
   @AfterClass
   public static void tearDownClass()
      throws Exception
   {
   }

   /**
    *
    */
   @Before
   public void setUp()
   {
   }

   /**
    *
    */
   @After
   public void tearDown()
   {
   }

   /**
    * Test of schedule method, of class DatabaseControl.
    */
   @Test
   public void testSchedule()
   {
      System.out.println("schedule");
      Person pPerson = null;
      Asset pAsset = null;
      Date pStart = null;
      Date pEnd = null;
      DatabaseControl instance = new DatabaseControl();
      instance.schedule(pPerson, pAsset, pStart, pEnd);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of checkout method, of class DatabaseControl.
    */
   @Test
   public void testCheckout()
   {
      System.out.println("checkout");
      Person pPerson = null;
      Asset pAsset = null;
      Date pEnd = null;
      DatabaseControl instance = new DatabaseControl();
      instance.checkout(pPerson, pAsset, pEnd);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of checkin method, of class DatabaseControl.
    */
   @Test
   public void testCheckin()
   {
      System.out.println("checkin");
      Asset pAsset = null;
      DatabaseControl instance = new DatabaseControl();
      instance.checkin(pAsset);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of cancel method, of class DatabaseControl.
    */
   @Test
   public void testCancel()
   {
      System.out.println("cancel");
      Person pPerson = null;
      Asset pAsset = null;
      DatabaseControl instance = new DatabaseControl();
      //Cannot do this No such method and will never be one.
      //instance.cancel(pPerson, pAsset);

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
      DatabaseControl instance = new DatabaseControl();
      Person expResult = null;
      Person result = instance.getPerson(pID);
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }
}
