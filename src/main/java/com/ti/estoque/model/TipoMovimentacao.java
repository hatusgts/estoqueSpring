package com.ti.estoque.model;

import java.util.List;

import com.ti.estoque.enums.FatorMovimentacao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tipo_movimentacao")
@Data
public class TipoMovimentacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoMovimentacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FatorMovimentacao fator;

    @OneToMany(mappedBy = "tipoMovimentacao")
    private List<MovimentacaoEquipamento> equipamento_movimentacao;

    @OneToMany(mappedBy = "tipoMovimentacao")
    private List<MovimentacaoUtensilio> utensilio_movimentacao;

}
