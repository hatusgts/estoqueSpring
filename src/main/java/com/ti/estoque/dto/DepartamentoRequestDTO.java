package com.ti.estoque.dto;

import com.ti.estoque.dto.validation.OnCreate;
import com.ti.estoque.dto.validation.OnUpdate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartamentoRequestDTO {

    @NotNull(message="Id não pode ser nulo para atualização", groups=OnUpdate.class)
    private long id;
    
    @NotBlank(message="Nome do departamento não pode ser nulo",groups={OnUpdate.class,OnCreate.class})
    private String nomeDepartamento;
}
