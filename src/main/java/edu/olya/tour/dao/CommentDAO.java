package edu.olya.tour.dao;

import edu.olya.tour.model.Comment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

public interface CommentDAO {
    List<Comment> getAllComments();

    void insertComment(Comment comment);

    int deleteComment(int id);
}
