/**
 * @file      ImageDAO.java
 * @author    Robert Liu <rql@andrew.cmu.edu>
 * @date      4/05/2012
 * @class     15-437
 */

package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;

import databean.Image;

public class ImageDAO extends GenericDAO<Image>
{
    // Errors
    final String ERROR_COMMIT_FAILED = "Transaction.commit() failed silently";

    public ImageDAO(String tableName, ConnectionPool connectionPool)
        throws DAOException
    {
        super(Image.class, tableName, connectionPool);
    }
}
