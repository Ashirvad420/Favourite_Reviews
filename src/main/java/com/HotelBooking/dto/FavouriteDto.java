package com.HotelBooking.dto;

import com.HotelBooking.entity.Property;
import com.HotelBooking.entity.PropertyUser;

public class FavouriteDto {

    private Long id;

    private Boolean isFavourite = false;

    private PropertyUser propertyUser;

    private Property property;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getFavourite() {
        return isFavourite;
    }

    public void setFavourite(Boolean favourite) {
        isFavourite = favourite;
    }

    public PropertyUser getPropertyUser() {
        return propertyUser;
    }

    public void setPropertyUser(PropertyUser propertyUser) {
        this.propertyUser = propertyUser;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
}
