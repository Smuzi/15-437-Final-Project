/**
 * @file   SearchAction.java
 * @author Jacob Olson <jholson@andrew.cmu.edu>
 * @date   4/23/2012
 * @class  15-437
 */

package controller;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.genericdao.MatchArg;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import formbean.SearchForm;
import model.Model;
import model.ShowDAO;
import databean.Show;

public class SearchAction extends Action {
    private FormBeanFactory<SearchForm> formBeanFactory =
            FormBeanFactory.getInstance(SearchForm.class);

    private ShowDAO showDAO;

    public SearchAction(Model model) {
        showDAO = model.getShowDAO();
    }

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

            /* Search through shows and find matches */
            Set<Show> shows = new HashSet<Show>();
            request.setAttribute("shows", shows);
            String[] queries = form.getQuery().split("\\s+");
            for (int i = 0; i < queries.length; i++) {
                // Ignore empty keywords in query if split() is weird
                if (queries[i].isEmpty()) {
                    continue;
                }

                Show[] matchedShows = showDAO.match(
                          MatchArg.containsIgnoreCase("showName", queries[i]));

                shows.addAll(Arrays.asList(matchedShows));
            }
            
            return "search.jsp";
        } catch (Exception e) {
            errors.add(e.getClass().getName() + ": " + e.getMessage());
            return "error.jsp";
        }
    }
}
