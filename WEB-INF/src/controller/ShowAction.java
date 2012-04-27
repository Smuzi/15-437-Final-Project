/**
 * @file   ShowAction.java
 * @author Robert Liu<rql@andrew.cmu.edu>
 * @date   4/26/2012
 * @class  15-437
 */

package controller;

import java.util.Comparator;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import org.genericdao.RollbackException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import databean.User;
import databean.Show;
import databean.Airing;
import formbean.IdForm;
import model.Model;
import model.ShowDAO;
import model.AiringDAO;

public class ShowAction extends Action
{
    private FormBeanFactory<IdForm> formBeanFactory =
            FormBeanFactory.getInstance(IdForm.class);

    private ShowDAO showDAO;
    private AiringDAO airingDAO;

    public ShowAction(Model model)
    {
        showDAO = model.getShowDAO();
        airingDAO = model.getAiringDAO();
    }

    public String getName() { return "show.do"; }

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

            Show show = showDAO.read(form.getIdAsInt());
            request.setAttribute("show", show);

            // For the "Add Show" anchor
            if (currUser != null)
            {
                request.setAttribute("isFavorite", 
                        currUser.hasShow(show.getId()));
            }
            else
            {
                request.setAttribute("isFavorite", true);
            }

            // Get airings for this show
            generateAirings(request, form.getIdAsInt(), 
                    currUser.getProviderId());

            return "show.jsp";
        } catch (Exception e)
        {
            errors.add(e.getClass().getName() + ": " + e.getMessage());
            return "error.jsp";
        }
    }

    private void generateAirings(HttpServletRequest request, int showId, 
                                 int providerId)
        throws RollbackException
    {
        List<Show> shows = new ArrayList<Show>();

        Airing[] airings = airingDAO.readAiringsByShowIdAndProviderId(
                            showId, providerId);

        Arrays.sort(airings, new Comparator<Airing>()
            {
                public int compare(Airing a1, Airing a2)
                {
                    if (a1.getStartTime().before(a2.getStartTime()))
                    {
                        return -1;
                    }
                    else if (a1.getStartTime().after(a2.getStartTime()))
                    {
                        return 1;
                    }
                    else
                    {
                        if (a1.getStopTime().before(a2.getStopTime()))
                        {
                            return -1;
                        }
                        else if (a1.getStopTime().after(a2.getStopTime()))
                        {
                            return 1;
                        }
                        else
                        {
                            return 0;
                        }
                    }
                }
            });
        
        
        request.setAttribute("airings", airings);
    }
}
