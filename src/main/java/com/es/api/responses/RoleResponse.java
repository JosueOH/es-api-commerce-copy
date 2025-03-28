package com.es.api.responses;

import java.time.OffsetDateTime;

import com.es.api.entities.RoleEntity;

/**
 * This record represents the body of the API response for a role entity.
 * It provides a structured and immutable representation of role-related data 
 * that is returned by the API.
 */
public record RoleResponse(
        Long id,
        String name,
        OffsetDateTime creationDate,
        OffsetDateTime updateDate) {

    // ~ FACTORY(S) --------------------------------------------

    /**
     * Factory method to create an instance of {@code RoleResponse} from a {@code RoleEntity}.
     *
     * @param role The role entity to be converted.
     * @return A new {@code RoleResponse} containing the data from the given role entity.
     */
    public static RoleResponse instance(RoleEntity role) {
        return new RoleResponse(role.getId(),
                role.getName(),
                role.getCreationDate(),
                role.getUpdateDate());
    }

}
