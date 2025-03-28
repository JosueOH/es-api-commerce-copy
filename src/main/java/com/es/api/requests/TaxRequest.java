package com.es.api.requests;

public record TaxRequest(
    String rfc,
    String name,
    String cfdiUse,
    String taxZipCode,
    String fiscalRegime,
    String constancyFile,
    Long userId
    
    ) {
}
