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
@Table(name = "marca")
@Data
public class Marca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeMarca;

    @OneToMany(mappedBy = "marca")
    private List<Equipamento> equipamentos;

    @OneToMany(mappedBy = "marca")
    private List<Utensilio> utensilios;
}
