package com.es.api.responses;

import java.util.List;

public record ErrorResponse(
        String message,
        HttpError httpError,
        List<ErrorDetail> errors) {

  public record HttpError(
          String message,
          Integer status) {
  }

  public record ErrorDetail(
          String code,
          String description) {

    public ErrorDetail(String description) {
      this(null, description);
    }

  }

}