package com.codigo.msvizacuti.application.controller;

import com.codigo.msvizacuti.domain.aggregates.dto.ProductoDto;
import com.codigo.msvizacuti.domain.aggregates.request.ProductoRequest;
import com.codigo.msvizacuti.domain.ports.in.ProductoServiceIn;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ms-viza-cuti/v1/producto")
@AllArgsConstructor
public class ProductoController {
    private final ProductoServiceIn productoServiceIn;

    @PostMapping
    public ResponseEntity<ProductoDto> registrar(@RequestBody ProductoRequest productoRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productoServiceIn.crearProductoIn(productoRequest));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductoDto> bsucarXid(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productoServiceIn.buscarXIdIn(id).get());
    }

    @GetMapping("/todos")
    public ResponseEntity<List<ProductoDto>> buscartodos(){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productoServiceIn.obtenerTodosIn());
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductoDto> actualizar(@PathVariable Long id, @RequestBody ProductoRequest productoRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productoServiceIn.actualizarIn(id,productoRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductoDto> delete(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productoServiceIn.deleteIn(id));
    }
}
