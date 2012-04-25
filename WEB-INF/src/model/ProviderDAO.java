/**
 * @file      ProviderDAO.java
 * @author    Robert Liu <rql@andrew.cmu.edu>
 * @date      4/05/2012
 * @class     15-437
 */

package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;

import databean.Provider;

public class ProviderDAO extends GenericDAO<Provider>
{
    public ProviderDAO(String tableName, ConnectionPool connectionPool)
                      throws DAOException
    {
        super(Provider.class, tableName, connectionPool);
    }
}
