package edu.olya.tour.dao;

import edu.olya.tour.model.User;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDaoImpl extends AbstractDAO implements UserDao {

    /**
     * Method search all users
     *
     * @return returns list of users
     */
    @Override
    public List<User> getUsers() {
        return getSession()
                .createQuery("from User")
                .list();
    }

    /**
     * Method search user by user name
     *
     * @param name user name
     * @return returns user object
     */
    public User getUser(String name) {
        return (User) getSession()
                .createQuery("from User where name=:n")
                .setParameter("n", name)
                .uniqueResult();
    }

    /**
     * Method determines whether user with specified name exists in database
     *
     * @param u_name user name
     * @return <b>returns true <i>if <u>the</u></i> User</b> with specified name exists in database, otherwise returns false
     */
    public boolean existsUser(String u_name) {
        return (getUser(u_name) != null) ? true : false;
    }

    /**
     * Deletes user with specified id parameter
     *
     * @param id user id from database
     */
    public int deleteUser(long id) {
        return getSession()
                .createQuery("delete from User where id=:id")
                .setParameter("id", id)
                .executeUpdate();
    }

    /**
     * Inserts user
     *
     * @param name     user name
     * @param password user password
     */
    public void insertUser(User user) {
        getSession().save(user);
    }

    //todo: change this method if it is used

    /**
     * <b>returns true <i>if <u>the</u></i> User</b> with specified name and email exists in database, otherwise returns false
     */
    public boolean verifiedUser(String name, String password) throws ClassNotFoundException, SQLException {
        User user = (User) getSession()
                .createQuery("from User where name=:n and password=:pwd")
                .setParameter("n", name)
                .setParameter("pwd", password)
                .uniqueResult();

        return user != null;
    }
}


