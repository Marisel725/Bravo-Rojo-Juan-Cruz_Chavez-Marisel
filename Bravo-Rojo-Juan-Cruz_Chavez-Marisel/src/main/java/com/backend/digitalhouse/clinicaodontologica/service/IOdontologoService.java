package com.backend.digitalhouse.clinicaodontologica.service;

import com.backend.digitalhouse.clinicaodontologica.dto.entrada.modificacion.OdontologoModificacionEntradaDto;
import com.backend.digitalhouse.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.digitalhouse.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.digitalhouse.clinicaodontologica.entity.Odontologo;
import com.backend.digitalhouse.clinicaodontologica.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface IOdontologoService {

    List<OdontologoSalidaDto> listarOdontologo();

    OdontologoSalidaDto registrarOdontologo (OdontologoEntradaDto odontologoEntradaDto);

    OdontologoSalidaDto buscarOdontologoPorid (int id);

    void eliminarOdontologo (int id) throws ResourceNotFoundException;

    OdontologoSalidaDto ModificarOdntologo (OdontologoModificacionEntradaDto odontologoModificacionEntradaDto) throws ResourceNotFoundException;
}
