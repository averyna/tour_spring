package edu.olya.tour.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Persistence;

public abstract class AbstractDAO {
    @Autowired
    protected SessionFactory sessionFactory;


    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
