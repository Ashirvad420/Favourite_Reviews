package com.HotelBooking.controller;

import com.HotelBooking.entity.Country;
import com.HotelBooking.repository.CountryRepository;
import com.HotelBooking.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/countries")
public class CountryController {

        private CountryService countryService;

    public CountryController( CountryService countryService) {
        this.countryService = countryService;
    }

    // Save Country
    @PostMapping("/addCountry")
    public ResponseEntity<?> addCountry(@RequestBody Country country)
    {
         Country country1=countryService.addCountry(country);
         return new ResponseEntity<>(country1,HttpStatus.OK);
    }

    // Get Country Data By Id
    @GetMapping("{id}")
    public ResponseEntity<?> getCountry(@PathVariable Long id)
    {
           Country country = countryService.getCountry(id);
           if (country!=null)
           {
               return  new ResponseEntity<>(country, HttpStatus.OK);
           }
           return new ResponseEntity<>("No Country Id",HttpStatus.NOT_FOUND);
    }

    // Get All Data of Country
    @GetMapping("/all")
    public ResponseEntity<List<Country>> getAll()
    {
        List<Country> countries = countryService.getAll();
        return new ResponseEntity<>(countries,HttpStatus.OK);
    }

    // Delete By Id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        String s=countryService.deleteById(id);
        if(s!=null){
            return new ResponseEntity<>("Deleted",HttpStatus.OK);
        }
        return new ResponseEntity<>("Record not found for this id",HttpStatus.NOT_FOUND);
    }

    // Delete By All
    @DeleteMapping("/deleteAlls")
    public ResponseEntity<?> deleteAll(){
        String s=countryService.deletAll();
        return new ResponseEntity<>(s,HttpStatus.OK);
    }

    // Updating by id
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCountry(@PathVariable Long id,@RequestBody Country country){
        Country country1=countryService.updateCountry(id,country);
        if(country1!=null){
            return new ResponseEntity<>(country1,HttpStatus.OK);
        }
        return new ResponseEntity<>("Country not present for this id",HttpStatus.NOT_FOUND);
    }
}

// add country url is authenticated