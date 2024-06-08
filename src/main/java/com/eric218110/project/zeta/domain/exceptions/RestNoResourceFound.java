package com.eric218110.project.zeta.domain.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class RestNoResourceFound {
  @ExceptionHandler(NoResourceFoundException.class)
  public ResponseEntity<ExceptionHandlerDetails> exceptionHandlerDetail(
      NoResourceFoundException noResourceFoundException) {
    Map<String, String> errorDetails = new HashMap<>();
    String message = String.format("Not found path /%s, verify documentation and try again",
        noResourceFoundException.getResourcePath());
    errorDetails.put("message", message);

    return new ResponseEntity<>(
        ExceptionHandlerDetails.builder()
            .status(HttpStatus.BAD_REQUEST.value())
            .timestamp(LocalDateTime.now())
            .title("Not found")
            .developMessage(noResourceFoundException.getClass().getName())
            .details(errorDetails)
            .build(),
        HttpStatus.BAD_REQUEST);
  }
}
