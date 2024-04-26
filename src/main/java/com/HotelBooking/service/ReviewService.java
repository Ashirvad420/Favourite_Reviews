package com.HotelBooking.service;

import com.HotelBooking.entity.Property;
import com.HotelBooking.entity.PropertyUser;
import com.HotelBooking.entity.Review;
import com.HotelBooking.repository.PropertyRepository;
import com.HotelBooking.repository.ReviewRepository;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ReviewService {
    private ReviewRepository reviewRepository;
    private PropertyRepository propertyRepository;

    public ReviewService(ReviewRepository reviewRepository, PropertyRepository propertyRepository) {
        this.reviewRepository = reviewRepository;
        this.propertyRepository = propertyRepository;
    }

    // Save the Review
    public Review addReview(PropertyUser propertyUser, Review review, Long id) {

        Optional<Property> opUser =propertyRepository.findById(id);
        Property property=opUser.get();
        review.setProperty(property);
        review.setPropertyUser(propertyUser);
       return reviewRepository.save(review);
    }

    // First Approach getBy id
    public Review getReview(Long id) {

           Optional<Review> opReview = reviewRepository.findById(id);
           if (opReview.isPresent())
           {
              Review review= opReview.get();
               return  review;
           }
           return null;

    }

    // Second Approach Get All Review
    public List<Review> getAll() {
       List<Review>  reviews = reviewRepository.findAll();
       return reviews;
    }


    // Third Approach get property by User
    public Review getReviewByPropertyAndUser(Long propertyId,PropertyUser user){
        Optional<Property> byId=propertyRepository.findById(propertyId);
        if(byId.isPresent()){
            Property property=byId.get();
            Review review=reviewRepository.findReviewByUserIdAndPropertyId(property,user);
            return review;
        }
        return null;
    }

    // Fourth Approach get All Review By user
    public List<Review> getReviewByUser(PropertyUser user){
        List<Review> r=reviewRepository.findReviewByUserId(user);
        return r;
    }

    // Find All the Review Based on Particular user
    public List<Review> getUserReviews(PropertyUser user) {
       List<Review> reviews = reviewRepository.findByPropertyUser(user);
       return reviews;
    }

    // Delete by id
    public String DeleteById(Long id) {
        Optional<Review> Opreview = reviewRepository.findById(id);
        if (Opreview.isPresent())
        {
             reviewRepository.deleteById(id);
             return "Deleted";
        }
        return null;
    }

    // Delete by all
    public String DeleteAll() {
        reviewRepository.deleteAll();
        return "Deleted";
    }

    // update by id
    public Review UpdateReview(Long id,Review review) {
        Optional<Review> OpReview = reviewRepository.findById(id);
        if (OpReview.isPresent())
        {
            Review reviews = OpReview.get();
            reviews.setContent(reviews.getContent());
           Review saved = reviewRepository.save(review);
           return saved;
        }
        return null;
    }
    
}
// we develop Controller of Review. Part-3