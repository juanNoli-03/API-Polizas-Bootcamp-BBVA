package com.bbva.tp_integrador_java.B_services.implementation;

import com.bbva.tp_integrador_java.B_services.interfaces.AuthService;
import com.bbva.tp_integrador_java.B_services.interfaces.UsuarioService;
import com.bbva.tp_integrador_java.D_models.Usuario;
import com.bbva.tp_integrador_java.E_config.JwtService;
import com.bbva.tp_integrador_java.E_constants.ErrorConstants;
import com.bbva.tp_integrador_java.E_exceptions.CustomException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImplementation implements AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImplementation.class);

    private final AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Map<String, Object> signIn(final String username, final String password) {
        Map<String, Object> response = new HashMap<>();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            Usuario usuario = usuarioService.getByUsername(username);
            System.out.println(usuario);
            response.put("id", usuario.getId());
            response.put("token", jwtService.generateToken(usuario.getId(), username, usuario.getRoles()));

        } catch (Exception e) {

            throw new CustomException(HttpStatus.UNAUTHORIZED, ErrorConstants.CREDENCIALES_INVALIDAS);
        }

        logger.info("Login exitoso...");

        return response;
    }

    public String generarHash(final String password) {

        return bCryptPasswordEncoder.encode(password);
    }
}