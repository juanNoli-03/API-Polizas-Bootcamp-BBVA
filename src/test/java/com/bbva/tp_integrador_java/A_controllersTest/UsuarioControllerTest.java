package com.bbva.tp_integrador_java.A_controllersTest;

import com.bbva.tp_integrador_java.A_controllers.UsuarioController;
import com.bbva.tp_integrador_java.B_services.implementation.UsuarioServiceImplementation;
import com.bbva.tp_integrador_java.D_dtos.usuarioDTO.CrearUsuarioDTO;
import com.bbva.tp_integrador_java.D_dtos.usuarioDTO.UsuarioDTO;
import com.bbva.tp_integrador_java.C_mocks.Mocks;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import java.util.List;

@SpringBootTest
public class UsuarioControllerTest {

    @Mock
    private UsuarioServiceImplementation usuarioServiceImplementation;

    @InjectMocks
    private UsuarioController usuarioController;

    private AutoCloseable autoCloseable;

    @BeforeEach
    void setup() {

        System.out.println("\nEste es el Before...");

        autoCloseable = MockitoAnnotations.openMocks(this);
    }

    //1- Test unitario save ()
    //---------------------------------------------------------------------------------------------------------------
    @Test
    void testSaveUsuario () throws MessagingException {

        System.out.println("Esta es la ejecucion de la Prueba Unitaria 'testCrearUsuario ()'...");

        CrearUsuarioDTO crearUsuarioDTO = Mocks.crearUsuarioDTOMock();

        Mockito.doNothing().when(usuarioServiceImplementation).signUp(Mockito.any(CrearUsuarioDTO.class));

        ResponseEntity<CrearUsuarioDTO> response = usuarioController.signUp(crearUsuarioDTO);

        Assertions.assertNotNull(response);
    }
    //---------------------------------------------------------------------------------------------------------------

    //2- Test unitario listarUsuarios ()
    //---------------------------------------------------------------------------------------------------------------
    @Test
    void testListarUsuarios () throws MessagingException {

        System.out.println("Esta es la ejecucion de la Prueba Unitaria 'testListarUsuarios ()'...");

        UsuarioDTO usuarioDTOMock = Mocks.usuarioDTOMock();

        Mockito.when(usuarioServiceImplementation.listar()).thenReturn(List.of(usuarioDTOMock));

        ResponseEntity<List<UsuarioDTO>> response = usuarioController.findAll();

        Assertions.assertNotNull(response);
    }
    //---------------------------------------------------------------------------------------------------------------

    @AfterEach
    void tearDown() throws Exception {

        System.out.println("Este es el After...");

        autoCloseable.close();
    }
}