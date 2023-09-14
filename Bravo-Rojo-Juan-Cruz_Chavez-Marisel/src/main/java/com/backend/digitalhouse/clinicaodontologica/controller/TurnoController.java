package com.backend.digitalhouse.clinicaodontologica.controller;

import com.backend.digitalhouse.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.digitalhouse.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.digitalhouse.clinicaodontologica.exceptions.BadRequestException;
import com.backend.digitalhouse.clinicaodontologica.dto.entrada.modificacion.TurnoModificacionEntradaDto;
import com.backend.digitalhouse.clinicaodontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.digitalhouse.clinicaodontologica.dto.salida.turno.TurnoSalidaDto;
import com.backend.digitalhouse.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.clinicaodontologica.service.ITurnoService;
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
@RequestMapping("/turnos")
public class TurnoController {
    private final ITurnoService turnoService; //La capa Controller depende de la capa Service

    public TurnoController(ITurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @Operation(summary = "Registro de un nuevo turno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Turno registrado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TurnoSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })

    @PostMapping("registrar")
    public ResponseEntity<TurnoSalidaDto> registrarTurno(@Valid @RequestBody TurnoEntradaDto turno) throws BadRequestException {
        return new ResponseEntity<>(turnoService.registrarTurno(turno), HttpStatus.CREATED);
    }
    @Operation(summary = "Modificar un turno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Turno modificado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TurnoSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Turno no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @PutMapping ("modificar")
    public ResponseEntity<TurnoSalidaDto> modificarTurno(@Valid @RequestBody TurnoModificacionEntradaDto turno) throws ResourceNotFoundException {
        return new ResponseEntity<>(turnoService.modificarTurno(turno), HttpStatus.OK);
    }
    @Operation(summary = "Buscar Turno por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Turno encontrado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TurnoSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Id inválido",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })

    @GetMapping("/buscar/{id}")
    public ResponseEntity<TurnoSalidaDto> buscarTurnoPorId (@PathVariable int id){
        return new ResponseEntity<>(turnoService.buscarTurnoPorId(id), HttpStatus.OK);
    }
    @Operation(summary = "Eliminar Turno por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Turno eliminado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TurnoSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Id inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Turno no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @DeleteMapping ("eliminar/{id}")
    public ResponseEntity<String> eliminarTurnoPorId (@PathVariable int id) throws ResourceNotFoundException {
        turnoService.eliminarTurno(id);
        return ResponseEntity.ok(("Se eliminó el turno con id " + id));
    }
    @Operation(summary = "Listado de todos los Turnos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de Turnos",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TurnoSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })

    @GetMapping("listar")
    public ResponseEntity<List<TurnoSalidaDto>> listarTodosLosTurnos(){
        return new ResponseEntity<>(turnoService.listarTurnos(), HttpStatus.OK);
    }
}
