/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package assetl.service;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kirnack
 */
public class DatabaseControlTest {

    public DatabaseControlTest() {
    }

   @BeforeClass
   public static void setUpClass() throws Exception {
   }

   @AfterClass
   public static void tearDownClass() throws Exception {
   }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

   /**
    * Test of schedule method, of class DatabaseControl.
    */
   @Test
   public void testSchedule() {
      System.out.println("schedule");
      String pPerson = "";
      String pAsset = "";
      String pStrtMon = "";
      String pStrtDay = "";
      String pStrtYear = "";
      String pEndMon = "";
      String pEndDay = "";
      String pEndYear = "";
      DatabaseControl instance = new DatabaseControl();
      instance.schedule(pPerson, pAsset, pStrtMon, pStrtDay, pStrtYear, pEndMon, pEndDay, pEndYear);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of checkout method, of class DatabaseControl.
    */
   @Test
   public void testCheckout() {
      System.out.println("checkout");
      String pPerson = "";
      String pAsset = "";
      String pEndMon = "";
      String pEndDay = "";
      String pEndYear = "";
      DatabaseControl instance = new DatabaseControl();
      instance.checkout(pPerson, pAsset, pEndMon, pEndDay, pEndYear);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of checkin method, of class DatabaseControl.
    */
   @Test
   public void testCheckin() {
      System.out.println("checkin");
      String pPerson = "";
      String pAsset = "";
      DatabaseControl instance = new DatabaseControl();
      instance.checkin(pPerson, pAsset);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of cancel method, of class DatabaseControl.
    */
   @Test
   public void testCancel() {
      System.out.println("cancel");
      String pPerson = "";
      String pAsset = "";
      DatabaseControl instance = new DatabaseControl();
      instance.cancel(pPerson, pAsset);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of getPersonFirst method, of class DatabaseControl.
    */
   @Test
   public void testGetPersonFirst() {
      System.out.println("getPersonFirst");
      String pID = "";
      DatabaseControl instance = new DatabaseControl();
      String expResult = "";
      String result = instance.getPersonFirst(pID);
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

}