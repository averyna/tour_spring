package edu.olya.tour.service.impl;

import edu.olya.tour.dao.EmailSubscriberDao;
import edu.olya.tour.model.EmailSubscriber;
import edu.olya.tour.service.EmailSubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Service
public class EmailSubscriberServiceImpl implements EmailSubscriberService {
    @Autowired
    EmailSubscriberDao emailSubscriberDao;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public List<EmailSubscriber> getSubscribers() {
        return emailSubscriberDao.getSubscribers();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int deleteSubscriber(EmailSubscriber subscriber) {
        return emailSubscriberDao.deleteSubscriber(subscriber);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void insertSubscriber(EmailSubscriber subscriber) {
        emailSubscriberDao.insertSubscriber(subscriber);
    }
}