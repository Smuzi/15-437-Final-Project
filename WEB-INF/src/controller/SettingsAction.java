/**
 * @file   SettingsAction.java
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

import databean.User;
import formbean.SettingsForm;
import model.Model;
import model.UserDAO;

public class SettingsAction extends Action {
    private FormBeanFactory<SettingsForm> formBeanFactory =
            FormBeanFactory.getInstance(SettingsForm.class);

    private UserDAO userDAO;

    public SettingsAction(Model model) {
        userDAO = model.getUserDAO();
    }

    public String getName() { return "settings.do"; }

    public String perform(HttpServletRequest request) {
        HttpSession session = request.getSession();

        /* If the user is not logged in, redirect to login screen. */
        User user = (User)session.getAttribute("user");
        if (user == null) {
            return "login.do";
        }

        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);

        try
        {
            SettingsForm form = formBeanFactory.create(request);
            request.setAttribute("form", form);

            if (!form.isPresent()) {
                form.setEmail(user.getEmail());
                form.setTimeZone(user.getTimeZone());
                form.setZipcode(user.getZipcode());
                form.setPhoneNumber(user.getPhoneNumber());

                return "settings.jsp";
            }

            /* See if there are any validation errors in the form. */
            errors.addAll(form.getValidationErrors());
            if (errors.size() != 0) {
                return "settings.jsp";
            }

            /* Update the information in the database. */
            user.setEmail(form.getEmail());
            user.setTimeZone(form.getTimeZone());
            user.setZipcode(form.getZipcode());
            user.setPhoneNumber(form.getPhoneNumber());
            userDAO.update(user);

            return "profile.do";
        } catch (Exception e) {
            errors.add(e.getClass().getName() + ": " + e.getMessage());
            return "error.jsp";
        }
    }
}
