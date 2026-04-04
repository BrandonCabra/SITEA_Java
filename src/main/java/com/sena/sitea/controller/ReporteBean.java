/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.sena.sitea.controller;

import com.sena.sitea.services.CaracterizacionFacadeLocal;
import com.sena.sitea.services.EstudianteFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;

import java.util.List;

import java.util.ArrayList;

@Named(value = "reporteBean")
@RequestScoped
public class ReporteBean implements Serializable {
    
    @EJB 
    private EstudianteFacadeLocal estudianteFacade;
    
    @EJB 
    private CaracterizacionFacadeLocal caracterizacionFacade;

    private List<DiagnosticoDTO> diagnosticos;

    // Total de estudiantes
    public long getTotalEstudiantes() {
        return estudianteFacade.count();
    }

    // Total de caracterizaciones
    public long getTotalCaracterizaciones() {
        return caracterizacionFacade.count();
    }

    // Lazy-loading de diagnósticos
    public List<DiagnosticoDTO> getDiagnosticos() {
        if (diagnosticos == null) {
            diagnosticos = new ArrayList<>();
            for (Object[] row : caracterizacionFacade.contarPorDiagnostico()) {
                String diag = (String) row[0];
                Long cantidad = (Long) row[1];
                diagnosticos.add(new DiagnosticoDTO(diag, cantidad));
            }
        }
        return diagnosticos;
    }

    // 📌 Devuelve etiquetas como cadena JS válida: "Diag1","Diag2","Diag3"
    public String getEtiquetasDiagnosticosJS() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getDiagnosticos().size(); i++) {
            if (i > 0) sb.append(",");
            sb.append("\"").append(getDiagnosticos().get(i).getDiagnostico()).append("\"");
        }
        return sb.toString();
    }

    // 📌 Devuelve valores como cadena JS válida: 10,20,30
    public String getValoresDiagnosticosJS() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getDiagnosticos().size(); i++) {
            if (i > 0) sb.append(",");
            sb.append(getDiagnosticos().get(i).getCantidad());
        }
        return sb.toString();
    }

    // DTO anidado
    public static class DiagnosticoDTO {
        private String diagnostico;
        private Long cantidad;

        public DiagnosticoDTO(String diagnostico, Long cantidad) {
            this.diagnostico = diagnostico;
            this.cantidad = cantidad;
        }

        public String getDiagnostico() { return diagnostico; }
        public Long getCantidad() { return cantidad; }
    }
}