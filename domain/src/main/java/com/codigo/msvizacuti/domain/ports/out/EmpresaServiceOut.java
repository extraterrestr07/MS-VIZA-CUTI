package com.codigo.msvizacuti.domain.ports.out;

import com.codigo.msvizacuti.domain.aggregates.dto.EmpresaDto;
import com.codigo.msvizacuti.domain.aggregates.request.EmpresaRequest;

import java.util.List;
import java.util.Optional;

public interface EmpresaServiceOut {
    EmpresaDto crearEmpresaOut(EmpresaRequest empresaRequest);
    Optional<EmpresaDto> buscarxIdOut(Long id);
    List<EmpresaDto> obtenerTodosOut();
    EmpresaDto actualizarEmpresaOut(Long id, EmpresaRequest empresaRequest);
    EmpresaDto deleteOut(Long id);
}
