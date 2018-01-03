package edu.olya.tour.dao;

import edu.olya.tour.model.EmailSubscriber;

import java.util.List;

public interface EmailSubscriberDao {

    List<EmailSubscriber> getSubscribers();

    int deleteSubscriber(EmailSubscriber subscriber);

    void insertSubscriber(EmailSubscriber subscriber);

}
