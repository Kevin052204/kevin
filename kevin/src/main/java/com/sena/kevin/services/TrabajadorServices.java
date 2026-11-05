package com.sena.kevin.services;

import java.util.List;

import com.sena.kevin.dtos.TrabajadorDto;

public interface TrabajadorServices {

    // Registrar un nuevo trabajador
    TrabajadorDto guardar(TrabajadorDto trabajadorDto);

    // Registrar varios trabajadores a la vez (por lote)
    List<TrabajadorDto> guardarTodos(List<TrabajadorDto> trabajadorDtos);

    // Listar todos los trabajadores
    List<TrabajadorDto> listarTodos();

    // Consultar trabajador por ID
    TrabajadorDto buscarPorId(Long id);

    // Actualizar trabajador
    TrabajadorDto actualizar(Long id, TrabajadorDto trabajadorDto);

    // Eliminar Trabajador
    void eliminar(Long id);
}