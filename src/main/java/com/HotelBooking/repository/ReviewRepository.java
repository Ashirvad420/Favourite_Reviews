package com.HotelBooking.repository;

import com.HotelBooking.entity.Property;
import com.HotelBooking.entity.PropertyUser;
import com.HotelBooking.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    @Query("select r from Review r where r.property=:propertyId and r.propertyUser=:UserId")
    Review findReviewByUserIdAndPropertyId(@Param("propertyId")Property property, @Param("UserId") PropertyUser user);

    @Query("select r from Review r where  r.propertyUser=:UserId")
    List<Review> findReviewByUserId( @Param("UserId")PropertyUser user);


    List<Review> findByPropertyUser(PropertyUser user);


}


// we develop Controller of Review. Part-3

//