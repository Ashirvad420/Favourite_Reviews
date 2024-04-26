package com.HotelBooking.service;

import com.HotelBooking.entity.Favourite;
import com.HotelBooking.entity.Property;
import com.HotelBooking.entity.PropertyUser;
import com.HotelBooking.repository.FavouriteRepository;
import com.HotelBooking.repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavouriteService {

    private FavouriteRepository favouriteRepository;
    private PropertyRepository propertyRepository;


    public FavouriteService(FavouriteRepository favouriteRepository,PropertyRepository propertyRepository) {
        this.favouriteRepository = favouriteRepository;
        this.propertyRepository=propertyRepository;
    }

    // How to save the data
    public Favourite addFavourite(Favourite favourite, PropertyUser user) {

        favourite.setPropertyUser(user);
        Favourite savedFav = favouriteRepository.save(favourite);
        return savedFav;
    }

// get find By id
    public Favourite getByFavId(Long id) {
        Optional<Favourite> Opfavourite = favouriteRepository.findById(id);
        if (Opfavourite.isPresent())
        {
            Favourite favourite = Opfavourite.get();
            return favourite;
        }
        return null;
    }

     //get find By user
    public List<Favourite> getFavByUser(PropertyUser user) {
        List<Favourite> favourites=favouriteRepository.findFavouriteByUser(user);
        return  favourites;
    }

    // get Update by Property
    public Favourite findByPropertyId(Long id){
        Optional<Property> byId=propertyRepository.findById(id);
        if(byId.isPresent()){
           Property property= byId.get();
           Optional<Favourite> byId1=favouriteRepository.findByProperty(property);
           if(byId1.isPresent()){
              return byId1.get();
           }
        }
        return null;
    }

    // update by id
    public Favourite updateFav(long id, PropertyUser user, Favourite favourite) {
       Property property= propertyRepository.findById(id).get();
       Favourite f=favouriteRepository.findFavByPropertyAndUser(property,user);
        f.setIsFavourite(favourite.getIsFavourite());
        return favouriteRepository.save(f);



    }
}
