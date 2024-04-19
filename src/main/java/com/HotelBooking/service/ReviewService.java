package com.HotelBooking.service;

import com.HotelBooking.entity.Property;
import com.HotelBooking.entity.PropertyUser;
import com.HotelBooking.entity.Review;
import com.HotelBooking.repository.PropertyRepository;
import com.HotelBooking.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class ReviewService {
    private ReviewRepository reviewRepository;
    private PropertyRepository propertyRepository;

    public ReviewService(ReviewRepository reviewRepository, PropertyRepository propertyRepository) {
        this.reviewRepository = reviewRepository;
        this.propertyRepository = propertyRepository;
    }

    public Review addReview(PropertyUser propertyUser, Review review, Long id) {

        Optional<Property> opUser =propertyRepository.findById(id);
        Property property=opUser.get();
        review.setProperty(property);
        review.setPropertyUser(propertyUser);
       return reviewRepository.save(review);

    }
}
// we develop Controller of Review. Part-3