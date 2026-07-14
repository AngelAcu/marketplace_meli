package com.meli.marketplace.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

//GlobalException
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductoNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound (ProductoNotFoundException ex) {
        return ResponseEntity.status(404)
                .body(new ErrorResponse(404, "Not Found", ex.getMessage(), LocalDateTime.now(), List.of()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleArgumentNotValid (MethodArgumentNotValidException ex) {
        return ResponseEntity.status(400)
                .body(new ErrorResponse(400, "Bad request", ex.getBindingResult().getFieldError().getDefaultMessage(), LocalDateTime.now(), List.of()));
    }

    @ExceptionHandler(ProductoConflictException.class)
    public ResponseEntity<ErrorResponse> handleConflict (ProductoConflictException ex) {
        return ResponseEntity.status(409)
                .body(new ErrorResponse(409, "Conflict", ex.getMessage(), LocalDateTime.now(), List.of()));
    }

}
