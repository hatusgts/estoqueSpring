package com.ti.estoque.dto;

import com.ti.estoque.dto.validation.OnCreate;
import com.ti.estoque.dto.validation.OnUpdate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CargoRequestDTO {

    @NotNull(message = "O Id é nessessário para a atualização", groups=OnUpdate.class)
    private long id;
    
    @NotBlank(message="Cargo Obrigatório", groups={OnCreate.class,OnUpdate.class})
    private String nomeCargo;
}
