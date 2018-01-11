package edu.olya.tour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/")
public class FallbackController {
    @RequestMapping("*")
    public String fallbackMethod(){
        return "error";
        // It is useful in sending custom 404 response pages to users
        // when there are no handler methods for the request.
    }
}
