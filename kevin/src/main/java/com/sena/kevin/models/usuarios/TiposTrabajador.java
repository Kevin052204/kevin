package com.sena.kevin.models.usuarios;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity 
@Table(name = "tipos_trabajador") 
@Getter
@NoArgsConstructor 


public class TiposTrabajador {

    @Id
    private Byte id;


    @Column(nullable = false, unique = true)
    private String nom_TipoTrab; 
}
