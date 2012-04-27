/**
 * @file      ProviderDAO.java
 * @author    Robert Liu <rql@andrew.cmu.edu>
 * @author    Jacob Olson <jholson@andrew.cmu.edu>
 * @date      4/05/2012
 * @class     15-437
 */

package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.RollbackException;
import org.genericdao.MatchArg;

import databean.Provider;

public class ProviderDAO extends GenericDAO<Provider>
{
    public ProviderDAO(String tableName, ConnectionPool connectionPool)
                      throws DAOException
    {
        super(Provider.class, tableName, connectionPool);
    }

    @Override
    public void create(Provider provider) throws RollbackException
    {
        super.createAutoIncrement(provider);
    }

    public Provider readByNameAndZipcode(String name, String zipcode)
        throws RollbackException
    {
        Provider[] matchedProviders = super.match(MatchArg.and(
                                        MatchArg.equals("name", name),
                                        MatchArg.equals("zipcode", zipcode)));

        if (matchedProviders.length > 1)
        {
            throw new RollbackException("Found multiple providers with the " +
                                        "same name and zipcode");
        }
        else
        {
            return (matchedProviders.length > 0) ? matchedProviders[0] : null;
        }
    }
}
