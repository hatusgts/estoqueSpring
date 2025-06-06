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
@Table(name = "modelo")
@Data
public class Modelo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeModelo;

    @OneToMany(mappedBy = "modelo")
    private List<Equipamento> equipamentos;

    @OneToMany(mappedBy = "modelo")
    private List<Utensilio> utensilios;
}
