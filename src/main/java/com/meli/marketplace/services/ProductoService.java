package com.meli.marketplace.services;

import com.meli.marketplace.dto.ProductoRequestDTO;
import com.meli.marketplace.dto.ProductoResponseDTO;
import com.meli.marketplace.exceptions.ProductoConflictException;
import com.meli.marketplace.exceptions.ProductoNotFoundException;
import com.meli.marketplace.models.Producto;
import com.meli.marketplace.repositories.ProductoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// ProductoService
@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<ProductoResponseDTO> findAll(String nombre, Pageable pageable) {

        Page<Producto> productos = null;

        if (nombre != null){
            productos = this.productoRepository.findByNombre(nombre, pageable);
        } else {
            productos = this.productoRepository.findAll(pageable);
        }

        return productos.stream()
                .map(p -> new ProductoResponseDTO(
                        p.getId(),
                        p.getNombre(),
                        p.getDescripcion(),
                        p.getPrecio()
                ))
                .toList();

    }

    public ProductoResponseDTO findById(Long id){

        Optional<Producto> productoOptional = this.productoRepository.findById(id);

        if (productoOptional.isPresent()){
            Producto producto = productoOptional.get();

            return new ProductoResponseDTO(producto.getId(), producto.getNombre(), producto.getDescripcion(), producto.getPrecio());
        }

        throw new ProductoNotFoundException(id);

    }

    public ProductoResponseDTO save(ProductoRequestDTO productoRequestDto){

        Producto producto = new Producto();
        producto.setNombre(productoRequestDto.getNombre());
        producto.setDescripcion(productoRequestDto.getDescripcion());
        producto.setPrecio(productoRequestDto.getPrecio());

        if (this.productoRepository.findByNombre(producto.getNombre()).isPresent()){
            throw  new ProductoConflictException(producto.getNombre());
        }

        Producto productoSaved = this.productoRepository.save(producto);

        return new ProductoResponseDTO(productoSaved.getId(), productoSaved.getNombre(), productoSaved.getDescripcion(), productoSaved.getPrecio());

    }

}
