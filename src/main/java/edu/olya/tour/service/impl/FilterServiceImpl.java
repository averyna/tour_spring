package edu.olya.tour.service.impl;


import edu.olya.tour.dao.FilterDAO;
import edu.olya.tour.model.Country;
import edu.olya.tour.model.Hotel;
import edu.olya.tour.model.MealType;
import edu.olya.tour.model.TourType;
import edu.olya.tour.service.FilterService;
import edu.olya.tour.utils.cache.CacheConfig;
import edu.olya.tour.utils.cache.CacheParam;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Service
public class FilterServiceImpl implements FilterService {
    @Autowired
    FilterDAO filterDAO;

    @CacheConfig(params = {
            @CacheParam(key = "expiration", value = "10000")
    })
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Country> getAllCountries() {
        return filterDAO.getAllCountries();
    }

    @CacheConfig(params = {
            @CacheParam(key = "expiration", value = "10000")
    })
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<TourType> getAllTourTypes() {
        return filterDAO.getAllTourTypes();
    }

    @CacheConfig(params = {
            @CacheParam(key = "expiration", value = "10000")
    })
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<MealType> getAllMealTypes() {
        return filterDAO.getAllMealTypes();
    }

    @CacheConfig(params = {
            @CacheParam(key = "expiration", value = "10000")
    })
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Hotel> getAllHotels() {
        return filterDAO.getAllHotels();
    }

}