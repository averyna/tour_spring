package edu.olya.tour.service.impl;

import edu.olya.tour.dao.CommentDAO;
import edu.olya.tour.model.Comment;
import edu.olya.tour.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentDAO commentDAO;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public List<Comment> getAllComments() {
        return commentDAO.getAllComments();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void insertComment(Comment comment) {
        commentDAO.insertComment(comment);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteComment(int id) {
        commentDAO.deleteComment(id);
    }
}
