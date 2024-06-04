package com.codigo.msvizacuti.domain.aggregates.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpleadoRequest {
    private Long idPersona;
    private Double sueldoEmpleado;
}
