package com.ti.estoque.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ti.estoque.model.TipoEquipamento;

@Repository
public interface TipoEquipamentoRepository extends JpaRepository <TipoEquipamento, Long> {

        List<TipoEquipamento> findByIdIn(List<Long> id);

        List<TipoEquipamento> findByDescricaoIgnoreCaseLike(String descricao);

        List<TipoEquipamento> findByDescricaoIgnoreCaseIn(List<String> descricao);
}
