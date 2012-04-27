/**
 * @file   ImageUploadForm.java
 * @author Jacob Olson <jholson@andrew.cmu.edu>
 * @date   4/26/2012
 * @class  15-437
 */

package formbean;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;

import java.util.TimeZone;

import org.mybeans.form.FormBean;
import org.mybeans.form.FileProperty;

public class ImageUploadForm extends FormBean {
    private static final Set<String> allowedTypes;
    static {
        Set<String> tmp = new HashSet<String>();
        tmp.add("image/jpg");
        tmp.add("image/jpeg");
        tmp.add("image/bmp");
        tmp.add("image/gif");
        tmp.add("image/png");
        allowedTypes = Collections.unmodifiableSet(tmp);
    }

    private String showId;
    private FileProperty image;
    private String action;

    public String getShowId()         { return showId; }
    public FileProperty getImage() { return image; }
    public String getAction()      { return action; }

    public void setShowId(String showId)        { this.showId = showId; }
    public void setImage(FileProperty image) { this.image  = image; }
    public void setAction(String action)     { this.action = action; }

    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();

        if (action == null) {
            errors.add("Invalid action");
            return errors;
        }
        
        if (action.equals("toForm")) {
            return errors;
        }

        if (!action.equals("upload")) {
            errors.add("Invalid action");
            return errors;
        }

        if (!allowedTypes.contains(image.getContentType())) {
            errors.add("Invalid file type");
            return errors;
        }

        return errors;
    }

    public int getShowIdAsInt() {
        try {
            return Integer.parseInt(showId);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}

