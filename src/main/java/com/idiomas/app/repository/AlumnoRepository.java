package com.idiomas.app.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.idiomas.app.entity.Alumno;

public interface AlumnoRepository extends MongoRepository<Alumno, String> {
    Alumno findByCedulaAndCorreo(String cedula, String correo);
    
    Alumno findByCedula(String cedula);
    
    List<Alumno> findByEstado(String estado);

    List<Alumno> findByNivel(String nivel);

  

    List<Alumno> findByCursoId(String nombre);

    List<Alumno> findByCorreo(String correo);

    List<Alumno> findAllByOrderByNombresAsc();
    
    // Método para filtrar alumnos por curso
    List<Alumno> findByCurso(String curso);

}
