package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class IdForm extends FormBean {
    private String id;

    public String getId() { return id; }

    public void setId(String s) { id = s.trim(); }

    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();

        /* Check that all of the fields are filled in. */
        if (id == null || id.length() == 0) {
            errors.add("Friend id is required");
        }

        if (errors.size() > 0) {
            return errors;
        }

        /* Check that the id string represents a valid integer. */
        if (getIdAsInt() == -1) {
            errors.add("Id string does not represent a valid integer");
        }

        return errors;
    }

    public int getIdAsInt() {
        try {
            return Integer.parseInt(id);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
