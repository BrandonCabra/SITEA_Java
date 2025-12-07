package com.sitea.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Managed Bean para la página de inicio
 */
@Named
@RequestScoped
public class HomeBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    public HomeBean() {
    }
    
    /**
     * Navega a la página de login (simulado)
     */
    public String goToLogin() {
        // En producción, esto navegaría al sistema de login
        return "login?faces-redirect=true";
    }
    
    /**
     * Obtiene el mensaje de bienvenida
     */
    public String getWelcomeMessage() {
        return "Bienvenido a SI TEA - Sistema de Información para Trastornos Específicos del Aprendizaje";
    }
}
