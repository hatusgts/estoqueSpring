package com.ti.estoque.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ti.estoque.model.Marca;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {
    List<Marca> findByNomeMarcaIgnoreCaseLike(String marca);

    List<Marca> findByNomeMarcaIgnoreCaseIn(List<String> marca);
}
