package com.eric218110.project.zeta.domain.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestControllerAdvice
public class ValidationExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<ExceptionHandlerDetails> handleValidationExceptions(
      MethodArgumentNotValidException methodArgumentNotValidException) {
    Map<String, String> errors = new HashMap<>();
    methodArgumentNotValidException.getBindingResult().getFieldErrors()
        .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

    return new ResponseEntity<>(
        ExceptionHandlerDetails.builder().status(HttpStatus.BAD_REQUEST.toString())
            .timestamp(LocalDateTime.now()).title("Bad request exception, verify documentation")
            .developMessage(methodArgumentNotValidException.getClass().getName()).details(errors)
            .build(),
        HttpStatus.BAD_REQUEST);

  }
}
