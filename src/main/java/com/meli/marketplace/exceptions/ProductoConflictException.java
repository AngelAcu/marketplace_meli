package com.meli.marketplace.exceptions;

public class ProductoConflictException extends RuntimeException {

    public ProductoConflictException(String nombre) {
        super("El nombre " + nombre + " ya se esta usando en un producto existente");
    }

}
