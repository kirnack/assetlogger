/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package assetl.service;

import assetl.system.Asset;
import assetl.system.AssetLModel;
import assetl.system.Person;
import assetl.system.Request;
import assetl.system.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author brogers3
 */
public class ServerTest
{

    public ServerTest()
    {
    }

    @BeforeClass
    public static void setUpClass() throws Exception
    {
       System.err.println("Setup");
    }

    @AfterClass
    public static void tearDownClass() throws Exception
    {
       System.err.println("Down");
    }

    /**
     * Test of getNumCheckouts method, of class Server.
     */
    @Test
    public void testGetNumCheckouts()
    {
        System.out.println("getNumCheckouts");
        Server instance = Server.getInstance();
        int expResult = 0;
        int result = instance.getNumCheckouts();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNumLogs method, of class Server.
     */
    @Test
    public void testGetNumLogs()
    {
        System.out.println("getNumLogs");
        Server instance = Server.getInstance();
        int expResult = 0;
        int result = instance.getNumLogs();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNumRequests method, of class Server.
     */
    @Test
    public void testGetNumRequests()
    {
        System.out.println("getNumRequests");
        Server instance = Server.getInstance();
        int expResult = 0;
        int result = instance.getNumRequests();
        assertEquals(expResult, result);
    }

    /**
     * Test of getInstance method, of class Server.
     */
    @Test
    public void testGetInstance()
    {
        System.out.println("getInstance");
        AssetLModel expResult = Server.getInstance();
        AssetLModel result = Server.getInstance();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPerson method, of class Server.
     */
    @Test
    public void testGetPerson()
    {
        System.out.println("getPerson");
        String pID = "";
        Server instance = Server.getInstance();
        Person expResult = null;
        Person result = instance.getPerson(pID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPerson method, of class Server.
     */
    @Test
    public void testSetPerson()
    {
        System.out.println("setPerson");
        Person pPerson = null;
        Server instance = Server.getInstance();
        instance.setPerson(pPerson);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAsset method, of class Server.
     */
    @Test
    public void testGetAsset()
    {
        System.out.println("getAsset");
        String pID = "";
        Server instance = Server.getInstance();
        Asset expResult = null;
        Asset result = instance.getAsset(pID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAsset method, of class Server.
     */
    @Test
    public void testSetAsset()
    {
        System.out.println("setAsset");
        Asset pAsset = null;
        Server instance = Server.getInstance();
        instance.setAsset(pAsset);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRequest method, of class Server.
     */
    @Test
    public void testGetRequest()
    {
        System.out.println("getRequest");
        String pID = "";
        Server instance = Server.getInstance();
        Request expResult = null;
        Request result = instance.getRequest(pID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRequest method, of class Server.
     */
    @Test
    public void testSetRequest()
    {
        System.out.println("setRequest");
        Request pRequest = null;
        String pUserID = "";
        Server instance = Server.getInstance();
        instance.setRequest(pRequest, pUserID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUsers method, of class Server.
     */
    @Test
    public void testGetUsers()
    {
        System.out.println("getUsers");
        Server instance = Server.getInstance();
        Collection<User> expResult = new ArrayList<User>();
        expResult.add(new User("Doctor"));
        expResult.add(new User("user"));
        Collection<User> result = instance.getUsers();
        assertEquals(expResult, result);
    }

    /**
     * Test of isAdmin method, of class Server.
     */
    @Test
    public void testIsAdmin()
    {
        System.out.println("isAdmin");
        Server instance = Server.getInstance();
        String pID = "brogers3";
        boolean expResult = false;
        boolean result = instance.isAdmin(pID);
        assertEquals(expResult, result);
        pID = "user";
        expResult = false;
        result = instance.isAdmin(pID);
        pID = "Doctor";
        expResult = true;
        result = instance.isAdmin(pID);
    }

    /**
     * Test of getUser method, of class Server.
     */
    @Test
    public void testGetUser()
    {
        System.out.println("getUser");
        String pID = "brogers3";
        Server instance = Server.getInstance();
        User expResult = null;
        User result = instance.getUser(pID);
        assertEquals(expResult, result);
        pID = "Doctor";
        expResult = new User("Doctor");
        result = instance.getUser(pID);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkPwd method, of class Server.
     */
    @Test
    public void testCheckPwd()
    {
        System.out.println("checkPwd");
        Server instance = Server.getInstance();
        System.out.println("No user exists");
        String pID = "brogers3";
        String pPwd = "Buffalo";
        boolean expResult = false;
        boolean result = instance.checkPwd(pID, pPwd);
        System.out.println("User exists correct password");
        assertEquals(expResult, result);
        pID = "Doctor";
        expResult = true;
        result = instance.checkPwd(pID, pPwd);
        assertEquals(expResult, result);
        System.out.println("User exists wrong password");
        pPwd = "";
        expResult = false;
        result = instance.checkPwd(pID, pPwd);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAvailAsset method, of class Server.
     */
    @Test
    public void testGetAvailAsset()
    {
        System.out.println("getAvailAsset");
        Date pStart = null;
        Date pEnd = null;
        Server instance = Server.getInstance();
        Collection expResult = null;
        Collection result = instance.getAvailAsset(pStart, pEnd);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAssets method, of class Server.
     */
    @Test
    public void testGetAssets()
    {
        System.out.println("getAssets");
        Server instance = Server.getInstance();
        Person pPerson = null;
        Collection<Asset> expResult = null;
        Collection<Asset> result = instance.getAssets(pPerson);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}