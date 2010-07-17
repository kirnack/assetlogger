/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package assetl.service;

import assetl.system.Asset;
import assetl.system.AssetLModel;
import assetl.system.Checkout;
import assetl.system.Person;
import assetl.system.Request;
import assetl.system.User;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author brogers3
 */
public class DefualtAssetLModel 
   implements AssetLModel
{

   public Person getPerson(String pID)
   {
      return new Person(pID);
   }

   public void setPerson(Person pPerson)
   {
      throw new UnsupportedOperationException("Not Possible for");
   }

   public Asset getAsset(String pID)
   {
      return new Asset(pID, null);
   }

   public void setAsset(Asset pAsset)
   {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   public Request getRequest(String pID)
   {
      return new Request(pID, null, null, null, null);
   }

   public void setRequest(Request pRequest, String pUserID)
   {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   public Checkout getCheckout(Asset pAsset)
   {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   public void setCheckout(Checkout pCheckout)
   {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   public Integer getAccessLevel(String pID)
   {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   public User getUser(String pID)
   {
      return new User(pID);
   }

   public Boolean checkPwd(String pID, String pPwd)
   {
      return false;
   }

   public Collection<Asset> getAvailAsset(Date pStart, Date pEnd)
   {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   public Collection<Person> getBorrowers(Asset pAsset)
   {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   public Collection<Request> getActiveRequests(Person pPerson)
   {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   public Collection<Asset> getAssets(Person pPerson)
   {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   public Integer getNumRequests()
   {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   public Integer getNumCheckouts()
   {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   public Integer getNumLogs()
   {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   public void setCheckout(Checkout pCheckout, String pUserID)
   {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   public Checkout getCheckout(String pID)
   {
      return new Checkout(pID, null, null, null, null, null);
   }

   public Collection<Checkout> getCheckouts(Request pRequest)
   {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   public Collection<User> getUsers()
   {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   public Collection<Checkout> getCheckedOutCheckouts(Asset pAsset)
   {
      throw new UnsupportedOperationException("Not supported yet.");
   }

}
