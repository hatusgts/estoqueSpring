package com.ti.estoque.dto;

import java.time.LocalDate;

import com.ti.estoque.enums.ModeloTrabalho;
import com.ti.estoque.model.Escritorio;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioRequestDTO {

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

    private Escritorio escritorio;

    private LocalDate dataCadastro;

    @NotNull
    private boolean isAtivo;
}
