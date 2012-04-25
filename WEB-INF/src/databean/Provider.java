/**
 * @file      Provider.java
 * @author    Robert Liu <rql@andrew.cmu.edu>
 * @author    Jacob Olson <jholson@andrew.cmu.edu>
 * @date      4/05/2012
 * @class     15-437
 */

package databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("id")
public class Provider
{
    /* Fields */
    private int id;
    private String name;
    private String zipcode;

    /* Getters */
    public int getId()      { return id; }
    public String getName() { return name; }
    public String getZipcode() { return zipcode; }

    /* Setters */
    public void setId(int id) { this.id = id; }   
    public void setName(String name) { this.name = name; }   
    public void setZipcode(String zipcode) { this.zipcode = zipcode; }
}
