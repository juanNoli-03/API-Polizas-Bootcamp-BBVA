package com.bbva.tp_integrador_java.B_servicesTest;

import com.bbva.tp_integrador_java.B_services.implementation.PolizaServiceImplementation;
import com.bbva.tp_integrador_java.C_repositories.ClienteRepository;
import com.bbva.tp_integrador_java.C_repositories.PolizaRepository;
import com.bbva.tp_integrador_java.C_repositories.TipoSeguroRepository;
import com.bbva.tp_integrador_java.D_dtos.polizaDTO.PolizaCompletaDTO;
import com.bbva.tp_integrador_java.D_dtos.polizaDTO.PolizaSimpleDTO;
import com.bbva.tp_integrador_java.D_models.Cliente;
import com.bbva.tp_integrador_java.D_models.Poliza;
import com.bbva.tp_integrador_java.C_mocks.Mocks;
import com.bbva.tp_integrador_java.D_models.TipoSeguro;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;


@SpringBootTest
public class PolizaServiceTest {

    @Mock
    private PolizaRepository polizaRepository;

    @Mock
    private TipoSeguroRepository tipoSeguroRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private PolizaServiceImplementation polizaServiceImplementation;

    private AutoCloseable autoCloseable;

    @BeforeEach
    void setup() {

        System.out.println("\nEste es el Before...");

        autoCloseable = MockitoAnnotations.openMocks(this);
    }

    //2.1- Test unitario findAllMapping()
    //---------------------------------------------------------------------------------------------------------------
    @Test
    void testFindAll () throws MessagingException {

        System.out.println("Esta es la ejecucion de la Prueba Unitaria 'testFindAllMapping ()'...");

        Poliza poliza = Mocks.polizaMock();

        Mockito.when(polizaRepository.findAll()).thenReturn(List.of(poliza));

        List <PolizaCompletaDTO> response = polizaServiceImplementation.findAllMapping();

        Assertions.assertNotNull(response);
    }
    //---------------------------------------------------------------------------------------------------------------

    //2.2- Test unitario findAllQuery()
    //---------------------------------------------------------------------------------------------------------------
    @Test
    void testFindAllQuery () throws MessagingException {

        System.out.println("Esta es la ejecucion de la Prueba Unitaria 'testFindAllQuery ()'...");

        Poliza poliza = Mocks.polizaMock();

        Mockito.when(polizaRepository.findAllQuery()).thenReturn(List.of(poliza));

        List <Poliza> response = polizaServiceImplementation.findAllQuery();

        Assertions.assertNotNull(response);
    }
    //---------------------------------------------------------------------------------------------------------------

    @AfterEach
    void tearDown() throws Exception {

        System.out.println("Este es el After...");

        autoCloseable.close();
    }
}
