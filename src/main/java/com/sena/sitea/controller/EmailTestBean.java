 package com.sena.sitea.controller;

import com.sena.sitea.services.EmailService;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

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
            
            // Agregar mensaje de éxito
        FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_INFO, 
            "Éxito", "Correo enviado correctamente"));
        
        /*return null; // Permanecer en la misma página*/
            
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error general: " + e.getMessage());
            
            // Mensaje de error
        FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_ERROR, 
            "Error", "No se pudo enviar el correo: " + e.getMessage()));
        
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
