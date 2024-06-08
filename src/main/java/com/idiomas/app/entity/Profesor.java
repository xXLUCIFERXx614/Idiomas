package com.idiomas.app.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Profesor")
public class Profesor {
    @Id
    private String id;
    private List<Cursos> cursos; // Cambiado a una lista de cursos

    private String nombreProf;
    private String apellidoProf;
    private String correoProf;
    private String passwordProf;

    public Profesor(String id, List<Cursos> cursos, String nombreProf, String apellidoProf, String correoProf,
            String passwordProf) {
        super();
        this.id = id;
        this.cursos = cursos;
        this.nombreProf = nombreProf;
        this.apellidoProf = apellidoProf;
        this.correoProf = correoProf;
        this.passwordProf = passwordProf;
    }

    public Profesor() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Cursos> getCursos() {
        return cursos;
    }

    public void setCursos(List<Cursos> cursos) {
        this.cursos = cursos;
    }

    public String getNombreProf() {
        return nombreProf;
    }

    public void setNombreProf(String nombreProf) {
        this.nombreProf = nombreProf;
    }

    public String getApellidoProf() {
        return apellidoProf;
    }

    public void setApellidoProf(String apellidoProf) {
        this.apellidoProf = apellidoProf;
    }

    public String getCorreoProf() {
        return correoProf;
    }

    public void setCorreoProf(String correoProf) {
        this.correoProf = correoProf;
    }

    public String getPasswordProf() {
        return passwordProf;
    }

    public void setPasswordProf(String passwordProf) {
        this.passwordProf = passwordProf;
    }
}
