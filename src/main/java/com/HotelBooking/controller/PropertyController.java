package com.HotelBooking.controller;


import com.HotelBooking.entity.Property;

import com.HotelBooking.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/properties")
public class PropertyController {

        private PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    // Save the Data in database
    @PostMapping("/addProperty")
    public  ResponseEntity<?> addProperty(@RequestBody Property property)
    {
        Property per = propertyService.addProperty(property);
        return  new ResponseEntity<>(per,HttpStatus.CREATED);
    }

    // First Approach of Get By Id
    @GetMapping("{id}")
    public ResponseEntity<?> getProperty(@PathVariable Long id)
    {
        Property property = propertyService.getProperty(id);
        if (property!=null)
        {
            return  new ResponseEntity<>(property, HttpStatus.OK);
        }
        return new ResponseEntity<>("No found property Id",HttpStatus.NOT_FOUND);
    }

    // Second Approach Get By Location
    @GetMapping("/loctions/{location}")
    public  ResponseEntity<List<Property>> findPropertyBy(@PathVariable String location)
    {
           List<Property> properties = propertyService.findPropertyBy(location);
           return new ResponseEntity<>(properties,HttpStatus.OK);
    }

    // Third Approach Get By Location And Country
    @GetMapping("/location/{locationName}")
        public ResponseEntity<List<Property>> findNewProperty(@PathVariable String locationName)
        {
            List<Property> properties = propertyService.findNewProperty(locationName);
            return new ResponseEntity<>(properties,HttpStatus.OK);
        }

        // Get All Information of property
        @GetMapping("/all")
    public ResponseEntity<List<Property>> getAll()
        {
            List<Property> properties = propertyService.getAll();
            return new ResponseEntity<>(properties,HttpStatus.OK);
        }

        // Get By Location
        @GetMapping("/getproperty/{location}")
        public ResponseEntity<List<Property>> getPropert(@PathVariable String location)
        {
           List<Property> properti = propertyService.getPropert(location);
           return new ResponseEntity<>(properti,HttpStatus.OK);
        }

        // Get By Country
        @GetMapping("/getcountry/{country}")
        public ResponseEntity<List<Property>> getCountry(@PathVariable String country)
        {
            List<Property> count = propertyService.getCountry(country);
            return new ResponseEntity<>(count,HttpStatus.OK);
        }

    // delete By id
        @DeleteMapping("{id}")
        public ResponseEntity<?> DeletebyId(@PathVariable Long id)
        {
            String ptr = propertyService.DeletebyId(id);
            if (ptr!=null)
            {
                return new ResponseEntity<>("Data is deleted",HttpStatus.OK);
            }
            return new ResponseEntity<>("Id is not found",HttpStatus.NOT_FOUND);
        }

        // Delete by all
        @DeleteMapping("/deleteAlls")
        public ResponseEntity<?> DeleteAll()
        {
            String str = propertyService.DeleteAll();
            return new ResponseEntity<>(str,HttpStatus.OK);
        }

        // update By id
        @PutMapping("/update/{id}")
        public ResponseEntity<?> UpdateProperty(@PathVariable Long id,@RequestBody Property property)
        {
            Property properties = propertyService.UpdatePropertty(id,property);
            if (properties!=null)
            {
                return new ResponseEntity<>(properties,HttpStatus.OK);
            }
            return new ResponseEntity<>("Property is not present for this id",HttpStatus.NOT_FOUND);
        }
}





// Date:-2024-04-08  Property with different id.... October Batch

