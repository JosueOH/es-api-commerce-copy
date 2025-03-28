package com.es.api.services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.es.api.entities.AddressEntity;
import com.es.api.entities.UserEntity;
import com.es.api.messages.ErrorMessage;
import com.es.api.repositories.AddressRespository;
import com.es.api.repositories.UserRepository;
import com.es.api.requests.AddressRequest;
import com.es.api.responses.AddressResponse;
import com.es.api.responses.PageResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

@Service
public class AddressService {

    private AddressRespository addressRepo;
    private UserRepository userRepo;

    // ~ CONMTRUCTOR(S) ----------------------------------------

    public AddressService(AddressRespository addressRepo, UserRepository userRepo) {
        this.addressRepo = addressRepo;
        this.userRepo = userRepo;
    }

    // ~ METHOD(S) ---------------------------------------------

    @Transactional(readOnly = true)
    public PageResponse<AddressResponse> findByUserId(Long userId, Pageable pageable) {
        UserEntity user = getUserById(userId);
        Page<AddressResponse> page = addressRepo.findAllByUserId(user.getId(), pageable).map(AddressResponse::instance);
        return new PageResponse<>(page);
    }

    @Transactional(readOnly = true)
    public AddressResponse findByUserIdAndAddressId(Long userId, Long addressId) {
        return AddressResponse.instance(getAddressFromSpecificUser(userId, addressId));
    }

    @Transactional
    public AddressResponse create(Long userId, AddressRequest request) {
        UserEntity user = getUserById(userId);
        AddressEntity address = new AddressEntity();
        mapFromRequestToAddressEntity(address, request);
        address.setUserId(user.getId());
        return AddressResponse.instance(addressRepo.save(address));
    }

    @Transactional
    public AddressResponse fullyUpdate(Long userId, Long addressId, AddressRequest request) {
        AddressEntity foundAddress = getAddressFromSpecificUser(userId, addressId);
        mapFromRequestToAddressEntity(foundAddress, request);
        return AddressResponse.instance(addressRepo.saveAndFlush(foundAddress));
    }

    @Transactional
    public AddressResponse partialUpdate(Long userId, Long addressId, AddressRequest request) {
        AddressEntity foundAddress = getAddressFromSpecificUser(userId, addressId);

        Optional.ofNullable(request.postalCode()).ifPresent(foundAddress::setPostalCode);
        Optional.ofNullable(request.name()).ifPresent(foundAddress::setName);
        Optional.ofNullable(request.country()).ifPresent(foundAddress::setCountry);
        Optional.ofNullable(request.state()).ifPresent(foundAddress::setState);
        Optional.ofNullable(request.locality()).ifPresent(foundAddress::setLocality);
        Optional.ofNullable(request.delegation()).ifPresent(foundAddress::setDelegation);
        Optional.ofNullable(request.street()).ifPresent(foundAddress::setStreet);
        Optional.ofNullable(request.number()).ifPresent(foundAddress::setNumber);
        Optional.ofNullable(request.intNumber()).ifPresent(foundAddress::setIntNumber);
        Optional.ofNullable(request.defaultDeliveryAddress()).ifPresent(foundAddress::setDefaultDeliveryAddress);
        Optional.ofNullable(request.invalidAddress()).ifPresent(foundAddress::setInvalidAddess);
        Optional.ofNullable(request.phoneNumber()).ifPresent(foundAddress::setPhoneNumber);
        Optional.ofNullable(request.addressType()).ifPresent(foundAddress::setAddressType);
        Optional.ofNullable(request.comments()).ifPresent(foundAddress::setComments);
        Optional.ofNullable(request.geolocation()).ifPresent(foundAddress::setGeolocation);
        return AddressResponse.instance(addressRepo.saveAndFlush(foundAddress));
    }

    @Transactional
    public void delete(Long userId, Long addressId) {
        AddressEntity foundAddress = getAddressFromSpecificUser(userId, addressId);
        addressRepo.delete(foundAddress);
    }

    private AddressEntity getAddressFromSpecificUser(Long userId, Long addressId) {
        UserEntity user = getUserById(userId);
        return addressRepo.findByUserIdAndId(user.getId(), addressId)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.ADDRESS_NOT_FOUND));
    }

    private void mapFromRequestToAddressEntity(AddressEntity address, AddressRequest request) {
        address.setPostalCode(request.postalCode());
        address.setName(request.name());
        address.setCountry(request.country());
        address.setState(request.state());
        address.setLocality(request.locality());
        address.setDelegation(request.delegation());
        address.setStreet(request.street());
        address.setNumber(request.number());
        address.setIntNumber(request.intNumber());
        address.setDefaultDeliveryAddress(request.defaultDeliveryAddress());
        address.setInvalidAddess(request.invalidAddress());
        address.setPhoneNumber(request.phoneNumber());
        address.setAddressType(request.addressType());
        address.setComments(request.comments());
        address.setGeolocation(request.geolocation());
    }

    private UserEntity getUserById(Long userId) {
        return userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.USER_NOT_FOUND));
    }

}
