/**
 * @file   UpdateProviderAction.java
 * @author Robert Liu <rql@andrew.cmu.edu>
 * @date   4/27/2012
 * @class  15-437
 */

package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.User;
import databean.Provider;
import formbean.UpdateProviderForm;
import model.Model;
import model.UserDAO;
import model.ProviderDAO;

public class UpdateProviderAction extends Action
{
    private FormBeanFactory<UpdateProviderForm> formBeanFactory =
            FormBeanFactory.getInstance(UpdateProviderForm.class);

    private UserDAO userDAO;
    private ProviderDAO providerDAO;

    public UpdateProviderAction(Model model)
    {
        userDAO = model.getUserDAO();
        providerDAO = model.getProviderDAO();
    }

    public String getName() { return "updateprovider.do"; }

    public String perform(HttpServletRequest request)
    {
        HttpSession session = request.getSession();

        // If the user is not logged in, redirect to login screen
        User currUser = (User) session.getAttribute("user");
        if (currUser == null)
        {
            return "login.do";
        }

        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);

        try
        {
            UpdateProviderForm form = formBeanFactory.create(request);
            if (!form.isPresent())
            {
                return "settings.do";
            }

            Provider provider = providerDAO.readByNameAndZipcode(
                                  form.getProvider(), currUser.getZipcode());
            if (provider == null)
            {
                errors.add("Provider: " + form.getProvider() + 
                        ", Zipcode: " + currUser.getZipcode() + 
                        ". Invalid provider selected (not in database)");
                return "error.jsp";
            }
            else
            {
                currUser.setProviderId(provider.getId());
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
