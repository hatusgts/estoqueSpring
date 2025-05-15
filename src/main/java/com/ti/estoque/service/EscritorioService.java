package com.ti.estoque.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ti.estoque.model.Escritorio;
import com.ti.estoque.repository.EscritorioRepository;

@Service
public class EscritorioService {
    @Autowired
    private EscritorioRepository escritorioRepository;

    public List<Escritorio> findAll(){
        return escritorioRepository.findAll();
    }

    public Optional<Escritorio> findById(Long id){
        return escritorioRepository.findById(id);
    }

    public List<Escritorio> findByIds(List<Long> ids){
        return escritorioRepository.findByIdIn(ids);
    }

    public List<Escritorio> findByNome(List<String> escritorio){
        return escritorioRepository.findByNomeEscritorioIgnoreCaseIn(escritorio);
    }
}
