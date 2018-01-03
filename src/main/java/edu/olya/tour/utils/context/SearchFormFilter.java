package edu.olya.tour.utils.context;

import edu.olya.tour.service.FilterService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * Filter is mapped to /addTour, /delTour and /tourSearch urls. It calls methods of
 * {@code FilterService} interface to obtain values from database and put them into cache.
 * This cached values will be used while creating response html.
 */
public class SearchFormFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        WebApplicationContext wc = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        FilterService filterService = wc.getBean(FilterService.class);

        filterService.getAllCountries();
        filterService.getAllTourTypes();
        filterService.getAllMealTypes();
        filterService.getAllHotels();

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
}
