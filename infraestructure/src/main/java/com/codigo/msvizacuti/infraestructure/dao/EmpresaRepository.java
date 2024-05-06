package com.codigo.msvizacuti.infraestructure.dao;

import com.codigo.msvizacuti.infraestructure.entity.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<EmpresaEntity, Long> {

}
