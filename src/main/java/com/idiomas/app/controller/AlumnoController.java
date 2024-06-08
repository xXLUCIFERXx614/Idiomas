package com.idiomas.app.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.idiomas.app.entity.Alumno;
import com.idiomas.app.entity.Cursos;
import com.idiomas.app.repository.AlumnoRepository;
import com.idiomas.app.repository.CursosRepository;

@Controller
public class AlumnoController {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private CursosRepository cursosRepository;

    @GetMapping("/registrar")
    public String mostrarFormularioRegistroAlumno(Model model) {
        List<Cursos> cursos = cursosRepository.findAll();
        List<String> niveles = Arrays.asList("Nivel 1", "Nivel 2", "Nivel 3");
        model.addAttribute("cursos", cursos);
        model.addAttribute("niveles", niveles);
        model.addAttribute("alumno", new Alumno());
        return "registro-alumnos";
    }
    @PostMapping("/registrar")
    public String registrarAlumno(@ModelAttribute Alumno alumno, Model model) {
        try {
            // Buscar si el alumno ya está inscrito en algún curso
            List<Alumno> alumnos = alumnoRepository.findByCorreo(alumno.getCorreo());

            // Verificar si el alumno está en algún curso no aprobado
            for (Alumno a : alumnos) {
                if (a.getEstado() == null || !"Aprobado".equals(a.getEstado())) {
                    model.addAttribute("mensaje", "El alumno no puede registrarse en un nuevo curso hasta aprobar el curso actual.");
                    List<Cursos> cursos = cursosRepository.findAll();
                    model.addAttribute("cursos", cursos);
                    List<String> niveles = Arrays.asList("Nivel 1", "Nivel 2", "Nivel 3");
                    model.addAttribute("niveles", niveles);
                    return "registro-alumnos";
                }
                // Verificar si el alumno ya está registrado en el curso específico (por ejemplo, "2021-1")
                if (a.getCurso().equals(alumno.getCurso())) {
                    model.addAttribute("mensaje", "El alumno ya está registrado en el curso " + alumno.getCurso());
                    List<Cursos> cursos = cursosRepository.findAll();
                    model.addAttribute("cursos", cursos);
                    List<String> niveles = Arrays.asList("Nivel 1", "Nivel 2", "Nivel 3");
                    model.addAttribute("niveles", niveles);
                    return "registro-alumnos";
                }
            }
            // Si pasa la verificación, guardar el alumno
            alumnoRepository.save(alumno);
            model.addAttribute("mensaje", "Alumno registrado exitosamente.");
        } catch (Exception e) {
            model.addAttribute("mensaje", "Error al registrar al alumno: " + e.getMessage());
        }
        return "index";
    }



    @GetMapping("/agregar-definitiva/{alumnoId}")
    public String mostrarFormularioDefinitiva(@PathVariable("alumnoId") String alumnoId, Model model) {
        Alumno alumno = alumnoRepository.findById(alumnoId).orElse(null);
        if (alumno != null) {
            model.addAttribute("alumno", alumno);
            return "definitiva";
        } else {
            return "redirect:/alumnos-cursos";
        }
    }

    @PostMapping("/actualizar-definitiva")
    public String actualizarDefinitiva(@RequestParam("id") String id, @RequestParam("definitiva") double definitiva, Model model) {
        try {
            Alumno alumno = alumnoRepository.findById(id).orElse(null);
            if (alumno != null) {
                alumno.setDefinitiva(definitiva);
                // Actualizar el estado del alumno basado en la calificación definitiva
                if (definitiva >= 3.0) {
                    alumno.setEstado("Aprobado");
                } else {
                    alumno.setEstado("No Aprobado");
                }
                alumnoRepository.save(alumno);
                model.addAttribute("mensaje", "Calificación definitiva actualizada exitosamente.");
            } else {
                model.addAttribute("mensaje", "No se encontró al alumno con el ID especificado.");
            }
        } catch (Exception e) {
            model.addAttribute("mensaje", "Error al actualizar la calificación definitiva: " + e.getMessage());
        }
        return "redirect:/alumnos-cursos";
    }

    @GetMapping("/alumnos-cursos")
    public String listarAlumnosPorCurso(Model model) {
        List<Alumno> alumnos = alumnoRepository.findAll();
        model.addAttribute("alumnos", alumnos);
        return "alumnos-cursos";
    }

    @GetMapping("/coordinador/alumnos/ordenar-por-nombre")
    public String listarAlumnosOrdenadosPorNombre(Model model) {
        List<Alumno> alumnos = alumnoRepository.findAllByOrderByNombresAsc();
        model.addAttribute("alumnos", alumnos);
        return "coordinador";
    }

    @GetMapping("/coordinador/alumnos/aprobados")
    public String listarAlumnosAprobados(Model model) {
        List<Alumno> alumnos = alumnoRepository.findByEstado("Aprobado");
        model.addAttribute("alumnos", alumnos);
        return "coordinador";
    }

    @GetMapping("/coordinador/alumnos/no-aprobados")
    public String listarAlumnosNoAprobados(Model model) {
        List<Alumno> alumnos = alumnoRepository.findByEstado("No Aprobado");
        model.addAttribute("alumnos", alumnos);
        return "coordinador";
    }

    @GetMapping("/coordinador/alumnos/por-nivel")
    public String listarAlumnosPorNivel(@RequestParam("nivel") String nivel, Model model) {
        List<Alumno> alumnos = alumnoRepository.findByNivel(nivel);
        model.addAttribute("alumnos", alumnos);
        return "coordinador";
    }

    @GetMapping("/coordinador/alumnos/por-curso")
    public String listarAlumnosPorCurso(@RequestParam("curso") String nombre, Model model) {
        // Use the appropriate method from the repository to find students by course ID
        List<Alumno> alumnos = alumnoRepository.findByCursoId(nombre);
        model.addAttribute("alumnos", alumnos);
        return "coordinador";
    }
}
