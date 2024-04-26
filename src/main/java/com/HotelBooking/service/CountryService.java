package com.HotelBooking.service;

import com.HotelBooking.entity.Country;
import com.HotelBooking.entity.Property;
import com.HotelBooking.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    private CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }


    // Save Country Data
    public Country addCountry(Country country){
        Country country1=countryRepository.save(country);
        return country1;
    }

    // get Country By Id
    public Country getCountry(Long id) {
        Optional<Country> opCountry = countryRepository.findById(id);
        if (opCountry.isPresent())
        {
           Country country = opCountry.get();
           return country;
        }
        return  null;
    }

    // Get All Country Name
    public List<Country> getAll() {
        List<Country> countries = countryRepository.findAll();
        return countries;
    }

    // delete By id
    public String deleteById(Long id) {
        Optional<Country> byId=countryRepository.findById(id);
        if(byId.isPresent()) {
            countryRepository.deleteById(id);
            return "deleted";
        }
        return null;
    }

    // delete  All
    public String deletAll() {
        countryRepository.deleteAll();
        return "deleted";
    }

    // Update By id
    public Country updateCountry(Long id,Country country) {
        Optional<Country> byId=countryRepository.findById(id);
        if(byId.isPresent()){
            Country country1=byId.get();
            country1.setCountryName(country.getCountryName());
            Country saved=countryRepository.save(country1);
            return saved;
        }
        return null;
    }


}
