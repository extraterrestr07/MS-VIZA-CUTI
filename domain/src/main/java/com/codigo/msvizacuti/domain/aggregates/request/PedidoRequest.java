package com.codigo.msvizacuti.domain.aggregates.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PedidoRequest {
    private Double total;
    private Long idCliente;
    private List<ProductoRequest> productos;
}
