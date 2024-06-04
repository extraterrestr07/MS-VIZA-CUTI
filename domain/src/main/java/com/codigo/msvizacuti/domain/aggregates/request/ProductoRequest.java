package com.codigo.msvizacuti.domain.aggregates.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoRequest {
    private String nombre;
    private Double precioUnidad;
}
