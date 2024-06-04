package com.codigo.msvizacuti.domain.impl;

import com.codigo.msvizacuti.domain.aggregates.dto.ClienteDto;
import com.codigo.msvizacuti.domain.aggregates.request.ClienteRequest;
import com.codigo.msvizacuti.domain.ports.in.ClienteServiceIn;
import com.codigo.msvizacuti.domain.ports.out.ClienteServiceOut;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class ClienteServiceImpl implements ClienteServiceIn {
    private final ClienteServiceOut clienteServiceOut;

    @Override
    public ClienteDto crearClienteIn(ClienteRequest clienteRequest) {
        return clienteServiceOut.crearClienteOut(clienteRequest);
    }

    @Override
    public Optional<ClienteDto> buscarXIdIn(Long id) {
        return clienteServiceOut.buscarXIdOut(id);
    }

    @Override
    public List<ClienteDto> obtenerTodosIn() {
        return clienteServiceOut.obtenerTodosOut();
    }

    @Override
    public ClienteDto actualizarIn(Long id, ClienteRequest clienteRequest) {
        return clienteServiceOut.actualizarOut(id, clienteRequest);
    }
}
