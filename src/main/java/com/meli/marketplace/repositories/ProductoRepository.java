package com.meli.marketplace.repositories;

import com.meli.marketplace.models.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    @Query("SELECT p FROM Producto p WHERE p.precio > :precio ")
    Page<Producto> findByPrecio(@Param("precio") double precio, Pageable pageable);

    Page<Producto> findByNombre(String nombre, Pageable pageable);

    Optional<Producto> findByNombre(String nombre);

}
