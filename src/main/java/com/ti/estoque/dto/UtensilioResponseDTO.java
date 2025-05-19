package com.ti.estoque.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UtensilioResponseDTO {
    private Long id;
    private String tipoEquipamento;
    private String marca;
    private String modelo;
}
