
package com.idiomas.app.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idiomas.app.entity.Alumno;
import com.idiomas.app.entity.Cursos;
import com.idiomas.app.repository.AlumnoRepository;

@Service
public class AlumnoService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    public List<Alumno> obtenerTodosLosAlumnos() {
        return alumnoRepository.findAll();
    }

    public List<Alumno> obtenerAlumnosPorCurso(String cursoId) {
        return alumnoRepository.findByCursoId(cursoId);
    }
    // Otros métodos relacionados con la gestión de alumnos

}
