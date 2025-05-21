package com.ti.estoque.dto;

import java.time.LocalDate;

import com.ti.estoque.dto.validation.OnCreate;
import com.ti.estoque.dto.validation.OnUpdate;
import com.ti.estoque.enums.TipoMovimentacao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovimentacaoEquipamentoRequestDTO {

    @NotNull(message="Id não pode ser nulo Ao alterar", groups=OnUpdate.class)
    private Long id;
    
    @NotNull(message="IdEquipamento não pode ser nulo", groups={OnCreate.class,OnUpdate.class})
    private Long idEquipamento;

    @NotBlank(message="Tipo de Movimentação é obrigatório", groups={OnCreate.class,OnUpdate.class})
    private TipoMovimentacao tipoMovimentacao;

    @NotNull(message="IdUsuario não pode ser nulo", groups={OnCreate.class,OnUpdate.class})
    private Long idUsuario;

    private LocalDate dataMovimentacao;
}
