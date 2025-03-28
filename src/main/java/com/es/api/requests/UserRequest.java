package com.es.api.requests;

public record UserRequest(
    String username,
    String name,
    String password,
    String lastName,
    String email,
    Long domain,
    Long role
) {


}
