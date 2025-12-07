package com.sitea.beans;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class ResetPasswordBean implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String token;
    private String newPassword;
    private String confirmPassword;
    
    public String cambiarPassword() {
        if (token == null || token.trim().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Por favor ingresa el código de recuperación"));
            return null;
        }
        
        if (newPassword == null || newPassword.trim().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Por favor ingresa la nueva contraseña"));
            return null;
        }
        
        if (newPassword.length() < 8) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La contraseña debe tener al menos 8 caracteres"));
            return null;
        }
        
        if (!newPassword.equals(confirmPassword)) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Las contraseñas no coinciden"));
            return null;
        }
        
        // Aquí iría la lógica para cambiar la contraseña
        FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", 
                "¡Contraseña actualizada exitosamente! Redirigiendo al login..."));
        
        return "home?faces-redirect=true";
    }
    
    // Getters y Setters
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public String getNewPassword() { return newPassword; }
    public void setNewPassword(String newPassword) { this.newPassword = newPassword; }
    public String getConfirmPassword() { return confirmPassword; }
    public void setConfirmPassword(String confirmPassword) { this.confirmPassword = confirmPassword; }
}
