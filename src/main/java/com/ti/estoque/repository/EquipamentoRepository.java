package com.ti.estoque.repository;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ti.estoque.model.Equipamento;

@Repository
public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {

    //Buscando por Lista
    List<Equipamento> findByNumeroPatrimonioContainingIgnoreCase(String patrimonio);
    List<Equipamento> findByNumeroPatrimonioIgnoreCaseIn(List<String> numeroPatrimonio);
    
    List<Equipamento> findByTipoEquipamentoDescricaoContainingIgnoreCase(String descricao);
    List<Equipamento> findByTipoEquipamentoDescricaoIgnoreCaseIn(List<String> equipamentos);

    List<Equipamento> findByMarcaNomeMarcaContainingIgnoreCase(String nomeMarca);
    List<Equipamento> findByMarcaNomeMarcaIgnoreCaseIn(List<String> marca);

    List<Equipamento> findByModeloNomeModeloContainingIgnoreCase(String nomeModelo);
    List<Equipamento> findByModeloNomeModeloIgnoreCaseIn(List<String> modelo);

    List<Equipamento> findByServiceTagContainingIgnoreCase(String serviceTag);
    List<Equipamento> findByServiceTagIgnoreCaseIn(List<String> serviceTag);

    //Buscando por Datas
    
    List<Equipamento> findByDataCadastro(LocalDate date);
    List<Equipamento> findByDataCadastroBefore(LocalDate date);
    List<Equipamento> findByDataCadastroAfter(LocalDate date);
    List<Equipamento> findByDataCadastroBetween(LocalDate startDate, LocalDate endDate);
}