package com.sena.kevin.services;

import java.util.List;

import com.sena.kevin.dtos.ClienteDto;
import com.sena.kevin.dtos.ClienteDtoResponse;

public interface ClienteServices {

    // Registrar un nuevo cliente
    ClienteDtoResponse guardar(ClienteDto clienteDto);

    // Listar todos los clientes
    List<ClienteDtoResponse> listarTodos();

    // Consultar cliente por ID
    ClienteDtoResponse buscarPorId(Long id);

    // Actualizar cliente
    ClienteDtoResponse actualizar(Long id, ClienteDto clienteDto);

    // Eliminar cliente
    void eliminar(Long id);

}