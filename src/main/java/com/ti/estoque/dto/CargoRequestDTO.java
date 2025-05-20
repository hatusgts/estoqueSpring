package com.ti.estoque.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CargoRequestDTO {

    private long id;
    
    @NotBlank(message="Cargo Obrigatório")
    private String nomeCargo;
}
