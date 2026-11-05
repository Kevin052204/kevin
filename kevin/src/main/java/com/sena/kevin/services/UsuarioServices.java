package com.sena.kevin.services;

import java.util.List;
import com.sena.kevin.dtos.UsuarioDto;

public interface UsuarioServices {

    // Registrar un nuevo usuario
    UsuarioDto guardar(UsuarioDto usuarioDto);

    // Registrar múltiples usuarios por lote
    List<UsuarioDto> guardarTodos(List<UsuarioDto> usuarioDtos);

    // Listar todos los usuarios
    List<UsuarioDto> listarTodos();

    // Consultar usuario por ID
    UsuarioDto buscarPorId(long id);

    // Actualizar usuario
    UsuarioDto actualizar(Long id, UsuarioDto usuarioDto);

    // Eliminar usuario
    void eliminar(Long id);

}