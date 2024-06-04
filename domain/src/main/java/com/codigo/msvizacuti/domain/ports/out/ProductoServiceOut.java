package com.codigo.msvizacuti.domain.ports.out;

import com.codigo.msvizacuti.domain.aggregates.dto.ProductoDto;
import com.codigo.msvizacuti.domain.aggregates.request.ProductoRequest;

import java.util.List;
import java.util.Optional;

public interface ProductoServiceOut {
    ProductoDto crearProductoOut(ProductoRequest productoRequest);
    Optional<ProductoDto> buscarXIdOut(Long id);
    List<ProductoDto> obtenerTodosOut();
    ProductoDto actualizarOut(Long id, ProductoRequest productoRequest);
    ProductoDto deleteOut(Long id);
}
