package com.ti.estoque.dto;

import com.ti.estoque.dto.validation.OnCreate;
import com.ti.estoque.dto.validation.OnUpdate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MarcaRequestDTO {

    @NotNull(message="Id não pode ser vazio para atualização", groups=OnUpdate.class)
    private Long id;
    
    @NotBlank(message="nomeMarca não pode ser vazio", groups={OnUpdate.class, OnCreate.class})
    private String nomeMarca;
}
