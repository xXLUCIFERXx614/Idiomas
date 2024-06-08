package com.idiomas.app.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.idiomas.app.entity.Cursos;

import com.idiomas.app.repository.CursosRepository;

@Service
public class CursosService {

	private final CursosRepository cursoRepository;

    
    public CursosService(CursosRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public List<Cursos> obtenerTodosLosCursos() {
        return cursoRepository.findAll();
    }

    
    // Otros métodos relacionados con la gestión de cursos

    public Cursos buscarCursoPorId(String id) {
        return cursoRepository.findById(id).orElse(null);
    }
}
