package com.es.api.exceptions;

import com.es.api.responses.ErrorResponse;
import com.es.api.responses.ErrorResponse.ErrorDetail;
import com.es.api.responses.ErrorResponse.HttpError;

import jakarta.persistence.PersistenceException;
import jakarta.validation.ValidationException;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.ForbiddenException;
import jakarta.ws.rs.InternalServerErrorException;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.ServiceUnavailableException;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.MethodNotAllowedException;

import static com.es.api.messages.ErrorMessage.ERROR_DETAILS;

@ControllerAdvice
public class GlobalExceptionHandler {

    // ~ CONSTANT(S) ----------------------------------------------------------

    private static final String INTERNAL_SERVER_ERROR_MESSAGE = "Unexpected internal server error.";
    private static final String BAD_REQUEST_ERROR_MESSAGE = "Invalid syntax for this request was provided.";
    private static final String FORBIDDEN_ERROR_MESSAGE = "Your account is not authorized to access the requested resource.";
    private static final String NOT_ALLOWED_ERROR_MESSAGE = "This method type is not currently supported.";
    private static final String NOT_AUTH_ERROR_MESSAGE = "Authentication failure.";
    private static final String NOT_FOUND_ERROR_MESSAGE = "We could not find the resource you requested. Please refer to the documentation for the list of resources.";
    private static final String PERSISTENCE_ERROR_MESSAGE = "Error attempting the operation on the database.";
    private static final String HIBERNATE_ERROR_MESSAGE = "Hibernate exception.";
    private static final String SERVICE_UNAVAILABLE_ERROR_MESSAGE = "The server is currently unavailable.";
    private static final String VALIDATION_ERROR_MESSAGE = "Values in request are not valid for defined constraints";

    // ~ HANDLER(S) -----------------------------------------------------------

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(InternalServerErrorException ex, WebRequest request) {
        var errorDetails = List.of(ex.getMessage());
        return createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MESSAGE, errorDetails);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(IllegalArgumentException ex, WebRequest request) {
        var errorDetails = List.of(ex.getMessage());
        return createErrorResponse(HttpStatus.BAD_REQUEST, BAD_REQUEST_ERROR_MESSAGE, errorDetails);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorResponse> handleForbiddenException(ForbiddenException ex, WebRequest request) {
        var errorDetails = List.of(ex.getMessage());
        return createErrorResponse(HttpStatus.FORBIDDEN, FORBIDDEN_ERROR_MESSAGE, errorDetails);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex,
            WebRequest request) {
        var errorDetails = List.of(ex.getMessage());
        return createErrorResponse(HttpStatus.NOT_FOUND, NOT_FOUND_ERROR_MESSAGE, errorDetails);
    }

    @ExceptionHandler(MethodNotAllowedException.class)
    public ResponseEntity<ErrorResponse> handleMethodNotAllowedException(MethodNotAllowedException ex,
            WebRequest request) {
        var errorDetails = List.of(ex.getMessage());
        return createErrorResponse(HttpStatus.METHOD_NOT_ALLOWED, NOT_ALLOWED_ERROR_MESSAGE, errorDetails);
    }

    @ExceptionHandler(NotAuthorizedException.class)
    public ResponseEntity<ErrorResponse> handleNotAuthorizedException(NotAuthorizedException ex,
            WebRequest request) {
        var errorDetails = List.of(ex.getMessage());
        return createErrorResponse(HttpStatus.UNAUTHORIZED, NOT_AUTH_ERROR_MESSAGE, errorDetails);
    }

    @ExceptionHandler(HibernateException.class)
    public ResponseEntity<ErrorResponse> handleHibernateException(HibernateException ex, WebRequest request) {
        var errorDetails = List.of(ex.getMessage());
        return createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, HIBERNATE_ERROR_MESSAGE, errorDetails);
    }

    @ExceptionHandler(PersistenceException.class)
    public ResponseEntity<ErrorResponse> handlePersistenceException(PersistenceException ex, WebRequest request) {
        var errorDetails = List.of(ex.getMessage());
        return createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, PERSISTENCE_ERROR_MESSAGE, errorDetails);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(ValidationException ex, WebRequest request) {
        var errorDetails = List.of(ex.getMessage());
        return createErrorResponse(HttpStatus.BAD_REQUEST, VALIDATION_ERROR_MESSAGE, errorDetails);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex,
            WebRequest request) {
        var errorDetails = List.of(ex.getMessage());
        return createErrorResponse(HttpStatus.BAD_REQUEST, VALIDATION_ERROR_MESSAGE, errorDetails);
    }
    
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleMessageNotReadableException(HttpMessageNotReadableException ex,
            WebRequest request) {
        var errorDetails = List.of(ex.getMessage());
        return createErrorResponse(HttpStatus.BAD_REQUEST, VALIDATION_ERROR_MESSAGE, errorDetails);
    }

    @ExceptionHandler(ServiceUnavailableException.class)
    public ResponseEntity<ErrorResponse> handleServiceUnavailableException(ServiceUnavailableException ex,
            WebRequest request) {
        var errorDetails = List.of(ex.getMessage());
        return createErrorResponse(HttpStatus.SERVICE_UNAVAILABLE, SERVICE_UNAVAILABLE_ERROR_MESSAGE, errorDetails);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
            WebRequest request) {
        var errorDetails = ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
        return createErrorResponse(HttpStatus.BAD_REQUEST, BAD_REQUEST_ERROR_MESSAGE, errorDetails);
    }

    // ~ METHOD(S) ------------------------------------------------------------

    private ResponseEntity<ErrorResponse> createErrorResponse(HttpStatus status, String message,
            List<String> errorDetails) {
        var httpError = new HttpError(status.getReasonPhrase(), status.value());
        var errors = errorDetails.stream()
                .map(detail -> ERROR_DETAILS.getOrDefault(detail, new ErrorDetail(detail)))
                .toList();
        var errorResponse = new ErrorResponse(message, httpError, errors);
        return new ResponseEntity<>(errorResponse, status);
    }
}