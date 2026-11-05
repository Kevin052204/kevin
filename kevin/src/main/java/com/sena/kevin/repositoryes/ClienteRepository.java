package com.sena.kevin.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.kevin.models.usuarios.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}

