/**
 * @file   HomeAction.java
 * @author Jacob Olson <jholson@andrew.cmu.edu>
 * @author Robert Liu <rql@andrew.cmu.edu>
 * @date   4/23/2012
 * @class  15-437
 */

package controller;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Comparator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import model.Model;
import model.ShowDAO;
import model.UserDAO;
import databean.Show;
import databean.User;

public class HomeAction extends Action {
    private final int NUM_TOP_SHOWS = 4;

    private ShowDAO showDAO;
    private UserDAO userDAO;
    // This needs to be here because of the way I declared the comparator
    // Probably easier if I just declared the Comparator separately
    private Map<Integer, Integer> showsToFollowers;

    public HomeAction(Model model) {
        showDAO = model.getShowDAO(); 
        userDAO = model.getUserDAO(); 
    }

    public String getName() { return "home.do"; }

    public String perform(HttpServletRequest request) {
        HttpSession session = request.getSession();

        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);

        try {
            Show[] allShows = showDAO.match(); 

            // Generate a map for shows -> number of followers
            showsToFollowers = new HashMap<Integer, Integer>();
            User[] allUsers = userDAO.match();

            if (allUsers != null)
            {
                for (User user : allUsers)
                {
                    for (Integer showId : user.getShowIds())
                    {
                        Integer numFollowers = showsToFollowers.get(showId);
                        if (numFollowers != null)
                        {
                            showsToFollowers.put(
                                    showId, numFollowers.intValue() + 1);
                        }
                        else
                        {
                            showsToFollowers.put(showId, 1);
                        }
                    }
                }
            }

            Arrays.sort(allShows, new Comparator<Show>() {
                        public int compare(Show show1, Show show2) {
                            // Most followers first
                            Integer followers1 = showsToFollowers.get(show1.getId());
                            Integer followers2 = showsToFollowers.get(show2.getId());

                            int fans1 = (followers1 == null) 
                                        ? 0 
                                        : followers1.intValue();
                                
                            int fans2 = (followers2 == null) 
                                        ? 0 
                                        : followers2.intValue();
                            
                            return fans2 - fans1;
                        }
                    });

            // Get the top shows from our sorted list
            List<Show> topShows = new ArrayList<Show>();
            request.setAttribute("topShows", topShows);
            for (int i = 0; i < NUM_TOP_SHOWS && i < allShows.length; i++)
            {
                topShows.add(allShows[i]);
            }

            return "home.jsp";
        } catch (Exception e) {
            errors.add(e.getClass().getName() + ": " + e.getMessage());
            return "error.jsp";
        }
    }
}
