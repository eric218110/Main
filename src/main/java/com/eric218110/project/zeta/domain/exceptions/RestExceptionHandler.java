package com.eric218110.project.zeta.domain.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<ExceptionHandlerDetails> exceptionHandlerDetail(BadRequestException badRequestException) {
    Map<String, String> errorDetails = new HashMap<>();
    errorDetails.put("message", badRequestException.getMessage());

    return new ResponseEntity<>(
        ExceptionHandlerDetails.builder()
            .status(HttpStatus.BAD_REQUEST.value())
            .timestamp(LocalDateTime.now())
            .title("Bad request exception, verify documentation")
            .developMessage(badRequestException.getClass().getName())
            .details(errorDetails)
            .build(),
        HttpStatus.BAD_REQUEST);
  }
}
