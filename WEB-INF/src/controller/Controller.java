/**
 * @file    Controller.java
 * @author  Robert Liu <rql@andrew.cmu.edu>
 * @date    4/05/2012
 * @class   15-437
 */

package controller;

import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import databean.User;
import model.Model;

public class Controller extends HttpServlet
{
    // My favorite number! HttpServlet is serializable and the specification
    // for serializable says we need this for deserialization purposes or
    // somesuch. Check the documentation on Serializable
    private static final long serialVersionUID = 13L;

    private Set<String> loginRequired;

    // Initializes our model and actions
    public void init() throws ServletException
    {
        String[] array = {"settings.do",
                          "profile.do"};

        loginRequired = new HashSet<String>(Arrays.asList(array));

        Model model = new Model(getServletConfig());

        Action.add(new LoginAction(model));
        Action.add(new LogoutAction(model));
        Action.add(new RegisterAction(model));
        Action.add(new HomeAction(model));
        Action.add(new ProfileAction(model));
        Action.add(new SearchAction(model));
        Action.add(new SettingsAction(model));
        // TODO: remove
        Action.add(new ParseTestAction(model));
    }

    // Handles POST requests
    // TODO: I think the way POSTs are done in HW4 is that they were only used
    // in forms and so we just redirected it to the form action. Are there any
    // instances when they'll POST and not have a corresponding form action?
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        doGet(request, response);
    }

    // Handles GET requests
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        String nextPage = performTheAction(request);
        sendToNextPage(nextPage, request, response);
    }

    // Helper function does the bulk of the work for requests
    private String performTheAction(HttpServletRequest request)
    {
        HttpSession session     = request.getSession(true);
        String      servletPath = request.getServletPath();
        User        user        = (User)session.getAttribute("user");
        String      action      = getActionName(servletPath);

        /* If no user is logged in and they're trying to do anything but
           view the home page or register, kindly redirect them to the login
           action. */
        if (user == null && loginRequired.contains(action)) {
            return Action.perform("login.do", request);
        }

        /* User is at the root of our web app */
        if (action.equals("home")) {
            return Action.perform("home.do", request);
        }

        return Action.perform(action, request);
    }

    /**
     * Sends the user to the next page
     *
     * -If nextPage is null, we send back a 404
     * -If nextPage ends with ".do" we perform the correspondinga ction
     * -If nextPage ends with ".jsp", dispatch (forward) to the page (the view)
     */
    private void sendToNextPage(String nextPage, HttpServletRequest request,
                                HttpServletResponse response)
        throws IOException, ServletException
    {
        if (nextPage == null)
        {
            response.sendError(HttpServletResponse.SC_NOT_FOUND,
                               request.getServletPath());
            return;
        }

        if (nextPage.endsWith(".do")) {
            response.sendRedirect(nextPage);
            return;
        }

        if (nextPage.endsWith(".jsp"))
        {
            RequestDispatcher d =
                request.getRequestDispatcher("WEB-INF/" + nextPage);
            d.forward(request, response);
            return;
        }

        throw new ServletException(Controller.class.getName() +
                ".sendToNextPage(\"" + nextPage + "\"): invalid extension.");
    }

    // Returns the path component after the last slash and removes any extension
    // if present
    private String getActionName(String path)
    {
        // Use the fact that the path will start with a slash.
        // According to Eppinger, this is guaranteed...
        int slashInd = path.lastIndexOf('/');
        return path.substring(slashInd + 1);
    }
}
