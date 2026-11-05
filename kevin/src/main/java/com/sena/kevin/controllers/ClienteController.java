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

import com.sena.kevin.dtos.ClienteDto;
import com.sena.kevin.services.ClienteServices;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteServices clienteServices;

    // 1. Registrar nuevo cliente
    @PostMapping
    public ResponseEntity<ClienteDto> guardar(@Valid @RequestBody ClienteDto clienteDto) {
        ClienteDto clienteguardado = clienteServices.guardar(clienteDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clienteguardado);
    }

    // 1.1 Registrar múltiples clientes a la vez (Lote)
    @PostMapping("/lote")
    public ResponseEntity<List<ClienteDto>> guardarTodos(@Valid @RequestBody List<ClienteDto> clienteDtos) {
        List<ClienteDto> clientesGuardados = clienteServices.guardarTodos(clienteDtos);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clientesGuardados);
    }

    // 2. Listar todos los clientes
    @GetMapping
    public ResponseEntity<List<ClienteDto>> listarTodos() {
        return ResponseEntity.ok(clienteServices.listarTodos());
    }

    // 3. Consultar cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> consultarId(@PathVariable Long id) {
        return ResponseEntity.ok(clienteServices.buscarPorId(id));
    }

    // 4. Consultar cliente por POST
    @PostMapping("/buscar")
    public ResponseEntity<ClienteDto> consultarIdpost(@RequestBody ClienteDto dto) {
        ClienteDto encontrado = clienteServices.buscarPorId(dto.getId());

        return ResponseEntity.ok(encontrado);
    }

    // 5. Actualizar cliente sin exponer el ID en la URL
    @PutMapping("/actualizar")
    public ResponseEntity<ClienteDto> actualizar(
            @RequestHeader("id") Long id,
            @RequestBody ClienteDto dto) {

        ClienteDto clienteActualizado = clienteServices.actualizar(id, dto);

        return ResponseEntity.ok(clienteActualizado);
    }

    // 6. Eliminar cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        clienteServices.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}