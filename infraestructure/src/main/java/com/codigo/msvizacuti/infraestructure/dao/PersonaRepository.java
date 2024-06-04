package com.codigo.msvizacuti.infraestructure.dao;

import com.codigo.msvizacuti.infraestructure.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<PersonaEntity, Long> {
    
}
