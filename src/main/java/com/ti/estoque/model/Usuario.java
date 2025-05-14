package com.ti.estoque.model;

import java.time.LocalDate;

import com.ti.estoque.enums.ModeloTrabalho;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
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

    @Enumerated(EnumType.STRING)
    private ModeloTrabalho modelo;

    @ManyToOne
    @JoinColumn(name = "id_escritorio")
    private Escritorio escritorio;

    private LocalDate data_cadastro;

    private boolean isAtivo;
    
}
