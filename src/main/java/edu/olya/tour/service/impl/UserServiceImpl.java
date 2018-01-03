package edu.olya.tour.service.impl;

import edu.olya.tour.dao.UserDao;
import edu.olya.tour.model.User;
import edu.olya.tour.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public User getUser(String u_name) {
        return userDao.getUser(u_name);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int deleteUser(long id) {
        return userDao.deleteUser(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void insertUser(User user) {
        userDao.insertUser(user);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public boolean existsUser(String u_name) {
        return userDao.existsUser(u_name);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public boolean verifiedUser(String u_name, String u_password) throws ClassNotFoundException, SQLException {
        return userDao.verifiedUser(u_name, u_password);
    }
}
