package com.sena.sitea.controller;

import com.sena.sitea.entities.Caracterizacion; // Asegúrate de importar la entidad
import com.sena.sitea.services.CaracterizacionFacadeLocal;
import com.sena.sitea.services.EstudianteFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped; // Cambiamos a ViewScoped para mejor manejo de la tabla

@Named(value = "reporteBean1")
@ViewScoped // Recomendado para mantener los datos de la tabla al paginar/filtrar
public class ReporteBean1 implements Serializable {
    
    @EJB 
    private EstudianteFacadeLocal estudianteFacade;
    
    @EJB 
    private CaracterizacionFacadeLocal caracterizacionFacade;

    private List<DiagnosticoDTO> diagnosticos;
    private List<Caracterizacion> listaDetallada; // Nueva lista para el reporte general

    // Total de estudiantes
    public long getTotalEstudiantes() {
        return estudianteFacade.count();
    }

    // Total de caracterizaciones
    public long getTotalCaracterizaciones() {
        return caracterizacionFacade.count();
    }

    // Lazy-loading de diagnósticos (Gráficos/Resumen)
    public List<DiagnosticoDTO> getDiagnosticos() {
        if (diagnosticos == null) {
            diagnosticos = new ArrayList<>();
            try {
                // Asegúrate que tu Facade tenga este método o usa findAll y procesa aquí
                List<Object[]> resultados = (List<Object[]>) caracterizacionFacade.contarPorDiagnostico();
                if (resultados != null) {
                    for (Object[] row : resultados) {
                        String diag = (String) row[0];
                        Long cantidad = (Long) row[1];
                        diagnosticos.add(new DiagnosticoDTO(diag, cantidad));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return diagnosticos;
    }

    // --- NUEVO: Lista Detallada para el Reporte General ---
    public List<Caracterizacion> getListaDetallada() {
        if (listaDetallada == null) {
            try {
                listaDetallada = caracterizacionFacade.findAll();
            } catch (Exception e) {
                listaDetallada = new ArrayList<>();
                e.printStackTrace();
            }
        }
        return listaDetallada;
    }

    // DTO anidado para el resumen
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