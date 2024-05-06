package com.codigo.msvizacuti.application.controller;

import com.codigo.msvizacuti.domain.aggregates.dto.EmpresaDto;
import com.codigo.msvizacuti.domain.aggregates.request.EmpresaRequest;
import com.codigo.msvizacuti.domain.ports.in.EmpresaServiceIn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ms-viza-cuti/v1/empresa")
@RequiredArgsConstructor
public class EmpresaController {
    private final EmpresaServiceIn empresaServiceIn;

    @PostMapping
    public ResponseEntity<EmpresaDto> crearEmpresa(@RequestBody EmpresaRequest empresaRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(empresaServiceIn.crearEmpresaIn(empresaRequest));
    }
    @GetMapping("/{id}")
    public ResponseEntity<EmpresaDto> buscarXid(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(empresaServiceIn.buscarxIdIn(id).get());
    }

    @GetMapping("/todos")
    public ResponseEntity<List<EmpresaDto>> buscartodos(){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(empresaServiceIn.obtenerTodosIn());
    }
    @PutMapping("/{id}")
    public ResponseEntity<EmpresaDto> actualizar(@PathVariable Long id, @RequestBody EmpresaRequest empresaRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(empresaServiceIn.actualizarEmpresaIn(id,empresaRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmpresaDto> delete(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(empresaServiceIn.deleteIn(id));
    }

}
