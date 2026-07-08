package com.meli.marketplace.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorResponse {

    int status;
    String error;
    String message;
    LocalDateTime timestamp;
    List<String> details;

}
