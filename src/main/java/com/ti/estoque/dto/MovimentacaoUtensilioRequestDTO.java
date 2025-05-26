package com.ti.estoque.dto;

import java.time.LocalDate;

import com.ti.estoque.dto.validation.OnCreate;
import com.ti.estoque.dto.validation.OnUpdate;
import com.ti.estoque.enums.TipoMovimentacao;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovimentacaoUtensilioRequestDTO {

    @NotNull(message="Id não pode ser nulo ao Editar", groups=OnUpdate.class)
    private Long id;

    @NotNull(message="IdUtensilio não pode ser nulo", groups={OnUpdate.class,OnCreate.class})
    private Long idUtensilio;

    @Min(1)
    @NotNull(message="Quantidade não pode ser nulo", groups={OnUpdate.class,OnCreate.class})
    private Integer quantidade;

    @NotNull(message = "O tipo de movimentação é obrigatório", groups={OnUpdate.class,OnCreate.class})
    private TipoMovimentacao tipoMovimentacao;

    @NotNull(message = "IdUsuario é obrigatório", groups={OnUpdate.class,OnCreate.class})
    private Long idUsuario;

    private LocalDate dataMovimentacao;
}
