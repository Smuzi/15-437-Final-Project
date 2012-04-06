/**
 * @file      NetworkDAO.java
 * @author    Robert Liu <rql@andrew.cmu.edu>
 * @date      4/05/2012
 * @class     15-437
 */

package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;

import databean.Network;

public class NetworkDAO extends GenericDAO<Network>
{
    public NetworkDAO(String tableName, ConnectionPool connectionPool)
                      throws DAOException
    {
        super(Network.class, tableName, connectionPool);
    }
}
