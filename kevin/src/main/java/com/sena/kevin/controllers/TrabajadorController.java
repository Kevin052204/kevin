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
import com.sena.kevin.dtos.TrabajadorDtoResponse;
import com.sena.kevin.services.TrabajadorServices;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/api/trabajadores")
@RequiredArgsConstructor
public class TrabajadorController {

    private final TrabajadorServices trabajadorServices;

     //Registrar nuevo cliente
    @PostMapping
    public ResponseEntity<TrabajadorDtoResponse> guardar(@Valid @RequestBody TrabajadorDto trabajadorDto){
        TrabajadorDtoResponse trabajadorguardado = trabajadorServices.guardar(trabajadorDto);

        return  ResponseEntity
            .status(HttpStatus.CREATED)
            .body(trabajadorguardado);
    }

    //Listar usuario
    @GetMapping
    public ResponseEntity <List<TrabajadorDtoResponse>>listarTodos() {
        return ResponseEntity.ok(trabajadorServices.listarTodos());
    }

    // 3. Consultar usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<TrabajadorDtoResponse> consultaId(
            @PathVariable Long id) {

        return ResponseEntity.ok(trabajadorServices.buscarPorId(id));
    }
    
    // 4. Consultar usuario por POST
    @PostMapping("/buscar")
    public ResponseEntity<TrabajadorDtoResponse> consultaIdpost(
            @RequestBody TrabajadorDto dto) {

        TrabajadorDtoResponse encontrado =
                trabajadorServices.buscarPorId(dto.getId());

        return ResponseEntity.ok(encontrado);
    }
    // 5. Actualizar usuario sin exponer el ID en la URL
    @PutMapping("/actualizar")
    public ResponseEntity<TrabajadorDtoResponse> actualizar(
            @RequestHeader("id") Long id,
            @RequestBody TrabajadorDto dto) {

        TrabajadorDtoResponse trabajadorActualizado =
                trabajadorServices.actualizar(id, dto);

        return ResponseEntity.ok(trabajadorActualizado);
    }

    // 6. Eliminar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        trabajadorServices.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}