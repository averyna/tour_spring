package edu.olya.tour.controller;

import edu.olya.tour.model.TourView;
import edu.olya.tour.service.FilterService;
import edu.olya.tour.service.TourService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "DelTourController")
public class DelTourController extends HttpServlet {
    private static final String LAYOUT_PAGE = "/static/jsp/layout.jsp";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebApplicationContext wc = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        TourService tourService = wc.getBean(TourService.class);

        List<TourView> tours = tourService.searchTours(null);
        request.setAttribute("tours", tours);

        request.setAttribute("page", "del_tour.jsp");
        request.getRequestDispatcher(LAYOUT_PAGE).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebApplicationContext wc = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        TourService tourService = wc.getBean(TourService.class);

        String button = request.getParameter("submit_button");
        if (button != null && button.compareTo("Удалить") == 0) {
            int qty = 0;

            String[] tourIds = request.getParameterValues("id");
            for (String id : tourIds) {
                int tourId = Integer.getInteger(id);
                qty += tourService.deleteTour(tourId);
            }
            request.setAttribute("deleted", qty);
        } else {
            Map<String, String[]> searchParameters = new HashMap<>();

            for (Map.Entry<String, String[]> param : request.getParameterMap().entrySet()) {
                searchParameters.put(param.getKey(), param.getValue());
            }

            List<TourView> tours = tourService.searchTours(searchParameters);
            request.setAttribute("tours", tours);
        }
        request.setAttribute("page", "del_tour.jsp");
        request.getRequestDispatcher(LAYOUT_PAGE).forward(request, response);
    }
}
