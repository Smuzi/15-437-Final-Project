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

    /* Getters */
    public int getId()             { return id; }
    public int[] getShowIds()      { return showIds; }
    public String getUsername()    { return username; }
    public String getPassword()    { return password; }
    public String getEmail()       { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getTimeZone()    { return timeZone; }
    public String getZipcode()     { return zipcode; }

    /* Setters */

    public void setId(int id)                { this.id = id; }   
    public void setShowIds(int[] showIds)    { this.showIds = showIds; }   
    public void setUsername(String username) { this.username = username; }   
    public void setPassword(String password) { this.password = password; }   
    public void setEmail(String email)       { this.email = email; }   
    public void setPhoneNumber(String phoneNumber)
                                             { this.phoneNumber = phoneNumber; }
    public void setTimeZone(String timeZone) { this.timeZone = timeZone; }   
    public void setZipcode(String zipcode)   { this.zipcode = zipcode; }

	/* Functions dealing with this databean */
	public void init() {
		setShowIds(new int[0]);
		setPhoneNumber("");
	}
}
