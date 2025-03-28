package com.es.api.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.es.api.entities.TaxEntity;
import com.es.api.entities.UserEntity;
import com.es.api.messages.ErrorMessage;
import com.es.api.repositories.TaxRepository;
import com.es.api.repositories.UserRepository;
import com.es.api.requests.TaxRequest;
import com.es.api.responses.PageResponse;
import com.es.api.responses.TaxResponse;

@Service
public class TaxService {

    private TaxRepository taxRepo;
    private UserRepository userRepo;

    // ~ CONMTRUCTOR(S) ----------------------------------------

    public TaxService(TaxRepository taxRepo, UserRepository userRepo) {
        this.taxRepo = taxRepo;
        this.userRepo = userRepo;
    }

    // ~ METHOD(S) ---------------------------------------------

    @Transactional(readOnly = true)
    public PageResponse<TaxResponse> findByUserId(Long userId, Pageable pageable) {
        UserEntity user = getUserById(userId);
        Page<TaxResponse> page = taxRepo.findAllByUserId(user.getId(), pageable).map(TaxResponse::instance);
        return new PageResponse<>(page);
    }

    @Transactional(readOnly = true)
    public TaxResponse findByUserIdAndTaxId(Long userId, Long taxId) {
        return TaxResponse.instance(getTaxFromSpecificUser(userId, taxId));
    }

    @Transactional

    public TaxResponse create(Long userId, TaxRequest request) {
        UserEntity user = getUserById(userId);
        TaxEntity tax = new TaxEntity();
        mapFromRequestToTaxEntity(tax, request);
        tax.setUserId(user.getId());
        return TaxResponse.instance(taxRepo.saveAndFlush(tax));
    }

    @Transactional
    public TaxResponse fullyUpdate(Long userId, Long taxId, TaxRequest request) {
        TaxEntity foundAddress = getTaxFromSpecificUser(userId, taxId);
        mapFromRequestToTaxEntity(foundAddress, request);
        return TaxResponse.instance(taxRepo.saveAndFlush(foundAddress));
    }

    @Transactional
    public TaxResponse partialUpdate(Long userId, Long taxId, TaxRequest request) {
        TaxEntity foundTax = getTaxFromSpecificUser(userId, taxId);

        Optional.ofNullable(request.name()).ifPresent(foundTax::setName);
        Optional.ofNullable(request.rfc()).ifPresent(foundTax::setRfc);
        Optional.ofNullable(request.name()).ifPresent(foundTax::setName);
        Optional.ofNullable(request.cfdiUse()).ifPresent(foundTax::setCfdiUse);
        Optional.ofNullable(request.taxZipCode()).ifPresent(foundTax::setTaxZipCode);
        Optional.ofNullable(request.fiscalRegime()).ifPresent(foundTax::setFiscalRegime);
        Optional.ofNullable(request.constancyFile()).ifPresent(foundTax::setConstancyFile);
        return TaxResponse.instance(taxRepo.saveAndFlush(foundTax));
    }

    @Transactional
    public void delete(Long userId, Long taxId) {
        TaxEntity foundAddress = getTaxFromSpecificUser(userId, taxId);
        taxRepo.delete(foundAddress);
    }

    private TaxEntity getTaxFromSpecificUser(Long userId, Long taxId) {
        UserEntity user = getUserById(userId);
        return taxRepo.findByUserIdAndId(user.getId(), taxId)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.TAX_NOT_FOUND));
    }

    private void mapFromRequestToTaxEntity(TaxEntity tax, TaxRequest request) {
        tax.setRfc(request.rfc());
        tax.setName(request.name());
        tax.setCfdiUse(request.cfdiUse());
        tax.setTaxZipCode(request.taxZipCode());
        tax.setFiscalRegime(request.fiscalRegime());
        tax.setConstancyFile(request.constancyFile());
    }

    private UserEntity getUserById(Long userId) {
        return userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.USER_NOT_FOUND));
    }
}
