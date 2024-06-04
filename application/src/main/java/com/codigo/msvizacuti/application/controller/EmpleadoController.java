package com.codigo.msvizacuti.application.controller;

import com.codigo.msvizacuti.domain.aggregates.dto.EmpleadoDto;
import com.codigo.msvizacuti.domain.aggregates.request.EmpleadoRequest;
import com.codigo.msvizacuti.domain.ports.in.EmpleadoServiceIn;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/ms-viza-cuti/v1/empleado")
@AllArgsConstructor
public class EmpleadoController {
    private final EmpleadoServiceIn empleadoServiceIn;
    @PostMapping
    public ResponseEntity<EmpleadoDto> registrar(@RequestBody EmpleadoRequest empleadoRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(empleadoServiceIn.crearEmpleadoIn(empleadoRequest));
    }
    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoDto> bsucarXid(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(empleadoServiceIn.buscarXIdIn(id).get());
    }

    @GetMapping("/todos")
    public ResponseEntity<List<EmpleadoDto>> buscartodos(){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(empleadoServiceIn.obtenerTodosIn());
    }
    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoDto> actualizar(@PathVariable Long id, @RequestBody EmpleadoRequest empleadoRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(empleadoServiceIn.actualizarIn(id,empleadoRequest));
    }
}
