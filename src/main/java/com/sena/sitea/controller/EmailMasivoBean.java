/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sena.sitea.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import com.sena.sitea.services.EmailService;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author brandon
 */

@Named("emailMasivoBean")
@ViewScoped

public class EmailMasivoBean implements Serializable {
    @Inject
    private EmailService emailService;
    
    private String asunto = "Prueba SendGrid desde SITEA";
    private String mensaje = "Este es un mensaje de prueba desde mi aplicación SITEA con SendGrid";
    private String destinatariosTexto = "jm4517499@gmail.com; bjcabrab@gmail.com"; // Un email por línea, separado con saltos
    private String resultadoEnvio;

    // Getters & Setters ...

    public EmailService getEmailService() {
        return emailService;
    }

    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getDestinatariosTexto() {
        return destinatariosTexto;
    }

    public void setDestinatariosTexto(String destinatariosTexto) {
        this.destinatariosTexto = destinatariosTexto;
    }

    public String getResultadoEnvio() {
        return resultadoEnvio;
    }

    public void setResultadoEnvio(String resultadoEnvio) {
        this.resultadoEnvio = resultadoEnvio;
    }
    

    public void enviarEmailMasivo() {
        try {
            // Convertir el textarea en una lista de emails (eliminar espacios y líneas vacías)
            List<String> destinatarios = Arrays.stream(destinatariosTexto.split("\\r?\\n"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
            
            int enviados = emailService.enviarEmailMasivo(asunto, mensaje, destinatarios);

            resultadoEnvio = enviados + " correos enviados correctamente.";
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, 
                    "Éxito", resultadoEnvio));
        } catch (Exception e) {
            resultadoEnvio = "Error: " + e.getMessage();
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", resultadoEnvio));
        }
    }
}
