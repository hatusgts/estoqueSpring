package com.ti.estoque.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "usuario")
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cpf;
    private String email;
    public boolean isAdmin;

    @ManyToOne
    @JoinColumn (name = "id_cargo")
    private Cargo cargo;

    @ManyToOne
    @JoinColumn(name = "id_departamento")
    private Departamento departamento;

    @ManyToOne
    @JoinColumn(name = "id_modelo_trabalho")
    private ModeloTrabalho modeloTrabalho;

    @ManyToOne
    @JoinColumn(name = "id_escritorio")
    private Escritorio escritorio;

    private LocalDate data_cadastro;

    private boolean isAtivo;
    
}
