package com.ti.estoque.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name  = "cargo")
@Data
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cargo;

    @OneToMany(mappedBy = "cargo")
    private List<Usuario> usuarios;

}
