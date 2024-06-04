package com.codigo.msvizacuti.application.controller;

import com.codigo.msvizacuti.domain.aggregates.dto.ClienteDto;
import com.codigo.msvizacuti.domain.aggregates.request.ClienteRequest;
import com.codigo.msvizacuti.domain.ports.in.ClienteServiceIn;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ms-viza-cuti/v1/cliente")
@AllArgsConstructor
public class ClienteController {
    private final ClienteServiceIn clienteServiceIn;
    @PostMapping
    public ResponseEntity<ClienteDto> registrar(@RequestBody ClienteRequest clienteRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clienteServiceIn.crearClienteIn(clienteRequest));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> bsucarXid(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clienteServiceIn.buscarXIdIn(id).get());
    }

    @GetMapping("/todos")
    public ResponseEntity<List<ClienteDto>> buscartodos(){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clienteServiceIn.obtenerTodosIn());
    }
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> actualizar(@PathVariable Long id, @RequestBody ClienteRequest clienteRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clienteServiceIn.actualizarIn(id,clienteRequest));
    }

}
