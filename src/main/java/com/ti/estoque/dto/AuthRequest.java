package com.ti.estoque.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AuthRequest {

    @NotBlank(message="Email obrigatório")
    private String email;
    @NotBlank(message="Senha obrigatório")
    private String senha;
}
