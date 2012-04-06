/**
 * @file      Network.java
 * @author    Robert Liu <rql@andrew.cmu.edu>
 * @date      4/05/2012
 * @class     15-437
 */

package databean;

import java.util.Date;

import org.genericdao.PrimaryKey;

@PrimaryKey("id")
public class Network
{
    /* Fields */
    private int id;
    private String name;

    /* Getters */
    public int getId()      { return id; }
    public String getName() { return name; }

    /* Setters */
    public void setId(int id) { this.id = id; }   
    public void setName(String name) { this.name = name; }   
}
