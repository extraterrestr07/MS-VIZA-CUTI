package com.codigo.msvizacuti.domain.ports.in;

import com.codigo.msvizacuti.domain.aggregates.dto.EmpleadoDto;
import com.codigo.msvizacuti.domain.aggregates.request.EmpleadoRequest;

import java.util.List;
import java.util.Optional;

public interface EmpleadoServiceIn {
    EmpleadoDto crearEmpleadoIn(EmpleadoRequest empleadoRequest);
    Optional<EmpleadoDto> buscarXIdIn(Long id);
    List<EmpleadoDto> obtenerTodosIn();
    EmpleadoDto actualizarIn(Long id, EmpleadoRequest empleadoRequest);
}
