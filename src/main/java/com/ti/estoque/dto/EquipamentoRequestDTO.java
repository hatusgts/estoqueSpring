package com.ti.estoque.dto;

import java.time.LocalDate;

import com.ti.estoque.dto.validation.OnCreate;
import com.ti.estoque.dto.validation.OnUpdate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EquipamentoRequestDTO {

    @NotNull(message = "O ID é obrigatório para atualização", groups = OnUpdate.class)
    private long id;

    @NotBlank(message = "Numero do patrimonio é obrigatório", groups = {OnCreate.class, OnUpdate.class})
    private String numeroPatrimonio;

    @NotNull(message = "Tipo de equipamento é obrigatório", groups = {OnCreate.class, OnUpdate.class})
    private Long tipoEquipamentoID;

    @NotNull(message = "Marca é obrigatório", groups = {OnCreate.class, OnUpdate.class})
    private Long idMarca;

    @NotNull(message = "Modelo é obrigatório", groups = {OnCreate.class, OnUpdate.class})
    private Long idModelo;

    @NotBlank(message = "ServiceTag é obrigatório", groups = {OnCreate.class, OnUpdate.class})
    private String serviceTag;

    private LocalDate data;
}
    