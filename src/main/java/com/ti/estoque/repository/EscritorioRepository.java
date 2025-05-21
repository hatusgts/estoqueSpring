package com.ti.estoque.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ti.estoque.model.Escritorio;

@Repository
public interface EscritorioRepository extends JpaRepository<Escritorio, Long> {

    List<Escritorio> findByIdIn(List<Long> ids);

    List<Escritorio> findByNomeEscritorioContainingIgnoreCase(String nome);
    List<Escritorio> findByNomeEscritorioIgnoreCaseIn(List<String> escritorio);
    
}
