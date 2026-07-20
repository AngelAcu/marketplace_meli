package com.meli.marketplace.exceptions;

//Not Found Exception
public class ProductoNotFoundException extends  RuntimeException{

    public ProductoNotFoundException(Long id){
        super("Producto " + id + " no encontrado");
    }

    public ProductoNotFoundException(Long id, Throwable cause){
        super("Producto " + id + " no encontrado", cause);
    }

}
