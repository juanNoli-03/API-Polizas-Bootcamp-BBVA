package com.bbva.tp_integrador_java.A_controllersTest;

import com.bbva.tp_integrador_java.A_controllers.PolizaController;
import com.bbva.tp_integrador_java.B_services.implementation.PolizaServiceImplementation;
import com.bbva.tp_integrador_java.D_dtos.polizaDTO.PolizaCompletaDTO;
import com.bbva.tp_integrador_java.D_dtos.polizaDTO.PolizaSimpleDTO;
import com.bbva.tp_integrador_java.D_models.Poliza;
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
import java.util.Set;

@SpringBootTest
public class PolizaControllerTest {

    @Mock
    private PolizaServiceImplementation polizaServiceImplementation;

    @InjectMocks
    private PolizaController polizaController;

    private AutoCloseable autoCloseable;

    @BeforeEach
    void setup() {

        System.out.println("\nEste es el Before...");

        autoCloseable = MockitoAnnotations.openMocks(this);
    }

    //1- Test unitario save ()
    //---------------------------------------------------------------------------------------------------------------
    @Test
    void testSavePoliza () throws MessagingException {

        System.out.println("Esta es la ejecucion de la Prueba Unitaria 'testSavePoliza ()'...");

        Poliza poliza = Mocks.polizaMock();

        Mockito.when(polizaServiceImplementation.save(Mockito.any(PolizaSimpleDTO.class), ArgumentMatchers.anyLong(),
                        ArgumentMatchers.anySet())).thenReturn(poliza);

        ResponseEntity<PolizaCompletaDTO> response = polizaController.savePoliza(Mocks.polizaSimpleDTOMock(),
              1, Set.of(1l, 2l));

        Assertions.assertNotNull(response);
    }
    //---------------------------------------------------------------------------------------------------------------

    //2.1- Test unitario findAllMapping ()
    //---------------------------------------------------------------------------------------------------------------
    @Test
    void testFindAllMapping () throws MessagingException {

        System.out.println("Esta es la ejecucion de la Prueba Unitaria 'testFindAllMapping ()'...");

        PolizaCompletaDTO polizaCompletaDTO = Mocks.polizaCompletaDTOMock();

        Mockito.when(polizaServiceImplementation.findAllMapping()).thenReturn(List.of(polizaCompletaDTO));

        ResponseEntity<List<PolizaCompletaDTO>> response = polizaController.findAllMapping();

        Assertions.assertNotNull(response);
    }
    //---------------------------------------------------------------------------------------------------------------

    //2.2 Test unitario findAllQuery ()
    //---------------------------------------------------------------------------------------------------------------
    @Test
    void testFindAllQuery () throws MessagingException {

        System.out.println("Esta es la ejecucion de la Prueba Unitaria 'testFindAllQuery ()'...");

        Poliza poliza = Mocks.polizaMock();

        Mockito.when(polizaServiceImplementation.findAllQuery()).thenReturn(List.of(poliza));

        ResponseEntity<List<Poliza>> response = polizaController.findAllQuery();

        Assertions.assertNotNull(response);
    }
    //---------------------------------------------------------------------------------------------------------------

    //3- Test unitario update ()
    //---------------------------------------------------------------------------------------------------------------
    @Test
    void testUpdatePoliza () throws MessagingException {

        System.out.println("Esta es la ejecucion de la Prueba Unitaria 'testUpdatePoliza ()'...");

        Poliza poliza = Mocks.polizaMock();

        Mockito.when(polizaServiceImplementation.update(ArgumentMatchers.anyLong(), Mockito.any(PolizaSimpleDTO.class),
                ArgumentMatchers.anyLong(), ArgumentMatchers.anySet())).thenReturn(poliza);

        ResponseEntity<PolizaCompletaDTO> response = polizaController.updatePoliza(Mocks.polizaSimpleDTOMock(), 1,
                2, Set.of(1l, 2l));

        Assertions.assertNotNull(response);
    }
    //---------------------------------------------------------------------------------------------------------------

    //4- Test unitario delete ()
    //---------------------------------------------------------------------------------------------------------------
    @Test
    void testDeletePoliza () throws MessagingException {

        System.out.println("Esta es la ejecucion de la Prueba Unitaria 'testDeletePoliza ()'...");

        Mockito.doNothing().when(polizaServiceImplementation).delete(ArgumentMatchers.anyLong());

        ResponseEntity<String> response = polizaController.delete( 1);

        Assertions.assertNotNull(response);
    }
    //---------------------------------------------------------------------------------------------------------------

    @AfterEach
    void tearDown() throws Exception {

        System.out.println("Este es el After...");

        autoCloseable.close();
    }
}
