package com.codigo.msvizacuti.domain.impl;

import com.codigo.msvizacuti.domain.aggregates.dto.EmpleadoDto;
import com.codigo.msvizacuti.domain.aggregates.request.EmpleadoRequest;
import com.codigo.msvizacuti.domain.ports.in.EmpleadoServiceIn;
import com.codigo.msvizacuti.domain.ports.out.EmpleadoServiceOut;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class EmpleadoServiceImpl implements EmpleadoServiceIn {
    private final EmpleadoServiceOut empleadoServiceOut;
    @Override
    public EmpleadoDto crearEmpleadoIn(EmpleadoRequest empleadoRequest) {
        return empleadoServiceOut.crearEmpleadoOut(empleadoRequest);
    }

    @Override
    public Optional<EmpleadoDto> buscarXIdIn(Long id) {
        return empleadoServiceOut.buscarXIdOut(id);
    }

    @Override
    public List<EmpleadoDto> obtenerTodosIn() {
        return empleadoServiceOut.obtenerTodosOut();
    }

    @Override
    public EmpleadoDto actualizarIn(Long id, EmpleadoRequest empleadoRequest) {
        return empleadoServiceOut.actualizarOut(id, empleadoRequest);
    }
}
