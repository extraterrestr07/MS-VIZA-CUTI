package com.codigo.msvizacuti.infraestructure.mapper;

import com.codigo.msvizacuti.domain.aggregates.dto.PedidoDto;
import com.codigo.msvizacuti.infraestructure.entity.PedidoEntity;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
@Service()
public class PedidoMapper {
    public static PedidoDto fromEntity(PedidoEntity entity) {
        PedidoDto dto = new PedidoDto();
        dto.setIdPedido(entity.getIdPedido());
        dto.setTotal(entity.getTotal());
        dto.setIdCliente(entity.getClienteEntity().getIdCliente()); // Suponiendo que ClienteEntity tiene un getIdCliente()
        dto.setProductos(entity.getProductos().stream()
                .map(ProductoMapper::fromEntity)
                .collect(Collectors.toList()));
        dto.setEstado(entity.getEstado());
        dto.setUsuaCrea(entity.getUsuaCrea());
        dto.setDateCreate(entity.getDateCreate());
        dto.setUsuaModif(entity.getUsuaModif());
        dto.setDateModif(entity.getDateModif());
        dto.setUsuaDelet(entity.getUsuaDelet());
        dto.setDateDelet(entity.getDateDelet());
        return dto;
    }
}
