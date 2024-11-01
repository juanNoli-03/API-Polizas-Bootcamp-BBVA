package com.bbva.tp_integrador_java.A_controllers;

import com.bbva.tp_integrador_java.D_dtos.usuarioDTO.CrearUsuarioDTO;
import com.bbva.tp_integrador_java.D_dtos.usuarioDTO.UsuarioDTO;
import com.bbva.tp_integrador_java.B_services.implementation.UsuarioServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioServiceImplementation usuarioServiceImplementation;

    @PostMapping ("/signUp")
    public ResponseEntity <CrearUsuarioDTO> signUp (@RequestBody CrearUsuarioDTO crearUsuarioDTO) {
        usuarioServiceImplementation.signUp(crearUsuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(crearUsuarioDTO);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> findAll() {
        return ResponseEntity.ok(usuarioServiceImplementation.listar());
    }
}
