package com.codigo.msvizacuti.domain.ports.out;

import com.codigo.msvizacuti.domain.aggregates.dto.EmpleadoDto;
import com.codigo.msvizacuti.domain.aggregates.request.EmpleadoRequest;

import java.util.List;
import java.util.Optional;

public interface EmpleadoServiceOut {
    EmpleadoDto crearEmpleadoOut(EmpleadoRequest empleadoRequest);
    Optional<EmpleadoDto> buscarXIdOut(Long id);
    List<EmpleadoDto> obtenerTodosOut();
    EmpleadoDto actualizarOut(Long id, EmpleadoRequest empleadoRequest);
}
