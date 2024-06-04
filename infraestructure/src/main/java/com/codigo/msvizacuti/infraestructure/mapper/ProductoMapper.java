package com.codigo.msvizacuti.infraestructure.mapper;

import com.codigo.msvizacuti.domain.aggregates.dto.ProductoDto;
import com.codigo.msvizacuti.infraestructure.entity.ProductoEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductoMapper {
    public static ProductoDto fromEntity(ProductoEntity entity) {
        ProductoDto productoDto = new ProductoDto();
        productoDto.setIdProducto(entity.getIdProducto());
        productoDto.setNombre(entity.getNombre());
        productoDto.setPrecioUnidad(entity.getPrecioUnidad());
        productoDto.setEstado(entity.getEstado());
        productoDto.setUsuaCrea(entity.getUsuaCrea());
        productoDto.setDateCreate(entity.getDateCreate());
        productoDto.setUsuaModif(entity.getUsuaModif());
        productoDto.setDateModif(entity.getDateModif());
        productoDto.setUsuaDelet(entity.getUsuaDelet());
        productoDto.setDateDelet(entity.getDateDelet());
        return productoDto;
    }
}
