package com.sena.kevin.mappers;

import org.springframework.stereotype.Component;

import com.sena.kevin.dtos.ClienteDto;
import com.sena.kevin.models.usuarios.Cliente;
import com.sena.kevin.dtos.ClienteDtoResponse;

@Component
public class ClienteMapperImpl implements ClienteMapper {

    @Override
    public ClienteDtoResponse toDto(Cliente cliente){

        //validad que tenga datos
        if (cliente == null) {
            return  null;

        }

        //convertir de cliente a  clienteDto
        return  ClienteDtoResponse.builder()
        //atribitos de usuarioDto
        .id(cliente.getId())
        .nomb(cliente.getNom())
        .apell(cliente.getApe())
        .direc(cliente.getDir())
        .tele(cliente.getTel())

         //atributos de clienteDto
        .tipCli(cliente.getTipoCliente().getNom_TipoCli())
        .pts(cliente.getPuntos())
        .build();
    }

        @Override
        public Cliente toCliente(ClienteDto clienteDto){

        //validad que tenga datos
        if (clienteDto == null) {
            return null;

        }

        return  Cliente.builder()
         //atributos de UsuarioDto
        .id(clienteDto.getId())
        .nom(clienteDto.getNomb())
        .ape(clienteDto.getApell())
        .dir(clienteDto.getDirec())
        .tel(clienteDto.getTele())


        //No es necesario mapear el tipo de cliente ya que tiene relacion con la entidad TiposCliente
        .puntos(clienteDto.getPts())
        .build();

    }
}

