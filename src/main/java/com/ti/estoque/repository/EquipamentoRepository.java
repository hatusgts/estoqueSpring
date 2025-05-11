package com.ti.estoque.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ti.estoque.model.Equipamento;
import java.util.List;
import java.time.LocalDate;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {

    //Buscando Individual

    List<Equipamento> findByNumeroPatrimonioContainingIgnoreCase(String numeroPatrimonio);
    
    List<Equipamento> findByTipoEquipamentoEquipamento(String tipoEquipamento);

    List<Equipamento> findByMarcaMarca(String marca);

    List<Equipamento> findByModeloModelo(String modelo);

    List<Equipamento> findByServiceTag(String service_tag);

    //Buscando por Lista

    List<Equipamento> findByTipoEquipamentoEquipamentoIn(List<String> equipamentos);

    List<Equipamento> findByMarcaMarcaIn(List<String> marca);

    List<Equipamento> findByModeloModeloIn(List<String> modelo);

    List<Equipamento> findByServiceTagIn(List<String> service_tag);

    //Buscando por Datas
    
    List<Equipamento> findByDataCadastro(LocalDate date);
    List<Equipamento> findByDataCadastroBefore(LocalDate date);
    List<Equipamento> findByDataCadastroAfter(LocalDate date);
    List<Equipamento> findByDataCadastroBetween(LocalDate startDate, LocalDate endDate);


}
