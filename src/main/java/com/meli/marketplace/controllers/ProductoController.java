package com.meli.marketplace.controllers;

import com.meli.marketplace.dto.ProductoRequestDTO;
import com.meli.marketplace.dto.ProductoResponseDTO;
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
public class ProductoController implements ProductoApi {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> getAll(@RequestParam(required = false) String nombre, @PageableDefault(size = 10, page = 0, sort = "nombre") Pageable pageable) {
        return ResponseEntity.ok(this.productoService.findAll(nombre, pageable));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(this.productoService.getById(id));
    }

    @Override
    @PostMapping
    public ResponseEntity<ProductoResponseDTO> save(@Valid @RequestBody ProductoRequestDTO productoRequestDto){
        ProductoResponseDTO productoSaved = this.productoService.save(productoRequestDto);
        URI uri = URI.create("/api/productos/" + productoSaved.id());
        return ResponseEntity.created(uri).body(productoSaved);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> update(@PathVariable Long id, @Valid @RequestBody ProductoRequestDTO productoRequestDTO){
        ProductoResponseDTO productoUpdated = this.productoService.update(id, productoRequestDTO);
        URI uri = URI.create("/api/productos/" + productoUpdated.id());
        return ResponseEntity.created(uri).body(productoUpdated);
    }

    @Override
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.productoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
