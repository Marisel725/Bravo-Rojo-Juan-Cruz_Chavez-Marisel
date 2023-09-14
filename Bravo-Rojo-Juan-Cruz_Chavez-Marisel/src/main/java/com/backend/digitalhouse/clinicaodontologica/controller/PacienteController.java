package com.backend.digitalhouse.clinicaodontologica.controller;

import com.backend.digitalhouse.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.digitalhouse.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.clinicaodontologica.dto.entrada.modificacion.PacienteModificacionEntradaDto;
import com.backend.digitalhouse.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.digitalhouse.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.digitalhouse.clinicaodontologica.service.IPacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/pacientes")
public class PacienteController {
    private final IPacienteService pacienteService;
    public PacienteController(IPacienteService pacienteService) {

        this.pacienteService = pacienteService;
    }

    @Operation(summary = "Registro de un nuevo paciente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Paciente registrado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PacienteSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })

    @PostMapping("registrar")
    public ResponseEntity<PacienteSalidaDto> registrarPaciente(@Valid @RequestBody PacienteEntradaDto paciente){
        return new ResponseEntity<>(pacienteService.registrarPaciente(paciente) , HttpStatus.CREATED);
    }

    @Operation(summary = "Modificar un paciente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente modificado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PacienteSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Paciente no encontrado en la BDD",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })

    @PutMapping("modificar")
    public ResponseEntity<PacienteSalidaDto> modificarPaciente(@Valid @RequestBody PacienteModificacionEntradaDto paciente) throws ResourceNotFoundException {
        return new ResponseEntity<>(pacienteService.modificarPaciente(paciente), HttpStatus.OK);
    }
    @Operation(summary = "Buscar Paciente por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente encontrado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PacienteSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Id inválido",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })

    @GetMapping("buscar/{id}")
    public ResponseEntity<PacienteSalidaDto> buscarPacientePorId(@PathVariable int id) {

        return new ResponseEntity<>(pacienteService.buscarPacientePorId(id), HttpStatus.OK);
    }
    @Operation(summary = "Eliminar Paciente por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Paciente eliminado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PacienteSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Id inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Paciente no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable int id) throws ResourceNotFoundException {
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.ok(("Se eliminó el paciente con id " + id));
    }
    @Operation(summary = "Listado de todos los Pacientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de pacientes",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PacienteSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @GetMapping("listar")
    public ResponseEntity<List<PacienteSalidaDto>> listarTodosLosPacientes() {
        return new ResponseEntity<>(pacienteService.listarPacientes(), HttpStatus.OK);
    }

}






