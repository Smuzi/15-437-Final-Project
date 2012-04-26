/**
 * @file      Show.java
 * @author    Robert Liu <rql@andrew.cmu.edu>
 * @author    Jacob Olson <jholson@andrew.cmu.edu>
 * @date      4/05/2012
 * @class     15-437
 */

package databean;

import org.genericdao.MaxSize;
import org.genericdao.PrimaryKey;

@PrimaryKey("id")
public class Show
{
    /* The maximum lengths of the data fields */
    public static final int showNameLength = 255;
    public static final int descriptionLength = 1000;

    /* Fields */
    private int id;
    private int imageId;
    //private int[] airingIds;
    //private int[] reviewIds;
    @MaxSize(showNameLength)
    private String showName;
    @MaxSize(descriptionLength)
    private String description;

    /* Getters */
    public int getId()          { return id; }
    public int getImageId()     { return imageId; }
    //public int[] getAiringIds() { return airingIds; }
    //public int[] getReviewIds() { return reviewIds; }
    public String getShowName() { return showName; }
    public String getDescription() { return description; }

    /* Setters */
    public void setId(int id)                 { this.id = id; }   
    public void setImageId(int imageId)       { this.imageId = imageId; }
    //public void setAiringIds(int[] airingIds) { this.airingIds = airingIds; }
    //public void setReviewIds(int[] reviewIds) { this.reviewIds = reviewIds; }
    public void setShowName(String showName)  { this.showName = showName; }   
    public void setDescription(String description) 
                                            { this.description = description; }

    public void init() {
        setImageId(0);
    }
}
