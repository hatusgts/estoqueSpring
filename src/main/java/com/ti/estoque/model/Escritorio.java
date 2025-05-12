package com.ti.estoque.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "escritorio")
@Data
public class Escritorio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String escritorio;

    @OneToMany(mappedBy = "escritorio")
    private List<Usuario> usuarios;

}
