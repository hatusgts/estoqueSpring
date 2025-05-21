package com.ti.estoque.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ti.estoque.dto.MovimentacaoEquipamentoRequestDTO;
import com.ti.estoque.dto.MovimentacaoEquipamentoResponseDTO;
import com.ti.estoque.enums.TipoMovimentacao;
import com.ti.estoque.mappers.MovimentacaoEquipamentoMapper;
import com.ti.estoque.model.MovimentacaoEquipamento;
import com.ti.estoque.repository.EquipamentoRepository;
import com.ti.estoque.repository.MovimentacaoEquipamentoRepository;
import com.ti.estoque.repository.UsuarioRepository;

@Service
public class MovimentacaoEquipamentoService {

        @Autowired 
        private UsuarioRepository usuarioRepository;

        @Autowired
        private MovimentacaoEquipamentoRepository movimentacao;

        @Autowired
        private MovimentacaoEquipamentoMapper movimentacaoMapper;

        @Autowired
        private EquipamentoRepository equipamentoRepository;

        private <T> List<T> validarLista(List<T> lista, String mensagemErro) {
        if (lista.isEmpty()) {
                throw new RuntimeException(mensagemErro);
        }
        return lista;
        }

        public List<MovimentacaoEquipamentoResponseDTO> findAll() {
        List<MovimentacaoEquipamento> movimentacoes = validarLista(movimentacao.findAll(),
                "Não foram encontradas movimentações de equipamentos.");
        return movimentacoes.stream()
                .map(movimentacaoMapper::toDto)
                .collect(Collectors.toList());
        }

        public MovimentacaoEquipamentoResponseDTO findById(Long id) {
        MovimentacaoEquipamento movEquipamento = movimentacao.findById(id)
                .orElseThrow(() -> new RuntimeException("Não foi encontrado uma movimentação com esse id."));
        return movimentacaoMapper.toDto(movEquipamento);
        }

        public List<MovimentacaoEquipamentoResponseDTO> findByIds(List<Long> ids) {
                List<MovimentacaoEquipamento> movimentacoesEncontradas = new ArrayList<>();

                for (Long id : ids) {
                        movimentacao.findById(id).ifPresent(movimentacoesEncontradas::add);
                }

                if (movimentacoesEncontradas.isEmpty()) {
                        throw new RuntimeException("Nenhuma movimentação encontrada com os IDs fornecidos.");
                }

                return movimentacoesEncontradas.stream()
                        .map(movimentacaoMapper::toDto)
                        .collect(Collectors.toList());
        }

        public List<MovimentacaoEquipamentoResponseDTO> findByNumPatrimonio(String numPatrimonio) {
        List<MovimentacaoEquipamento> movimentacoes = validarLista(
                movimentacao.findByEquipamentoNumeroPatrimonioIgnoreCaseLike(numPatrimonio),
                "Não foram encontrados registros com esse número de Patrimônio.");
        return movimentacoes.stream()
                .map(movimentacaoMapper::toDto)
                .collect(Collectors.toList());
        }

        public List<MovimentacaoEquipamentoResponseDTO> findByTipoEquipamento(String tipoEquipamento) {
        List<MovimentacaoEquipamento> movimentacoes = validarLista(
                movimentacao.findByEquipamentoTipoEquipamentoDescricaoIgnoreCaseLike(tipoEquipamento),
                "Não foram encontrados registros desse tipo de Equipamento.");
        return movimentacoes.stream()
                .map(movimentacaoMapper::toDto)
                .collect(Collectors.toList());
        }

        public List<MovimentacaoEquipamentoResponseDTO> findByMarca(String marca) {
        List<MovimentacaoEquipamento> movimentacoes = validarLista(
                movimentacao.findByEquipamentoMarcaNomeMarcaIgnoreCaseLike(marca),
                "Não foram encontrados registros de equipamentos dessa marca.");
        return movimentacoes.stream()
                .map(movimentacaoMapper::toDto)
                .collect(Collectors.toList());
        }

        public List<MovimentacaoEquipamentoResponseDTO> findByModelo(String modelo) {
        List<MovimentacaoEquipamento> movimentacoes = validarLista(
                movimentacao.findByEquipamentoModeloNomeModeloIgnoreCaseLike(modelo),
                "Não foram encontrados registros de equipamentos desse modelo.");
        return movimentacoes.stream()
                .map(movimentacaoMapper::toDto)
                .collect(Collectors.toList());
        }

        public List<MovimentacaoEquipamentoResponseDTO> findByServiceTag(String serviceTag) {
        List<MovimentacaoEquipamento> movimentacoes = validarLista(
                movimentacao.findByEquipamentoServiceTagIgnoreCaseLike(serviceTag),
                "Não foram encontrados registros de equipamentos com essa Service Tag.");
        return movimentacoes.stream()
                .map(movimentacaoMapper::toDto)
                .collect(Collectors.toList());
        }

        public List<MovimentacaoEquipamentoResponseDTO> findByTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
        List<MovimentacaoEquipamento> movimentacoes = validarLista(
                movimentacao.findByTipoMovimentacao(tipoMovimentacao),
                "Não foram encontrados registros desse tipo de Movimentação.");
        return movimentacoes.stream()
                .map(movimentacaoMapper::toDto)
                .collect(Collectors.toList());
        }

        public List<MovimentacaoEquipamentoResponseDTO> findByUserId(Long id) {
        List<MovimentacaoEquipamento> movimentacoes = validarLista(
                movimentacao.findByUsuarioId(id),
                "Não foram encontrados registros de equipamentos movimentados com esse ID.");
        return movimentacoes.stream()
                .map(movimentacaoMapper::toDto)
                .collect(Collectors.toList());
        }

        public List<MovimentacaoEquipamentoResponseDTO> findByUserName(String name) {
        List<MovimentacaoEquipamento> movimentacoes = validarLista(
                movimentacao.findByUsuarioNomeIgnoreCaseLike(name),
                "Não foram encontrados registros desse Usuário.");
        return movimentacoes.stream()
                .map(movimentacaoMapper::toDto)
                .collect(Collectors.toList());
        }

        public List<MovimentacaoEquipamentoResponseDTO> getEquipamentoIds(List<Long> ids) {
        List<MovimentacaoEquipamento> movimentacoes = validarLista(
                movimentacao.findByEquipamentoIdIn(ids),
                "Não foram encontrados registros de equipamentos com estes IDs.");
        return movimentacoes.stream()
                .map(movimentacaoMapper::toDto)
                .collect(Collectors.toList());
        }

        public List<MovimentacaoEquipamentoResponseDTO> getNumPatrimonios(List<String> patrimonios) {
        List<MovimentacaoEquipamento> movimentacoes = validarLista(
                movimentacao.findByEquipamentoNumeroPatrimonioIgnoreCaseIn(patrimonios),
                "Não foram encontrados registros de equipamentos com estes números de Patrimônio.");
        return movimentacoes.stream()
                .map(movimentacaoMapper::toDto)
                .collect(Collectors.toList());
        }

        public List<MovimentacaoEquipamentoResponseDTO> getTiposEquipamentos(List<String> tipoEquipamento) {
        List<MovimentacaoEquipamento> movimentacoes = validarLista(
                movimentacao.findByEquipamentoTipoEquipamentoDescricaoIgnoreCaseIn(tipoEquipamento),
                "Não foram encontrados registros de equipamentos desses tipos.");
        return movimentacoes.stream()
                .map(movimentacaoMapper::toDto)
                .collect(Collectors.toList());
        }

        public List<MovimentacaoEquipamentoResponseDTO> getMarcas(List<String> marcas) {
        List<MovimentacaoEquipamento> movimentacoes = validarLista(
                movimentacao.findByEquipamentoMarcaNomeMarcaIgnoreCaseIn(marcas),
                "Não foram encontrados registros de equipamentos dessas marcas.");
        return movimentacoes.stream()
                .map(movimentacaoMapper::toDto)
                .collect(Collectors.toList());
        }

        public List<MovimentacaoEquipamentoResponseDTO> getModelos(List<String> modelos) {
        List<MovimentacaoEquipamento> movimentacoes = validarLista(
                movimentacao.findByEquipamentoModeloNomeModeloIgnoreCaseIn(modelos),
                "Não foram encontrados registros de equipamentos desses modelos.");
        return movimentacoes.stream()
                .map(movimentacaoMapper::toDto)
                .collect(Collectors.toList());
        }

        public List<MovimentacaoEquipamentoResponseDTO> getServiceTags(List<String> serviceTags) {
        List<MovimentacaoEquipamento> movimentacoes = validarLista(
                movimentacao.findByEquipamentoServiceTagIgnoreCaseIn(serviceTags),
                "Não foram encontrados registros de equipamentos com essas Service Tags.");
        return movimentacoes.stream()
                .map(movimentacaoMapper::toDto)
                .collect(Collectors.toList());
        }

        public List<MovimentacaoEquipamentoResponseDTO> getTiposMovimentacao(List<TipoMovimentacao> tipos) {
        List<MovimentacaoEquipamento> movimentacoes = validarLista(
                movimentacao.findByTipoMovimentacaoIn(tipos),
                "Não foram encontrados registros desses tipos de Movimentação.");
        return movimentacoes.stream()
                .map(movimentacaoMapper::toDto)
                .collect(Collectors.toList());
        }

        public List<MovimentacaoEquipamentoResponseDTO> getUserIds(List<Long> ids) {
        List<MovimentacaoEquipamento> movimentacoes = validarLista(
                movimentacao.findByUsuarioIdIn(ids),
                "Não foram encontrados registros com esses IDs de Usuário.");
        return movimentacoes.stream()
                .map(movimentacaoMapper::toDto)
                .collect(Collectors.toList());
        }

        public List<MovimentacaoEquipamentoResponseDTO> getUsers(List<String> nomes) {
        List<MovimentacaoEquipamento> movimentacoes = validarLista(
                movimentacao.findByUsuarioNomeIgnoreCaseIn(nomes),
                "Não foram encontrados registros de equipamentos desses usuários.");
        return movimentacoes.stream()
                .map(movimentacaoMapper::toDto)
                .collect(Collectors.toList());
        }

        public MovimentacaoEquipamentoResponseDTO create(MovimentacaoEquipamentoRequestDTO requestDTO) {
        MovimentacaoEquipamento entity = movimentacaoMapper.toEntity(requestDTO);
        MovimentacaoEquipamento savedEntity = movimentacao.save(entity);
        return movimentacaoMapper.toDto(savedEntity);
        }

        public MovimentacaoEquipamentoResponseDTO update(MovimentacaoEquipamentoRequestDTO dto){
        MovimentacaoEquipamento entity = movimentacao.findById(dto.getId())
        .orElseThrow(() -> new RuntimeException("Movimentação não encontrada"));

        entity.setEquipamento(equipamentoRepository.findById(dto.getId())
        .orElseThrow(() -> new RuntimeException("Equipamento não encontrado")));

        entity.setTipoMovimentacao(dto.getTipoMovimentacao());

        entity.setUsuario(usuarioRepository.findById(dto.getIdUsuario())
        .orElseThrow(() -> new RuntimeException("Usuario não encontrado")));

        entity.setDataMovimentacao(dto.getDataMovimentacao());

        MovimentacaoEquipamento atualizado = movimentacao.save(entity);

        return movimentacaoMapper.toDto(atualizado);
        }

        public void delete(Long id){
        MovimentacaoEquipamento moviment = movimentacao.findById(id)
        .orElseThrow(() -> new RuntimeException("Movimentação não encontrada"));

        movimentacao.delete(moviment);
        }

        public void deleteIds(List<Long> ids) {
                List<MovimentacaoEquipamento> movimentacoes = movimentacao.findAllById(ids);

                if (movimentacoes.isEmpty()) {
                        throw new RuntimeException("Nenhuma movimentação encontrada com os IDs fornecidos.");
                }

                movimentacao.deleteAll(movimentacoes);
        }
}