package com.codigo.msvizacuti.domain.impl;

import com.codigo.msvizacuti.domain.aggregates.dto.ProductoDto;
import com.codigo.msvizacuti.domain.aggregates.request.ProductoRequest;
import com.codigo.msvizacuti.domain.ports.in.ProductoServiceIn;
import com.codigo.msvizacuti.domain.ports.out.ProductoServiceOut;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductoServiceImpl implements ProductoServiceIn {
    private final ProductoServiceOut productoServiceOut;

    @Override
    public ProductoDto crearProductoIn(ProductoRequest productoRequest) {
        return productoServiceOut.crearProductoOut(productoRequest);
    }

    @Override
    public Optional<ProductoDto> buscarXIdIn(Long id) {
        return productoServiceOut.buscarXIdOut(id);
    }

    @Override
    public List<ProductoDto> obtenerTodosIn() {
        return productoServiceOut.obtenerTodosOut();
    }

    @Override
    public ProductoDto actualizarIn(Long id, ProductoRequest productoRequest) {
        return productoServiceOut.actualizarOut(id, productoRequest);
    }

    @Override
    public ProductoDto deleteIn(Long id) {
        return productoServiceOut.deleteOut(id);
    }
}
