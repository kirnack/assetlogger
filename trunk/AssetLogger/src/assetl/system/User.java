package assetl.system;

import javax.xml.bind.annotation.*;

/**
 * Represents the user of the view.
 * 
 * @author Bryon Rogers3
 * @author Modified by Michael Hale
 */
@XmlRootElement(name = "asset")
@XmlAccessorType(XmlAccessType.FIELD)
public class User
{

   public static final int CLIENT = 2;

   public static final int PROVIDER = 1;

   public static final int GATEKEEPER = User.PROVIDER;

   public static final int ADMIN = 0;

   public static final int NONEXISTANT = 246;
   /**
    * The unique identifier of the user
    */
   @XmlElement(name = "id")
   private String mID;
   /**
    * The user's password
    */
   @XmlElement(name = "password")
   private String mPassword;
   /**
    * Indicates if the user is an admin
    */
   @XmlElement(name = "admin")
   private int mAdmin;

   /**
    * Default Constructor.
    */
   public User()
   {
      this("", "", 3);
   }

   /**
    * Constructor with an id as a parameter.
    *
    * @param pID The id of the user
    */
   public User(String pID)
   {
      this(pID, "", 3);
   }

   /**
    * Constructor with all parameters.
    *
    * @param pID The id of the user
    * @param pPassword The user's password
    * @param pAdmin True if the user is an admin
    */
   public User(String pID, String pPassword, int pAdmin)
   {
      mID = pID;
      mPassword = pPassword;
      mAdmin = pAdmin;
   }

   /**
    * Returns true if the user is an admin.
    *
    * @return true if the user is an admin
    */
   public int isAdmin()
   {
      return mAdmin;
   }

   /**
    * Sets whether the user is an admin or not.
    *
    * @param pAdmin The admin status to set
    */
   public void setAdmin(int pAdmin)
   {
      mAdmin = pAdmin;
   }

   /**
    * Gets the id of this user.
    *
    * @return The user id.
    */
   public String getID()
   {
      return mID;
   }

   /**
    * Sets the id of this user.
    *
    * @param pID The id to set.
    */
   public void setID(String pID)
   {
      this.mID = pID;
   }

   /**
    * Gets this user's password.
    *
    * @return The password of this user.
    */
   public String getPassword()
   {
      return mPassword;
   }

   /**
    * Sets this user's password.
    *
    * @param pPassword This user's password.
    */
   public void setPassword(String pPassword)
   {
      this.mPassword = pPassword;
   }

   /**
    * Users are considered equal if they have the same id.
    *
    * @param obj The object to compare to
    * @return True if the objects are equal
    */
   @Override
   public boolean equals(Object obj)
   {
      if (obj == null)
      {
         return false;
      }
      if (getClass() != obj.getClass())
      {
         return false;
      }
      final User other = (User) obj;
      if ((this.mID == null) ? (other.mID != null)
         : !this.mID.equals(other.mID))
      {
         return false;
      }
      return true;
   }

   /**
    * Overriden hashCode() method for a User.
    *
    * @return The unique hash code for this user.
    */
   @Override
   public int hashCode()
   {
      int hash = 7;
      hash = 97 * hash + (this.mID != null ? this.mID.hashCode() : 0);
      return hash;
   }

   /**
    * Converts the User object to a string.
    *
    * @return A string representing a user
    */
   @Override
   public String toString()
   {
      return mID;
   }
}
