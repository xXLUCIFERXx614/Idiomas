package com.idiomas.app.controller;

import jakarta.servlet.http.HttpSession;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.idiomas.app.entity.Alumno;
import com.idiomas.app.entity.Coordinador;
import com.idiomas.app.entity.Cursos;
import com.idiomas.app.entity.Profesor;
import com.idiomas.app.repository.AlumnoRepository;
import com.idiomas.app.repository.CoordinadorRepository;
import com.idiomas.app.repository.CursosRepository;
import com.idiomas.app.repository.ProfesorRepository;

@Controller
public class LoginController {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private CoordinadorRepository coordinadorRepository;

    @Autowired
    private ProfesorRepository profesorRepository;
    

    @Autowired
    private CursosRepository cursoRepository;

    @PostMapping("login-alumno")
    public String alumnoLogin(@RequestParam("correo") String correo,
                              @RequestParam("cedula") String cedula,
                              HttpSession session,
                              Model model) {
        // Verificar si el correo y la cédula están presentes
        if (correo == null || cedula == null || correo.isEmpty() || cedula.isEmpty()) {
            model.addAttribute("error", "Por favor, ingresa un correo electrónico y una cédula.");
            return "index"; // Redirige al formulario de inicio de sesión con un mensaje de error
        }

        // Normalizar el correo electrónico a minúsculas (opcional)
        correo = correo.toLowerCase();

        // Buscar al alumno por cédula y correo
        Alumno alumno = alumnoRepository.findByCedulaAndCorreo(cedula, correo);

        if (alumno != null) {
            // Establecer el objeto alumno en la sesión
            session.setAttribute("usuario", alumno);
            // Pasar el objeto alumno al modelo para usarlo en la vista
            model.addAttribute("alumno", alumno);
            return "alumno"; // Redirige al perfil del alumno
        } else {
            model.addAttribute("error", "No se encontró ningún alumno con las credenciales proporcionadas.");
            return "index"; // Devuelve al formulario de inicio de sesión con un mensaje de error
        }
    }




    @PostMapping("login-Coordinador")
    public String coordinadorLogin(@RequestParam("correoCord") String correoCord,
                                   @RequestParam("passwrdCord") String passwrdCord,
                                   HttpSession session,
                                   Model model) {
        // Autenticación para coordinador
        Coordinador coordinador = coordinadorRepository.findByCorreoCordAndPasswrdCord(correoCord, passwrdCord);
        if (coordinador != null) {
            // Recuperar la lista de alumnos y agregarla al modelo
            List<Alumno> alumnos = alumnoRepository.findAll(); // Cambia esto por la forma en que recuperas la lista de alumnos en tu aplicación
            model.addAttribute("alumnos", alumnos);

            session.setAttribute("usuario", coordinador);
            return "coordinador"; // Redirige al perfil del coordinador con la lista de alumnos
        } else {
            model.addAttribute("error", "Credenciales de coordinador inválidas");
            return "index"; // Devuelve al formulario de login con un mensaje de error
        }
    }


    @PostMapping("login-Profesor")
    public String profesorLogin(@RequestParam("correoProf") String correoProf,
                                @RequestParam("passwrdProf") String passwrdProf,
                                HttpSession session,
                                Model model) {
        // Autenticación para profesor
        Profesor profesor = profesorRepository.findByCorreoProfAndPasswordProf(correoProf, passwrdProf);
        if (profesor != null) {
            session.setAttribute("usuario", profesor);
            
            // Obtener los cursos asociados al profesor
            List<Cursos> cursos = cursoRepository.findByProfesor(profesor);
            
            // Agregar los datos al modelo
            model.addAttribute("profesor", profesor);
            model.addAttribute("cursos", cursos);
            
            return "profesor"; // Redirige al perfil del profesor
        } else {
            model.addAttribute("error", "Credenciales de profesor inválidas");
            return "index"; // Devuelve al formulario de login con un mensaje de error
        }
    }



    @PostMapping("/select-role")
    public String selectRole(@RequestParam("role") String role) {
        // Dependiendo del rol seleccionado, redirige a la página de inicio de sesión correspondiente
        if ("coordinador".equals(role)) {
            return "login-coordinador";
        } else if ("profesor".equals(role)) {
            return "login-profesor";
        } else if ("alumno".equals(role)) {
            return "login-alumno";
        } else {
            // En caso de un rol no válido, podrías redirigir a una página de error o manejarlo de otra manera
            return "redirect:/error";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // Invalida la sesión
        session.invalidate();
        // Redirige a la página de inicio o a donde desees después del cierre de sesión
        return "redirect:/";
    }
}
