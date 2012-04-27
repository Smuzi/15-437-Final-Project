/**
 * @file   DeleteShowAction.java
 * @author Robert Liu <rql@andrew.cmu.edu>
 * @date   4/27/2012
 * @class  15-437
 */

package controller;

import java.util.List;
import java.util.ArrayList;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import databean.User;
import formbean.IdForm;
import model.Model;
import model.UserDAO;

public class DeleteShowAction extends Action
{
    private FormBeanFactory<IdForm> formBeanFactory =
            FormBeanFactory.getInstance(IdForm.class);

    private UserDAO userDAO;

    public DeleteShowAction(Model model)
    {
        userDAO = model.getUserDAO();
    }

    public String getName() { return "deleteshow.do"; }

    public String perform(HttpServletRequest request)
    {

        HttpSession session = request.getSession();

        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);

        User currUser = (User) session.getAttribute("user");

        if (currUser == null)
        {
            return "login.do";
        }

        try
        {
            IdForm form = formBeanFactory.create(request);

            errors.addAll(form.getValidationErrors());
            if (errors.size() != 0)
            {
                return "error.jsp";
            }

            if (!currUser.deleteShowId(form.getIdAsInt()))
            {
                errors.add("Show not favorited");
                return "error.jsp";
            }

            userDAO.update(currUser);

            return "profile.do";
        } catch (Exception e)
        {
            errors.add(e.getClass().getName() + ": " + e.getMessage());
            return "error.jsp";
        }
    }
}
