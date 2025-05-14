package com.ti.estoque.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ti.estoque.model.TipoEquipamento;

public interface TipoEquipamentoRepository extends JpaRepository <TipoEquipamento, Long> {
        Optional<TipoEquipamento> findByDescricaoIgnoreCaseLike(String descricao);

        List<TipoEquipamento> findByDescricaoIgnoreCaseIn(List<String> descricao);
}
