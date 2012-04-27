/**
 * @file   RegisterAction.java
 * @author Jacob Olson <jholson@andrew.cmu.edu>
 * @date   4/23/2012
 * @class  15-437
 */

package controller;

import java.io.File;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.User;
import formbean.RegisterForm;
import model.Model;
import model.UserDAO;
import util.DatabaseSync;

public class RegisterAction extends Action {
    private FormBeanFactory<RegisterForm> formBeanFactory =
        FormBeanFactory.getInstance(RegisterForm.class);

    private UserDAO userDAO;
    private Model model;

    public RegisterAction(Model model) {
        this.model = model;
        userDAO = model.getUserDAO();
    }

    public String getName() { return "register.do"; }

    public String perform(HttpServletRequest request) {
        HttpSession session = request.getSession();

        /* If the user is already logged in, redirect to home.do */
        if (session.getAttribute("user") != null) {
            return "profile.do";
        }

        List<String> errors = new ArrayList<String>();
        request.setAttribute("registerErrors", errors);

        try {
            RegisterForm form = formBeanFactory.create(request);
            request.setAttribute("registerForm", form);

            /* See if there are any validation errors in the form. */
            errors.addAll(form.getValidationErrors());
            if (errors.size() != 0) {
                return "login_reg.jsp";
            }

            /* Make a new user and initialize the fields. */
            User user = new User();
            user.init();
            user.setUsername(form.getUsername());
            user.setPassword(form.getPassword());
            user.setEmail(form.getEmail());
            user.setTimeZone(form.getTimeZone());
            user.setZipcode(form.getZipcode());

            /* Add the user to the database. */
            userDAO.create(user);

            /* Attach the user bean to the session. */
            session.setAttribute("user", user);

            File tempDir = (File)request.getServletContext().
                           getAttribute("javax.servlet.context.tempdir");
            String contextPath = request.getServletContext().
                                 getRealPath("/");

            DatabaseSync.generateProviders(model, tempDir, contextPath,
                                           user.getZipcode());

            return "profile.do";
        } catch (Exception e) {
            request.setAttribute("errors", errors);
            errors.add(e.getClass().getName() + ": " + e.getMessage());
            return "error.jsp";
        }
    }
}
