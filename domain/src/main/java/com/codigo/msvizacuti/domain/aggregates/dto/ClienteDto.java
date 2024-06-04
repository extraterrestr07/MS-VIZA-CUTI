package com.codigo.msvizacuti.domain.aggregates.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDto {
    private Long idCliente;
    private Double saldoCliente;
    private Long idPersona;
}
