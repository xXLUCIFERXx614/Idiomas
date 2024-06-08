package com.idiomas.app.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Alumnos")
public class Alumno {
	
@Id
private String id;

@DBRef
private Cursos curso;

private String nombres;
private String apellidos;
private String cedula;
private String correo;
private String estado = "No Aprobado"; 
private double definitiva;
private String nivel;
private String programa;
private String fechaI;
private String fechaF;




public Alumno() {
	super();
}


public String getId() {
	return id;
}


public void setId(String id) {
	this.id = id;
}


public String getNombres() {
	return nombres;
}


public void setNombres(String nombres) {
	this.nombres = nombres;
}


public String getApellidos() {
	return apellidos;
}


public void setApellidos(String apellidos) {
	this.apellidos = apellidos;
}


public String getCedula() {
	return cedula;
}


public void setCedula(String cedula) {
	this.cedula = cedula;
}


public String getCorreo() {
	return correo;
}


public void setCorreo(String correo) {
	this.correo = correo;
}


public String getEstado() {
	return estado;
}


public void setEstado(String estado) {
	this.estado = estado;
}


public double getDefinitiva() {
	return definitiva;
}


public void setDefinitiva(double definitiva) {
	this.definitiva = definitiva;
}


public String getNivel() {
	return nivel;
}


public void setNivel(String nivel) {
	this.nivel = nivel;
}


public String getPrograma() {
	return programa;
}


public void setPrograma(String programa) {
	this.programa = programa;
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


public Cursos getCurso() {
	return curso;
}


public void setCurso(Cursos curso) {
	this.curso = curso;
}


public Alumno(String id, String nombres, String apellidos, String cedula, String correo, String estado,
		double definitiva, String nivel, String programa, String fechaI, String fechaF, Cursos curso) {
	super();
	this.id = id;
	this.nombres = nombres;
	this.apellidos = apellidos;
	this.cedula = cedula;
	this.correo = correo;
	this.estado = estado;
	this.definitiva = definitiva;
	this.nivel = nivel;
	this.programa = programa;
	this.fechaI = fechaI;
	this.fechaF = fechaF;
	this.curso = curso;
}


}
