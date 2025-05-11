package com.ti.estoque.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "departamento")
@Data
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String departamento;

    @OneToMany(mappedBy = "departamento")
    private List<Usuario> usuarios;

}
