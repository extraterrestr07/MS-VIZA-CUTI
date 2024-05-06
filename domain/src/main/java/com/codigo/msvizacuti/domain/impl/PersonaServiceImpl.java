package com.codigo.msvizacuti.domain.impl;

import com.codigo.msvizacuti.domain.aggregates.dto.PersonaDto;
import com.codigo.msvizacuti.domain.aggregates.request.PersonaRequest;
import com.codigo.msvizacuti.domain.ports.in.PersonaServiceIn;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonaServiceImpl implements PersonaServiceIn {
    @Override
    public PersonaDto crearPersonaIn(PersonaRequest personaRequest) {
        return null;
    }

    @Override
    public Optional<PersonaDto> buscarXIdIn(Long id) {
        return Optional.empty();
    }

    @Override
    public List<PersonaDto> obtenerTodosIn() {
        return List.of();
    }

    @Override
    public PersonaDto actualizarIn(Long id, PersonaRequest personaRequest) {
        return null;
    }

    @Override
    public PersonaDto deleteIn(Long id) {
        return null;
    }
}
