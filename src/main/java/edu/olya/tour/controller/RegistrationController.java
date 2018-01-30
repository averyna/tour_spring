package edu.olya.tour.controller;

import edu.olya.tour.model.User;
import edu.olya.tour.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/register")
public class RegistrationController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView getView() {
        ModelMap model = new ModelMap();
        model.put("user", new User());
        return new ModelAndView("registration", model);
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public ModelAndView saveUser(@ModelAttribute("user") User user, BindingResult result) {
        if(result.hasErrors()){
            //todo: log and inform client
        }
        //todo: verify whether the user is a new one
        user.setRole("user");
        userService.insertUser(user);

        //todo: perform log in operation and redirect to index.jsp

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword());
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);


        ModelMap model = new ModelMap();
        model.put("page", "index.jsp");
        return new ModelAndView("layout", model);
    }


//
//    public void authWithHttpServletRequest(HttpServletRequest request, String username, String password) {
//        try {
//            request.login(username, password);
//        } catch (ServletException e) {
//            LOGGER.error("Error while login ", e);
//        }
//    }
//
//    public void authWithAuthManager(HttpServletRequest request, String username, String password) {
//        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
//        authToken.setDetails(new WebAuthenticationDetails(request));
//        Authentication authentication = authenticationManager.authenticate(authToken);
//        SecurityContextHolder.getContext()
//                .setAuthentication(authentication);
//        // request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
//    }
}
