package com.backend.digitalhouse.clinicaodontologica.service.impl;

import com.backend.digitalhouse.clinicaodontologica.dto.entrada.modificacion.DomicilioModificacionEntradaDto;
import com.backend.digitalhouse.clinicaodontologica.dto.entrada.modificacion.PacienteModificacionEntradaDto;
import com.backend.digitalhouse.clinicaodontologica.dto.entrada.paciente.DomicilioEntradaDto;
import com.backend.digitalhouse.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.digitalhouse.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.digitalhouse.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.clinicaodontologica.repository.PacienteRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource(locations = "classpath:application-test.properties")
class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;

    @Order(1)
    @Test
    void verificarQueRegistraUnPaciente() {
        DomicilioEntradaDto domicilioEntradaDto = new DomicilioEntradaDto("Calle",1234,"Localidad","Provincia");
        PacienteEntradaDto pacienteEntradaDto = new PacienteEntradaDto("Denis","Bilicki",30959925,LocalDate.of(2023,8,12),domicilioEntradaDto);
        pacienteService.registrarPaciente(pacienteEntradaDto);
        assertNotNull(pacienteService.registrarPaciente(pacienteEntradaDto));
    }

    @Order(2)
    @Test
    void modificarNombreDePacienteConId1_SeLlameAlexis() throws ResourceNotFoundException {
        pacienteService.modificarPaciente(new PacienteModificacionEntradaDto(1,"Alexis","Bilicki","30959925",LocalDate.of(2023,8,12),new DomicilioModificacionEntradaDto(2,"Calle",1234,"Localidad","Provincia")));
        assertEquals("Alexis",pacienteService.buscarPacientePorId(1).getNombre());
    }

    @Order(3)
    @Test
    void alIntentarMpodificarUnPacienteConIdQueNoSeEncuentraEnLaBDD_ArrojaResourceNotFoundException(){
        PacienteModificacionEntradaDto pacienteModificacionEntradaDto = new PacienteModificacionEntradaDto(3,"Alexis","Bilicki","30959925",LocalDate.of(2023,8,12),new DomicilioModificacionEntradaDto(2,"Calle",1234,"Localidad","Provincia"));

        assertThrows(ResourceNotFoundException.class, ()-> pacienteService.modificarPaciente(pacienteModificacionEntradaDto));
    }
}