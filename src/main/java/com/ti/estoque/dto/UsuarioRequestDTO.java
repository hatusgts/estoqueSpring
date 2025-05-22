package com.ti.estoque.dto;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import com.ti.estoque.dto.validation.OnCreate;
import com.ti.estoque.dto.validation.OnUpdate;
import com.ti.estoque.enums.ModeloTrabalho;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioRequestDTO {

    @NotNull(message="Id não pode ser nulo em Update", groups=OnUpdate.class)
    private Long id;

    @NotBlank(message="Nome não pode ser nulo", groups={OnUpdate.class, OnCreate.class})
    private String nome;

    @CPF(message="CPF inválido")
    private String cpf;

    @NotBlank(message="Email não pode ser nulo", groups={OnUpdate.class, OnCreate.class})
    private String email;

    @NotNull
    private boolean isAdmin;

    @NotBlank(message="Senha não pode ser nulo", groups={OnCreate.class})
    private String senha;

    private Long idCargo;

    private Long idDepartamento;

    private ModeloTrabalho modeloTrabalho;

    private Long idEscritorio;

    private LocalDate dataCadastro;

    @NotNull(message="Ativo não pode ser nulo", groups={OnUpdate.class, OnCreate.class})
    private boolean isAtivo;
}