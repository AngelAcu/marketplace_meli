package com.meli.marketplace.mappers;

import com.meli.marketplace.dto.ProductoRequestDTO;
import com.meli.marketplace.dto.ProductoResponseDTO;
import com.meli.marketplace.models.Producto;
import org.springframework.stereotype.Component;

// INYECTAR UN OBJETO EN EL CONTEXTO DE SPRING
@Component
public class ProductoMapper {

    public ProductoResponseDTO toDto(Producto producto) {
        return new ProductoResponseDTO(producto.getId(), producto.getNombre(), producto.getDescripcion(), producto.getPrecio());
    }

    public Producto toEntity(ProductoRequestDTO productoRequestDto) {
        Producto producto = new Producto();

        producto.setNombre(productoRequestDto.nombre());
        producto.setDescripcion(productoRequestDto.descripcion());
        producto.setPrecio(productoRequestDto.precio());

        return producto;
    }

    public Producto updateEntity(Producto producto, ProductoRequestDTO productoRequestDto) {
        producto.setNombre(productoRequestDto.nombre());
        producto.setDescripcion(productoRequestDto.descripcion());
        producto.setPrecio(productoRequestDto.precio());

        return producto;
    }

}
