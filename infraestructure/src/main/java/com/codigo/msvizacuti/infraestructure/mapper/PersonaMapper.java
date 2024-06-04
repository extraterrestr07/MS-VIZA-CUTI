package com.codigo.msvizacuti.infraestructure.mapper;

import com.codigo.msvizacuti.domain.aggregates.dto.PersonaDto;
import com.codigo.msvizacuti.infraestructure.entity.PersonaEntity;
import org.springframework.stereotype.Service;

@Service
public class PersonaMapper {
    public static PersonaDto fromEntity(PersonaEntity entity) {
        PersonaDto dto = new PersonaDto();
        dto.setIdPersona(entity.getIdPersona());
        dto.setNumDocu(entity.getNumDocu());
        dto.setNombres(entity.getNombres());
        dto.setApePat(entity.getApePat());
        dto.setApeMat(entity.getApeMat());
        dto.setEstado(entity.getEstado());
        dto.setUsuaCrea(entity.getUsuaCrea());
        dto.setDateCreate(entity.getDateCreate());
        dto.setUsuaModif(entity.getUsuaModif());
        dto.setDateModif(entity.getDateModif());
        dto.setUsuaDelet(entity.getUsuaDelet());
        dto.setDateDelet(entity.getDateDelet());
        return dto;
    }
}
