package com.ti.estoque.model;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;


@Entity
@Table(name = "modelo")
@Data
public class Modelo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String modelo;

    @OneToMany(mappedBy = "modelo")
    private List<Equipamento> equipamentos;

    @OneToMany(mappedBy = "modelo")
    private List<Utensilio> utensilios;
}
