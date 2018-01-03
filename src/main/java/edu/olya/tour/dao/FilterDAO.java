package edu.olya.tour.dao;

import edu.olya.tour.model.Country;
import edu.olya.tour.model.Hotel;
import edu.olya.tour.model.MealType;
import edu.olya.tour.model.TourType;
import org.springframework.context.annotation.Bean;

import java.util.List;

public interface FilterDAO {
    List<Country> getAllCountries();

    List<TourType> getAllTourTypes();

    List<MealType> getAllMealTypes();

    List<Hotel> getAllHotels();

}
