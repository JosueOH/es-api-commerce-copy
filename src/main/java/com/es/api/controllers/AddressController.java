package com.es.api.controllers;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.es.api.requests.AddressRequest;
import com.es.api.responses.AddressResponse;
import com.es.api.responses.PageResponse;
import com.es.api.services.AddressService;

import jakarta.validation.constraints.Max;

@RestController
@RequestMapping("api/0/rest/users/{userId}/address")
public class AddressController {

    private AddressService addressService;

    // ~ CONSTRUCTOR(S) -------------------------------------------

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    // ~ METHOD(S) --------------------------------------------------

    @GetMapping
    public PageResponse<AddressResponse> list(@PathVariable Long userId, @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int pageSize) {
        PageRequest pageable = PageRequest.of(page, pageSize, Sort.by("id").ascending());
        return addressService.findByUserId(userId, pageable);
    }

    @GetMapping("/{id}")
    public AddressResponse get(@PathVariable Long userId, @PathVariable Long id) {
        return addressService.findByUserIdAndAddressId(userId, id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AddressResponse post(@PathVariable Long userId, @RequestBody AddressRequest address) {
        return addressService.create(userId, address);
    }

    @PutMapping("/{id}")
    public AddressResponse put(@PathVariable Long userId, @PathVariable Long id, @RequestBody AddressRequest address) {
        return addressService.fullyUpdate(userId, id, address);
    }

    @PatchMapping("/{id}")
    public AddressResponse patch(@PathVariable Long userId, @PathVariable Long id,
            @RequestBody AddressRequest address) {
        return addressService.partialUpdate(userId, id, address);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long userId, @PathVariable Long id) {
        addressService.delete(userId, id);
    }
}
