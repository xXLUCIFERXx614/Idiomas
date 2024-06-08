package com.idiomas.app.service;

import com.idiomas.app.entity.Coordinador;

import com.idiomas.app.repository.CoordinadorRepository;


import org.springframework.stereotype.Service;

@Service
public class CoordinadorService {

    private final CoordinadorRepository coordinadorRepository;


    public CoordinadorService(CoordinadorRepository coordinadorRepository) {
        this.coordinadorRepository = coordinadorRepository;
    }

    public Coordinador registrar(Coordinador coordinador) {
        return coordinadorRepository.save(coordinador);
    }
}
