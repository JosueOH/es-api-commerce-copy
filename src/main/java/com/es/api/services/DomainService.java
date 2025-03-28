package com.es.api.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.es.api.entities.DomainEntity;
import com.es.api.messages.ErrorMessage;
import com.es.api.repositories.DomainRepository;
import com.es.api.requests.DomainRequest;
import com.es.api.responses.DomainResponse;
import com.es.api.responses.PageResponse;

@Service
public class DomainService {

    private DomainRepository domainRepo;

    // ~ CONMTRUCTOR(S) ----------------------------------------

    public DomainService(DomainRepository domainRepo) {
        this.domainRepo = domainRepo;
    }

    // ~ METHOD(S) ---------------------------------------------

    @Transactional(readOnly = true)
    public PageResponse<DomainResponse> findAll(Pageable pageable) {
        Page<DomainResponse> page = domainRepo.findAll(pageable).map(DomainResponse::instance);
        return new PageResponse<>(page);
    }

    @Transactional(readOnly = true)
    public DomainResponse findById(Long domainId) {
        DomainEntity domain = getDomain(domainId);
        return DomainResponse.instance(domain);
    }

    @Transactional
    public DomainResponse create(DomainRequest request) {
        DomainEntity domain = new DomainEntity();
        mapFromRequestToDomainEntity(domain, request);
        return DomainResponse.instance(domainRepo.saveAndFlush(domain));
    }

    @Transactional
    public DomainResponse fullyUpdate(Long domainId, DomainRequest request) {
        DomainEntity domain = getDomain(domainId);
        mapFromRequestToDomainEntity(domain, request);
        return DomainResponse.instance(domainRepo.saveAndFlush(domain));
    }

    @Transactional
    public DomainResponse partialUpdate(Long domainId, DomainRequest request) {
        DomainEntity domain = getDomain(domainId);
        Optional.ofNullable(request.name()).ifPresent(domain::setName);
        return DomainResponse.instance(domainRepo.saveAndFlush(domain));
    }

    @Transactional
    public void delete(Long domainId) {
        DomainEntity domain = getDomain(domainId);
        domainRepo.delete(domain);
    }

    private DomainEntity getDomain(Long domainId) {
        return domainRepo.findById(domainId)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.DOMAIN_NOT_FOUND));
    }

    private void mapFromRequestToDomainEntity(DomainEntity domain, DomainRequest request) {
        domain.setName(request.name());
    }
}
