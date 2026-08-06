package com.meli.marketplace.controllers;

import com.meli.marketplace.dto.ProductoRequestDTO;
import com.meli.marketplace.dto.ProductoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "Productos", description = "Api de productos")
public interface ProductoApi {

    @Operation(summary = "Obtener todos los productos")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "operacion exitosa") })
    ResponseEntity<List<ProductoResponseDTO>> getAll(@RequestParam(required = false) String nombre, @PageableDefault(size = 10, page = 0, sort = "nombre") Pageable pageable);

    @Operation(summary = "Obtener producto por id")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "operacion exitosa"),
                            @ApiResponse(responseCode = "404", description = "recurso no encontrado") })
    ResponseEntity<ProductoResponseDTO> findById(@PathVariable Long id);

    @Operation(summary = "Guardar producto")
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "operacion exitosa"),
                            @ApiResponse(responseCode = "409", description = "nombre duplicado"),
                            @ApiResponse(responseCode = "400", description = "argumentos no validos") })
    ResponseEntity<ProductoResponseDTO> save(@Valid @RequestBody ProductoRequestDTO productoRequestDto);

    @Operation(summary = "Actualizar producto")
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "operacion exitosa"),
                            @ApiResponse(responseCode = "404", description = "recurso no encontrado"),
                            @ApiResponse(responseCode = "400", description = "argumentos no validos") })
    ResponseEntity<ProductoResponseDTO> update(@PathVariable Long id, @Valid @RequestBody ProductoRequestDTO productoRequestDTO);

    @Operation(summary = "Borrar producto")
    @ApiResponses(value = { @ApiResponse(responseCode = "204", description = "operacion exitosa"),
                            @ApiResponse(responseCode = "404", description = "recurso no encontrado") })
    ResponseEntity<Void> delete(@PathVariable Long id);
}
