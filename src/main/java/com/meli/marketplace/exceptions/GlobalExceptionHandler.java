package com.meli.marketplace.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
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
        HttpStatus status = HttpStatus.NOT_FOUND;

        return ResponseEntity.status(status)
                .body(new ErrorResponse(status.value(), "Not Found", ex.getMessage(), LocalDateTime.now(), List.of()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleArgumentNotValid (MethodArgumentNotValidException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        List<String> errores = ex.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .toList();

        return ResponseEntity.status(status)
                .body(new ErrorResponse(status.value(), "Bad request", "Argumentos no validos", LocalDateTime.now(), errores));
    }

    @ExceptionHandler(ProductoConflictException.class)
    public ResponseEntity<ErrorResponse> handleConflict (ProductoConflictException ex) {
        HttpStatus status = HttpStatus.CONFLICT;

        return ResponseEntity.status(status)
                .body(new ErrorResponse(status.value(), "Conflict", ex.getMessage(), LocalDateTime.now(), List.of()));
    }

}
