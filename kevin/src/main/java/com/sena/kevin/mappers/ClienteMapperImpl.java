package com.sena.kevin.mappers;

import org.springframework.stereotype.Component;

import com.sena.kevin.dtos.ClienteDto;
import com.sena.kevin.models.usuarios.Cliente;

@Component 
public class ClienteMapperImpl implements ClienteMapper {

    @Override
    public ClienteDto tDto(Cliente cliente) {
        if (cliente == null) {
            return null;
        }

        // Creamos el DTO usando el constructor por defecto o setters temporalmente 
        // para aislar si el fallo viene estrictamente del Builder de Lombok:
        ClienteDto dto = new ClienteDto();
        dto.setId(cliente.getId());
        dto.setNomb(cliente.getNom());
        dto.setApell(cliente.getApe());
        dto.setDirec(cliente.getDir());
        dto.setTele(cliente.getTel());
        dto.setTipcli(cliente.getTipoCliente());
        dto.setPts(cliente.getPuntos());

        return dto;
    }

    @Override 
    public Cliente toCliente(ClienteDto clientedDto) {
        if (clientedDto == null) {
            return null;
        }

        Cliente cliente = new Cliente();
        cliente.setId(clientedDto.getId());
        cliente.setNom(clientedDto.getNomb());
        cliente.setApe(clientedDto.getApell());
        cliente.setDir(clientedDto.getDirec());
        cliente.setTel(clientedDto.getTele());
        cliente.setTipoCliente(clientedDto.getTipcli());
        cliente.setPuntos(clientedDto.getPts());

        return cliente;
    }

    @Override
    public Cliente tCliente(ClienteDto clienteDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'tCliente'");
    }
}