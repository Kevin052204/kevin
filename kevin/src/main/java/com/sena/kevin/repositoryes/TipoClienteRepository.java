package com.sena.kevin.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.kevin.models.usuarios.TiposCliente;

public interface TipoClienteRepository extends JpaRepository <TiposCliente, Byte> {

}
