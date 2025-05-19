package com.ti.estoque.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovimentacaoEquipamentoResponseDTO {
    private Long idMovimentacao;
    private String equipamento;
    private String TipoMovimentacao;
    private String usuario;
    private LocalDate dataMovimentacao;
}