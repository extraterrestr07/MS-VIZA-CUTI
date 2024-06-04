package com.codigo.msvizacuti.domain.aggregates.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpleadoDto {
    private Long idEmpleado;
    private Double sueldoEmpleado;
    private Long idPersona;
}
