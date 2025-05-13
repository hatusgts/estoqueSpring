package com.ti.estoque.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ti.estoque.model.Departamento;

public interface DepartamentoRespository extends JpaRepository<Departamento, Long> {
    
    Optional<Departamento> findByDepartamentoIgnoreCaseLike(String departamento);

    List<Departamento> findByIdIn(List<Long> ids);

    List<Departamento> findByDepartamentoIn(List<String> departamento);
}
