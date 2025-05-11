package com.ti.estoque.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;


@Entity
@Table(name = "tipo_movimentacao")
@Data
public class TipoMovimentacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo_movimentacao;   

    @OneToMany(mappedBy = "tipoMovimentacao")
    private List<MovimentacaoEquipamento> equipamento_movimentacao;

    @OneToMany(mappedBy = "tipoMovimentacao")
    private List<MovimentacaoUtensilio> utensilio_movimentacao;

}
