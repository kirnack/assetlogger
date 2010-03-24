package assetl.service;

/**
 * Locates data for person and loads it so the next view can
 * perform functions for that person.
 *
 * @author Devin Doman
 */
public class IDTeacherFunction 
        extends DBFunction
{
    /**
     * Loads teacher data into the next view
     */
    public void performFunction()
    {
        mControl.setFunction("Schedule");
    }
}
