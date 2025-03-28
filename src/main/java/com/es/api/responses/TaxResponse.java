package com.es.api.responses;

import java.time.OffsetDateTime;

import com.es.api.entities.TaxEntity;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record TaxResponse(
    Long id,
    String rfc,
    String name,
    String cfdiUse,
    String taxZipCode,
    String fiscalRegime,
    String constancyFile,
    OffsetDateTime creationDate,
    OffsetDateTime updateDate,
    Long userId
) {
    public static TaxResponse instance(TaxEntity tax){

        return new TaxResponse(
                tax.getId(),
                tax.getRfc(),
                tax.getName(),
                tax.getCfdiUse(),
                tax.getTaxZipCode(),
                tax.getFiscalRegime(),
                tax.getConstancyFile(),
                tax.getCreationDate(),
                tax.getUpdateDate(),
                tax.getUserId()
        );

    }
}
