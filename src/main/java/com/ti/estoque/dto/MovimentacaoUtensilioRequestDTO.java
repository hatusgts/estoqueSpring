package com.ti.estoque.dto;

import java.time.LocalDate;

import com.ti.estoque.enums.TipoMovimentacao;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovimentacaoUtensilioRequestDTO {

    private long id;

    @NotNull
    private Long idUtensilio;

    @Min(1)
    private int quantidade;

    @NotBlank(message = "O tipo de movimentação é obrigatório")
    private TipoMovimentacao tipoMovimentacao;

    @NotNull
    private Long idUsuario;

    private LocalDate dataMovimentacao;
}
