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
    /* Fields */
    private int id;
    private String contentType;
    private byte[] bytes;

    /* Getters */
    public int getId()             { return id; }
    public String getContentType() { return contentType; }
    public byte[] getBytes()        { return bytes; }

    /* Setters */
    public void setId(int id)        { this.id = id; }
    public void setContentType(String contentType) 
                                     { this.contentType = contentType; }
    public void setBytes(byte[] bytes) { this.bytes = bytes; }
}
