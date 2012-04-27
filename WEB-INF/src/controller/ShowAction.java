/**
 * @file   ShowAction.java
 * @author Robert Liu<rql@andrew.cmu.edu>
 * @date   4/26/2012
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
import databean.Show;
import formbean.IdForm;
import model.Model;
import model.ShowDAO;

public class ShowAction extends Action
{
    private FormBeanFactory<IdForm> formBeanFactory =
            FormBeanFactory.getInstance(IdForm.class);

    private ShowDAO showDAO;

    public ShowAction(Model model)
    {
        showDAO = model.getShowDAO();
    }

    public String getName() { return "show.do"; }

    public String perform(HttpServletRequest request)
    {
        HttpSession session = request.getSession();

        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);

        User currUser = (User) session.getAttribute("user");

        try
        {
            IdForm form = formBeanFactory.create(request);

            errors.addAll(form.getValidationErrors());
            if (errors.size() != 0)
            {
                return "errors.jsp";
            }

            Show show = showDAO.read(form.getIdAsInt());
            request.setAttribute("show", show);

            // For the "Add Show" anchor
            if (currUser != null)
            {
                request.setAttribute("isFavorite", currUser.hasShow(show.getId()));
            }
            else
            {
                request.setAttribute("isFavorite", true);
            }

            return "show.jsp";
        } catch (Exception e)
        {
            errors.add(e.getClass().getName() + ": " + e.getMessage());
            return "error.jsp";
        }
    }
}
