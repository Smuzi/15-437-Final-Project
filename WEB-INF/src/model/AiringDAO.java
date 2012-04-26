/**
 * @file      AiringDAO.java
 * @author    Robert Liu <rql@andrew.cmu.edu>
 * @date      4/05/2012
 * @class     15-437
 */

package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.RollbackException;
import org.genericdao.MatchArg;

import databean.Airing;

public class AiringDAO extends GenericDAO<Airing>
{
    public AiringDAO(String tableName, ConnectionPool connectionPool)
                     throws DAOException
    {
        super(Airing.class, tableName, connectionPool);
    }

    @Override
    public void create(Airing airing) throws RollbackException
    {
        super.createAutoIncrement(airing);
    }

    public Airing[] readAiringsByShowId(int showId) throws RollbackException
    {
        Airing[] matchedAirings = super.match(
                                    MatchArg.equals("showId", showId));

        return matchedAirings;
    }
}
