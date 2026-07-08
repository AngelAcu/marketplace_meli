package com.meli.marketplace.services;

import com.meli.marketplace.dto.ProductoDTO;
import com.meli.marketplace.exceptions.ProductoConflictException;
import com.meli.marketplace.exceptions.ProductoNotFoundException;
import com.meli.marketplace.models.Producto;
import com.meli.marketplace.repositories.ProductoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<ProductoDTO> findAll(String nombre, Pageable pageable) {

        Page<Producto> productos = null;

        if (nombre != null){
            productos = this.productoRepository.findByNombre(nombre, pageable);
        } else {
            productos = this.productoRepository.findAll(pageable);
        }

        return productos.stream()
                .map(p -> new ProductoDTO(
                        p.getId(),
                        p.getNombre(),
                        p.getDescripcion(),
                        p.getPrecio()
                ))
                .toList();

    }

    public ProductoDTO findById(Long id){

        Optional<Producto> productoOptional = this.productoRepository.findById(id);

        if (productoOptional.isPresent()){
            Producto producto = productoOptional.get();

            return new ProductoDTO(producto.getId(), producto.getNombre(), producto.getDescripcion(), producto.getPrecio());
        }

        throw new ProductoNotFoundException(id);

    }

    public ProductoDTO save(ProductoDTO productoDto){

        Producto producto = new Producto();
        producto.setNombre(productoDto.getNombre());
        producto.setDescripcion(productoDto.getDescripcion());
        producto.setPrecio(productoDto.getPrecio());

        if (this.productoRepository.findByNombre(producto.getNombre()).isPresent()){
            throw  new ProductoConflictException(producto.getNombre());
        }

        Producto productoSaved = this.productoRepository.save(producto);

        return new ProductoDTO(productoSaved.getId(), productoSaved.getNombre(), productoSaved.getDescripcion(), productoSaved.getPrecio());

    }

}
