package com.sena.sitea.controller;

import com.sena.sitea.services.EmailService;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.IOException;

@ManagedBean
@ViewScoped
public class EmailTestBean {
    
    private String destinatario = "tu-email@gmail.com";
    private String asunto = "Prueba SendGrid desde JSF";
    private String mensaje = "Este es un mensaje de prueba desde mi aplicación JSF con SendGrid";
    
    public void enviarEmailPrueba() {
        try {
            EmailService emailService = new EmailService();
            emailService.enviarEmailPrueba(destinatario, asunto, mensaje);
            System.out.println("Proceso completado, revisa logs");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error general: " + e.getMessage());
        }
    }
    
    // Getters y setters
    public String getDestinatario() { return destinatario; }
    public void setDestinatario(String destinatario) { this.destinatario = destinatario; }
    
    public String getAsunto() { return asunto; }
    public void setAsunto(String asunto) { this.asunto = asunto; }
    
    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
}
