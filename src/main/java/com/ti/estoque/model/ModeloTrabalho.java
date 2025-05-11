package com.ti.estoque.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "modelo_trabalho")
@Data
public class ModeloTrabalho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String modeloTrabalho;

    @OneToMany(mappedBy = "modeloTrabalho")
    private List<Usuario> usuarios;

}
