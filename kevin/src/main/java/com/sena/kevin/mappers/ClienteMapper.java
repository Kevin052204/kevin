package com.sena.kevin.mappers;

import com.sena.kevin.dtos.ClienteDto;
import com.sena.kevin.models.usuarios.Cliente;

public interface ClienteMapper {

    //de Cliente a ClienteDto
    ClienteDto tDto (Cliente cliente);

    //De ClienteDto a Cliente 
    Cliente tCliente (ClienteDto clienteDto);

    Cliente toCliente(ClienteDto clientedDto);

}