package com.ti.estoque.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "tipo_equipamento")
@Data
public class TipoEquipamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String equipamento;

    private boolean patrimonio;

    @OneToMany(mappedBy = "tipoEquipamento")
    private List<Equipamento> equipamentos;

    @OneToMany(mappedBy = "tipoEquipamento")
    private List<Utensilio> utensilios;

}
