package com.sena.kevin.repositoryes;

import com.sena.kevin.models.usuarios.TiposTrabajador;

import org.springframework.data.jpa.repository.JpaRepository;


public interface TipostrabajadorRepository extends JpaRepository<TiposTrabajador, Byte> {
}