package com.ti.estoque.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "marca")
@Data
public class Marca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String marca;

    @OneToMany(mappedBy = "marca")
    private List<Equipamento> equipamentos;

    @OneToMany(mappedBy = "marca")
    private List<Utensilio> utensilios;
}
