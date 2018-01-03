package edu.olya.tour.service;

import edu.olya.tour.model.Country;
import edu.olya.tour.model.Hotel;
import edu.olya.tour.model.MealType;
import edu.olya.tour.model.TourType;

import java.util.List;

public interface FilterService {

    List<Country> getAllCountries();

    List<TourType> getAllTourTypes();

    List<MealType> getAllMealTypes();

    List<Hotel> getAllHotels();

}
