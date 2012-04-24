/**
 * @file   SearchAction.java
 * @author Jacob Olson <jholson@andrew.cmu.edu>
 * @date   4/23/2012
 * @class  15-437
 */

package controller;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import formbean.SearchForm;
import model.Model;

public class SearchAction extends Action {
    private FormBeanFactory<SearchForm> formBeanFactory =
            FormBeanFactory.getInstance(SearchForm.class);

    public SearchAction(Model model) {}

    public String getName() { return "search.do"; }

    public String perform(HttpServletRequest request) {
        HttpSession session = request.getSession();

        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);


        try {
            SearchForm form = formBeanFactory.create(request);
            request.setAttribute("form", form);

            /* See if there are any validation errors in the form. */
            errors.addAll(form.getValidationErrors());
            if (errors.size() != 0) {
                return "search.jsp";
            }

            /* TODO In here, we need to generate search page content. */

            return "search.jsp";
        } catch (Exception e) {
            errors.add(e.getClass().getName() + ": " + e.getMessage());
            return "error.jsp";
        }
    }
}
