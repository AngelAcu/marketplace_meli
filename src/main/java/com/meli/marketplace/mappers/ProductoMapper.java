package com.meli.marketplace.mappers;

import com.meli.marketplace.dto.ProductoResponseDTO;
import com.meli.marketplace.models.Producto;
import org.springframework.stereotype.Component;

// INYECTAR UN OBJETO EN EL CONTEXTO DE SPRING
@Component
public class ProductoMapper {

    public ProductoResponseDTO toDto(Producto producto) {
        return new ProductoResponseDTO(producto.getId(), producto.getNombre(), producto.getDescripcion(), producto.getPrecio());
    }

}
