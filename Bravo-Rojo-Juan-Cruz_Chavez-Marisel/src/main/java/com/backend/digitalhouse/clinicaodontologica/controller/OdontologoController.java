package com.backend.digitalhouse.clinicaodontologica.controller;


import com.backend.digitalhouse.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.clinicaodontologica.dto.entrada.modificacion.OdontologoModificacionEntradaDto;
import com.backend.digitalhouse.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.digitalhouse.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.digitalhouse.clinicaodontologica.service.IOdontologoService;
import com.backend.digitalhouse.clinicaodontologica.service.impl.OdontologoService;
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
@RequestMapping("/odontologos")
public class OdontologoController {

    private final IOdontologoService odontologoService;

    public OdontologoController(OdontologoService odontologoService) {

        this.odontologoService = odontologoService;
    }
    @Operation(summary = "Registro de un nuevo odontólogo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Odontólogo registrado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OdontologoSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })

    @PostMapping("registrar")
    public ResponseEntity<OdontologoSalidaDto> registrarOdontologo (@Valid @RequestBody OdontologoEntradaDto odontologo){
        return new ResponseEntity<>(odontologoService.registrarOdontologo(odontologo), HttpStatus.CREATED);
    }
    @Operation(summary = "Modificar un odontólogo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Odontólogo modificado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OdontologoSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Odontólogo no encontrado en la BDD",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })

    @PutMapping("modificar")
    public ResponseEntity<OdontologoSalidaDto> modificarOdontologo (@Valid @RequestBody OdontologoModificacionEntradaDto odontologo) throws ResourceNotFoundException {
        return new ResponseEntity<>(odontologoService.ModificarOdntologo(odontologo), HttpStatus.OK);
    }

    @Operation(summary = "Buscar Odontólogo por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Odontólogo encontrado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OdontologoSalidaDto.class))}),
            @ApiResponse(responseCode = "404", description = "Odontólogo no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })

    @GetMapping("buscar/{id}")
    public ResponseEntity<OdontologoSalidaDto> buscarOdontologoPorId(@PathVariable int id){
        return new ResponseEntity<>(odontologoService.buscarOdontologoPorid(id), HttpStatus.OK);

    }
    @Operation(summary = "Eliminar Odontólogo por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Odontólogo eliminado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OdontologoSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Id inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Odontólogo no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })

    @DeleteMapping ("eliminar/{id}")
    public ResponseEntity<String> eliminarPorId(@PathVariable int id) throws ResourceNotFoundException {
        odontologoService.eliminarOdontologo(id);
        return ResponseEntity.ok(("Se eliminó el Odontologo con id " + id));
    }
    @Operation(summary = "Listado de todos los odontólogos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de odontólogos obtenidos",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OdontologoSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })

    @GetMapping("listar")
    public ResponseEntity<List<OdontologoSalidaDto>> listarTodos(){
        return new ResponseEntity<>(odontologoService.listarOdontologo(), HttpStatus.OK);
    }

}


