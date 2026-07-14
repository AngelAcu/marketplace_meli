package com.meli.marketplace.repositories;

import com.meli.marketplace.models.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//Producto Repository
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    Page<Producto> findByNombre(String nombre, Pageable pageable);

    Optional<Producto> findByNombre(String nombre);

}
