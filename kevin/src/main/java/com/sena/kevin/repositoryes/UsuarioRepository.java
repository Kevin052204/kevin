package com.sena.kevin.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.kevin.models.usuarios.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}