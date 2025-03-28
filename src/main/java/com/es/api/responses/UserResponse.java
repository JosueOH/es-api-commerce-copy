package com.es.api.responses;

import com.es.api.entities.UserEntity;
import java.time.OffsetDateTime;

/**
 * This record represents the body of the API response for a {@code UserEntity}.
 * It provides a structured and immutable representation of user-related data 
 * that is returned by the API.
 */
public record UserResponse(
    Long id,
    String name,
    String username,
    String lastName,
    String email,
    OffsetDateTime creationDate,
    OffsetDateTime updateDate,
    Long domainId,
    Long roleId
) {

    // ~ FACTORY(S) --------------------------------------------

    /**
     * Factory method to create an instance of {@code UserResponse} from a {@code UserEntity}.
     *
     * @param user The {@code UserEntity} to be converted.
     * @return A new {@code UserResponse} containing the data from the given user entity.
     */
    public static UserResponse instance(UserEntity user) {
        return new UserResponse(
            user.getId(),
            user.getName(),
            user.getUsername(),
            user.getLastName(),
            user.getEmail(),
            user.getCreationDate(),
            user.getUpdateDate(),
            user.getDomainId(),
            user.getRoleId()
        );
    }

}
