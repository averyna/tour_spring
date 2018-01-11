package edu.olya.tour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/view")
public class PageController {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView definePage(@RequestParam("page") String page) {
        return new ModelAndView("layout", "page", page);
    }
}
