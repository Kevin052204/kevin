package com.sena.kevin.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.kevin.dtos.TrabajadorDto;
import com.sena.kevin.services.TrabajadorServices;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/api/trabajadores")
@RequiredArgsConstructor 
public class TrabajadorController {

    private final TrabajadorServices trabajadorServices;

    // 1. Registrar nuevo trabajador
    @PostMapping
    public ResponseEntity<TrabajadorDto> guardar(@Valid @RequestBody TrabajadorDto trabajadorDto) {
        TrabajadorDto trabajadorguardado = trabajadorServices.guardar(trabajadorDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(trabajadorguardado);
    }

    // 1.1 Registrar múltiples trabajadores a la vez (Lote)
    @PostMapping("/lote")
    public ResponseEntity<List<TrabajadorDto>> guardarTodos(@Valid @RequestBody List<TrabajadorDto> trabajadorDtos) {
        List<TrabajadorDto> trabajadoresGuardados = trabajadorServices.guardarTodos(trabajadorDtos);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(trabajadoresGuardados);
    }

    // 2. Listar todos los trabajadores
    @GetMapping
    public ResponseEntity<List<TrabajadorDto>> listarTodos() {
        return ResponseEntity.ok(trabajadorServices.listarTodos());
    }
    
    // 3. Consultar trabajador por ID
    @GetMapping("/{id}")
    public ResponseEntity<TrabajadorDto> consultaid(@PathVariable Long id) {
        return ResponseEntity.ok(trabajadorServices.buscarPorId(id));
    }
    
    // 4. Consultar trabajador por POST
    @PostMapping("/buscar")
    public ResponseEntity<TrabajadorDto> consultaIdpost(@RequestBody TrabajadorDto dto) {
        TrabajadorDto encontrado = trabajadorServices.buscarPorId(dto.getId());
        return ResponseEntity.ok(encontrado);
    }
    
    // 5. Actualizar trabajador sin exponer el ID en la URL
    @PutMapping("/actualizar")
    public ResponseEntity<TrabajadorDto> actualizar(
            @RequestHeader("id") Long id,
            @RequestBody TrabajadorDto dto) {

        TrabajadorDto trabajadorActualizado = trabajadorServices.actualizar(id, dto);   
        return ResponseEntity.ok(trabajadorActualizado);
    }

    // 6. Eliminar trabajador
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        trabajadorServices.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}