package com.sena.kevin.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sena.kevin.dtos.TrabajadorDto;
import com.sena.kevin.dtos.TrabajadorDtoResponse;
import com.sena.kevin.models.usuarios.Trabajador;


@Mapper(componentModel = "spring")
public interface TrabajadorMapper {

    // Convierte de Entidad a Dto
    // Trabajador a trabajadorDTO
    @Mapping(source = "nomb", target = "nom")
    @Mapping(source = "apell", target = "ape")
    @Mapping(source = "direc", target = "dir")
    @Mapping(source = "tele", target = "tel")
    @Mapping(target = "cargo" , ignore = true)
    Trabajador toEntidad(TrabajadorDto trabajadorDto);

    // Convierte de Dto a Entidad
    // TrabajadorDto a Trabajador
    @Mapping(source = "nom", target = "nomb") 
    @Mapping(source = "ape", target = "apell") 
    @Mapping(source = "dir", target = "direc")
    @Mapping(source = "tel", target = "tele")
    @Mapping (source = "cargo.nom_TipoTrab", target = "cargo")
    TrabajadorDtoResponse toDto(Trabajador trabajador);

    

}