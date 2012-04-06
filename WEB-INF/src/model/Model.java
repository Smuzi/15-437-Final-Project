/**
 * @file    Model.java
 * @author  Robert Liu <rql@andrew.cmu.edu>
 * @date    4/05/2012
 * @class   15-437
 */

package model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;

public class Model
{
    private AiringDAO airingDAO;
    private ImageDAO imageDAO;
    private NetworkDAO networkDAO;
    private ReviewDAO reviewDAO;
    private ShowDAO showDAO;
    private UserDAO userDAO;

    public Model(ServletConfig config) throws ServletException
    {
        try
        {
            String jdbcDriver = config.getInitParameter("jdbcDriverName");
            String jdbcURL = config.getInitParameter("jdbcURL");
            
            ConnectionPool pool = new ConnectionPool(jdbcDriver, jdbcURL);

            airingDAO = new AiringDAO("airing", pool);
            imageDAO = new ImageDAO("image", pool);
            networkDAO = new NetworkDAO("network", pool);
            reviewDAO = new ReviewDAO("review", pool);
            showDAO = new ShowDAO("tvshow", pool);
            userDAO = new UserDAO("user", pool);
        }
        catch (DAOException e)
        {
            throw new ServletException(e);
        }
    }

    /* Getters */
    public AiringDAO getAiringDAO()   { return airingDAO; }
    public ImageDAO getImageDAO()     { return imageDAO; }
    public NetworkDAO getNetworkDAO() { return networkDAO; }
    public ReviewDAO getReviewDAO()   { return reviewDAO; }
    public ShowDAO getShowDAO()       { return showDAO; }
    public UserDAO getUserDAO()       { return userDAO; }
}
