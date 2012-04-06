/**
 * @file      Review.java
 * @author    Robert Liu <rql@andrew.cmu.edu>
 * @date      4/05/2012
 * @class     15-437
 */

package databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("id")
public class Review
{
    /* Fields */
    private int id;
    private int showId;
    private int userId;
    private int rating;
    private String text;

    /* Getters */
    public int getId()      { return id; }
    public int getShowId()  { return showId; }
    public int getUserId()  { return userId; }
    public int getRating()  { return rating; }
    public String getText() { return text; }

    /* Setters */
    public void setId(int id)         { this.id = id; }   
    public void setShowId(int showId) { this.showId = showId; }   
    public void setUserId(int userId) { this.userId = userId; }   
    public void setRating(int rating) { this.rating = rating; }   
    public void setText(String text)  { this.text = text; }   
}
