/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package assetl.system;

/**
 *
 * @author 
 */
public class User
{

    private String mID;
    private String mPassword;
    private boolean mAdmin;

    public User()
    {
        this("", "", false);
    }

    public User(String pID)
    {
        this(pID, "", false);
    }

    public User(String pID, String pPassword, boolean pAdmin)
    {
        mID = pID;
        mPassword = pPassword;
        mAdmin = pAdmin;
    }

    public boolean isAdmin()
    {
        return mAdmin;
    }

    public void setAdmin(boolean pAdmin)
    {
        this.mAdmin = pAdmin;
    }

    public String getID()
    {
        return mID;
    }

    public void setID(String pID)
    {
        this.mID = pID;
    }

    public String getPassword()
    {
        return mPassword;
    }

    public void setPassword(String pPassword)
    {
        this.mPassword = pPassword;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null) {
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

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 97 * hash + (this.mID != null ? this.mID.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString()
    {
        return mID;
    }
}
