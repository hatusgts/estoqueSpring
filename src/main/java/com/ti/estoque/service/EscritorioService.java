package com.ti.estoque.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ti.estoque.model.Escritorio;
import com.ti.estoque.repository.EscritorioRepository;

@Service
public class EscritorioService {
    @Autowired
    private EscritorioRepository escritorioRepository;

    public List<Escritorio> findAll(){
        List<Escritorio> escritorios = escritorioRepository.findAll();

        if(escritorios.isEmpty()) throw new RuntimeException("Não foram encontrados escritorios");

        return escritorios;
    }

    public Escritorio findById(Long id){
        return escritorioRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("Escritorio com id: " + id + " Não encontrado"));
    }

    public List<Escritorio> findByIds(List<Long> ids){
        List<Escritorio> escritorios = escritorioRepository.findByIdIn(ids);

        if(escritorios.isEmpty()) throw new RuntimeException("Não foram encontrados escritorios com esse nome");

        return escritorios;
    }

    public List<Escritorio> findByNome(List<String> escritorio){
        List<Escritorio> nomeEscritorio = escritorioRepository.findByNomeEscritorioIgnoreCaseIn(escritorio);

        if(escritorio.isEmpty()) throw new RuntimeException("Não foram encontrados escritorios com esse nome");

        return nomeEscritorio;
    }

    public Escritorio saveEscritorio(Escritorio item){
        return escritorioRepository.save(item);
    }
}
