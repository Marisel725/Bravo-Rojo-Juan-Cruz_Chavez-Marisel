package com.backend.digitalhouse.clinicaodontologica.service;

import com.backend.digitalhouse.clinicaodontologica.dto.entrada.modificacion.TurnoModificacionEntradaDto;
import com.backend.digitalhouse.clinicaodontologica.exceptions.BadRequestException;
import com.backend.digitalhouse.clinicaodontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.digitalhouse.clinicaodontologica.dto.salida.turno.TurnoSalidaDto;
import com.backend.digitalhouse.clinicaodontologica.exceptions.ResourceNotFoundException;

import java.util.List;

public interface ITurnoService {
    TurnoSalidaDto registrarTurno(TurnoEntradaDto turno) throws BadRequestException;
    List<TurnoSalidaDto> listarTurnos();
    TurnoSalidaDto buscarTurnoPorId(int id);
    void eliminarTurno(int id) throws ResourceNotFoundException;
    TurnoSalidaDto modificarTurno(TurnoModificacionEntradaDto turnoModificacionEntradaDto) throws ResourceNotFoundException;


}
