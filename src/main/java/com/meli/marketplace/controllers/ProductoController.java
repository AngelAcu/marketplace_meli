package com.meli.marketplace.controllers;

import com.meli.marketplace.dto.ProductoDTO;
import com.meli.marketplace.services.ProductoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

//Producto Controller
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> getAll(@RequestParam(required = false) String nombre,
                                                    @PageableDefault(size = 10, page = 0, sort = "nombre") Pageable pageable) {
        return ResponseEntity.ok(this.productoService.findAll(nombre, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(this.productoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> save(@Valid @RequestBody ProductoDTO productoDto){
        ProductoDTO productoSaved = this.productoService.save(productoDto);
        URI uri = URI.create("/api/productos/" + productoSaved.getId());
        return ResponseEntity.created(uri).body(productoSaved);
    }

}
