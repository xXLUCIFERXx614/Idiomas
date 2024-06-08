package com.idiomas.app.entity;


import java.util.List;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Cursos")
public class Cursos {
	
@Id
private String id;

private String nombre;

@DBRef
private Profesor profesor;
private String estado;
private String nivel;
private String fechaI;
private String fechaF;
private String curso;

@DBRef
private List<Alumno> alumnos; // Agregado para incluir lista de alumnos


public Cursos() {
	super();
}


public Cursos(String id, String nombre, Profesor profesor, String estado, String nivel, String fechaI, String fechaF,
		String curso, List<Alumno> alumnos) {
	super();
	this.id = id;
	this.nombre = nombre;
	this.profesor = profesor;
	this.estado = estado;
	this.nivel = nivel;
	this.fechaI = fechaI;
	this.fechaF = fechaF;
	this.curso = curso;
	this.alumnos = alumnos;
}


public String getId() {
	return id;
}


public void setId(String id) {
	this.id = id;
}


public String getNombre() {
	return nombre;
}


public void setNombre(String nombre) {
	this.nombre = nombre;
}


public Profesor getProfesor() {
	return profesor;
}


public void setProfesor(Profesor profesor) {
	this.profesor = profesor;
}


public String getEstado() {
	return estado;
}


public void setEstado(String estado) {
	this.estado = estado;
}


public String getNivel() {
	return nivel;
}


public void setNivel(String nivel) {
	this.nivel = nivel;
}


public String getFechaI() {
	return fechaI;
}


public void setFechaI(String fechaI) {
	this.fechaI = fechaI;
}


public String getFechaF() {
	return fechaF;
}


public void setFechaF(String fechaF) {
	this.fechaF = fechaF;
}


public String getCurso() {
	return curso;
}


public void setCurso(String curso) {
	this.curso = curso;
}


public List<Alumno> getAlumnos() {
	return alumnos;
}


public void setAlumnos(List<Alumno> alumnos) {
	this.alumnos = alumnos;
}



}
