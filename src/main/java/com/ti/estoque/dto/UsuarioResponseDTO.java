package com.ti.estoque.dto;

import java.time.LocalDate;

import com.ti.estoque.enums.ModeloTrabalho;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioResponseDTO {
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String Cargo;
    private String departamento;
    private ModeloTrabalho modeloTrabalho;
    private String escritorio;
    private LocalDate dataCadastro;
    private boolean isAtivo;
}
