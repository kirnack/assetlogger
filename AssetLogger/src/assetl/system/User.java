/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package assetl.system;

/**
 * 
 * @author Bryon Rogers3
 */
public class User
{

    private String mID;
    private String mPassword;
    private boolean mAdmin;

    /**
     *
     */
    public User()
    {
        this("", "", false);
    }

    /**
     *
     * @param pID
     */
    public User(String pID)
    {
        this(pID, "", false);
    }

    /**
     *
     * @param pID
     * @param pPassword
     * @param pAdmin
     */
    public User(String pID, String pPassword, boolean pAdmin)
    {
        mID = pID;
        mPassword = pPassword;
        mAdmin = pAdmin;
    }

    /**
     * 
     * @return
     */
    public boolean isAdmin()
    {
        return mAdmin;
    }

    /**
     *
     * @param pAdmin
     */
    public void setAdmin(boolean pAdmin)
    {
        this.mAdmin = pAdmin;
    }

    /**
     *
     * @return
     */
    public String getID()
    {
        return mID;
    }

    /**
     *
     * @param pID
     */
    public void setID(String pID)
    {
        this.mID = pID;
    }

    /**
     *
     * @return
     */
    public String getPassword()
    {
        return mPassword;
    }

    /**
     *
     * @param pPassword
     */
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
