package com.sena.kevin.services;

import java.util.List;

import com.sena.kevin.dtos.ClienteDto;

public interface ClienteServices {

    // Registrar un nuevo cliente
    ClienteDto guardar(ClienteDto clienteDto);

    // Registrar varios clientes a la vez (por lote)
    List<ClienteDto> guardarTodos(List<ClienteDto> clienteDtos);

    // Listar todos los cliente
    List<ClienteDto> listarTodos();

    // Consultar cliente por ID
    ClienteDto buscarPorId(Long id);

    // Actualizar cliente
    ClienteDto actualizar(Long id, ClienteDto clienteDto);

    // Eliminar cliente
    void eliminar(Long id);

}