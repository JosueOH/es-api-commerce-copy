package com.es.api.requests;

import org.locationtech.jts.geom.Point;

import com.es.api.models.GeolocationModel;

public record AddressRequest(
    
    String name,
    String postalCode,
    String country,
    String state,
    String locality,
    String delegation,
    String street,
    String number,
    String intNumber,
    Boolean defaultDeliveryAddress,
    Boolean invalidAddress,
    GeolocationModel geolocation,
    String phoneNumber,
    String addressType,
    String comments,
    Long user
    
    ) {
    

}
