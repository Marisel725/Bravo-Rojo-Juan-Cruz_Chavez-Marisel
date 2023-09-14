package com.backend.digitalhouse.clinicaodontologica.dto.entrada.modificacion;

import com.backend.digitalhouse.clinicaodontologica.entity.Odontologo;
import com.backend.digitalhouse.clinicaodontologica.entity.Paciente;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TurnoModificacionEntradaDto {
    @NotNull
    private int id;
    @NotNull
    private LocalDateTime fechaYhora;
    @NotNull
    private int odontologoId;
    @NotNull
    private int pacienteId;

    public TurnoModificacionEntradaDto() {
    }

    public TurnoModificacionEntradaDto(int id, LocalDateTime fechaYhora, int odontologoId, int pacienteId) {
        this.id = id;
        this.fechaYhora = fechaYhora;
        this.odontologoId = odontologoId;
        this.pacienteId = pacienteId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getFechaYhora() {
        return fechaYhora;
    }

    public void setFechaYhora(LocalDateTime fechaYhora) {
        this.fechaYhora = fechaYhora;
    }

    public int getOdontologoId() {
        return odontologoId;
    }

    public void setOdontologoId(int odontologoId) {
        this.odontologoId = odontologoId;
    }

    public int getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(int pacienteId) {
        this.pacienteId = pacienteId;
    }
}