package com.bbva.tp_integrador_java.A_controllers;

import com.bbva.tp_integrador_java.B_services.implementation.PolizaServiceImplementation;
import com.bbva.tp_integrador_java.D_dtos.polizaDTO.PolizaCompletaDTO;
import com.bbva.tp_integrador_java.D_dtos.polizaDTO.PolizaSimpleDTO;
import com.bbva.tp_integrador_java.D_models.Poliza;
import com.bbva.tp_integrador_java.E_exceptions.CustomException;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;

@Validated
@RestController
@RequestMapping("api/polizas")
public class PolizaController {

    @Autowired
    private PolizaServiceImplementation polizaServiceImplementation;

    //1- Crear una nueva Poliza en nuestra BD.
    @PostMapping
    public ResponseEntity<PolizaCompletaDTO> savePoliza (@Valid @RequestBody PolizaSimpleDTO polizaSimpleDTO, @RequestParam
    @NotNull(message = "El ID del Cliente no puede ser NULO") Integer idCliente, @RequestParam
    @NotEmpty(message = "La Poliza debe estar asociada si o si a uno o varios Tipos de Seguro") Set<Long> tiposSeguros)
            throws CustomException, MessagingException {

        Poliza polizaCreada = polizaServiceImplementation.save(polizaSimpleDTO, idCliente, tiposSeguros);

        PolizaCompletaDTO polizaCompletaDTO = new PolizaCompletaDTO(polizaCreada);

        return ResponseEntity.status(HttpStatus.CREATED).body(polizaCompletaDTO);
    }

    //2- Obtener la lista de todas las Polizas de nuestra BD.
    //-------------------------------------------------------
    //2.1- Mediante mapping
    @GetMapping("/byMapping")
    public ResponseEntity<List<PolizaCompletaDTO>> findAllMapping () {
        return ResponseEntity.ok(polizaServiceImplementation.findAllMapping());
    }

    //2.2- Mediante Query
    @GetMapping("/byQuery")
    public ResponseEntity<List<Poliza>> findAllQuery () {
        return ResponseEntity.ok(polizaServiceImplementation.findAllQuery());
    }
    //-------------------------------------------------------

    //3- Actualizar la informacion de una Poliza de nuestra BD.
    @PutMapping("/{idPoliza}")
    public ResponseEntity<PolizaCompletaDTO> updatePoliza (@Valid @RequestBody PolizaSimpleDTO polizaSimpleDTO, @PathVariable
    @NotNull(message = "El ID de la Poliza no puede ser Nulo") long idPoliza, @RequestParam
    @NotNull(message = "El ID del Cliente no puede ser NULO") int idCliente, @RequestParam
    @NotEmpty(message = "La Poliza debe estar asociada si o si a uno o varios Tipos de Seguro") Set<Long> tiposSeguros)
            throws CustomException, MessagingException, SQLGrammarException {

        Poliza polizaActualizada = polizaServiceImplementation.update(idPoliza, polizaSimpleDTO, idCliente, tiposSeguros);

        PolizaCompletaDTO polizaCompletaDTO = new PolizaCompletaDTO(polizaActualizada);

        return ResponseEntity.status(HttpStatus.CREATED).body(polizaCompletaDTO);
    }

    //4- Eliminamos una Poliza de nuestra BD.
    @DeleteMapping("/{idPoliza}")
    public ResponseEntity <String> delete (@PathVariable long idPoliza) throws CustomException, MessagingException {

        polizaServiceImplementation.delete(idPoliza);

        return ResponseEntity.ok().body("Poliza con ID = " + idPoliza + " eliminada con exíto!");
    }
}