package com.backend.digitalhouse.clinicaodontologica.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="ODONTOLOGOS")
public class Odontologo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "MATRICULA")
    private String matricula;
    private String nombre;
    private String apellido;

    @OneToMany(mappedBy = "odontologo", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Turno> turnos =new ArrayList<>();
    public Odontologo(String matricula, String nombre, String apellido) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Odontologo() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }



    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public String toString() {
        return "\n 🧑🏻‍⚕️️ Odontologo {" +
                "id=" + id +
                ", numeroMatricula='" + matricula + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                '}'+'\n';
    }
}


