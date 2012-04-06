/**
 * @file      ReviewDAO.java
 * @author    Robert Liu <rql@andrew.cmu.edu>
 * @date      4/05/2012
 * @class     15-437
 */

package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;

import databean.Review;

public class ReviewDAO extends GenericDAO<Review>
{
    public ReviewDAO(String tableName, ConnectionPool connectionPool)
                     throws DAOException
    {
        super(Review.class, tableName, connectionPool);
    }
}
