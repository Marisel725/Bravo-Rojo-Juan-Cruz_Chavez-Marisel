package com.backend.digitalhouse.clinicaodontologica.service.impl;


import com.backend.digitalhouse.clinicaodontologica.dto.entrada.modificacion.OdontologoModificacionEntradaDto;
import com.backend.digitalhouse.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.digitalhouse.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.digitalhouse.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.digitalhouse.clinicaodontologica.entity.Odontologo;
import com.backend.digitalhouse.clinicaodontologica.entity.Paciente;
import com.backend.digitalhouse.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.clinicaodontologica.repository.OdontologoRepository;
import com.backend.digitalhouse.clinicaodontologica.service.IOdontologoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
@Service
public class OdontologoService implements IOdontologoService {
   private final Logger LOGGER = LoggerFactory.getLogger(OdontologoService.class);
    private final OdontologoRepository odontologoRepository;
    private final ModelMapper modelMapper;

    public OdontologoService( OdontologoRepository odontologoRepository, ModelMapper modelMapper) {
        this.odontologoRepository = odontologoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<OdontologoSalidaDto> listarOdontologo() {
        List<Odontologo> odontologos = odontologoRepository.findAll();
        List<OdontologoSalidaDto> odontologoDto = new ArrayList<>();

        for (Odontologo od: odontologos) {
            odontologoDto.add(entidadAdtoSalida(od));
        }

        LOGGER.info("Listado de todos los odontologos : " + odontologos);
        return odontologoDto;
    }

    @Override
    public OdontologoSalidaDto registrarOdontologo(OdontologoEntradaDto odontologoEntradaDto) {
        Odontologo odontologoRecibido = dtoEntradaAentidad(odontologoEntradaDto);
        Odontologo odontologoRegistrado = odontologoRepository.save((odontologoRecibido));
        OdontologoSalidaDto odontologoResultado = entidadAdtoSalida(odontologoRegistrado);
        LOGGER.info("Odontologo registrado :" + odontologoRegistrado);
        return odontologoResultado;
    }

    @Override
    public OdontologoSalidaDto buscarOdontologoPorid(int id) {
        Odontologo odontologoBuscado = odontologoRepository.findById(id).orElse(null);

        OdontologoSalidaDto odontologoEncontrado = null;
        if(odontologoBuscado != null){
            odontologoEncontrado = entidadAdtoSalida(odontologoBuscado);
            LOGGER.info("Odontologo encontrado : " + odontologoBuscado);
        } else LOGGER.error("El id del Odontologo no se encuentra registrado en la base de datos.");

        return odontologoEncontrado;
    }

    @Override
    public void eliminarOdontologo(int id) throws ResourceNotFoundException {
    if (buscarOdontologoPorid(id) !=null){
        odontologoRepository.deleteById(id);
        LOGGER.info("Se elimino el odontologo con el id : " + id);
    } else {
        LOGGER.error("No se encontró el odontologo con el id " + id);
        throw new ResourceNotFoundException("No se encontró el odontologo con el id en la BDD");
    }

    }

    @Override
    public OdontologoSalidaDto ModificarOdntologo(OdontologoModificacionEntradaDto odontologoModificacionEntradaDto) throws ResourceNotFoundException {
        Odontologo odontologoAmodifcar = dtoModificadoAentidad(odontologoModificacionEntradaDto);
        Odontologo odontologoPorId = odontologoRepository.findById(odontologoAmodifcar.getId()).orElse(null);
        OdontologoSalidaDto odontologoModificadoDto = null;

        if (odontologoPorId != null) {
            Odontologo odontologoModificado = odontologoRepository.save(odontologoAmodifcar);
            odontologoModificadoDto = entidadAdtoSalida(odontologoModificado);
            LOGGER.info("Odontologo Modificado : " + odontologoModificado);
        } else {
            LOGGER.error("Odontologo no encontrado");
            throw new ResourceNotFoundException("No se encontro el odontologo en la BDD");
        }

            return odontologoModificadoDto;
        }


    public Odontologo dtoEntradaAentidad(OdontologoEntradaDto odontologoEntradaDto){
        return modelMapper.map(odontologoEntradaDto,Odontologo.class);
    }

    public OdontologoSalidaDto entidadAdtoSalida(Odontologo odontologo){
        return modelMapper.map(odontologo, OdontologoSalidaDto.class);
    }

    public Odontologo dtoModificadoAentidad(OdontologoModificacionEntradaDto odontolgoModificacionEntradaDto){
        return modelMapper.map(odontolgoModificacionEntradaDto,Odontologo.class);
    }
    public Odontologo dtoSalidaAentidad(OdontologoSalidaDto odontologoSalidaDto){
        return modelMapper.map(odontologoSalidaDto,Odontologo.class);
    }
}


