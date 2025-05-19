package com.ti.estoque.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovimentacaoUtensilioResponseDTO {
    private Long id;
    private String utensilio;
    private int quantidade; 
    private String tipoMovimentacao;
    private String usuario;
    private LocalDate dataMovimentacao;
}
