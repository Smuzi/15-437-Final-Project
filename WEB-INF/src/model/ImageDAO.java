/**
 * @file      ImageDAO.java
 * @author    Robert Liu <rql@andrew.cmu.edu>
 * @date      4/05/2012
 * @class     15-437
 */

package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.RollbackException;
import org.genericdao.GenericDAO;

import databean.Image;

public class ImageDAO extends GenericDAO<Image>
{
    public ImageDAO(String tableName, ConnectionPool connectionPool)
        throws DAOException
    {
        super(Image.class, tableName, connectionPool);
    }

    @Override
    public void create(Image image) throws RollbackException {
        super.createAutoIncrement(image);
    }
}
