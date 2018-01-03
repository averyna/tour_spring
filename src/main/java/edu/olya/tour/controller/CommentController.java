package edu.olya.tour.controller;

import edu.olya.tour.model.Comment;
import edu.olya.tour.service.CommentService;
import edu.olya.tour.utils.ObjectBuilder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CommentController extends HttpServlet {

    private static final String LAYOUT_PAGE = "/static/jsp/layout.jsp";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forwardComments(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        WebApplicationContext wc = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        CommentService commentService = wc.getBean(CommentService.class);

        Comment comment = ObjectBuilder.parse(request.getParameterMap(), new Comment());
        commentService.insertComment(comment);

        forwardComments(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebApplicationContext wc = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        CommentService commentService = wc.getBean(CommentService.class);

        String comment_id = request.getHeader("id");
        int id = Integer.getInteger(comment_id);
        commentService.deleteComment(id);
    }

    private void forwardComments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebApplicationContext wc = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        CommentService commentService = wc.getBean(CommentService.class);

        List<Comment> comments = commentService.getAllComments();

        request.setAttribute("comments", comments);
        request.setAttribute("page", "comments.jsp");
        request.getRequestDispatcher(LAYOUT_PAGE).forward(request, response);

    }
}


