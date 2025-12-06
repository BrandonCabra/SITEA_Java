package com.sena.sitea.beans;

import com.mycompany.webserviceconsumer.PerplexityAPIClient;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "intervencionBean")
@ViewScoped
public class IntervencionBean implements Serializable {

    // 1. Variable para capturar lo que escribes en el área de texto
    private String fichaEstudiante;
    
    // 2. Variable para mostrar la respuesta
    private String resultado;

    // --- ACCIÓN ---
    public void consultarRecomendacion() {
        System.out.println("--- Iniciando Test de Conexión ---");
        System.out.println("Datos enviados: " + fichaEstudiante);

        try {
            if (fichaEstudiante == null || fichaEstudiante.trim().isEmpty()) {
                this.resultado = "Por favor escribe algo en la ficha del estudiante.";
                return;
            }

            // Llamamos a la API (Asegúrate de que PerplexityAPIClient.java tenga las correcciones que te di antes)
            String respuestaApi = PerplexityAPIClient.generarEstrategia(fichaEstudiante);
            
            // Asignamos la respuesta
            this.resultado = respuestaApi;
            
        } catch (Exception e) {
            this.resultado = "ERROR CONECTANDO: " + e.getMessage();
            e.printStackTrace(); // Esto imprimirá el error real en la consola de NetBeans/Glassfish
        }
    }

    // --- GETTERS Y SETTERS OBLIGATORIOS ---
    public String getFichaEstudiante() { return fichaEstudiante; }
    public void setFichaEstudiante(String fichaEstudiante) { this.fichaEstudiante = fichaEstudiante; }

    public String getResultado() { return resultado; }
    public void setResultado(String resultado) { this.resultado = resultado; }
}