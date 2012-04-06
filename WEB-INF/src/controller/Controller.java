/**
 * @file    Controller.java
 * @author  Robert Liu <rql@andrew.cmu.edu>
 * @date    4/05/2012
 * @class   15-437
 */

package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Model;

public class Controller extends HttpServlet
{
    // My favorite number! HttpServlet is serializable and the specification
    // for serializable says we need this for deserialization purposes or
    // somesuch. Check the documentation on Serializable
    private static final long serialVersionUID = 13L;

    // Initializes our model and actions
    public void init() throws ServletException
    {
        Model model = new Model(getServletConfig());
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
        HttpSession session = request.getSession(true);
        String servletPath = request.getServletPath();
        String action = getActionName(servletPath);

        if (action.equals("home"))
        {
            // TODO: temporary
            return "home.jsp";
        }
        else
        {
            return null;
        }
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

        if (nextPage.endsWith(".jsp"))
        {
            RequestDispatcher d = request.getRequestDispatcher(nextPage);
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
