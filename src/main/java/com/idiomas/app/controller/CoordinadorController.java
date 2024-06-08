package com.idiomas.app.controller;

import com.idiomas.app.entity.Alumno;

import com.idiomas.app.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
public class CoordinadorController {

	
   @Autowired
   private AlumnoRepository alumnoRepository;

   @GetMapping("/coordinador")
   public String listarAlumnos(Model model) {
       List<Alumno> alumnos = alumnoRepository.findAll();
       model.addAttribute("alumnos", alumnos);
       // Agrega un objeto vacío de Alumno para el formulario de búsqueda
       model.addAttribute("busqueda", new Alumno());
       return "coordinador";
   }

   @GetMapping("/coordinador/alumnos/search")
   public String buscarAlumnoPorCedula(@RequestParam(name = "busquedaCedula", required = false) String busquedaCedula, Model model) {
       if (busquedaCedula != null && !busquedaCedula.isEmpty()) {
           // Si se proporciona una cédula para buscar, realiza la búsqueda por cédula
           Alumno alumno = alumnoRepository.findByCedula(busquedaCedula);
           if (alumno != null) {
               model.addAttribute("alumnos", Collections.singletonList(alumno));
           } else {
               model.addAttribute("alumnos", Collections.emptyList());
               model.addAttribute("noResultsMessage", "No se encontraron resultados para la cédula especificada.");
           }
       } else {
           // Si no se proporciona una cédula, simplemente muestra la lista completa de alumnos
           List<Alumno> alumnos = alumnoRepository.findAll();
           model.addAttribute("alumnos", alumnos);
       }
       // Aquí puedes agregar más lógica según sea necesario
       return "coordinador";
   }

   // Método de Inicialización para Cargar los Alumnos al Iniciar Sesión como Coordinador
   public void cargarAlumnos(Model model) {
       List<Alumno> alumnos = alumnoRepository.findAll();
       model.addAttribute("alumnos", alumnos);
       // Agrega un objeto vacío de Alumno para el formulario de búsqueda
       model.addAttribute("busqueda", new Alumno());
   }

}
