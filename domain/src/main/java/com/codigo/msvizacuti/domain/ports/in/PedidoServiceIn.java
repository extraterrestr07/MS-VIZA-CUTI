package com.codigo.msvizacuti.domain.ports.in;


import com.codigo.msvizacuti.domain.aggregates.dto.PedidoDto;
import com.codigo.msvizacuti.domain.aggregates.request.PedidoRequest;

import java.util.List;
import java.util.Optional;

public interface PedidoServiceIn {
    PedidoDto crearPedidoIn(PedidoRequest pedidoRequest);
    Optional<PedidoDto> buscarXIdIn(Long id);
    List<PedidoDto> obtenerTodosIn();
    PedidoDto actualizarIn(Long id, PedidoRequest pedidoRequest);
    PedidoDto deleteIn(Long id);
}
