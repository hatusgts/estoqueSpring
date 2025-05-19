package com.ti.estoque.dto;

import java.time.LocalDate;

import com.ti.estoque.enums.TipoMovimentacao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovimentacaoEquipamentoRequestDTO {
    
    @NotNull
    private Long idEquipamento;

    @NotBlank(message = "O tipo de movimentação é obrigatório")
    private TipoMovimentacao tipoMovimentacao;

    @NotNull
    private Long idUsuario;

    private LocalDate dataMovimentacao;
}
