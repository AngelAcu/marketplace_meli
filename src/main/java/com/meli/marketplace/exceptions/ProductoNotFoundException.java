package com.meli.marketplace.exceptions;

public class ProductoNotFoundException extends  RuntimeException{

    public ProductoNotFoundException(Long id){
        super("Producto " + id + " no encontrado");
    }

}
