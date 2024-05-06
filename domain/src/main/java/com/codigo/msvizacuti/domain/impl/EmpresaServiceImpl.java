package com.codigo.msvizacuti.domain.impl;

import com.codigo.msvizacuti.domain.aggregates.dto.EmpresaDto;
import com.codigo.msvizacuti.domain.aggregates.request.EmpresaRequest;
import com.codigo.msvizacuti.domain.ports.in.EmpresaServiceIn;
import com.codigo.msvizacuti.domain.ports.out.EmpresaServiceOut;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmpresaServiceImpl implements EmpresaServiceIn {
    private final EmpresaServiceOut empresaServiceOut;
    @Override
    public EmpresaDto crearEmpresaIn(EmpresaRequest empresaRequest) {
        return empresaServiceOut.crearEmpresaOut(empresaRequest);
    }

    @Override
    public Optional<EmpresaDto> buscarxIdIn(Long id) {
        return empresaServiceOut.buscarxIdOut(id);
    }

    @Override
    public List<EmpresaDto> obtenerTodosIn() {
        return empresaServiceOut.obtenerTodosOut();
    }

    @Override
    public EmpresaDto actualizarEmpresaIn(Long id, EmpresaRequest empresaRequest) {
        return empresaServiceOut.actualizarEmpresaOut(id, empresaRequest);
    }

    @Override
    public EmpresaDto deleteIn(Long id) {
        return empresaServiceOut.deleteOut(id);
    }
}
