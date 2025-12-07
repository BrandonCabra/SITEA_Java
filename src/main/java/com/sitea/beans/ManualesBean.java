package com.sitea.beans;

import com.sitea.models.Manual;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class ManualesBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Manual> manuales;
    
    @PostConstruct
    public void init() {
        manuales = new ArrayList<>();
        
        manuales.add(new Manual("fas fa-book", "Guía para Padres",
            "Manual completo con estrategias y recomendaciones para apoyar el aprendizaje de niños con dislexia, discalculia y otros TEA en el hogar.",
            "Popular", "2.5 MB", "45 páginas", "guia-padres.pdf"));
        
        manuales.add(new Manual("fas fa-graduation-cap", "Manual para Educadores",
            "Recursos y estrategias para la inclusión educativa y adaptaciones curriculares para estudiantes con Trastorno Específico del Aprendizaje.",
            "Recomendado", "3.2 MB", "68 páginas", "manual-educadores.pdf"));
        
        manuales.add(new Manual("fas fa-clipboard-list", "Guía de Evaluación",
            "Protocolos de evaluación psicopedagógica y diagnóstico de TEA basados en evidencia científica.",
            "Profesional", "1.8 MB", "32 páginas", "guia-evaluacion.pdf"));
        
        manuales.add(new Manual("fas fa-bullseye", "Estrategias de Intervención",
            "Técnicas y metodologías psicopedagógicas para la intervención en dislexia, discalculia y disgrafía.",
            "Nuevo", "2.1 MB", "52 páginas", "estrategias-intervencion.pdf"));
        
        manuales.add(new Manual("fas fa-chart-line", "Herramientas de Evaluación",
            "Instrumentos y escalas para la evaluación del desarrollo y progreso académico.",
            "Actualizado", "1.5 MB", "28 páginas", "herramientas-evaluacion.pdf"));
        
        manuales.add(new Manual("fas fa-globe", "Recursos Online",
            "Enlaces a recursos digitales, videos educativos y materiales interactivos para el apoyo continuo.",
            "Digital", "Online", "Varios", null));
    }
    
    public void downloadManual(Manual manual) {
        if (manual.getFile() != null) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Descarga", 
                    "Descargando: " + manual.getFile()));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Recursos Online", 
                    "Redirigiendo a recursos online..."));
        }
    }
    
    public List<Manual> getManuales() {
        return manuales;
    }
}
