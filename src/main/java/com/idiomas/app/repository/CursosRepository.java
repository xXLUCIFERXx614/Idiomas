package com.idiomas.app.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.idiomas.app.entity.Alumno;
import com.idiomas.app.entity.Cursos;
import com.idiomas.app.entity.Profesor;

import java.util.List;
import com.idiomas.app.entity.Cursos;
import com.idiomas.app.entity.Profesor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CursosRepository extends MongoRepository<Cursos, String> {
    List<Cursos> findByProfesor(Profesor profesor);
    
    
    List<Cursos> findByNombre(String nombre);
}


