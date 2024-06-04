package com.codigo.msvizacuti.infraestructure.mapper;

import com.codigo.msvizacuti.domain.aggregates.dto.EmpleadoDto;
import com.codigo.msvizacuti.infraestructure.entity.EmpleadoEntity;
import org.springframework.stereotype.Service;

@Service

public class EmpleadoMapper {
    public static EmpleadoDto fromEntity(EmpleadoEntity entity) {
        EmpleadoDto dto = new EmpleadoDto();
        dto.setIdEmpleado(entity.getIdEmpleado());
        dto.setSueldoEmpleado(entity.getSueldoEmpleado());
        dto.setIdPersona(entity.getPersona().getIdPersona());
        return dto;
    }
}
