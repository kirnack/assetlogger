package assetl.system;

/**
 * A packet to hold and transmit a String
 *
 * @author Devin Doman
 */
public class StringPacket
        implements DataPacket
{
    String mString;

    public StringPacket()
    {
        this(null);
    }

    public StringPacket(String pString)
    {
        mString = pString;
    }

    public void setString(String pString)
    {
        mString = pString;
    }

    public String getString()
    {
        return mString;
    }
}
