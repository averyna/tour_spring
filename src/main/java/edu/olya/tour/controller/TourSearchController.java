package edu.olya.tour.controller;


import edu.olya.tour.model.TourView;
import edu.olya.tour.service.TourService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TourSearchController extends HttpServlet {
    private static final String LAYOUT_PAGE = "/static/jsp/layout.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        WebApplicationContext wc = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        TourService tourService = wc.getBean(TourService.class);

        List<TourView> tours = tourService.searchTours(request.getParameterMap());

        request.setAttribute("tours", tours);
        request.setAttribute("page", "tour_search.jsp");
        request.getRequestDispatcher(LAYOUT_PAGE).forward(request, response);
    }
}
