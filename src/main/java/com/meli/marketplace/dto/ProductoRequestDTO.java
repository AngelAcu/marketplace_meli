package com.meli.marketplace.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoRequestDTO {

    @NotBlank(message = "Nombre obligatorio")
    @Size(max = 100, message = "El campo nombre no debe exceder los 100 caracteres")
    private String nombre;

    @Size(max = 1000, message = "La descripcion no debe exceder los 1000 caracteres")
    private String descripcion;

    @NotNull(message = "Precio obligatorio")
    @DecimalMin(value = "50.0", message = "El precio debe estar minimo en 50 pesos COP")
    @DecimalMax(value = "70000000.00", message = "El precio no debe exceder los 70.000.000 COP")
    private BigDecimal precio;

}
