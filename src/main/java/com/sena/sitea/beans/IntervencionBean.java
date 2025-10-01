/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sena.sitea.beans;

/**
 *
 * @author brandon
 */


import com.mycompany.webserviceconsumer.PerplexityAPIClient;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class IntervencionBean {

    private String fichaEstudiante;
    private String resultado;

    public String getFichaEstudiante() {
        return fichaEstudiante;
    }

    public void setFichaEstudiante(String fichaEstudiante) {
        this.fichaEstudiante = fichaEstudiante;
    }

    public String getResultado() {
        return resultado;
    }

    public void consultarRecomendacion() {
        PerplexityAPIClient client = new PerplexityAPIClient();
        try {
            resultado = client.obtenerRecomendacion(fichaEstudiante);
        } catch (Exception e) {
            resultado = "Error: " + e.getMessage();
        }
    }
}
