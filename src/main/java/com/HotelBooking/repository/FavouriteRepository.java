package com.HotelBooking.repository;

import com.HotelBooking.entity.Favourite;
import com.HotelBooking.entity.Property;
import com.HotelBooking.entity.PropertyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FavouriteRepository extends JpaRepository<Favourite, Long> {
    @Query("select f from Favourite f where f.isFavourite=true and f.propertyUser=:user")
    List<Favourite> findFavouriteByUser(@Param("user") PropertyUser user);




    @Query("select f from Favourite f where f.property=:propertyId and f.propertyUser=:userId")
    Favourite findFavByPropertyAndUser(@Param("propertyId") Property property, @Param("userId") PropertyUser user);


    Optional<Favourite> findByProperty(Property property);

}