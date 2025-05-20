package com.ti.estoque.dto;

import java.time.LocalDate;

import com.ti.estoque.enums.ModeloTrabalho;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioRequestDTO {

    private Long id;

    @NotBlank
    private String nome;

    private String cpf;

    @NotBlank
    private String email;

    @NotNull
    private boolean isAdmin;

    @NotBlank
    private String senha;

    private Long idCargo;

    private Long idDepartamento;

    private ModeloTrabalho modeloTrabalho;

    private Long idEscritorio;

    private LocalDate dataCadastro;

    @NotNull
    private boolean isAtivo;
}