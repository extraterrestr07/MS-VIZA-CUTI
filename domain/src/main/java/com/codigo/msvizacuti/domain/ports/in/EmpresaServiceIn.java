package com.codigo.msvizacuti.domain.ports.in;

import com.codigo.msvizacuti.domain.aggregates.dto.EmpresaDto;
import com.codigo.msvizacuti.domain.aggregates.request.EmpresaRequest;

import java.util.List;
import java.util.Optional;

public interface EmpresaServiceIn {
    EmpresaDto crearEmpresaIn(EmpresaRequest empresaRequest);
    Optional<EmpresaDto> buscarxIdIn(Long id);
    List<EmpresaDto> obtenerTodosIn();
    EmpresaDto actualizarEmpresaIn(Long id, EmpresaRequest empresaRequest);
    EmpresaDto deleteIn(Long id);
}
