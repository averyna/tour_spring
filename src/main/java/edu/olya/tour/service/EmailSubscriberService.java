package edu.olya.tour.service;

import edu.olya.tour.model.EmailSubscriber;

import java.util.List;

public interface EmailSubscriberService {

    List<EmailSubscriber> getSubscribers();

    int deleteSubscriber(EmailSubscriber subscriber);

    void insertSubscriber(EmailSubscriber subscriber);

}