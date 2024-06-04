package com.codigo.msvizacuti.infraestructure.dao;

import com.codigo.msvizacuti.infraestructure.entity.EmpleadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<EmpleadoEntity, Long> {
}
