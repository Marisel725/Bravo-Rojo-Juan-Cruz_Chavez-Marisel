package com.backend.digitalhouse.clinicaodontologica.service;

import com.backend.digitalhouse.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.clinicaodontologica.dto.entrada.modificacion.PacienteModificacionEntradaDto;
import com.backend.digitalhouse.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.digitalhouse.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;

import java.util.List;

public interface IPacienteService {

    List<PacienteSalidaDto> listarPacientes();

    PacienteSalidaDto registrarPaciente(PacienteEntradaDto paciente);

    PacienteSalidaDto buscarPacientePorId(int id);

    void eliminarPaciente(int id) throws ResourceNotFoundException;

    PacienteSalidaDto modificarPaciente(PacienteModificacionEntradaDto pacienteModificado) throws ResourceNotFoundException;

}
