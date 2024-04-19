package com.HotelBooking.controller;
import com.HotelBooking.entity.PropertyUser;
import com.HotelBooking.entity.Review;
import com.HotelBooking.repository.ReviewRepository;
import com.HotelBooking.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {


    // we develop Controller of Review. Part-3

    private ReviewService reviewService;
    private ReviewRepository reviewRepository;

    public ReviewController(ReviewService reviewService, ReviewRepository reviewRepository) {
        this.reviewService = reviewService;
        this.reviewRepository = reviewRepository;
    }

    @PostMapping("/{propertyId}")
    public ResponseEntity<?> addReview(
            @PathVariable Long propertyId,
            @RequestBody Review review,
            @AuthenticationPrincipal PropertyUser propertyUser)
    {

        Review reviews= reviewService.addReview(propertyUser,review,propertyId);
        return new ResponseEntity<>(reviews,HttpStatus.OK);

    }

}
