package com.sena.kevin.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sena.kevin.dtos.UsuarioDto;
import com.sena.kevin.models.usuarios.Usuario;

@Mapper (componentModel = "spring")
public interface UsuarioMapper {

    //convertir de entidad a Dto
    @Mapping (source = "nom", target = "nomb")
    @Mapping (source = "ape", target = "apell")
    @Mapping (source = "dir", target = "direc")
    @Mapping (source = "tel", target = "tele")
    UsuarioDto ConvierteaDto(Usuario usuario);

    
    //convertir de Dto a entidad
    @Mapping (source = "nomb", target = "nom")
    @Mapping (source = "apell", target = "ape")
    @Mapping (source = "direc", target = "dir")
    @Mapping (source = "tele", target = "tel")
    Usuario ConvierteaEntidad(UsuarioDto usuariodDto);

}