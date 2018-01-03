package edu.olya.tour.dao;

import edu.olya.tour.model.User;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;
import java.util.List;
//Data Access Object Interface defines the standard operations on a model object
public interface UserDao {

    List<User> getUsers();

    User getUser(String u_name);

    int deleteUser(long id);

    void insertUser(User user);

    boolean existsUser(String u_name);

    boolean verifiedUser(String u_name, String u_password)throws ClassNotFoundException, SQLException;

}
