package com.HotelBooking.service;

import com.HotelBooking.entity.Location;
import com.HotelBooking.repository.LocationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    private LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    // Save the data in database
    public Location addLocation(Location location) {
        Location locations = locationRepository.save(location);
        return locations;
    }

    // get By Id
    public Location getLocationId(Long id) {
       Optional<Location> Oplocation = locationRepository.findById(id);
       if (Oplocation.isPresent())
       {
           Location location = Oplocation.get();
           return location;
       }
       return null;
    }

    // Get All Location
    public List<Location> getAllLocation() {
        List<Location> locations = locationRepository.findAll();
        return locations;
    }

    // delete By id
    public String deletebyId(Long id) {
       Optional<Location> location = locationRepository.findById(id);
       if (location.isPresent())
       {
           locationRepository.deleteById(id);
           return "deleted";
       }
       return null;
    }

    // Delete All
    public String deleteAll() {
        locationRepository.deleteAll();
        return "Delete";
    }

    // update By id
    public Location UpdateLocation(Long id, Location location) {
       Optional<Location> OpLocation = locationRepository.findById(id);
       if (OpLocation.isPresent())
       {
           Location locations = OpLocation.get();
           locations.setLocationName(locations.getLocationName());
            Location saved = locationRepository.save(locations);
            return saved;
       }
       return null;
    }
}
