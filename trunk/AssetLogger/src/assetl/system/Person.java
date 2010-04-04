package assetl.system;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

/**
 * This class is used to represent individuals whom will request/pick-up
 * assets.  It will contain their contact information and a unique
 * identifier for the person.
 *
 * @author Bryon Rogers
 */
@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person
{
   /**
    * The unique identifier for a person
    */
   @XmlElement(name = "id")
   protected String mID;
   /**
    * The person's first name
    */
   @XmlElement(name = "firstname")
   protected String mFirstName;
   /**
    * The person's middle name
    */
   @XmlElement(name = "middlename")
   protected String mMiddleName;
   /**
    * The person's last name
    */
   @XmlElement(name = "lastname")
   protected String mLastName;
   /**
    * The person's email
    */
   @XmlElement(name = "email")
   protected String mEmail;
   /**
    * The person's phone number
    */
   @XmlElement(name = "phonenumber")
   protected String mPhoneNumber;

   /**
    * Constructor for Person with the person's id as a parameter.
    *
    */
   public Person()
   {
      this("Do", "Not", "Use", "Person", "Non", "Existant");
   }

   /**
    *
    * @param pID
    */
   public Person(String pID)
   {
      this(pID, "", "", "", "", "");
   }

   /**
    * Constructor for Person with all parameters.
    *
    * @param pID The unique identifier for this person
    * @param pFirstName The peron's first name
    * @param pMiddleName The person's middle name
    * @param pLastName The person's last name
    * @param pEmail The person's email
    * @param pPhoneNumber The person's phone number
    */
   public Person(String pID, String pFirstName, String pMiddleName,
      String pLastName, String pEmail, String pPhoneNumber)
   {
      this.mID = pID;
      this.mFirstName = pFirstName;
      this.mMiddleName = pMiddleName;
      this.mLastName = pLastName;
      this.mEmail = pEmail;
      this.mPhoneNumber = pPhoneNumber;
   }

   /**
    * Returns the phone number of the Person.
    *
    * @return The phone number of the person.
    */
   public String getPhoneNumber()
   {
      return mPhoneNumber;
   }

   /**
    * Set the value of phone number of the person.
    *
    * @param pPhoneNumber new value of mPhoneNumber
    */
   public void setPhoneNumber(String pPhoneNumber)
   {
      this.mPhoneNumber = pPhoneNumber;
   }

   /**
    * Returns the email address of the Person as a string.
    *
    * @return The email address of the Person as a String.
    */
   public String getEmail()
   {
      return mEmail;
   }

   /**
    * Sets the email of the Person.
    *
    * @param pEmail The new email string for the person.
    */
   public void setEmail(String pEmail)
   {
      this.mEmail = pEmail;
   }

   /**
    * Gets the person's last name.
    *
    * @return The last name of the Person.
    */
   public String getLastName()
   {
      return mLastName;
   }

   /**
    * Sets the value last name of the person.
    *
    * @param pLastName The person's new last name.
    */
   public void setLastName(String pLastName)
   {
      this.mLastName = pLastName;
   }

   /**
    * Gets the middle name of the Person
    *
    * @return The person's middle name.
    */
   public String getMiddleName()
   {
      return mMiddleName;
   }

   /**
    * Sets the middle name of the person.
    *
    * @param pMiddleName The new middle name.
    */
   public void setMiddleName(String pMiddleName)
   {
      this.mMiddleName = pMiddleName;
   }

   /**
    * Gets the identifier of the person.
    *
    * @return The identifier of the person.
    */
   public String getID()
   {
      return mID;
   }

   /**
    * Sets the identifier of the person.
    *
    * @param pID The new identifier of the person.
    */
   public void setID(String pID)
   {
      this.mID = pID;
   }

   /**
    * Returns the vale first name of the Person.
    *
    * @return The first name of the person
    */
   public String getFirstName()
   {
      return mFirstName;
   }

   /**
    * Sets the first name of the Person.
    *
    * @param pFirstName The new first name of the person.
    */
   public void setFirstName(String pFirstName)
   {
      this.mFirstName = pFirstName;
   }

   /**
    * Converts a Person object to a string of the person's first, middle,
    * and last name.
    *
    * @return The person's first, middle, and last name
    */
   public String toStringName()
   {
      return mFirstName + " " + mMiddleName + " " + mLastName;
   }

   /**
    * A person is considered equal if the unique identifiers are the same.
    *
    * @param obj The object to compare to see if it is equal to this Person.
    * @return True if obj and Person are equal.
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
      final Person other = (Person) obj;
      if ((this.mID == null) ? (other.mID != null)
         : !this.mID.equals(other.mID))
      {
         return false;
      }
      return true;
   }

   /**
    * Overriden hashCode method for Person.
    *
    * @return The unique hash code for this Person
    */
   @Override
   public int hashCode()
   {
      int hash = 3;
      hash = 97 * hash + (this.mID != null ? this.mID.hashCode() : 0);
      return hash;
   }

   @Override
   public String toString()
   {
      return mID + ": " + mFirstName.substring(0,1) + ". " + mLastName;
   }


}
