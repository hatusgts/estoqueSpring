package com.ti.estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ti.estoque.model.Cargo;
import java.util.List;
import java.util.Optional;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {
    Optional<Cargo> findByCargoContainingIgnoreCase(String cargo);

    List<Cargo> findByidIn(List<Long> ids);
}
