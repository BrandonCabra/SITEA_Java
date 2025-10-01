/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sena.sitea.services;

/**
 *
 * @author brandon
 */
import com.sendgrid.*;
import com.sendgrid.helpers.mail.*;
import com.sendgrid.helpers.mail.objects.*;
import java.io.IOException;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author brandon
 */
@Stateless


public class EmailService {
    
    // BORRAR ESTE MÉTODO CUANDO SE INTEGRE VARIABLES ENTORNO
    //private String getApiKey() {
        // TEMPORAL: Solo para desarrollo compartido
        // TODO: Cambiar por variable de entorno en producción
      // Reemplazar por tu clave real}
    
    public void enviarEmailPrueba(String toEmail, String subject, String message) throws IOException {
      
        // Leer API Key desde variable de entorno
        // para cuando se configuren variables de entorno 
        String apiKey = System.getenv("SENDGRID_API_KEY");
        // POR ESTA: String apiKey = getApiKey();
        
        if (apiKey == null) {
            throw new RuntimeException("SENDGRID_API_KEY no está configurada");
        }
                
        // Configurar remitente (cambiar por tu email verificado en SendGrid)
        Email from = new Email("sitea.edu@gmail.com");
        Email to = new Email(toEmail);
        Content content = new Content("text/plain", message);
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            
            System.out.println("Status Code: " + response.getStatusCode());
            System.out.println("Body: " + response.getBody());
            System.out.println("Headers: " + response.getHeaders());
            
            if (response.getStatusCode() == 202) {
                System.out.println("Email enviado correctamente!");
            } else {
                System.out.println("Error al enviar email");
            }
        } catch (IOException ex) {
            throw ex;
        }
    }
    
    public int enviarEmailMasivo(String asunto, String mensaje, List<String> destinatarios) {
        int exitosos = 0;
        
        // Opción 1: Usar personalizations de SendGrid (más eficiente)
        try {
            Mail mail = new Mail();
            mail.setFrom(new Email("sitea.edu@gmail.com"));
            mail.setSubject(asunto);
            mail.addContent(new Content("text/html", mensaje));
            
            // Agregar destinatarios en lotes de 1000 (límite SendGrid)
            Personalization personalization = new Personalization();
            for (int i = 0; i < Math.min(destinatarios.size(), 1000); i++) {
                personalization.addTo(new Email(destinatarios.get(i)));
            }
            mail.addPersonalization(personalization);
            
            SendGrid sg = new SendGrid(getApiKey());
            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            
            Response response = sg.api(request);
            
            if (response.getStatusCode() >= 200 && response.getStatusCode() < 300) {
                exitosos = Math.min(destinatarios.size(), 1000);
            }
            
        } catch (IOException e) {
            throw new RuntimeException("Error enviando correos masivos", e);
        }
        
        return exitosos;
    }

    private String getApiKey() {
    String apiKey = System.getenv("SENDGRID_API_KEY"); // O usa System.getProperty si lo agregaste como JVM option
    if(apiKey == null || apiKey.trim().isEmpty()) {
        throw new RuntimeException("SENDGRID_API_KEY no está configurada");
    }
    return apiKey;
}
    
}
