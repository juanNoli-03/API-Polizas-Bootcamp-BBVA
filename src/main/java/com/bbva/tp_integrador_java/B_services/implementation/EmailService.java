package com.bbva.tp_integrador_java.B_services.implementation;

import com.bbva.tp_integrador_java.D_models.Poliza;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String codPoliza, String emailDestino, String subject, String accion) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setTo(emailDestino);
        helper.setSubject(subject);

        String htmlMsg = "<h3>Estimado Cliente. </h3>" +
                "<p>" + "Se " + accion + " la Poliza con CODIGO = " + "<b>" + codPoliza + "</b>" + "</p>" +
                "<p><b>Muchas gracias!</b></p>";
        helper.setText(htmlMsg, true);  // `true` para habilitar HTML
        javaMailSender.send(mimeMessage);
    }
}