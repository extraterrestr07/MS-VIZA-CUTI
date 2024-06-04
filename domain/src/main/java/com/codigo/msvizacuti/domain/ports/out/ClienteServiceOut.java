package com.codigo.msvizacuti.domain.ports.out;

import com.codigo.msvizacuti.domain.aggregates.dto.ClienteDto;
import com.codigo.msvizacuti.domain.aggregates.request.ClienteRequest;

import java.util.List;
import java.util.Optional;

public interface ClienteServiceOut {
    ClienteDto crearClienteOut(ClienteRequest clienteRequest);
    Optional<ClienteDto> buscarXIdOut(Long id);
    List<ClienteDto> obtenerTodosOut();
    ClienteDto actualizarOut(Long id, ClienteRequest clienteRequest);
}
