package com.meli.marketplace.exceptions;

//Conflict Exception
public class ProductoConflictException extends RuntimeException {

    public ProductoConflictException(String nombre) {
        super("El nombre " + nombre + " ya se esta usando en un producto existente");
    }

}
