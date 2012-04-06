/**
 * @file      Show.java
 * @author    Robert Liu <rql@andrew.cmu.edu>
 * @date      4/05/2012
 * @class     15-437
 */

package databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("id")
public class Show
{
    /* Fields */
    private int id;
    private int imageId;
    private int[] airingIds;
    private int[] reviewIds;
    private String showName;
    private String description;

    /* Getters */
    public int getId() { return id; }
    public int[] getAiringIds() { return airingIds; }
    public int[] getReviewIds() { return reviewIds; }
    public String getShowName() { return showName; }
    public String getDescription() { return description; }

    /* Setters */
    public void setId(int id)                 { this.id = id; }   
    public void setAiringIds(int[] airingIds) { this.airingIds = airingIds; }   
    public void setReviewIds(int[] reviewIds) { this.reviewIds = reviewIds; }   
    public void setShowName(String showName)  { this.showName = showName; }   
    public void setDescription(String description) 
                                            { this.description = description; }
}
