package com.ti.estoque.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tipo_equipamento")
@Data
public class TipoEquipamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    private boolean patrimonio;

    @OneToMany(mappedBy = "tipoEquipamento")
    private List<Equipamento> equipamentos;

    @OneToMany(mappedBy = "tipoEquipamento")
    private List<Utensilio> utensilios;

}
