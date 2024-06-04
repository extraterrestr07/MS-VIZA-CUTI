package com.codigo.msvizacuti.infraestructure.dao;

import com.codigo.msvizacuti.infraestructure.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
}
