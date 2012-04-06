/**
 * @file      Image.java
 * @author    Robert Liu <rql@andrew.cmu.edu>
 * @date      4/05/2012
 * @class     15-437
 */

package databean;

import java.util.Arrays;
import java.util.List;
import java.util.Collections;

import org.genericdao.PrimaryKey;

@PrimaryKey("id")
public class Image
{
    public final static List<String> EXTENSIONS =
        Collections.unmodifiableList(Arrays.asList(
                    new String[] {".jpg", ".png"}));

    /* Fields */
    private int id;
    private String contentType;
    private byte[] data;

    /* Getters */
    public int getId()             { return id; }
    public String getContentType() { return contentType; }
    public byte[] getData()        { return data; }

    /* Setters */
    public void setId(int id)        { this.id = id; }
    public void setContentType(String contentType) 
                                     { this.contentType = contentType; }
    public void setData(byte[] data) { this.data = data; }
}
