package com.ti.estoque.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TipoEquipamentoRequestDTO {

    private Long id;

    @NotBlank
    private String descricao;
    
}
