package com.ti.estoque.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EscritorioRequestDTO {
    @NotBlank
    private String nomeEscritorio;
}
