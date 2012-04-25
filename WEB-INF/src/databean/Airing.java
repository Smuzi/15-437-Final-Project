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
    private int providerId;
    private Date airTime;
    private int channelNumber;
    private String channelName;

    /* Getters */
    public int getId()          { return id; }
    public int getShowId()      { return showId; }
    public int getProviderId()  { return providerId; }
    public Date getAiringTime() { return airTime; }
    public int getChannelNumber() { return channelNumber; }
    public String getChannelName() { return channelName; }

    /* Setters */
    public void setId(int id)                 { this.id = id; }
    public void setShowId(int showId)         { this.showId = showId; }
    public void setProviderId(int providerId) { this.providerId = providerId; }
    public void setDescription(Date airTime)  { this.airTime = airTime; }
    public void setChannelNumber(int channelNumber)
                                        { this.channelNumber = channelNumber; }
    public void setChannelName(String channelName)
                                        { this.channelName = channelName; }
}
