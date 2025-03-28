package com.es.api.responses;

import java.util.Collection;

import org.springframework.data.domain.Page;

public record PageResponse<T>(
        long currentPage,
        int pageSize,
        int pageResults,
        long totalResults,
        Collection<T> results) {

    public PageResponse(Page<T> page) {
        this(
                page.getPageable().getPageNumber(),
                page.getSize(),
                page.getNumberOfElements(),
                page.getTotalElements(),
                page.getContent()
        );
    }
}
