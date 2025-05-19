package com.ti.estoque.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EquipamentoRequestDTO {

    @NotBlank
    private String numeroPatrimonio;

    @NotNull
    private Long tipoEquipamentoID;

    @NotNull
    private Long idMarca;

    @NotNull
    private Long idModelo;

    @NotBlank
    private String serviceTag;

    private LocalDate data;
}
    