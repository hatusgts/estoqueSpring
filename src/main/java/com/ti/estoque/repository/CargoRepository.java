package com.ti.estoque.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ti.estoque.model.Cargo;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {
    Optional<Cargo> findByNomeCargoContainingIgnoreCaseLike(String cargo);

    List<Cargo> findByNomeCargoIgnoreCaseIn(List<String> cargo);
    List<Cargo> findByidIn(List<Long> ids);
}
