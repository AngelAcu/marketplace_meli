package com.meli.marketplace.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoResponseDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private double precio;

}
