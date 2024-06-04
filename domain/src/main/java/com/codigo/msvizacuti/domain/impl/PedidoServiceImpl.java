package com.codigo.msvizacuti.domain.impl;

import com.codigo.msvizacuti.domain.aggregates.dto.PedidoDto;
import com.codigo.msvizacuti.domain.aggregates.request.PedidoRequest;
import com.codigo.msvizacuti.domain.ports.in.PedidoServiceIn;
import com.codigo.msvizacuti.domain.ports.out.PedidoServiceOut;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PedidoServiceImpl implements PedidoServiceIn {
    private final PedidoServiceOut pedidoServiceOut;
    @Override
    public PedidoDto crearPedidoIn(PedidoRequest pedidoRequest) {
        return pedidoServiceOut.crearPedidoOut(pedidoRequest);
    }

    @Override
    public Optional<PedidoDto> buscarXIdIn(Long id) {
        return pedidoServiceOut.buscarXIdOut(id);
    }

    @Override
    public List<PedidoDto> obtenerTodosIn() {
        return pedidoServiceOut.obtenerTodosOut();
    }

    @Override
    public PedidoDto actualizarIn(Long id, PedidoRequest pedidoRequest) {
        return pedidoServiceOut.actualizarOut(id, pedidoRequest);
    }

    @Override
    public PedidoDto deleteIn(Long id) {
        return pedidoServiceOut.deleteOut(id);
    }
}
