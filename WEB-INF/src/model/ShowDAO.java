/**
 * @file      ShowDAO.java
 * @author    Robert Liu <rql@andrew.cmu.edu>
 * @author    Jacob Olson <jholson@andrew.cmu.edu>
 * @date      4/05/2012
 * @class     15-437
 */

package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import databean.Show;

public class ShowDAO extends GenericDAO<Show>
{
    public ShowDAO(String tableName, ConnectionPool connectionPool)
                     throws DAOException
    {
        super(Show.class, tableName, connectionPool);
    }

    public Show readByShowName(Object key) throws RollbackException
    {
        String showName = (String) key;

        Show[] matchedShows = super.match(
                                MatchArg.equals("showName", showName));
        
        return (matchedShows.length == 0) ? null : matchedShows[0];
    }
}
