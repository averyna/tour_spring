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
@RequestMapping("/tourSearch")
public class TourSearchController {

    @Autowired
    TourService tourService;

    @RequestMapping( method = RequestMethod.GET)
    public ModelAndView search(@RequestParam Map<String, String> searchParameters) {

        List<TourView> tours = tourService.searchTours(searchParameters);
        ModelMap model = new ModelMap();
        model.put("tours", tours);
        model.put("page", "tour_search.jsp");

        return new ModelAndView("layout", model);
    }
}
