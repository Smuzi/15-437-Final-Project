/**
 * @file   SearchForm.java
 * @author Jacob Olson <jholson@andrew.cmu.edu>
 * @date   4/24/2012
 * @class  15-437
 */

package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class SearchForm extends FormBean {
    private String query;

    public String getQuery()  { return query; }

    public void setQuery(String s)  { query  = s.trim(); }

    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();

        if (query == null || query.length() == 0) {
            errors.add("Search query is required");
        }   

        if (errors.size() > 0) {
            return errors;
        }   

        if (query.matches(".*[<>\"].*")) {
            errors.add(
                    "Search query may not contain angle brackets or quotes");
        }

        return errors;
    }   
}
