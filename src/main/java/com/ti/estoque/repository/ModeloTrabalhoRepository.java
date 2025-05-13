package com.ti.estoque.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ti.estoque.model.ModeloTrabalho;

public interface ModeloTrabalhoRepository extends JpaRepository <ModeloTrabalho, Long>{
    Optional<ModeloTrabalho> findByModeloTrabalhoIgnoreCaseLike(String modeloTrabalho);
    List<ModeloTrabalho> findByModeloTrabalhoIgnoreCaseIn(List<String> modeloTrabalho);
}
