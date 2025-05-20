package com.ti.estoque.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ti.estoque.model.Cargo;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {
    List<Cargo> findByNomeCargoIgnoreCaseLike(String cargo);

    List<Cargo> findByNomeCargoIgnoreCaseContaining(String nome);
    
    List<Cargo> findByIdIn(List<Long> ids);

    List<Cargo> findByNomeCargoIgnoreCaseIn(List<String> cargo);

}