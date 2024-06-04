package com.codigo.msvizacuti.application.controller;

import com.codigo.msvizacuti.domain.aggregates.dto.PedidoDto;
import com.codigo.msvizacuti.domain.aggregates.request.PedidoRequest;
import com.codigo.msvizacuti.domain.ports.in.PedidoServiceIn;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/ms-viza-cuti/v1/pedido")
@AllArgsConstructor
public class PedidoController {
    private final PedidoServiceIn pedidoServiceIn;
    @PostMapping
    public ResponseEntity<PedidoDto> registrar(@RequestBody PedidoRequest pedidoRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pedidoServiceIn.crearPedidoIn(pedidoRequest));
    }
    @GetMapping("/{id}")
    public ResponseEntity<PedidoDto> bsucarXid(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pedidoServiceIn.buscarXIdIn(id).get());
    }

    @GetMapping("/todos")
    public ResponseEntity<List<PedidoDto>> buscartodos(){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pedidoServiceIn.obtenerTodosIn());
    }
    @PutMapping("/{id}")
    public ResponseEntity<PedidoDto> actualizar(@PathVariable Long id, @RequestBody PedidoRequest pedidoRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pedidoServiceIn.actualizarIn(id,pedidoRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PedidoDto> delete(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pedidoServiceIn.deleteIn(id));
    }

}
