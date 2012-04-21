/**
 * @file      User.java
 * @author    Robert Liu <rql@andrew.cmu.edu>
 * @date      4/05/2012
 * @class     15-437
 */

package databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("id")
public class User
{
    /* Fields */
    private int id;
    private int[] showIds;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String timezone;
    private String zipcode;

    /* Getters */
    public int getId()             { return id; }
    public int[] getShowIds()      { return showIds; }
    public String getUsername()    { return username; }
    public String getPassword()    { return password; }
    public String getEmail()       { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getTimezone()    { return timezone; }
    public String getZipcode()     { return zipcode; }

    /* Setters */

    public void setId(int id)                { this.id = id; }   
    public void setShowIds(int[] showIds)    { this.showIds = showIds; }   
    public void setUsername(String username) { this.username = username; }   
    public void setPassword(String password) { this.password = password; }   
    public void setEmail(String email)       { this.email = email; }   
    public void setPhoneNumber(String phoneNumber)
                                             { this.phoneNumber = phoneNumber; }
    public void setTimezone(String timezone) { this.timezone = timezone; }   
    public void setZipcode(String zipcode)   { this.zipcode = zipcode; }
}

