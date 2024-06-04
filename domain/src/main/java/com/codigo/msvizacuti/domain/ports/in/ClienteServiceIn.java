package com.codigo.msvizacuti.domain.ports.in;

import com.codigo.msvizacuti.domain.aggregates.dto.ClienteDto;
import com.codigo.msvizacuti.domain.aggregates.request.ClienteRequest;

import java.util.List;
import java.util.Optional;

public interface ClienteServiceIn {
    ClienteDto crearClienteIn(ClienteRequest clienteRequest);
    Optional<ClienteDto> buscarXIdIn(Long id);
    List<ClienteDto> obtenerTodosIn();
    ClienteDto actualizarIn(Long id, ClienteRequest clienteRequest);
}
