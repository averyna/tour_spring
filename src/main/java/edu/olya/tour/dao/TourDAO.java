package edu.olya.tour.dao;

import edu.olya.tour.model.Tour;
import edu.olya.tour.model.TourView;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Map;

public interface TourDAO {
    List<TourView> searchTours(Map<String, String> searchParameters);
    void insertTour(Tour tour);
    int deleteTour(int tourId);

}
