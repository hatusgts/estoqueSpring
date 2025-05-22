package com.ti.estoque.dto;

import com.ti.estoque.dto.validation.OnCreate;
import com.ti.estoque.dto.validation.OnUpdate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TipoEquipamentoRequestDTO {

    @NotNull(message="ID não pode ser Nulo para Update", groups=OnUpdate.class)
    private Long id;

    @NotBlank(message="descricao não pode ser Nulo", groups={OnUpdate.class, OnCreate.class})
    private String descricao;
    
}
