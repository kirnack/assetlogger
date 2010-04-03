package assetl.system;

/**
 * A packet to send a single Person object
 * @author Devo
 */
public class PersonPacket
   implements DataPacket
{
   /**
    * The person to send in the packet
    */
   private Person mPerson;

   /**
    * Default Constructor
    */
   public PersonPacket()
   {
      this(null);
   }

   /**
    * Constructor with parameters
    *
    * @param pPerson The person to send
    */
   public PersonPacket(Person pPerson)
   {
      mPerson = pPerson;
   }

   /**
    * Sets a person
    *
    * @param pPerson The person to send
    */
   public void setPerson(Person pPerson)
   {
      mPerson = pPerson;
   }

   /**
    * Gets a person
    *
    * @return The person
    */
   public Person getPerson()
   {
      return mPerson;
   }
}
