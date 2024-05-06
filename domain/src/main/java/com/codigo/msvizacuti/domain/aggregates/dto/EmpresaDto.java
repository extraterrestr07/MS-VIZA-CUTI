package com.codigo.msvizacuti.domain.aggregates.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class EmpresaDto {
    private Long id;
    private String tipoDoc;
    private String numDoc;
    private String razonSocial;
    private Integer estado;
    private String condicion;
    private Boolean EsAgenteRetencion;
    private String usuaCrea;
    private Timestamp dateCreate;
    private String usuaModif;
    private Timestamp dateModif;
    private String usuaDelet;
    private Timestamp dateDelet;
}
