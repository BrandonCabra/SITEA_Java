package com.sitea.beans;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class ForgotPasswordBean implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String email;
    private String token;
    private boolean tokenGenerated = false;
    
    public String enviarCodigo() {
        if (email == null || email.trim().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Por favor ingresa tu email"));
            return null;
        }
        
        if (!email.contains("@")) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Por favor ingresa un email válido"));
            return null;
        }
        
        // Generar token (simulado)
        token = "TOKEN-" + System.currentTimeMillis();
        tokenGenerated = true;
        
        FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", 
                "Se ha generado un código de recuperación. Revisa tu email."));
        
        return null;
    }
    
    public String continuar() {
        return "reset-password?faces-redirect=true";
    }
    
    // Getters y Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public boolean isTokenGenerated() { return tokenGenerated; }
    public void setTokenGenerated(boolean tokenGenerated) { this.tokenGenerated = tokenGenerated; }
}
