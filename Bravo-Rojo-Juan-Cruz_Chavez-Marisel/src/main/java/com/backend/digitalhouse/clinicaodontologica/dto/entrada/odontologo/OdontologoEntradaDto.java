package com.backend.digitalhouse.clinicaodontologica.dto.entrada.odontologo;

import javax.validation.constraints.*;

public class OdontologoEntradaDto {
    @NotNull(message = "La matricula no puede ser nula")
    @Pattern(regexp = "^[A-Za-z]{2}-\\d{1,3}\\d*$", message = "El campo debe contener 2 letras mayúsculas o minúsculas más un guión, seguido de números")
    @Size(min = 4, max = 10, message = "El dato numerico debe tener entre 5 y 10 digitos")
    private String matricula;

    @Size(min = 3,max = 20, message = "El nombre debe tener un mínimo de 3 a 20 caracteres")
    @NotNull(message = "El nombre no puede ser nulo")
    @NotBlank(message = "Debe especificarse el nombre del odontologo")
    private String nombre;

    @Size(min= 3, max = 20, message = "El apellido debe tener un mínimo de 3 a 20 caracteres")
    @NotNull(message = "El apellido no puede ser nulo")
    @NotBlank(message = "Debe especificarse el apellido del odontologo")
    private String apellido;

    public OdontologoEntradaDto() {
    }

    public OdontologoEntradaDto(String matricula, String nombre, String apellido) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
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
}
