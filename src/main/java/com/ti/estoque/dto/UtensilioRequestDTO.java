package com.ti.estoque.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UtensilioRequestDTO {
    @NotNull
    private Long idTipoEquipamento;

    @NotNull
    private Long idMarca;

    @NotNull
    private Long idModelo;

}
