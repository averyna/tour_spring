package edu.olya.tour.dao;

import edu.olya.tour.model.Country;
import edu.olya.tour.model.Hotel;
import edu.olya.tour.model.MealType;
import edu.olya.tour.model.TourType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FilterDAOImpl extends AbstractDAO implements FilterDAO {

    @Override
    public List<Country> getAllCountries() {
        return getSession()
                .createQuery("from Country")
                .setCacheable(true)
                .list();
    }

    @Override
    public List<TourType> getAllTourTypes() {
        return getSession()
                .createQuery("from TourType")
                .setCacheable(true)
                .list();
    }

    @Override
    public List<MealType> getAllMealTypes() {
        return getSession()
                .createQuery("from MealType")
                .setCacheable(true)
                .list();
    }

    @Override
    public List<Hotel> getAllHotels() {
        return getSession()
                .createQuery("from Hotel")
                .setCacheable(true)
                .list();
    }
}





