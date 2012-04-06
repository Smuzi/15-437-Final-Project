/**
 * @file      ShowDAO.java
 * @author    Robert Liu <rql@andrew.cmu.edu>
 * @date      4/05/2012
 * @class     15-437
 */

package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;

import databean.Show;

public class ShowDAO extends GenericDAO<Show>
{
    public ShowDAO(String tableName, ConnectionPool connectionPool)
                     throws DAOException
    {
        super(Show.class, tableName, connectionPool);
    }
}
