package com.ti.estoque.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ti.estoque.model.Departamento;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
    
    List<Departamento> findByNomeDepartamentoIgnoreCaseLike(String departamento);

    List<Departamento> findByIdIn(List<Long> ids);

    List<Departamento> findByNomeDepartamentoIgnoreCaseIn(List<String> departamento);
}
