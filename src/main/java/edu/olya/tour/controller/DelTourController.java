package edu.olya.tour.controller;

import edu.olya.tour.model.TourView;
import edu.olya.tour.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping ("/delTour")
public class DelTourController {

    @Autowired
    TourService tourService;

    @RequestMapping(path = "/")
    public ModelAndView processParams(@RequestParam Map<String, String> searchParameters) {
        List<TourView> tours = tourService.searchTours(searchParameters);
        ModelMap model = new ModelMap();
        model.put("page", "del_tour.jsp");
        model.put("tours", tours);
        return new ModelAndView("layout", model);
    }

    //@RequestMapping(params = "dept") - can use param without value, just ro show it's presence

    @RequestMapping(path = "/", method = RequestMethod.POST, params = {"submit_button=Удалить"})
    public ModelAndView deleteTour(@RequestParam("id") int[] id /*required*/) {
        int qty = 0;

        for (int tourId : id) {
            qty += tourService.deleteTour(tourId);
        }
        ModelMap model = new ModelMap();
        model.put("page", "del_tour.jsp");
        model.put("deleted", qty);
        return new ModelAndView("layout", model);


    }
}
