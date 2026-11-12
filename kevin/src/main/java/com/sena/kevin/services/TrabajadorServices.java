package com.sena.kevin.services;

import java.util.List;

import com.sena.kevin.dtos.TrabajadorDto;
import com.sena.kevin.dtos.TrabajadorDtoResponse;

public interface TrabajadorServices {

    // Registrar un nuevo trabajador
    TrabajadorDtoResponse guardar(TrabajadorDto trabajadorDto);

    // Listar todos los cliente
    List<TrabajadorDtoResponse> listarTodos();

    // Consultar usuario por ID
    TrabajadorDtoResponse buscarPorId(Long id);

    // Actualizar usuario
    TrabajadorDtoResponse actualizar(Long id, TrabajadorDto trabajadorDto);

    // Eliminar usuario
    void eliminar(Long id);
}