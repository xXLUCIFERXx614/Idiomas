package com.idiomas.app.entity;
import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Coordinador")
public class Coordinador {

	@Id
	private String id;
	private String nombreCord;
	private String apellidoCord;
	private String correoCord;
	private String passwrdCord;
	
	
	
	
	public Coordinador() {
		super();
	}
	public Coordinador(String id, String nombreCord, String apellidoCord, String correoCord, String passwrdCord) {
		super();
		this.id = id;
		this.nombreCord = nombreCord;
		this.apellidoCord = apellidoCord;
		this.correoCord = correoCord;
		this.passwrdCord = passwrdCord;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombreCord() {
		return nombreCord;
	}
	public void setNombreCord(String nombreCord) {
		this.nombreCord = nombreCord;
	}
	public String getApellidoCord() {
		return apellidoCord;
	}
	public void setApellidoCord(String apellidoCord) {
		this.apellidoCord = apellidoCord;
	}
	public String getCorreoCord() {
		return correoCord;
	}
	public void setCorreoCord(String correoCord) {
		this.correoCord = correoCord;
	}
	public String getPasswrdCord() {
		return passwrdCord;
	}
	public void setPasswrdCord(String passwrdCord) {
		this.passwrdCord = passwrdCord;
	}
	
	
	
	
}
