package com.codigo.msvizacuti.domain.aggregates.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ProductoDto {
    private Long idProducto;
    private String nombre;
    private Double precioUnidad;
    private Integer estado;
    private String usuaCrea;
    private Timestamp dateCreate;
    private String usuaModif;
    private Timestamp dateModif;
    private String usuaDelet;
    private Timestamp dateDelet;

}
