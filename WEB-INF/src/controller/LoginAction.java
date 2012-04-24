/**
 * @file   LoginAction.java
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
import formbean.LoginForm;
import model.Model;
import model.UserDAO;

public class LoginAction extends Action {
    private FormBeanFactory<LoginForm> formBeanFactory =
        FormBeanFactory.getInstance(LoginForm.class);

    private UserDAO userDAO;

    public LoginAction(Model model) {
        userDAO = model.getUserDAO();
    }

    public String getName() { return "login.do"; }

    public String perform(HttpServletRequest request) {
        HttpSession session = request.getSession();

        /* If the user is already logged in, redirect to home.do */
        if (session.getAttribute("user") != null) {
            return "profile.do";
        }

        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);

        try {
            LoginForm form = formBeanFactory.create(request);
            request.setAttribute("loginForm", form);

            /* If no parameters were passed, assume that this is the first time
               the form's being shown. */
            if (!form.isPresent()) {
                return "login_reg.jsp";
            }

            /* See if there are any validation errors in the form. */
            errors.addAll(form.getValidationErrors());
            if (errors.size() != 0) {
                return "login_reg.jsp";
            }

            /* Look up the user in the database. */
            User user = userDAO.readByUsername(form.getUsername());
            if (user == null) {
                errors.add("No such username");
                return "login_reg.jsp";
            }

            /* Check the password. */
            if (!user.getPassword().equals(form.getPassword())) {
                errors.add("Incorrect password");
                return "login_reg.jsp";
            }

            /* Attach the user bean to the session. */
            session.setAttribute("user", user);

            return "profile.do";
        } catch (Exception e) {
            errors.add(e.getClass().getName() + ": " + e.getMessage());
            return "error.jsp";
        }
    }
}
