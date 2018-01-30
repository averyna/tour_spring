package edu.olya.tour.service.impl;

import edu.olya.tour.dao.TourDAO;
import edu.olya.tour.model.Tour;
import edu.olya.tour.model.TourView;
import edu.olya.tour.service.TourService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TourServiceImpl implements TourService {
    @Autowired
    TourDAO tourDAO;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<TourView> searchTours(Map<String, String> searchParameters) {
        return tourDAO.searchTours(searchParameters);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertTour(Tour tour) {
        tourDAO.insertTour(tour);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int deleteTour(int tourId) {
        return tourDAO.deleteTour(tourId);
    }
}
