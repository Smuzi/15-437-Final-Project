/**
 * @file   ImageUploadForm.java
 * @author Jacob Olson <jholson@andrew.cmu.edu>
 * @date   4/26/2012
 * @class  15-437
 */

package formbean;

import java.util.ArrayList;
import java.util.List;

import java.util.TimeZone;

import org.mybeans.form.FormBean;
import org.mybeans.form.FileProperty;

public class ImageUploadForm extends FormBean {
    private int showId;
    private FileProperty image;

    public int getShowId()         { return showId; }
    public FileProperty getImage() { return image; }

    public void setShowId(int showId)        { this.showId = showId; }
    public void setImage(FileProperty image) { this.image  = image; }

    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();
        return errors;
    }
}

