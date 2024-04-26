package com.HotelBooking.service;

import com.HotelBooking.entity.Property;
import com.HotelBooking.repository.PropertyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {

        private PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }


    // Save the Data in database
    public Property addProperty(Property property) {
        Property properties =  propertyRepository.save(property);
        return properties;
    }


    // This is for Get By Id first Approach
    public Property getProperty(Long id) {

        Optional<Property> opProperty = propertyRepository.findById(id);
           if (opProperty.isPresent())
           {
               Property property = opProperty.get();
               return property;
           }
           return null;
    }

    // Second Approach Get By Location
    public List<Property> findPropertyBy(String location) {
        List<Property> properties = propertyRepository.findPropertyByLocation(location);
        return properties;
    }


    // Third Approach Get By Location And Country
    public List<Property> findNewProperty(String locationName) {
       List<Property> properties = propertyRepository.findPropertyByLocation(locationName);
       return properties;
    }



    // Get All Information of property
    public List<Property> getAll() {
        List<Property> properties = propertyRepository.findAll();
        return properties;
    }

    // Get By Location
    public List<Property> getPropert(String location) {
        return  propertyRepository.findByLocation(location);
    }

    // Get By Country
    public List<Property> getCountry(String country) {
        return propertyRepository.findByCountry(country);
    }


    // delete By id
    public String DeletebyId(Long id) {
        Optional<Property> Opproperty =propertyRepository.findById(id);
        if (Opproperty.isPresent())
        {
            propertyRepository.deleteById(id);
            return "delete";
        }
        return null;
    }

    // Delete by all
    public String DeleteAll() {
        propertyRepository.deleteAll();
        return "Deleted";
    }


    // update By id
    public Property UpdatePropertty(Long id, Property property) {
          Optional<Property> OpProperty =  propertyRepository.findById(id);
          if (OpProperty.isPresent())
          {
              Property properties = OpProperty.get();
              properties.setPropertyName(properties.getPropertyName());
              Property saved = propertyRepository.save(properties);
              return saved;
          }
          return null;
    }
}
