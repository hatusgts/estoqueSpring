package com.ti.estoque.dto;

import com.ti.estoque.dto.validation.OnCreate;
import com.ti.estoque.dto.validation.OnUpdate;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UtensilioRequestDTO {

    @NotNull(message="Id não pode ser nulo para Update", groups=OnUpdate.class)
    private Long id;

    @NotNull(message="Tipo de equipamento não pode ser nulo", groups={OnUpdate.class,OnCreate.class})
    private Long idTipoEquipamento;

    @NotNull(message="idMarca não pode ser nulo", groups={OnUpdate.class,OnCreate.class})
    private Long idMarca;

    @NotNull(message="idModelo não pode ser nulo", groups={OnUpdate.class,OnCreate.class})
    private Long idModelo;
}
