package com.idiomas.app.controller;


import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.idiomas.app.entity.Alumno;
import com.idiomas.app.entity.Cursos;
import com.idiomas.app.entity.Profesor;
import com.idiomas.app.repository.CursosRepository;
import com.idiomas.app.repository.ProfesorRepository;
import com.idiomas.app.service.AlumnoService;
import com.idiomas.app.service.CursosService;

@Controller
@RequestMapping("/cursos")
public class CursosController {

    @Autowired
    private CursosRepository cursosRepository;
    @Autowired
    private CursosService cursoService;
    @Autowired
    private AlumnoService alumnoService;
    
    @Autowired
    private ProfesorRepository profesorRepository;

    // Obtener todos los cursos y mostrarlos en la vista
    @GetMapping("/lista")
    public String obtenerTodosLosCursos(Model model) {
        List<Cursos> cursos = cursosRepository.findAll();
        model.addAttribute("cursos", cursos);
        return "lista-cursos"; // Nombre de la vista (lista-cursos.html)
    }
    
    
    @GetMapping("/alumnos/{cursoId}")
    public String mostrarAlumnosPorCurso(@PathVariable String cursoId, Model model) {
        List<Alumno> alumnos = alumnoService.obtenerAlumnosPorCurso(cursoId);
        Cursos curso = cursoService.buscarCursoPorId(cursoId);

        if (curso != null) {
            model.addAttribute("curso", curso);
            model.addAttribute("alumnos", alumnos);
            return "alumnos-cursos";
        } else {
            // Manejo de la situación donde el curso no se encuentra
            return "error";
        }
    }
    // Mostrar formulario para crear un nuevo curso
    @GetMapping("/nuevo")
    public String mostrarFormularioDeNuevoCurso(Model model) {
        model.addAttribute("curso", new Cursos());
        List<Profesor> todosLosProfesores = profesorRepository.findAll();
        model.addAttribute("profesores", todosLosProfesores);
        // Obtener todos los cursos y agregarlos al modelo
        List<Cursos> todosLosCursos = cursosRepository.findAll();
        model.addAttribute("cursos", todosLosCursos);
        return "formulario-curso"; // Nombre de la vista (formulario-curso.html)
    }


 // Guardar un nuevo curso
    @PostMapping("/guardar")
    public String guardarCurso(@ModelAttribute("curso") Cursos curso) {
        cursosRepository.save(curso);
        return "redirect:/cursos/lista"; // Redirigir a la lista de cursos
    }

    // Mostrar formulario para editar un curso existente
    @GetMapping("/editar/{id}")
    public String mostrarFormularioDeEditarCurso(@PathVariable String id, Model model) {
        Optional<Cursos> curso = cursosRepository.findById(id);
        List<Profesor> profesores = profesorRepository.findAll(); // Recuperar la lista de todos los profesores
        if (curso.isPresent()) {
            model.addAttribute("curso", curso.get());
            model.addAttribute("profesores", profesores); // Pasar la lista de profesores al modelo
            return "formulario-curso"; // Nombre de la vista (formulario-curso.html)
        } else {
            return "redirect:/cursos/lista"; // Redirigir a la lista de cursos si el curso no se encuentra
        }
    }



 // Actualizar un curso existente
 @PostMapping("/actualizar/{id}")
 public String actualizarCurso(@PathVariable String id, @ModelAttribute("curso") Cursos cursoActualizado) {
     Optional<Cursos> cursoOptional = cursosRepository.findById(id);
     if (cursoOptional.isPresent()) {
         Cursos curso = cursoOptional.get();
         curso.setNombre(cursoActualizado.getNombre());
         curso.setProfesor(cursoActualizado.getProfesor()); // Asegúrate de asignar el profesor asociado
         curso.setEstado(cursoActualizado.getEstado());
         curso.setNivel(cursoActualizado.getNivel());
         curso.setFechaI(cursoActualizado.getFechaI());
         curso.setFechaF(cursoActualizado.getFechaF());
        
         cursosRepository.save(curso);
     }
     return "redirect:/cursos/lista"; // Redirigir a la lista de cursos
 }

    // Eliminar un curso existente
    @GetMapping("/eliminar/{id}")
    public String eliminarCurso(@PathVariable String id) {
        cursosRepository.deleteById(id);
        return "redirect:/cursos/lista"; // Redirigir a la lista de cursos
    }
}
