package com.ti.estoque.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "equipamento")
@Data
public class Equipamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, name = "numero_patrimonio")
    private String numeroPatrimonio;

    @ManyToOne
    @JoinColumn(name = "id_tipo_equipamento")
    private TipoEquipamento tipoEquipamento;

    @ManyToOne
    @JoinColumn(name = "id_marca")
    private Marca marca;

    @ManyToOne
    @JoinColumn(name = "id_modelo")
    private Modelo modelo;

    @Column(unique = true)
    private String serviceTag;

    @Column(name = "data_cadastro")
    private LocalDate dataCadastro;

    @OneToMany(mappedBy = "equipamento")
    private List<MovimentacaoEquipamento> movimentacaoEquipamentos;

}
