package com.ti.estoque.dto;

import com.ti.estoque.dto.validation.OnCreate;
import com.ti.estoque.dto.validation.OnUpdate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EscritorioRequestDTO {

    @NotNull(message="Id não pode ser nulo para Update", groups=OnUpdate.class)
    private Long id;
    
    @NotBlank(message="Nome para cadastro Não pode ser Nulo", groups={OnCreate.class, OnUpdate.class})
    private String nomeEscritorio;
}
