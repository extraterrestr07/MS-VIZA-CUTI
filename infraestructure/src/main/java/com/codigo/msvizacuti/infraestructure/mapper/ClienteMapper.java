package com.codigo.msvizacuti.infraestructure.mapper;

import com.codigo.msvizacuti.domain.aggregates.dto.ClienteDto;
import com.codigo.msvizacuti.infraestructure.entity.ClienteEntity;
import org.springframework.stereotype.Service;

@Service
public class ClienteMapper {
    public static ClienteDto fromEntity(ClienteEntity entity) {
        ClienteDto dto = new ClienteDto();
        dto.setIdCliente(entity.getIdCliente());
        dto.setSaldoCliente(entity.getSaldoCliente());
        dto.setIdPersona(entity.getPersona().getIdPersona());
        return dto;
    }
}
