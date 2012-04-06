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
    public Model(ServletConfig config) throws ServletException
    {
        try
        {
            String jdbcDriver = config.getInitParameter("jdbcDriverName");
            String jdbcURL = config.getInitParameter("jdbcURL");
            
            ConnectionPool pool = new ConnectionPool(jdbcDriver, jdbcURL);
        }
        catch (DAOException e)
        {
            throw new ServletException(e);
        }
    }
}
