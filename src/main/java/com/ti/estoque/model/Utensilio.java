package com.ti.estoque.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;


@Entity
@Table(name = "utensilio")
@Data
public class Utensilio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_tipo_equipamento")
    private TipoEquipamento tipoEquipamento;

    @ManyToOne
    @JoinColumn(name = "id_marca")
    private Marca marca;

    @ManyToOne
    @JoinColumn(name = "id_modelo")
    private Modelo modelo;

    @OneToMany(mappedBy = "utensilio")
    private List<MovimentacaoUtensilio> movimentacaoUtensilios;

}
