package com.bbva.tp_integrador_java.A_controllers;

import com.bbva.tp_integrador_java.B_services.implementation.AuthServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
    @Autowired
    private AuthServiceImplementation authServiceImplementation;

    @PostMapping("/signIn")
    public ResponseEntity<?> signIn(
            @RequestHeader final String username,
            @RequestHeader final String password
    ) {
        return ResponseEntity.ok(authServiceImplementation.signIn(username, password));
    }

    @GetMapping("/hashing")
    public ResponseEntity<String> hashing (@RequestParam String password) {

        return ResponseEntity.ok().body("Contraseña encriptada: " + authServiceImplementation.generarHash(password));
    }
}