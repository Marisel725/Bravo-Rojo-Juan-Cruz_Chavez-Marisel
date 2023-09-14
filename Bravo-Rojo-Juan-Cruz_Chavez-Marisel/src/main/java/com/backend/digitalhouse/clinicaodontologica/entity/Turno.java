package com.backend.digitalhouse.clinicaodontologica.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "TURNOS")
public class Turno {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime fechaYhora;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    @JoinColumn(name = "odontologo_id")
    private Odontologo odontologo;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    public Turno() {
    }
    public Turno(LocalDateTime fechaYhora, Odontologo odontologo, Paciente paciente) {
        this.fechaYhora = fechaYhora;
        this.odontologo = odontologo;
        this.paciente = paciente;
    }



    public int getId() {
        return id;
    }
    public LocalDateTime getFechaYhora() {
        return fechaYhora;
    }

    public void setFechaYhora(LocalDateTime fechaYhora) {
        this.fechaYhora = fechaYhora;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    @Override
    public String toString() {
        return "Turno{" +
                "id=" + id +
                ", fecha=" + fechaYhora +
                ", paciente=" + paciente +
                ", odontologo=" + odontologo +
                '}';
    }
}
