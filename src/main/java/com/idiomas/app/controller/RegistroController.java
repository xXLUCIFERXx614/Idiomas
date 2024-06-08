package com.idiomas.app.controller;


import com.idiomas.app.entity.Coordinador;
import com.idiomas.app.service.CoordinadorService;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class RegistroController {

	@Autowired
    private CoordinadorService coordinadorService;

	@GetMapping("/registro")
	public String registrarse(Model model) {
	    model.addAttribute("coordinador", new Coordinador());
	    return "registro"; // Cambiado
	}


	@PostMapping("/registro")
	public String registrarCoordinador(@RequestParam("nombreCord") String nombre,
	                                   @RequestParam("apellidoCord") String apellido,
	                                   @RequestParam("correoCord") String correo,
	                                   @RequestParam("passwrdCord") String passwrd,
	                                   Model model) {
	    // Validaciones simples
	    if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || passwrd.isEmpty()) {
	        model.addAttribute("error", "Todos los campos son obligatorios");
	        return "registro";
	    }

	    // Crear el nuevo coordinador
	    Coordinador nuevoCoordinador = new Coordinador();
	    nuevoCoordinador.setNombreCord(nombre);
	    nuevoCoordinador.setApellidoCord(apellido);
	    nuevoCoordinador.setCorreoCord(correo);
	    nuevoCoordinador.setPasswrdCord(passwrd);

	    // Guardar el nuevo coordinador
	    coordinadorService.registrar(nuevoCoordinador);

	    // Redirigir a la página de inicio
	    return "index";
	}
}


