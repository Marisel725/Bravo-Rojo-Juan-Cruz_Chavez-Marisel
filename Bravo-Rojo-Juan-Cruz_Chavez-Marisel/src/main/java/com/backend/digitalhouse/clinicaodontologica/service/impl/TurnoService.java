package com.backend.digitalhouse.clinicaodontologica.service.impl;

import com.backend.digitalhouse.clinicaodontologica.dto.salida.turno.OdontologoTurnoSalidaDto;
import com.backend.digitalhouse.clinicaodontologica.dto.salida.turno.PacienteTurnoSalidaDto;
import com.backend.digitalhouse.clinicaodontologica.entity.Odontologo;
import com.backend.digitalhouse.clinicaodontologica.entity.Paciente;
import com.backend.digitalhouse.clinicaodontologica.exceptions.BadRequestException;
import com.backend.digitalhouse.clinicaodontologica.dto.entrada.modificacion.TurnoModificacionEntradaDto;
import com.backend.digitalhouse.clinicaodontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.digitalhouse.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.digitalhouse.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.digitalhouse.clinicaodontologica.dto.salida.turno.TurnoSalidaDto;
import com.backend.digitalhouse.clinicaodontologica.entity.Turno;
import com.backend.digitalhouse.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.clinicaodontologica.repository.TurnoRepository;
import com.backend.digitalhouse.clinicaodontologica.service.ITurnoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TurnoService implements ITurnoService {

    private final Logger LOGGER = LoggerFactory.getLogger(TurnoService.class);
    private final TurnoRepository turnoRepository;
    private final PacienteService pacienteService;
    private final OdontologoService odontologoService;

    private final ModelMapper modelMapper;

    public TurnoService(TurnoRepository turnoRepository, PacienteService pacienteService, OdontologoService odontologoService, ModelMapper modelMapper) {
        this.turnoRepository = turnoRepository;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
        this.modelMapper = modelMapper;

    }

    @Override
    public TurnoSalidaDto registrarTurno(TurnoEntradaDto turnoEntradaDto) throws BadRequestException {

        TurnoSalidaDto turnoSalidaDto ;

        PacienteSalidaDto pacienteSalidaDto = pacienteService.buscarPacientePorId(turnoEntradaDto.getPacienteId());
        OdontologoSalidaDto odontologoSalidaDto = odontologoService.buscarOdontologoPorid(turnoEntradaDto.getOdontologoId());
        if (odontologoSalidaDto == null || pacienteSalidaDto == null){
            if (odontologoSalidaDto == null && pacienteSalidaDto == null){
                LOGGER.error("El odontologo y el paciente no se encuentra en nuestra base de datos");
                throw new BadRequestException("El paciente y el odontologo no se encuentran en nuestra base de datos");
            } else if (pacienteSalidaDto == null){
                LOGGER.error("El paciente no se encuentra en la base de datos");
                throw new BadRequestException("El paciente no se encuentra en la base de datos");
            } else {
                LOGGER.error("El odontolgo no existe en nuestra base de datos");
                throw new BadRequestException("El odontologo no se encuentra en la base de datos");
            }
        }else {
            Turno turnoEntrada = turnoRepository.save(dtoEntradaAentidad(turnoEntradaDto));
            turnoSalidaDto = entidadADto(turnoEntrada);
            LOGGER.info("Nuevo turno registardo con exito : " + turnoEntrada);
        }

        return turnoSalidaDto;
    }


    @Override
    public List<TurnoSalidaDto> listarTurnos() {
        List<Turno> turnos = turnoRepository.findAll();
        List<TurnoSalidaDto> turnosDto = new ArrayList<>();

        for (Turno t: turnos) {
            turnosDto.add(entidadADto(t));
        }

        LOGGER.info("Listado de todos los turnos : " + turnos);
        return turnosDto;
    }

    @Override
    public TurnoSalidaDto buscarTurnoPorId(int id) {
        Turno turnoAbuscar = turnoRepository.findById(id).orElse(null);

        TurnoSalidaDto turnoEncontrado = null;
        if (turnoAbuscar != null) {
            turnoEncontrado = entidadADto(turnoAbuscar);
            LOGGER.info("Se ha encontrado el turno con el id : " + turnoAbuscar);
        } else LOGGER.error("No se encontró el turno con el id : " + id );


        return turnoEncontrado;
    }

    @Override
    public void eliminarTurno(int id) throws ResourceNotFoundException {
        TurnoSalidaDto turnoAeliminar = buscarTurnoPorId(id);
        if (turnoAeliminar != null){
            LOGGER.warn("Se ha eliminado el turno:" +  modelMapper.map(turnoAeliminar,Turno.class));
            turnoRepository.deleteById(id);
        }
        else {
            LOGGER.error("No se ha encontrado el turno con id " + id);
            throw new ResourceNotFoundException("No se ha encontrado el turno con id " + id);
        }

    }

    @Override
    public TurnoSalidaDto modificarTurno(TurnoModificacionEntradaDto turnoModificacionEntradaDto) throws ResourceNotFoundException {

        Turno turnoId = turnoRepository.findById(turnoModificacionEntradaDto.getId()).orElse(null);
        TurnoSalidaDto turnoModificadoDto;
        if (turnoId != null) {
            turnoId.setPaciente(modelMapper.map(pacienteService.buscarPacientePorId(turnoModificacionEntradaDto.getPacienteId()), Paciente.class));
            turnoId.setOdontologo(modelMapper.map(odontologoService.buscarOdontologoPorid(turnoModificacionEntradaDto.getOdontologoId()), Odontologo.class));
            turnoId.setFechaYhora(turnoModificacionEntradaDto.getFechaYhora());



            turnoModificadoDto = entidadADto(turnoId);
            turnoRepository.save(turnoId);
            LOGGER.info("Se ha modificado el turno: " + turnoId);
        } else {
            LOGGER.error("No se ha encontrado el turno en la BDD");
            throw new ResourceNotFoundException("No se ha encontrado el turno en la BDD");
        }

        return turnoModificadoDto;

    }

    public Turno dtoEntradaAentidad(TurnoEntradaDto turnoEntradaDto) {
        return modelMapper.map(turnoEntradaDto, Turno.class);
    }

    public TurnoSalidaDto entidadDtoSalida(Turno turno) {
        return modelMapper.map(turno, TurnoSalidaDto.class);
    }

    public Turno dtoModificadoAentidad(TurnoModificacionEntradaDto turnoModificacionEntradaDto) {
        return modelMapper.map(turnoModificacionEntradaDto, Turno.class);
    }

    private PacienteTurnoSalidaDto pacienteSalidaDtoASalidaTurnoDto(int id) {
        return modelMapper.map(pacienteService.buscarPacientePorId(id), PacienteTurnoSalidaDto.class);
    }

    private OdontologoTurnoSalidaDto odontologoSalidaDtoASalidaTurnoDto(int id) {
        return modelMapper.map(odontologoService.buscarOdontologoPorid(id), OdontologoTurnoSalidaDto.class);
    }

    private TurnoSalidaDto entidadADto(Turno turno) {
        TurnoSalidaDto turnoSalidaDto = modelMapper.map(turno, TurnoSalidaDto.class);
        turnoSalidaDto.setPacienteTurnoSalidaDto(pacienteSalidaDtoASalidaTurnoDto(turno.getPaciente().getId()));
        turnoSalidaDto.setOdontologoTurnoSalidaDto(odontologoSalidaDtoASalidaTurnoDto(turno.getOdontologo().getId()));
        return turnoSalidaDto;
    }
}
