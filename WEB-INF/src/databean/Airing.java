/**
 * @file      Airing.java
 * @author    Robert Liu <rql@andrew.cmu.edu>
 * @date      4/05/2012
 * @class     15-437
 */

package databean;

import java.util.Date;

import org.genericdao.PrimaryKey;

@PrimaryKey("id")
public class Airing
{
    /* Fields */
    private int id;
    private int showId;
    private int networkId;
    private Date airTime;

    /* Getters */
    public int getId()          { return id; }
    public int getShowId()      { return showId; }
    public int getNetworkId()   { return networkId; }
    public Date getAiringTime() { return airTime; }

    /* Setters */
    public void setId(int id) { this.id = id; }   
    public void setShowId(int showId) { this.showId = showId; }   
    public void setNetworkId(int networkId) { this.networkId = networkId; }   
    public void setDescription(Date airTime) { this.airTime = airTime; }   
}
