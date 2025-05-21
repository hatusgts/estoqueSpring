package com.ti.estoque.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ti.estoque.model.Departamento;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
    
    List<Departamento> findByNomeDepartamentoIgnoreCaseContaining(String nome);

    @Query("SELECT d FROM Departamento d WHERE LOWER(d.nomeDepartamento) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Departamento> searchByNome(@Param("nome") String nome);

    List<Departamento> findByNomeDepartamentoIgnoreCaseLike(String departamento);

    List<Departamento> findByIdIn(List<Long> ids);

    List<Departamento> findByNomeDepartamentoIgnoreCaseIn(List<String> departamento);
}
