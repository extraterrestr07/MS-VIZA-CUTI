package com.codigo.msvizacuti.domain.ports.out;

import com.codigo.msvizacuti.domain.aggregates.dto.PedidoDto;
import com.codigo.msvizacuti.domain.aggregates.request.PedidoRequest;

import java.util.List;
import java.util.Optional;

public interface PedidoServiceOut {
    PedidoDto crearPedidoOut(PedidoRequest pedidoRequest);
    Optional<PedidoDto> buscarXIdOut(Long id);
    List<PedidoDto> obtenerTodosOut();
    PedidoDto actualizarOut(Long id, PedidoRequest pedidoRequest);
    PedidoDto deleteOut(Long id);
}
