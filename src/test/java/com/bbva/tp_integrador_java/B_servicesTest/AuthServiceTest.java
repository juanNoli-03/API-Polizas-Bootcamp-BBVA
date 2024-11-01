package com.bbva.tp_integrador_java.B_servicesTest;

import com.bbva.tp_integrador_java.B_services.implementation.AuthServiceImplementation;
import com.bbva.tp_integrador_java.B_services.implementation.EmailService;
import com.bbva.tp_integrador_java.B_services.interfaces.UsuarioService;
import com.bbva.tp_integrador_java.D_models.Poliza;
import com.bbva.tp_integrador_java.C_mocks.Mocks;
import com.bbva.tp_integrador_java.E_config.JwtService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.mockito.Mockito.when;

@SpringBootTest
public class AuthServiceTest {

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthServiceImplementation authServiceImplementation;

    private AutoCloseable autoCloseable;

    @BeforeEach
    void setup() {

        System.out.println("\nEste es el Before...");

        autoCloseable = MockitoAnnotations.openMocks(this);
    }

    //1- Test unitario signIn()
    //---------------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------------


    //2- Test unitario generarHash()
    //---------------------------------------------------------------------------------------------------------------
    @Test
    void testGenerarHash() {

        String contraseñaEncriptada = "hashSimulado";

        when(bCryptPasswordEncoder.encode(Mockito.anyString())).thenReturn(contraseñaEncriptada);

        String response = authServiceImplementation.generarHash(contraseñaEncriptada);

        Assertions.assertNotNull(response);
    }
    //---------------------------------------------------------------------------------------------------------------

    @AfterEach
    void tearDown() throws Exception {

        System.out.println("Este es el After...");

        autoCloseable.close();
    }
}