/**
 * @file      UserDAO.java
 * @author    Robert Liu <rql@andrew.cmu.edu>
 * @date      4/05/2012
 * @class     15-437
 */

package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databean.User;

public class UserDAO extends GenericDAO<User>
{
    // Errors
    private final static String ERROR_USERNAME_EXISTS =
        "Username already exists";

    public UserDAO(String tableName, ConnectionPool connectionPool)
                   throws DAOException
    {
        super(User.class, tableName, connectionPool);
    }

    // Overrides GenericDAO's create method
    @Override
    public void create(User user) throws RollbackException
    {
        // We need to touch the database for multiple calls
        // Make sure we're in a Transaction
        if (!Transaction.isActive()) {
            try {
                Transaction.begin();
                create(user);
                Transaction.commit();

                return;
            } finally {
                if (Transaction.isActive()) {
                    Transaction.rollback();
                }
            }
        }

        if (this.readByUsername(user.getUsername()) != null) {
            throw new RollbackException(ERROR_USERNAME_EXISTS);
        } else {
            super.createAutoIncrement(user);
        }
    }

    public User readByUsername(Object key) throws RollbackException
    {
        String username = (String) key;

        User[] matchedUsers = super.match(
                                MatchArg.equals("username", username));

        return (matchedUsers.length == 0) ? null : matchedUsers[0];
    }
}
