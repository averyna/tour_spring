package edu.olya.tour.dao;

import edu.olya.tour.model.Tour;
import edu.olya.tour.model.TourView;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository //указывает, что класс используется для работы с поиском, получением и хранением данных
public class TourDAOImpl extends AbstractDAO implements TourDAO {

    @Override
    public void insertTour(Tour tour) {
        getSession().save(tour);
    }

    @Override
    public int deleteTour(int tourId) {
        return getSession()
                .createQuery("delete from Tour where id=:id")
                .setParameter("id", tourId)
                .executeUpdate();
    }


    @Override
    public List<TourView> searchTours(Map<String, String[]> searchParameters) {
        EntityManager em = sessionFactory.createEntityManager();

        //Used to construct criteria queries, compound selections, expressions, predicates, orderings.
        CriteriaBuilder cb = em.getCriteriaBuilder();

        //создается пустой запрос, но с определенным типом возвращаемого результата
        CriteriaQuery<TourView> c = cb.createQuery(TourView.class);

        //Create and add a query root corresponding to the given entity
        Root<TourView> p = c.from(TourView.class);

        Predicate condition = null;

        for (Map.Entry<String, String[]> e : searchParameters.entrySet()) {
            String propertyName = e.getKey();
            String[] value = e.getValue();

            if (value[0].length() > 0) {

                Predicate constraint = null;

                if (propertyName.equals("startDate")) {
                    //java.lang.ClassCastException: java.lang.String cannot be cast to java.util.Date
                    Date date;
                    try {
                        date = new SimpleDateFormat("yyyy-MM-dd").parse(value[0]);
                    } catch (ParseException ex) {
                        date = new Date();
                    }
                    constraint = cb.greaterThanOrEqualTo(p.get(propertyName), date);

                } else if (propertyName.equals("nights")) {

                    constraint = cb.greaterThanOrEqualTo(p.get(propertyName), value[0]);

                } else if (propertyName.equals("price")) {

                    constraint = cb.lessThanOrEqualTo(p.get(propertyName), value[0]);

                } else {
                    try {
                        constraint = cb.equal(p.get(propertyName), value[0]);
                    } catch (IllegalArgumentException ex) {
                        //"submit_button" parameter came - it is not a field of TourView class
                        //so let's proceed to the next cycle iteration
                        continue;
                    }
                }
                if (condition == null) {
                    condition = constraint;
                } else {
                    condition = cb.and(condition, constraint); // добавляет новое условие к уже существующему предикату
                }
            }
        }
        if (condition != null) {
            c.where(condition);
        }

        TypedQuery<TourView> q = em.createQuery(c);

        return q.getResultList();
    }
}


