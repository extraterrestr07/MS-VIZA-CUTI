package com.codigo.msvizacuti.domain.aggregates.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
public class PedidoDto {
    private Long idPedido;
    private Double total;
    private Long idCliente;
    private List<ProductoDto> productos;
    private Integer estado;
    private String usuaCrea;
    private Timestamp dateCreate;
    private String usuaModif;
    private Timestamp dateModif;
    private String usuaDelet;
    private Timestamp dateDelet;
}
