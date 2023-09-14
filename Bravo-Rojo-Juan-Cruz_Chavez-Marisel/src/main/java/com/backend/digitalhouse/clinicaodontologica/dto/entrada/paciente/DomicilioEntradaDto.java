package com.backend.digitalhouse.clinicaodontologica.dto.entrada.paciente;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DomicilioEntradaDto {
    @NotNull(message = "La calle no puede ser nula")
    @NotBlank(message = "La calle debe tener un nombre")
    @Size(min = 5, max = 50, message = "El domicilio debe tener entre 5 y 50 caracteres")
    private String calle;
    @NotNull(message = "El numero no puede ser nula")
    @Digits(integer = 8, fraction = 0, message = "El número debe tener como máximo 8 dígitos")
    private int numero;
    @NotNull(message = "La localidad no puede ser nula")
    @NotBlank(message = "La localidad debe tener un nombre")
    private String localidad;
    @NotNull(message = "La provincia no puede ser nula")
    @NotBlank(message = "La provincia debe tener un nombre")
    @Size(min = 5, max = 50, message = "El domicilio debe tener entre 5 y 50 caracteres")
    private String provincia;

    public DomicilioEntradaDto() {
    }

    public DomicilioEntradaDto(String calle, int numero, String localidad, String provincia) {
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
}
