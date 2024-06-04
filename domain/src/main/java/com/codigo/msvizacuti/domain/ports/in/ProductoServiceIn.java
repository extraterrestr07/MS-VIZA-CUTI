package com.codigo.msvizacuti.domain.ports.in;

import com.codigo.msvizacuti.domain.aggregates.dto.ProductoDto;
import com.codigo.msvizacuti.domain.aggregates.request.ProductoRequest;

import java.util.List;
import java.util.Optional;

public interface ProductoServiceIn {
    ProductoDto crearProductoIn(ProductoRequest productoRequest);
    Optional<ProductoDto> buscarXIdIn(Long id);
    List<ProductoDto> obtenerTodosIn();
    ProductoDto actualizarIn(Long id, ProductoRequest productoRequest);
    ProductoDto deleteIn(Long id);
}
