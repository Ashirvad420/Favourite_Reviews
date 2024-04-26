package com.HotelBooking.controller;
import com.HotelBooking.entity.Property;
import com.HotelBooking.entity.PropertyUser;
import com.HotelBooking.entity.Review;
import com.HotelBooking.service.ReviewService;
import org.apache.catalina.User;
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

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // Save the Review
    @PostMapping("/{propertyId}")
    public ResponseEntity<?> addReview(
            @PathVariable Long propertyId,
            @RequestBody Review review,
            @AuthenticationPrincipal PropertyUser propertyUser)
    {

        Review reviews= reviewService.addReview(propertyUser,review,propertyId);

        return new ResponseEntity<>(reviews,HttpStatus.OK);

    }

    // First Approach getBy id
    @GetMapping("{id}")
    public ResponseEntity<?> getReview(@PathVariable Long id)
    {
        Review review = reviewService.getReview(id);
        if (review!=null)
        {
            return new ResponseEntity<>(review,HttpStatus.OK);
        }
        return new ResponseEntity<>("Not Found Id Of Review",HttpStatus.NOT_FOUND);
    }

    // Second Approach Get All Review
    @GetMapping("/allReview")
    public ResponseEntity<List<Review>> getAll()
    {
        List<Review> reviews = reviewService.getAll();
        return new ResponseEntity<>(reviews,HttpStatus.OK);
    }


    // Third Approach get property by User
    @GetMapping("/getReviewByPropertyAndUser/{propertyId}")
    public ResponseEntity<?> getReviewByPropertyAndUser(@PathVariable Long propertyId,
                                                        @AuthenticationPrincipal PropertyUser user){
        Review review=reviewService.getReviewByPropertyAndUser(propertyId,user);
        if(review!=null){
            return new ResponseEntity<>(review,HttpStatus.OK);
        }
        return new ResponseEntity<>("No review for this property or property is not present",HttpStatus.BAD_REQUEST);
    }

    // Fourth Approach get All Review By user
    @GetMapping("/getReviewByUser")
    public ResponseEntity<List<Review>> getReviewByUser(@AuthenticationPrincipal PropertyUser user){
       List<Review> reviews= reviewService.getReviewByUser(user);
       return new ResponseEntity<>(reviews,HttpStatus.OK);
    }


    // Find All the Review Based on Particular user
    @GetMapping("/UserReviews")
    public ResponseEntity<List<Review>> getUserReviews(@AuthenticationPrincipal PropertyUser user)
    {
        List<Review> reviews = reviewService.getUserReviews(user);
        return new ResponseEntity<>(reviews,HttpStatus.OK);
    }

    // Delete by Id
    @DeleteMapping("{id}")
    public ResponseEntity<?> DeleteById(@PathVariable Long id)
    {
        String review = reviewService.DeleteById(id);
        if (review!=null)
        {
            return new ResponseEntity<>("Data is deleted",HttpStatus.OK);
        }
        return new ResponseEntity<>("Id is Not Found of Property",HttpStatus.NOT_FOUND);
    }

    // Delete by All
    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> DeleteAll()
    {
        String str = reviewService.DeleteAll();
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

    // update by id
    @PutMapping("/update/{id}")
    public ResponseEntity<?> UpdateReview(@PathVariable Long id, @RequestBody Review review)
    {
        Review reviews = reviewService.UpdateReview(id,review);
        if (reviews!=null)
        {
            return new ResponseEntity<>(reviews,HttpStatus.OK);
        }
        return new ResponseEntity<>("Review is not present for this id",HttpStatus.NOT_FOUND);
    }

}



// Date:-2024-04-09 to 10  Review with different id October Batch