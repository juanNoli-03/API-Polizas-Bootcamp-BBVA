package com.bbva.tp_integrador_java.B_servicesTest;

import com.bbva.tp_integrador_java.B_services.implementation.EmailService;
import com.bbva.tp_integrador_java.D_models.Poliza;
import com.bbva.tp_integrador_java.C_mocks.Mocks;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
public class EmailServiceTest {

    @Mock
    private JavaMailSender javaMailSender;

    @InjectMocks
    private EmailService emailService;

    private AutoCloseable autoCloseable;

    @BeforeEach
    void setup() {

        System.out.println("\nEste es el Before...");

        autoCloseable = MockitoAnnotations.openMocks(this);
    }

    //1- Test unitario sendEmail()
    //---------------------------------------------------------------------------------------------------------------
    @Test
    void testSendEmail () throws MessagingException {

        System.out.println("Esta es la ejecucion de la Prueba Unitaria 'testSendEmail ()'...");

        Poliza poliza = Mocks.polizaMock();

        MimeMessage mimeMessage = Mockito.mock(MimeMessage.class);

        Mockito.when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);

        emailService.sendEmail(poliza.getCodigoPoliza(), "juanmanuelnoli03@gmail.com", "Creacion Poliza",
                "Creación");

        Mockito.verify(javaMailSender, Mockito.times(1)).send(mimeMessage);
    }
    //---------------------------------------------------------------------------------------------------------------

    @AfterEach
    void tearDown() throws Exception {

        System.out.println("Este es el After...");

        autoCloseable.close();
    }
}
