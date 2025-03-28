package com.es.api.messages;

import com.es.api.responses.ErrorResponse.ErrorDetail;

import java.util.Map;

import static java.util.Map.entry;

public class ErrorMessage {

    // ~ CONSTANT(S) ----------------------------------------------------------

    public static final String PARSING_OBJECT_TO_JSON = "ES-1";
    public static final String READING_OBJECT_TO_JSON = "ES-2";
    public static final String USER_NOT_FOUND = "ES-3";
    public static final String USERNAME_NOT_EMPTY = "ES-4";
    public static final String PASSWORD_NOT_EMPTY = "ES-5";
    public static final String DOMAIN_NOT_FOUND = "ES-6";
    public static final String DOMAIN_NOT_NULL = "ES-7";
    public static final String DOMAIN_POSITIVE = "ES-8";
    public static final String DOMAIN_CANNOT_BE_MODIFIED = "ES-9";
    public static final String ROLE_NOT_FOUND = "ES-10";
    public static final String ROLE_NOT_NULL = "ES-11";
    public static final String ROLE_POSITIVE = "ES-12";
    public static final String ROLE_CANNOT_BE_MODIFIED = "ES-13";
    public static final String USERNAME_SIZE = "ES-14";
    public static final String NAME_SIZE = "ES-15";
    public static final String PASSWORD_SIZE = "ES-16";
    public static final String LAST_NAME_SIZE = "ES-17";
    public static final String EMAIL_SIZE = "ES-18";
    public static final String ADDRESS_NOT_FOUND = "ES-19";
    public static final String TAX_NOT_FOUND = "ES-20";
    public static final String GEOLOCATION_INVALID_DATA = "ES-21";


    public static final Map<String, ErrorDetail> ERROR_DETAILS = Map.ofEntries(
            entry(PARSING_OBJECT_TO_JSON,
                    new ErrorDetail(PARSING_OBJECT_TO_JSON, "An error occurred while parsing object to json")),
            entry(READING_OBJECT_TO_JSON,
                    new ErrorDetail(READING_OBJECT_TO_JSON, "An error occurred while reading json")),
            entry(USER_NOT_FOUND, new ErrorDetail(USER_NOT_FOUND, "Requested user was not found")),
            entry(USERNAME_NOT_EMPTY, new ErrorDetail(USERNAME_NOT_EMPTY, "UserName cannot be empty")),
            entry(PASSWORD_NOT_EMPTY, new ErrorDetail(PASSWORD_NOT_EMPTY, "Password cannot be empty")),
            entry(DOMAIN_NOT_FOUND, new ErrorDetail(DOMAIN_NOT_FOUND, "Requested domain was not found")),
            entry(DOMAIN_NOT_NULL, new ErrorDetail(DOMAIN_NOT_NULL, "Domain cannot be null")),
            entry(DOMAIN_POSITIVE, new ErrorDetail(DOMAIN_POSITIVE, "Domain value must be positive")),
            entry(DOMAIN_CANNOT_BE_MODIFIED, new ErrorDetail(DOMAIN_CANNOT_BE_MODIFIED, "Domain cannot be modified")),
            entry(ROLE_NOT_FOUND, new ErrorDetail(ROLE_NOT_FOUND, "Requested role was not found")),
            entry(ROLE_NOT_NULL, new ErrorDetail(ROLE_NOT_NULL, "Role cannot be null")),
            entry(ROLE_POSITIVE, new ErrorDetail(ROLE_POSITIVE, "Role value must be positive")),
            entry(ROLE_CANNOT_BE_MODIFIED, new ErrorDetail(ROLE_CANNOT_BE_MODIFIED, "Role cannot be modified")),
            entry(USERNAME_SIZE, new ErrorDetail(USERNAME_SIZE, "Invalid size for username")),
            entry(NAME_SIZE, new ErrorDetail(NAME_SIZE, "Invalid size for name")),
            entry(PASSWORD_SIZE, new ErrorDetail(PASSWORD_SIZE, "Invalid size for password")),
            entry(LAST_NAME_SIZE, new ErrorDetail(LAST_NAME_SIZE, "Invalid size for lastName")),
            entry(EMAIL_SIZE, new ErrorDetail(EMAIL_SIZE, "Invalid size for email")),
            entry(ADDRESS_NOT_FOUND, new ErrorDetail(ADDRESS_NOT_FOUND, "Requested address was not found")),
            entry(TAX_NOT_FOUND, new ErrorDetail(TAX_NOT_FOUND, "Requested tax was not found")),
            entry(GEOLOCATION_INVALID_DATA, new ErrorDetail(GEOLOCATION_INVALID_DATA, "Invalid geolocation data could not be converted to GeolocationModel")));

}