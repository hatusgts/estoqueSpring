package com.ti.estoque.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModeloRequestDTO {

    private long id;
    
    @NotBlank
    private String nomeModelo;
}
