package edu.olya.tour.dao;

import edu.olya.tour.model.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDAOImpl extends AbstractDAO implements CommentDAO {
    @Override
    public List<Comment> getAllComments() {
        return getSession()
                .createQuery("from Comment order by date")
                .list();
    }

    @Override
    public void insertComment(Comment comment) {
        getSession().save(comment);
    }

    @Override
    public int deleteComment(int id) {
        return getSession()
                .createQuery("delete from Comment where id=:id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
