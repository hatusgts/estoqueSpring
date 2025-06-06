package com.ti.estoque.model;

import java.time.LocalDate;
import java.util.List;

import com.ti.estoque.enums.ModeloTrabalho;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuario")
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cpf;
    private String email;
    public boolean isAdmin;

    private String senha;

    @ManyToOne
    @JoinColumn(name = "id_cargo")
    private Cargo cargo;

    @ManyToOne
    @JoinColumn(name = "id_departamento")
    private Departamento departamento;

    @Enumerated(EnumType.STRING)
    private ModeloTrabalho modelo;

    @ManyToOne
    @JoinColumn(name = "id_escritorio")
    private Escritorio escritorio;

    private LocalDate dataCadastro;

    private boolean isAtivo;

    @OneToMany(mappedBy="usuario")
    private List<MovimentacaoEquipamento> usuario;

    @OneToMany(mappedBy="admin")
    private List<MovimentacaoEquipamento> admins;

}
