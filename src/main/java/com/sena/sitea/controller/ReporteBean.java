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
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author bjcab
 */
@Named(value = "reporteBean")
@RequestScoped
public class ReporteBean implements Serializable {
    
    @EJB private EstudianteFacadeLocal estudianteFacade;
    @EJB private CaracterizacionFacadeLocal caracterizacionFacade;

    private List<DiagnosticoDTO> diagnosticos;
    
    public long getTotalEstudiantes() {
        return estudianteFacade.count();
    }

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
    
    // DTO anidado
    public static class DiagnosticoDTO {
        private String diagnostico;
        private Long cantidad;
        public DiagnosticoDTO(String d, Long c) {
            diagnostico = d; cantidad = c;
        }
        public String getDiagnostico() { return diagnostico; }
        public Long getCantidad() { return cantidad; }
    }
}

