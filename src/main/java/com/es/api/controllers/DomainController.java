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

import com.es.api.requests.DomainRequest;
import com.es.api.responses.DomainResponse;
import com.es.api.responses.PageResponse;
import com.es.api.services.DomainService;

@RestController
@RequestMapping("api/0/rest/domains")
public class DomainController {

    private DomainService domainServicee;

    // ~ CONSTRUCTOR(S) -------------------------------------------

    public DomainController(DomainService domainServicee) {
        this.domainServicee = domainServicee;
    }

    // ~ METHOD(S) --------------------------------------------------

    @GetMapping
    public PageResponse<DomainResponse> list(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int pageSize) {
        PageRequest pageable = PageRequest.of(page, pageSize, Sort.by("id").ascending());
        return domainServicee.findAll(pageable);
    }

    @GetMapping("/{id}")
    public DomainResponse get(@PathVariable Long id) {
        return domainServicee.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DomainResponse post(@RequestBody DomainRequest domain) {
        return domainServicee.create(domain);
    }

    @PutMapping("/{id}")
    public DomainResponse put(@PathVariable Long id, @RequestBody DomainRequest domain) {
        return domainServicee.fullyUpdate(id, domain);
    }

    @PatchMapping("/{id}")
    public DomainResponse patch(@PathVariable Long id, @RequestBody DomainRequest domain) {
        return domainServicee.partialUpdate(id, domain);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        domainServicee.delete(id);
    }
}
