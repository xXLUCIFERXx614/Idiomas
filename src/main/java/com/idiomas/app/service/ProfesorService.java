package com.idiomas.app.service;

import com.idiomas.app.entity.Alumno;
import com.idiomas.app.entity.Cursos;
import com.idiomas.app.entity.Profesor;
import com.idiomas.app.repository.AlumnoRepository;
import com.idiomas.app.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfesorService {

    @Autowired
    private ProfesorRepository profesorRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;

    public Optional<Profesor> obtenerProfesorConCursos(String id) {
        Optional<Profesor> profesorOpt = profesorRepository.findById(id);
        if (profesorOpt.isPresent()) {
            Profesor profesor = profesorOpt.get();
            for (Cursos curso : profesor.getCursos()) {
                List<Alumno> alumnos = alumnoRepository.findByCursoId(curso.getId());
                curso.setAlumnos(alumnos);
            }
            return Optional.of(profesor);
        }
        return Optional.empty();
    }
}
