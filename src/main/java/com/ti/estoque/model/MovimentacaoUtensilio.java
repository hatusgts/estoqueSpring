package com.ti.estoque.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;


@Entity
@Table(name = "movimentacao_utensilio")
@Data
public class MovimentacaoUtensilio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "utensilio_id")
    private Utensilio utensilio;

    private int quantidade;

    @ManyToOne
    @JoinColumn(name = "tipo_movimentacao_id")
    private TipoMovimentacao tipoMovimentacao;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private LocalDate dataMovimentacao;
}
