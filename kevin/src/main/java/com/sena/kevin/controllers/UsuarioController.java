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

import com.sena.kevin.dtos.UsuarioDto;
import com.sena.kevin.services.UsuarioServices;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioServices usuarioServices;

    // 1. Registrar nuevo usuario
    @PostMapping
    public ResponseEntity<UsuarioDto> guardar(
            @Valid @RequestBody UsuarioDto usuarioDto) {

        UsuarioDto usuarioguardado = usuarioServices.guardar(usuarioDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usuarioguardado);
    }

    // 1.1 Registrar múltiples usuarios por lote
    @PostMapping("/lote")
    public ResponseEntity<List<UsuarioDto>> guardarTodos(
            @Valid @RequestBody List<UsuarioDto> usuarioDtos) {

        List<UsuarioDto> usuariosGuardados = usuarioServices.guardarTodos(usuarioDtos);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usuariosGuardados);
    }

    // 2. Listar todos los usuarios
    @GetMapping
    public ResponseEntity<List<UsuarioDto>> listarTodos() {
        return ResponseEntity.ok(usuarioServices.listarTodos());
    }

    // 3. Consultar usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> consultaId(
            @PathVariable Long id) {

        return ResponseEntity.ok(usuarioServices.buscarPorId(id));
    }

    // 4. Consultar usuario por POST
    @PostMapping("/buscar")
    public ResponseEntity<UsuarioDto> consultaIdpost(
            @RequestBody UsuarioDto dto) {

        UsuarioDto encontrado =
                usuarioServices.buscarPorId(dto.getId());

        return ResponseEntity.ok(encontrado);
    }

    // 5. Actualizar usuario sin exponer el ID en la URL
    @PutMapping("/actualizar")
    public ResponseEntity<UsuarioDto> actualizar(
            @RequestHeader("id") Long id,
            @RequestBody UsuarioDto dto) {

        UsuarioDto usuarioActualizado =
                usuarioServices.actualizar(id, dto);

        return ResponseEntity.ok(usuarioActualizado);
    }

    // 6. Eliminar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        usuarioServices.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}