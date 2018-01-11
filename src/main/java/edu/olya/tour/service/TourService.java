package edu.olya.tour.service;

import edu.olya.tour.model.Tour;
import edu.olya.tour.model.TourView;
import java.util.List;
import java.util.Map;

public interface TourService {
    //@CacheConfig()
    List<TourView> searchTours(Map<String, String> searchParameters);

    void insertTour(Tour tour);

    int deleteTour(int tourId);
}
