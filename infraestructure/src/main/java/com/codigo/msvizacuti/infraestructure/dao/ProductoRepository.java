package com.codigo.msvizacuti.infraestructure.dao;


import com.codigo.msvizacuti.infraestructure.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<ProductoEntity, Long> {
}
