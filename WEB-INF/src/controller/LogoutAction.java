/**
 * @file   LogoutAction.java
 * @author Jacob Olson <jholson@andrew.cmu.edu>
 * @date   4/23/2012
 * @class  15-437
 */
package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.User;
import model.Model;
import model.UserDAO;

public class LogoutAction extends Action {
    public LogoutAction(Model model) {
    }   

    public String getName() { return "logout.do"; }

    public String perform(HttpServletRequest request) {
        HttpSession session = request.getSession();

        session.removeAttribute("user");

        return "home.do";
    }   
}
