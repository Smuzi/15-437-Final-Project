/**
 * @file      User.java
 * @author    Robert Liu <rql@andrew.cmu.edu>
 * @date      4/05/2012
 * @class     15-437
 */

package databean;

import org.genericdao.PrimaryKey;
import org.genericdao.MaxSize;

@PrimaryKey("id")
public class User
{
	/* The maximum lengths of the data fields */
	public static final int usernameLength = 255;
	public static final int passwordLength = 255;
	public static final int emailLength = 255;
	public static final int phoneNumberLength = 255;
	public static final int timeZoneLength = 255;
	public static final int zipcodeLength = 255;

    /* Fields */
    private int id;
    private int[] showIds;
    private int providerId;
	@MaxSize(usernameLength)
    private String username;
	@MaxSize(passwordLength)
    private String password;
	@MaxSize(emailLength)
    private String email;
	@MaxSize(phoneNumberLength)
    private String phoneNumber;
	@MaxSize(timeZoneLength)
    private String timeZone;
	@MaxSize(zipcodeLength)
    private String zipcode;
    private boolean admin;

    /* Getters */
    public int getId()             { return id; }
    public int[] getShowIds()      { return showIds; }
    public int getProviderId()     { return providerId; }
    public String getUsername()    { return username; }
    public String getPassword()    { return password; }
    public String getEmail()       { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getTimeZone()    { return timeZone; }
    public String getZipcode()     { return zipcode; }
    public boolean getAdmin()      { return admin; }

    /* Setters */

    public void setId(int id)                { this.id = id; }   
    public void setShowIds(int[] showIds)    { this.showIds = showIds; }   
    public void setProviderId(int providerId)
                                             { this.providerId = providerId; }
    public void setUsername(String username) { this.username = username; }   
    public void setPassword(String password) { this.password = password; }   
    public void setEmail(String email)       { this.email = email; }   
    public void setPhoneNumber(String phoneNumber)
                                             { this.phoneNumber = phoneNumber; }
    public void setTimeZone(String timeZone) { this.timeZone = timeZone; }   
    public void setZipcode(String zipcode)   { this.zipcode = zipcode; }
    public void setAdmin(boolean admin)      { this.admin = admin; }

	/* Functions dealing with this databean */
	public void init() {
		setShowIds(new int[0]);
		setPhoneNumber("");
        setAdmin(false);
	}

    public boolean addShowId(int showId)
    {
        // Validate showId
        if (hasShow(showId))
        {
            // We didn't add it
            return false;
        }

        int[] oldShowIds = this.showIds;
        int[] newShowIds = new int[oldShowIds.length + 1];

        // Copy the showIds over
        for (int i = 0; i < oldShowIds.length; i++)
        {
            newShowIds[i] = oldShowIds[i];
        }

        newShowIds[oldShowIds.length] = showId;

        this.setShowIds(newShowIds);
        
        return true;
    }

    public boolean deleteShowId(int showId)
    {
        // Validate showId
        if (!hasShow(showId))
        {
            // We can't delete it, it's not there
            return false;
        }
        
        int[] oldShowIds = this.showIds;
        int[] newShowIds = new int[oldShowIds.length - 1];

        // Copy ids over other than the deleted one
        for (int i = 0, newShowInd = 0; i < oldShowIds.length; 
                i++, newShowInd++)
        {
            if (oldShowIds[i] == showId)
            {
                newShowInd--;
                continue;
            } 
            else
            {
                newShowIds[newShowInd] = oldShowIds[i];
            }
        }

        this.setShowIds(newShowIds);

        return true;
    }

    // Used both for adding/deleting and for deciding whether or not
    // to display links for adding/deleting
    public boolean hasShow(int showId)
    {
        for (int i = 0; i < this.showIds.length; i++)
        {
            if (showIds[i] == showId)
            {
                return true;
            }
        }

        return false;
    }
}
