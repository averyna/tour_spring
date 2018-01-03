package edu.olya.tour.service;

import edu.olya.tour.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    List<User> getUsers();
    User getUser(String u_name);
    int deleteUser(long id);
    void insertUser(User user);
    boolean existsUser(String u_name);
    boolean verifiedUser(String u_name, String u_password)throws ClassNotFoundException, SQLException;

}
