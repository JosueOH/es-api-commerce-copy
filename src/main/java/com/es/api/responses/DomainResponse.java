package com.es.api.responses;

import java.time.OffsetDateTime;

import com.es.api.entities.DomainEntity;

/**
 * This record represents the body of the API response for a domain entity.
 * It provides a structured and immutable representation of domain-related data 
 * that is returned by the API.
 */
public record DomainResponse(
        Long id,
        String name,
        OffsetDateTime creationDate,
        OffsetDateTime updateDate) {

    // ~ FACTORY(S) --------------------------------------------

    /**
     * Factory method to create an instance of {@code DomainResponse} from a {@code DomainEntity}.
     *
     * @param domain The domain entity to be converted.
     * @return A new {@code DomainResponse} containing the data from the given domain entity.
     */
    public static DomainResponse instance(DomainEntity domain) {
        return new DomainResponse(domain.getId(),
                domain.getName(),
                domain.getCreationDate(),
                domain.getUpdateDate());
    }
}
