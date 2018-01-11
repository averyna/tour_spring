package edu.olya.tour.service;

import edu.olya.tour.model.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getAllComments();

    void insertComment(Comment comment);

    void deleteComment(int id);
}
