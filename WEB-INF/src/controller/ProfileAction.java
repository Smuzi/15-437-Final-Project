/**
 * @file   ProfileAction.java
 * @author Jacob Olson <jholson@andrew.cmu.edu>
 * @author Robert Liu <rql@andrew.cmu.edu>
 * @date   4/23/2012
 * @class  15-437
 */

package controller;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.Comparator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;

import model.Model;
import model.ShowDAO;
import model.AiringDAO;
import databean.User;
import databean.Show;
import databean.Airing;
import viewmodel.ShowCalVM;

public class ProfileAction extends Action
{
    private ShowDAO showDAO;
    private AiringDAO airingDAO;
    // Used for calculating what day it is
    private Calendar cal;

    public ProfileAction(Model model)
    {
        showDAO = model.getShowDAO();
        airingDAO = model.getAiringDAO();
        cal = new GregorianCalendar();
    }

    public String getName() { return "profile.do"; }

    public String perform(HttpServletRequest request)
    {
        HttpSession session = request.getSession();

        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);

        try
        {
            User currUser = (User) session.getAttribute("user");

            // Get current user's shows
            int[] showIds = currUser.getShowIds();
            List<Show> shows = new ArrayList<Show>();

            for (int i = 0; i < showIds.length; i++)
            {
                shows.add(showDAO.read(showIds[i]));
            }

            // Grab airings and package them nicely for the jsp
            generateShowTimes(request, shows);

            return "profile.jsp";
        } catch (Exception e)
        {
            errors.add(e.getClass().getName() + ": " + e.getMessage());
            return "error.jsp";
        }
    }

    private void generateShowTimes(HttpServletRequest request, List<Show> shows)
        throws RollbackException
    {
        List<ShowCalVM> showCalVMs = new ArrayList<ShowCalVM>();

        for (Show show : shows)
        {
            Airing[] allAirings = airingDAO.readAiringsByShowId(show.getId());

            ShowCalVM showCalVM = new ShowCalVM(show,
                                generateOneDay(allAirings, Calendar.MONDAY),
                                generateOneDay(allAirings, Calendar.TUESDAY),
                                generateOneDay(allAirings, Calendar.WEDNESDAY),
                                generateOneDay(allAirings, Calendar.THURSDAY),
                                generateOneDay(allAirings, Calendar.FRIDAY),
                                generateOneDay(allAirings, Calendar.SATURDAY),
                                generateOneDay(allAirings, Calendar.SUNDAY));

            showCalVMs.add(showCalVM);
        }

        request.setAttribute("showCalVMs", showCalVMs);
    }

    private Airing[] generateOneDay(Airing[] airings, int day) 
        throws RollbackException
    {
        // Filtered by what day it is
        List<Airing> filteredAirings = new ArrayList<Airing>();

        for (Airing airing : airings)
        {
            cal.setTime(airing.getStartTime());
            // Check what day the airing is
            if (cal.get(Calendar.DAY_OF_WEEK) == day)
            {
                filteredAirings.add(airing);
            }
        }

        // Sort the airings by start date
        Airing[] filteredAiringsAsArray;
        filteredAiringsAsArray = filteredAirings.toArray(new Airing[1]);
        Arrays.sort(filteredAiringsAsArray, new Comparator<Airing>()
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

        return filteredAiringsAsArray;
    }
}
