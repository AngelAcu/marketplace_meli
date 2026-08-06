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
import org.springframework.transaction.annotation.Transactional;

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

    private Producto findById(Long id){
        return this.productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));
    }

    private void findByNombre(String nombre){
        if (this.productoRepository.findByNombreIgnoreCase(nombre).isPresent()){
            throw  new ProductoConflictException(nombre);
        }
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

    public ProductoResponseDTO getById(Long id){
        Producto producto = this.findById(id);
        return this.productoMapper.toDto(producto);
    }

    @Transactional
    public ProductoResponseDTO save(ProductoRequestDTO productoRequestDto){
        this.findByNombre(productoRequestDto.nombre());

        Producto producto = productoMapper.toEntity(productoRequestDto);
        Producto productoSaved = this.productoRepository.save(producto);

        return this.productoMapper.toDto(productoSaved);
    }

    @Transactional
    public ProductoResponseDTO update(Long id, ProductoRequestDTO productoRequestDto){
        Producto producto = this.findById(id);
        this.findByNombre(productoRequestDto.nombre());

        Producto productoUpdated = this.productoMapper.updateEntity(producto, productoRequestDto);
        Producto productoSaved = this.productoRepository.save(productoUpdated);

        return this.productoMapper.toDto(productoSaved);
    }

    @Transactional
    public void delete(Long id){
        Producto producto = this.findById(id);
        this.productoRepository.delete(producto);
    }

}
