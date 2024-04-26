package com.HotelBooking.controller;

import com.HotelBooking.entity.Location;
import com.HotelBooking.entity.Review;
import com.HotelBooking.service.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Location")
public class LocationController {

    private LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    // Save the data in database
    @PostMapping("/locations")
    public ResponseEntity<?> addLocation(@RequestBody Location location)
    {
        Location loca = locationService.addLocation(location);
        return new ResponseEntity<>(loca, HttpStatus.CREATED);
    }

    // Get By Id
    @GetMapping("{id}")
    public ResponseEntity<?> getLocationId(@PathVariable Long id)
    {
        Location location = locationService.getLocationId(id);
       if (location!=null)
       {
           return new ResponseEntity<>(location,HttpStatus.OK);
       }
       return new ResponseEntity<>("Location Id is Not Found",HttpStatus.NOT_FOUND);
    }

    // Get By All
    @GetMapping("/all")
    public ResponseEntity<List<Location>> getAllLocations()
    {
        List<Location> locations = locationService.getAllLocation();
        return new ResponseEntity<>(locations,HttpStatus.OK);
    }

    // delete By id
    @DeleteMapping("{id}")
    public ResponseEntity<?> deletebyId(@PathVariable Long id)
    {
       String str = locationService.deletebyId(id);
       if (str!=null)
       {
           return  new ResponseEntity<>("Deleted",HttpStatus.OK);
       }
       return new ResponseEntity<>("Record is not found by id",HttpStatus.NOT_FOUND);
    }

    // delete All
    @DeleteMapping("/deleteAlls")
    public ResponseEntity<?> deleteAll()
    {
       String  delete = locationService.deleteAll();
       return new ResponseEntity<>(delete,HttpStatus.OK);
    }

    // update By id
    @PutMapping("/update/{id}")
    public ResponseEntity<?> UpdateLocation(@PathVariable Long id, @RequestBody Location location)
    {
       Location locations = locationService.UpdateLocation(id,location);
       if (location!=null)
       {
           return new ResponseEntity<>(location,HttpStatus.OK);
       }
       return new ResponseEntity<>("Location is not present for this id",HttpStatus.NOT_FOUND);
    }
}
