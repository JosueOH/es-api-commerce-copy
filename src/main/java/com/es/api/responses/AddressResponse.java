package com.es.api.responses;

import java.time.OffsetDateTime;

import org.locationtech.jts.geom.Point;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.es.api.entities.AddressEntity;
import com.es.api.models.GeolocationModel;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AddressResponse(
    Long id,
    String name,
    String postalCode,
    String country,
    String state,
    String locality,
    String delegation,
    String street,
    String number,
    String intNumber,
    Boolean defaultdelliveryAddress,
    Boolean invalidAddress,
    GeolocationModel geolocation,
    String phoneNumber,
    String addressType,
    String comments,
    OffsetDateTime creationDate,
    OffsetDateTime updateDate,
    Long userId
    
    ) {

    public static AddressResponse instance(AddressEntity address){

        return new AddressResponse(
                address.getId(),
                address.getName(),
                address.getPostalCode(),
                address.getCountry(),
                address.getState(),
                address.getLocality(),
                address.getDelegation(),
                address.getStreet(),
                address.getNumber(),
                address.getIntNumber(),
                address.isDefaultDeliveryAddress(),
                address.isInvalidAddess(),
                address.getGeolocation(),
                address.getPhoneNumber(),
                address.getAddressType(),
                address.getComments(),
                address.getCreationDate(),
                address.getUpdateDate(),
                address.getUserId()
        );

    }

}
