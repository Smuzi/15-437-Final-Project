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

import model.Model;

public class SettingsAction extends Action {
    public SettingsAction(Model model) {}

    public String getName() { return "settings.do"; }

    public String perform(HttpServletRequest request) {
        HttpSession session = request.getSession();

        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);

        /* TODO In here, we need to generate settings page content. */

        try {
            return "settings.jsp";
        } catch (Exception e) {
            errors.add(e.getClass().getName() + ": " + e.getMessage());
            return "error.jsp";
        }
    }
}
