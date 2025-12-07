package com.sitea.beans;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class ContactoBean implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String nombre;
    private String email;
    private String telefono;
    private String mensaje;
    
    public String enviarMensaje() {
        // Validaciones
        if (nombre == null || nombre.trim().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El nombre es requerido"));
            return null;
        }
        
        if (email == null || email.trim().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El email es requerido"));
            return null;
        }
        
        if (mensaje == null || mensaje.trim().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El mensaje es requerido"));
            return null;
        }
        
        // Aquí iría la lógica para enviar el email
        FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", 
                "Mensaje enviado exitosamente. Nos pondremos en contacto contigo pronto."));
        
        // Limpiar formulario
        nombre = null;
        email = null;
        telefono = null;
        mensaje = null;
        
        return null;
    }
    
    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
}
