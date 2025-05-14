package com.ti.estoque.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ti.estoque.model.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Long> {
    Optional<Marca> findByNomeMarcaIgnoreCaseLike(String marca);

    List<Marca> findByNomeMarcaIgnoreCaseIn(List<String> marca);
}
