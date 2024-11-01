package com.bbva.tp_integrador_java.B_servicesTest;

import com.bbva.tp_integrador_java.B_services.implementation.UsuarioServiceImplementation;
import com.bbva.tp_integrador_java.C_repositories.UsuarioRepository;
import com.bbva.tp_integrador_java.C_mocks.Mocks;
import com.bbva.tp_integrador_java.D_dtos.usuarioDTO.CrearUsuarioDTO;
import com.bbva.tp_integrador_java.D_dtos.usuarioDTO.UsuarioDTO;
import com.bbva.tp_integrador_java.D_models.Usuario;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;


@SpringBootTest
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioServiceImplementation usuarioServiceImplementation;

    private AutoCloseable autoCloseable;

    @BeforeEach
    void setup() {

        System.out.println("\nEste es el Before...");

        autoCloseable = MockitoAnnotations.openMocks(this);
    }

    //1- Test unitario getByUsername ()
    //---------------------------------------------------------------------------------------------------------------
    @Test
    void testGetByUsername () throws MessagingException {

        System.out.println("Esta es la ejecucion de la Prueba Unitaria 'testGetByUsername ()'...");

        Usuario usuario = Mocks.usuarioMock();

        Mockito.when(usuarioRepository.findByUsername(Mockito.anyString())).thenReturn(usuario);

        Usuario response = usuarioServiceImplementation.getByUsername("Juan");

        Assertions.assertNotNull(response);
    }
    //-------------------------------------------------------------------------------------------------------------

    //2- Test unitario listar ()
    //---------------------------------------------------------------------------------------------------------------
    @Test
    void testListar () throws MessagingException {

        System.out.println("Esta es la ejecucion de la Prueba Unitaria 'testListar ()'...");

        Usuario usuario = Mocks.usuarioMock();

        Mockito.when(usuarioRepository.findAll()).thenReturn(List.of(usuario));

        List <UsuarioDTO> response = usuarioServiceImplementation.listar();

        Assertions.assertNotNull(response);
    }
    //---------------------------------------------------------------------------------------------------------------

    //3- Test unitario crear ()
    //---------------------------------------------------------------------------------------------------------------
    @Test
    void testCrear () throws MessagingException {

        System.out.println("Esta es la ejecucion de la Prueba Unitaria 'testCrear ()'...");

        Usuario usuario = Mocks.usuarioMock();

        Mockito.when(usuarioRepository.save(Mockito.any(Usuario.class))).thenReturn(usuario);

        CrearUsuarioDTO crearUsuarioDTO = Mocks.crearUsuarioDTOMock();

        usuarioServiceImplementation.signUp(crearUsuarioDTO);
    }
    //---------------------------------------------------------------------------------------------------------------

    @AfterEach
    void tearDown() throws Exception {

        System.out.println("Este es el After...");

        autoCloseable.close();
    }
}
