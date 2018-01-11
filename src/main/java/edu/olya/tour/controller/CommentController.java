package edu.olya.tour.controller;

import edu.olya.tour.model.Comment;
import edu.olya.tour.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView getView(){
        return forwardComments();
    }


    @RequestMapping (path = "/", method = RequestMethod.POST)
    public ModelAndView addComment(@ModelAttribute("comment") Comment comment,
                             BindingResult result) {
        if (result.hasErrors()) {
            //return "error"; todo: process exceptions while binding
        }
        commentService.insertComment(comment);
        return forwardComments();
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,false));

    }

    @RequestMapping (path = "/", method = RequestMethod.DELETE, headers = "id")
    public void delComment(@RequestHeader("id") int id){
        commentService.deleteComment(id);
    }


    private ModelAndView forwardComments(){
        List<Comment> comments = commentService.getAllComments();
        ModelMap model = new ModelMap();
        model.put("comment", new Comment());
        model.put("comments", comments);
        model.put("page", "comments.jsp");
        return new ModelAndView("layout", model);
    }
}
