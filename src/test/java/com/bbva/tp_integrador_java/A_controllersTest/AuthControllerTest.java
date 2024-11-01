package com.bbva.tp_integrador_java.A_controllersTest;

import com.bbva.tp_integrador_java.A_controllers.AuthController;
import com.bbva.tp_integrador_java.B_services.implementation.AuthServiceImplementation;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class AuthControllerTest {

    @Mock
    private AuthServiceImplementation authServiceImplementation;

    @InjectMocks
    private AuthController authControllerController;

    private AutoCloseable autoCloseable;

    @BeforeEach
    void setup() {

        System.out.println("\nEste es el Before...");

        autoCloseable = MockitoAnnotations.openMocks(this);
    }

    //1- Test unitario signIn ()
    //---------------------------------------------------------------------------------------------------------------
    @Test
    void testLogin () throws MessagingException {

        System.out.println("Esta es la ejecucion de la Prueba Unitaria 'testLogin ()'...");

        Map<String, Object> mapMockResponse = new HashMap<>();

        Mockito.when(authServiceImplementation.signIn(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
                .thenReturn(mapMockResponse);

        ResponseEntity<?> response = authControllerController.signIn("Juan", "123346");

        Assertions.assertNotNull(response);
    }
    //---------------------------------------------------------------------------------------------------------------

    //2- Test unitario hashing ()
    //---------------------------------------------------------------------------------------------------------------
    @Test
    void testHashing () throws MessagingException {

        System.out.println("Esta es la ejecucion de la Prueba Unitaria 'testHashing ()'...");

        String passwordEncriptada = " ";

        Mockito.when(authServiceImplementation.generarHash(ArgumentMatchers.anyString()))
                .thenReturn(passwordEncriptada);

        ResponseEntity<String> response = authControllerController.hashing("123456");

        Assertions.assertNotNull(response);
    }
    //---------------------------------------------------------------------------------------------------------------

    @AfterEach
    void tearDown() throws Exception {

        System.out.println("Este es el After...");

        autoCloseable.close();
    }
}