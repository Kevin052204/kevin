package com.sena.kevin.mappers;

import com.sena.kevin.dtos.ClienteDto;
import com.sena.kevin.dtos.ClienteDtoResponse;
import com.sena.kevin.models.usuarios.Cliente;

public interface ClienteMapper {

    //de Cliente a ClienteDto
    ClienteDtoResponse toDto (Cliente cliente);

    //De ClienteDto a Cliente 
    Cliente toCliente (ClienteDto clienteDto);

}