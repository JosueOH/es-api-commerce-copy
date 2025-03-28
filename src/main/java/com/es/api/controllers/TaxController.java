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

import com.es.api.requests.TaxRequest;
import com.es.api.responses.PageResponse;
import com.es.api.responses.TaxResponse;
import com.es.api.services.TaxService;

@RestController
@RequestMapping("api/0/rest/users/{userId}/taxes")
public class TaxController {

    private TaxService taxService;

    // ~ CONSTRUCTOR(S) -------------------------------------------

    public TaxController(TaxService taxService) {
        this.taxService = taxService;
    }

    // ~ METHOD(S) --------------------------------------------------

    @GetMapping
    public PageResponse<TaxResponse> list(@PathVariable Long userId, @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int pageSize) {
        PageRequest pageable = PageRequest.of(page, pageSize, Sort.by("id").ascending());
        return taxService.findByUserId(userId, pageable);
    }

    @GetMapping("/{id}")
    public TaxResponse get(@PathVariable Long userId, @PathVariable Long id) {
        return taxService.findByUserIdAndTaxId(userId, id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaxResponse post(@PathVariable Long userId, @RequestBody TaxRequest tax) {
        return taxService.create(userId, tax);
    }

    @PutMapping("/{id}")
    public TaxResponse put(@PathVariable Long userId, @PathVariable Long id, @RequestBody TaxRequest tax) {
        return taxService.fullyUpdate(userId, id, tax);
    }

    @PatchMapping("/{id}")
    public TaxResponse patch(@PathVariable Long userId, @PathVariable Long id, @RequestBody TaxRequest tax) {
        return taxService.partialUpdate(userId, id, tax);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long userId, @PathVariable Long id) {
        taxService.delete(userId, id);
    }
}
