package com.es.api.entities;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import com.es.api.models.GeolocationModel;

@Entity
@Table(name = "addresses")
@AttributeOverride(name = "id", column = @Column(name = "address_id"))
public class AddressEntity extends EntityIdentifier {

    // ~ ATTRIBUTE(S) ---------------------------------------------------------

    @Column(name = "postal_code")
    private String postalCode;

    private String country;

    private String state;

    private String locality;

    private String delegation;

    private String street;

    private String number;

    @Column(name = "int_number")
    private String intNumber;

    @Column(name = "default_delivery_address")
    private Boolean defaultDeliveryAddress;

    @Column(name = "invalid_address")
    private Boolean invalidAddess;

    private GeolocationModel geolocation;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address_type")
    private String addressType;

    private String comments;

    @Column(name = "user_id")
    private Long userId;

    // ~ CONSTRUCTORS(S) ------------------------------------------------------

    public AddressEntity() {
    }

    // ~ GETTER(S) SETTER(S) --------------------------------------------------

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getDelegation() {
        return delegation;
    }

    public void setDelegation(String delegation) {
        this.delegation = delegation;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getIntNumber() {
        return intNumber;
    }

    public void setIntNumber(String intNumber) {
        this.intNumber = intNumber;
    }

    public Boolean isDefaultDeliveryAddress() {
        return defaultDeliveryAddress;
    }

    public void setDefaultDeliveryAddress(Boolean defaultDeliveryAddress) {
        this.defaultDeliveryAddress = defaultDeliveryAddress;
    }

    public Boolean isInvalidAddess() {
        return invalidAddess;
    }

    public void setInvalidAddess(Boolean invalidAddess) {
        this.invalidAddess = invalidAddess;
    }
    
    public GeolocationModel getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(GeolocationModel geolocation) {
        this.geolocation = geolocation;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
