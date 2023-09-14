package com.backend.digitalhouse.clinicaodontologica.service.impl;

import com.backend.digitalhouse.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.clinicaodontologica.dto.entrada.modificacion.PacienteModificacionEntradaDto;
import com.backend.digitalhouse.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.digitalhouse.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.digitalhouse.clinicaodontologica.entity.Paciente;
import com.backend.digitalhouse.clinicaodontologica.repository.PacienteRepository;
import com.backend.digitalhouse.clinicaodontologica.service.IPacienteService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class PacienteService implements IPacienteService {
    private final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);
    private final PacienteRepository pacienteRepository;
    private final ModelMapper modelMapper;

    public PacienteService( PacienteRepository pacienteRepository, ModelMapper modelMapper) {
        this.pacienteRepository = pacienteRepository;
        this.modelMapper = modelMapper;
        configuracionMapper();
    }


    @Override
    public PacienteSalidaDto registrarPaciente(PacienteEntradaDto pacienteEntradaDto) {
    Paciente pacienteRecibido = dtoEntradaAentidad(pacienteEntradaDto);
    Paciente pacienteRegistrado = pacienteRepository.save((pacienteRecibido));
    PacienteSalidaDto pacienteResultado = entidadAdtoSalida(pacienteRegistrado);
    LOGGER.info("Paciente registrado : " + pacienteRegistrado);
        return pacienteResultado;
    }

    @Override
    public PacienteSalidaDto buscarPacientePorId(int id) {
        Paciente pacienteBuscado = pacienteRepository.findById(id).orElse(null);

        PacienteSalidaDto pacienteEncontrado = null;
        if (pacienteBuscado !=null){
            pacienteEncontrado = entidadAdtoSalida(pacienteBuscado);
            LOGGER.info("Paciente encontrado : " + pacienteBuscado);
        }else
            LOGGER.error("El id del Paciente no se encuentra registrado en la base de datos");


        return pacienteEncontrado;
    }

    @Override
    public void eliminarPaciente(int id) throws ResourceNotFoundException {
        if (buscarPacientePorId(id) !=null){
            LOGGER.warn("Se elimino el paciente con el id : " + dtoSalidaAentidad(buscarPacientePorId(id)));
            pacienteRepository.deleteById(id);
        } else {
            LOGGER.error("No se encontró el paciente con el id " + id);
            throw new ResourceNotFoundException("No se ha encontrado el paciente con id " + id);
        }

    }

    @Override
    public List<PacienteSalidaDto> listarPacientes() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        List<PacienteSalidaDto> pacientesSalidaDto = new ArrayList<>();

        for (Paciente p: pacientes) {
            PacienteSalidaDto pacienteSalidaDto = entidadAdtoSalida(p);
            pacientesSalidaDto.add(pacienteSalidaDto);

        }
        LOGGER.info("Listado de todos los pacientes : " + pacientes);
        return pacientesSalidaDto;
    }

    @Override
    public PacienteSalidaDto modificarPaciente(PacienteModificacionEntradaDto pacienteModificado) throws ResourceNotFoundException {
       Paciente pacienteAmodificar = dtoModificadoAentidad(pacienteModificado);
       Paciente pacientePorId = pacienteRepository.findById(pacienteAmodificar.getId()).orElse(null);
       PacienteSalidaDto pacienteModificadoDto;

       if (pacientePorId !=null) {
           Paciente pacienteGuardado = pacienteRepository.save(pacienteAmodificar);
           pacienteModificadoDto = entidadAdtoSalida(pacienteGuardado);
           LOGGER.info ("Paciente modificado : " + pacienteGuardado);
       }else {
           LOGGER.error ("Paciente no encontrado");
           throw new ResourceNotFoundException("No fue posible actualizar los datos ya que el paciente no se encuentra registrado");
       }


       return pacienteModificadoDto;
    }

    private void configuracionMapper(){
        modelMapper.typeMap(PacienteEntradaDto.class, Paciente.class)
                .addMappings(mapper -> mapper.map(PacienteEntradaDto::getDomicilio, Paciente::setDomicilio));

        modelMapper.typeMap(Paciente.class, PacienteSalidaDto.class )
                .addMappings(mapper -> mapper.map(Paciente::getDomicilio, PacienteSalidaDto::setDomicilio));

        modelMapper.typeMap(PacienteModificacionEntradaDto.class,Paciente.class)
                .addMappings(mapper -> mapper.map(PacienteModificacionEntradaDto::getDomicilio,Paciente::setDomicilio));
    }

    public Paciente dtoEntradaAentidad(PacienteEntradaDto pacienteEntradaDto){
        return modelMapper.map(pacienteEntradaDto,Paciente.class);
    }

    public PacienteSalidaDto entidadAdtoSalida(Paciente paciente){
        return modelMapper.map(paciente, PacienteSalidaDto.class);
    }

    public Paciente dtoModificadoAentidad(PacienteModificacionEntradaDto pacienteModificacionEntradaDto){
        return modelMapper.map(pacienteModificacionEntradaDto,Paciente.class);
    }
    public Paciente dtoSalidaAentidad(PacienteSalidaDto pacienteSalidaDto){
        return modelMapper.map(pacienteSalidaDto,Paciente.class);
    }
}
