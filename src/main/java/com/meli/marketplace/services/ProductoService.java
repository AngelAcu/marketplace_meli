package com.meli.marketplace.services;

import com.meli.marketplace.dto.ProductoRequestDTO;
import com.meli.marketplace.dto.ProductoResponseDTO;
import com.meli.marketplace.exceptions.ProductoConflictException;
import com.meli.marketplace.exceptions.ProductoNotFoundException;
import com.meli.marketplace.mappers.ProductoMapper;
import com.meli.marketplace.models.Producto;
import com.meli.marketplace.repositories.ProductoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

// ProductoService
@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;

    public ProductoService(ProductoRepository productoRepository, ProductoMapper productoMapper) {
        this.productoRepository = productoRepository;
        this.productoMapper = productoMapper;
    }

    public List<ProductoResponseDTO> findAll(String nombre, Pageable pageable) {

        Page<Producto> productos = null;

        if (nombre != null){
            productos = this.productoRepository.findByNombreContainingIgnoreCase(nombre, pageable);
        } else {
            productos = this.productoRepository.findAll(pageable);
        }

        return productos.stream()
                .map(p -> this.productoMapper.toDto(p))
                .toList();

    }

    public ProductoResponseDTO findById(Long id){

        return this.productoRepository.findById(id)
                .map(p -> this.productoMapper.toDto(p))
                .orElseThrow(() -> new ProductoNotFoundException(id));

    }

    public ProductoResponseDTO save(ProductoRequestDTO productoRequestDto){

        Producto producto = new Producto();
        producto.setNombre(productoRequestDto.getNombre());
        producto.setDescripcion(productoRequestDto.getDescripcion());
        producto.setPrecio(productoRequestDto.getPrecio());

        if (this.productoRepository.findByNombreIgnoreCase(producto.getNombre()).isPresent()){
            throw  new ProductoConflictException(producto.getNombre());
        }

        Producto productoSaved = this.productoRepository.save(producto);

        return this.productoMapper.toDto(productoSaved);

    }

}
