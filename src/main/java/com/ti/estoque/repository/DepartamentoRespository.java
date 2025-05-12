package com.ti.estoque.repository;
import com.ti.estoque.model.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface DepartamentoRespository extends JpaRepository<Departamento, Long> {

    Optional<Departamento> findByIdIn(List<Long> ids);

    List<Departamento> findByDepartamento(String departamento);
}
