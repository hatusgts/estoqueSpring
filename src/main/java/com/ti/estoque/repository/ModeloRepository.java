package com.ti.estoque.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ti.estoque.model.Modelo;

public interface ModeloRepository extends JpaRepository<Modelo, Long>{
    Optional<Modelo> findByModeloIgnoreCaseLike(String modelo);
    List<Modelo> findByModeloIgnoreCaseIn(List<String> modelo);
}
