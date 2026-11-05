package com.sena.kevin.repositoryes;


import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.kevin.models.usuarios.Trabajador;

public interface TrabajadorRepository extends JpaRepository<Trabajador, Long>{

}
