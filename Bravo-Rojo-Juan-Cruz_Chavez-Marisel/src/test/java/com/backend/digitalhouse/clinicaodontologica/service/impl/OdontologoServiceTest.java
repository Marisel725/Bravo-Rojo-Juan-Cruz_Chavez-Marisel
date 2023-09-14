package com.backend.digitalhouse.clinicaodontologica.service.impl;

import com.backend.digitalhouse.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.digitalhouse.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource(locations = "classpath:application-test.properties")
class OdontologoServiceTest {
    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    void registrarOdontologoConNombreGuillermoConMatricula_MB_123(){
        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto("MB-123","Guillermo","Jaimito");
        OdontologoSalidaDto odontologoSalidaDto = odontologoService.registrarOdontologo(odontologoEntradaDto);

        assertEquals("Guillermo", odontologoSalidaDto.getNombre());
        assertEquals("MB-123",odontologoSalidaDto.getMatricula());
    }

    @Test
    @Order(2)
    void buscarOdontologoConId1(){
        OdontologoSalidaDto odontologoSalidaDto = odontologoService.buscarOdontologoPorid(1);
        assertEquals(1,odontologoSalidaDto.getId());
    }

    @Test
    @Order(3)
        void ListarTodosLosOdontologosRegistrados(){

        assertFalse(odontologoService.listarOdontologo().isEmpty());
        }


}