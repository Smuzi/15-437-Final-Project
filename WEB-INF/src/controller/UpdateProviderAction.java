/**
 * @file   UpdateProviderAction.java
 * @author Robert Liu <rql@andrew.cmu.edu>
 * @date   4/27/2012
 * @class  15-437
 */

package controller;

import java.io.File;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import org.joda.time.DateTime;
import org.joda.time.Hours;

import databean.User;
import databean.Provider;
import formbean.UpdateProviderForm;
import model.Model;
import model.UserDAO;
import model.ProviderDAO;
import util.DatabaseSync;

public class UpdateProviderAction extends Action
{
    private FormBeanFactory<UpdateProviderForm> formBeanFactory =
            FormBeanFactory.getInstance(UpdateProviderForm.class);

    private UserDAO userDAO;
    private ProviderDAO providerDAO;
    private Model model;

    public UpdateProviderAction(Model model)
    {
        this.model = model;
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

            Provider provider = providerDAO.read(
                    Integer.parseInt(form.getProviderIdAsString()));

            if (provider == null)
            {
                errors.add("ProviderId: " + form.getProviderIdAsString() + 
                        ", Zipcode: " + currUser.getZipcode() + 
                        ". Invalid provider selected (not in database)");
                return "error.jsp";
            }
            else
            {
                currUser.setProviderId(provider.getId());
            }

            DateTime lastSync = new DateTime(provider.getLastSync());
            DateTime now = new DateTime();

            int hours = Hours.hoursBetween(lastSync, now).getHours();
            if (hours >= 24) {
                File tempDir = (File)request.getServletContext().
                               getAttribute("javax.servlet.context.tempdir");
                String contextPath = request.getServletContext().
                                     getRealPath("/");

                int hoursFromNow = Math.max(7*24 - hours, 0);
                int hoursDuration = Math.min(hours, 7*24);

                DatabaseSync.syncAirings(model,
                                            tempDir,
                                            contextPath,
                                            provider.getZipcode(),
                                            provider.getName(),
                                            hoursFromNow,
                                            24);

                /*
                int i;
                for (i = 0; i < hoursDuration - 24; i += 24) {
                    DatabaseSync.syncAirings(model,
                                             tempDir,
                                             contextPath,
                                             provider.getZipcode(),
                                             provider.getName(),
                                             hoursFromNow + i,
                                             24);
                }

                DatabaseSync.syncAirings(model,
                                            tempDir,
                                            contextPath,
                                            provider.getZipcode(),
                                            provider.getName(),
                                            hoursFromNow + i,
                                            hoursDuration - i);
                                            */
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
