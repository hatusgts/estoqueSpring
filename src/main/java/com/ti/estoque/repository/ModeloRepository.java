package com.ti.estoque.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ti.estoque.model.Modelo;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Long>{
    List<Modelo> findByNomeModeloIgnoreCaseLike(String modelo);
    List<Modelo> findByNomeModeloIgnoreCaseIn(List<String> modelo);
}
