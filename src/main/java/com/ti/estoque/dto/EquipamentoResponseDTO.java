package com.ti.estoque.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EquipamentoResponseDTO {
    private Long id;

    private String numeroPatrimonio;

    private String tipoEquipamento;

    private String marca;

    private String modelo;

    private String serviceTag;

    private LocalDate dataCadastro;
}
