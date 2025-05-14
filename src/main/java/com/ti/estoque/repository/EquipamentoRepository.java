package com.ti.estoque.repository;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ti.estoque.model.Equipamento;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {

    //Buscando Individual

    List<Equipamento> findByNumeroPatrimonioIgnoreCaseLike(String numeroPatrimonio);
    
    List<Equipamento> findByTipoEquipamentoDescricaoIgnoreCaseLike(String tipoEquipamento);

    List<Equipamento> findByMarcaNomeMarcaIgnoreCaseLike(String marca);

    List<Equipamento> findByModeloNomeModeloIgnoreCaseLike(String modelo);

    List<Equipamento> findByServiceTagIgnoreCaseLike(String service_tag);

    //Buscando por Lista

    List<Equipamento> findByTipoEquipamentoDescricaoIgnoreCaseIn(List<String> equipamentos);

    List<Equipamento> findByMarcaNomeMarcaIgnoreCaseIn(List<String> marca);

    List<Equipamento> findByModeloNomeModeloIgnoreCaseIn(List<String> modelo);

    List<Equipamento> findByServiceTagIgnoreCaseIn(List<String> service_tag);

    //Buscando por Datas
    
    List<Equipamento> findByDataCadastro(LocalDate date);
    List<Equipamento> findByDataCadastroBefore(LocalDate date);
    List<Equipamento> findByDataCadastroAfter(LocalDate date);
    List<Equipamento> findByDataCadastroBetween(LocalDate startDate, LocalDate endDate);


}
