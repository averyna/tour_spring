package edu.olya.tour.dao;

import edu.olya.tour.model.EmailSubscriber;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class EmailSubscriberDaoImpl extends AbstractDAO implements EmailSubscriberDao {
    @Override
    public List<EmailSubscriber> getSubscribers() {
        return getSession()
                .createQuery("from EmailSubscriber")
                .list();
    }

    @Override
    public int deleteSubscriber(EmailSubscriber subscriber) {
        return getSession()
                .createQuery("delete from EmailSubscriber where id=:id")
                .setParameter("id", subscriber.getId())
                .executeUpdate();
    }

    @Override
    public void insertSubscriber(EmailSubscriber subscriber) {
        getSession().save(subscriber);
    }
}