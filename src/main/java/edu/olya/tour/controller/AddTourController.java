package edu.olya.tour.controller;

import edu.olya.tour.model.Tour;
import edu.olya.tour.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/addTour")
public class AddTourController {
//    final static ModelAndView ADD_TOUR_PAGE = new ModelAndView("layout",
//            "page",
//            "add_tour.jsp"
//    );

    @Autowired
    TourService tourService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView getView() {
        ModelMap model = new ModelMap();
        model.put("page", "add_tour.jsp");
        model.put("tour", new Tour());
        return new ModelAndView("layout", model);
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public ModelAndView saveTour(@ModelAttribute ("tour") Tour tour, BindingResult result) {
        if(result.hasErrors()){
            //todo: log and inform client
        }
        tourService.insertTour(tour);
        return getView();
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,false));
    }
}
