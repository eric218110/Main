package com.eric218110.project.zeta.domain.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalExceptionFilter {
  @ExceptionHandler(ResponseStatusException.class)
  public ResponseEntity<ExceptionHandlerDetails> handleResponseStatusException(
      ResponseStatusException responseStatusException) {
    Map<String, String> errorDetails = new HashMap<>();
    errorDetails.put("message", responseStatusException.getReason());

    HttpStatusCode statusCode = responseStatusException.getStatusCode();

    return new ResponseEntity<>(ExceptionHandlerDetails.builder().status(statusCode.toString())
        .timestamp(LocalDateTime.now()).title("Request exception, verify documentation")
        .developMessage(responseStatusException.getClass().getName()).details(errorDetails).build(),
        statusCode);
  }

}
