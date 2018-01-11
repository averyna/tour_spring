package edu.olya.tour.controller;

import edu.olya.tour.model.EmailSubscriber;
import edu.olya.tour.service.EmailSubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/subscription")
public class MailController {

    @Autowired
    EmailSubscriberService subscriberService;

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public void addSubscriber(EmailSubscriber subscriber){
        subscriberService.insertSubscriber(subscriber);
    }
}
