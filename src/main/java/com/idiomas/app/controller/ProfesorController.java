package com.idiomas.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.idiomas.app.entity.Cursos;
import com.idiomas.app.entity.Profesor;
import com.idiomas.app.repository.ProfesorRepository;
import com.idiomas.app.service.ProfesorService;

@Controller
@RequestMapping("/profesores")
public class ProfesorController {

    @Autowired
    private ProfesorRepository profesorRepository;
    
    @Autowired
    private ProfesorService profesorService;
    
  
    @GetMapping("/profesor/{id}")
    public String obtenerProfesor(@PathVariable String id, Model model) {
        Optional<Profesor> profesorOpt = profesorService.obtenerProfesorConCursos(id);
        if (profesorOpt.isPresent()) {
            Profesor profesor = profesorOpt.get();
            model.addAttribute("profesor", profesor);
            model.addAttribute("cursos", profesor.getCursos());
        } else {
            model.addAttribute("error", "Profesor no encontrado");
        }
        return "profesor";
    }
    
    // Obtener todos los profesores y mostrarlos en la vista
    @GetMapping("/lista")
    public String obtenerTodosLosProfesores(Model model) {
        List<Profesor> profesores = profesorRepository.findAll();
        model.addAttribute("profesores", profesores);
        return "lista-profesores"; // Nombre de la vista (lista-profesores.html)
    }

    // Mostrar formulario para crear un nuevo profesor
    @GetMapping("/nuevo")
    public String mostrarFormularioDeNuevoProfesor(Model model) {
        model.addAttribute("profesor", new Profesor());
        return "formulario-profesor"; // Nombre de la vista (formulario-profesor.html)
    }

    // Guardar un nuevo profesor
    @PostMapping("/guardar")
    public String guardarProfesor(@ModelAttribute("profesor") Profesor profesor) {
        profesorRepository.save(profesor);
        return "redirect:/profesores/lista"; // Redirigir a la lista de profesores
    }

    // Mostrar formulario para editar un profesor existente
    @GetMapping("/editar/{id}")
    public String mostrarFormularioDeEditarProfesor(@PathVariable String id, Model model) {
        Optional<Profesor> profesor = profesorRepository.findById(id);
        if (profesor.isPresent()) {
            model.addAttribute("profesor", profesor.get());
            return "formulario-profesor"; // Nombre de la vista (formulario-profesor.html)
        } else {
            return "redirect:/profesores/lista"; // Redirigir a la lista de profesores si el profesor no se encuentra
        }
    }

    // Actualizar un profesor existente
    @PostMapping("/actualizar/{id}")
    public String actualizarProfesor(@PathVariable String id, @ModelAttribute("profesor") Profesor profesorActualizado) {
        Optional<Profesor> profesorOptional = profesorRepository.findById(id);
        if (profesorOptional.isPresent()) {
            Profesor profesor = profesorOptional.get();
            profesor.setNombreProf(profesorActualizado.getNombreProf());
            profesor.setApellidoProf(profesorActualizado.getApellidoProf());
            profesor.setCorreoProf(profesorActualizado.getCorreoProf());
            profesor.setPasswordProf(profesorActualizado.getPasswordProf());
            profesorRepository.save(profesor);
        }
        return "redirect:/profesores/lista"; // Redirigir a la lista de profesores
    }

    // Eliminar un profesor existente
    @GetMapping("/eliminar/{id}")
    public String eliminarProfesor(@PathVariable String id) {
        profesorRepository.deleteById(id);
        return "redirect:/profesores/lista"; // Redirigir a la lista de profesores
    }
}
