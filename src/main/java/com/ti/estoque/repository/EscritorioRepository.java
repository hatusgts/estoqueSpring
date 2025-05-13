package com.ti.estoque.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ti.estoque.model.Escritorio;

public interface EscritorioRepository extends JpaRepository<Escritorio, Long> {
    
    Optional<Escritorio> findByEscritorioIgnoreCaseLike(String escritorio);

    List<Escritorio> findByEscritorioIgnoreCaseIn(List<String> escritorio);
    
}
