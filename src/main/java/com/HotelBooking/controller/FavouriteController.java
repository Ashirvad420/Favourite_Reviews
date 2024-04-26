package com.HotelBooking.controller;

import com.HotelBooking.entity.Favourite;
import com.HotelBooking.entity.PropertyUser;
import com.HotelBooking.service.FavouriteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/favourite")
public class FavouriteController {

    private FavouriteService favouriteService;

    public FavouriteController(FavouriteService favouriteService) {
        this.favouriteService = favouriteService;
    }

    // How to save the data
    @PostMapping
    public ResponseEntity<Favourite> addFavourite(
            @RequestBody Favourite favourite,
            @AuthenticationPrincipal PropertyUser user
    )
    {
        Favourite favourites = favouriteService.addFavourite(favourite,user);
        return new ResponseEntity<>(favourites, HttpStatus.CREATED);
    }

    // Get Find By Id
    @GetMapping("{id}")
    public ResponseEntity<?> getByFavId(@PathVariable Long id)
    {
        Favourite favourite =favouriteService.getByFavId(id);
        if(favourite!=null)
        {
            return new ResponseEntity<>(favourite,HttpStatus.OK);
        }
        return new ResponseEntity<>("Id is not found for favourite",HttpStatus.NOT_FOUND);
    }

    // Get User By Id
    @GetMapping("/byUser")
    public ResponseEntity<?> getFavByUser(@AuthenticationPrincipal PropertyUser user){
        List<Favourite> favourites=favouriteService.getFavByUser(user);
        return new ResponseEntity<>(favourites,HttpStatus.OK);
    }

    // Get Property by id
    @GetMapping("/byPropertyId/{propertyId}")
    public ResponseEntity<?> getFavByPropertyId(@PathVariable long id){
        Favourite favourite = favouriteService.findByPropertyId(id);
        if (favourite!=null)
        {
            return new ResponseEntity<>(favourite,HttpStatus.OK);
        }
        return new ResponseEntity<>("Record is not found for this id",HttpStatus.NOT_FOUND);
    }

    // update by id
    @PutMapping("/update/{propertyId}")
    public ResponseEntity<?> updateFavourite(@PathVariable long propertyId,@AuthenticationPrincipal PropertyUser user,@RequestBody Favourite favourite){
       Favourite favourites = favouriteService.updateFav(propertyId,user,favourite);
       return new ResponseEntity<>(favourites,HttpStatus.OK);
    }
}




// Date:-2024-04-12 Favourite Review October Batch