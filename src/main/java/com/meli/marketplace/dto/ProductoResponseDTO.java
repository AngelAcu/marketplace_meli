package com.meli.marketplace.dto;

import java.math.BigDecimal;

public record ProductoResponseDTO(

    Long id,
    String nombre,
    String descripcion,
    BigDecimal precio

){}
