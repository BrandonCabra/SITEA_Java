package com.sitea.beans;

import com.sitea.models.Servicio;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Managed Bean para la página de servicios
 */
@Named
@RequestScoped
public class ServiciosBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private List<Servicio> servicios;
    
    @PostConstruct
    public void init() {
        servicios = new ArrayList<>();
        
        servicios.add(new Servicio(
            "fas fa-search",
            "Evaluación y Diagnóstico",
            "Realizamos evaluaciones integrales para el diagnóstico de dislexia, discalculia, disgrafía y otros TEA utilizando herramientas estandarizadas y validadas científicamente."
        ));
        
        servicios.add(new Servicio(
            "fas fa-bullseye",
            "Intervención Psicopedagógica",
            "Programas de intervención personalizados diseñados para fortalecer las áreas de aprendizaje afectadas y desarrollar estrategias compensatorias."
        ));
        
        servicios.add(new Servicio(
            "fas fa-users",
            "Apoyo Familiar y Escolar",
            "Orientación y capacitación para familias y docentes, proporcionando estrategias prácticas para el apoyo académico y emocional."
        ));
        
        servicios.add(new Servicio(
            "fas fa-book",
            "Refuerzo en Lectoescritura",
            "Programas especializados para mejorar las habilidades de lectura, escritura y comprensión lectora en estudiantes con dislexia y disgrafía."
        ));
        
        servicios.add(new Servicio(
            "fas fa-calculator",
            "Apoyo en Matemáticas",
            "Intervenciones específicas para estudiantes con discalculia, utilizando metodologías multisensoriales y adaptadas."
        ));
        
        servicios.add(new Servicio(
            "fas fa-graduation-cap",
            "Adaptaciones Curriculares",
            "Asesoramiento para la implementación de adaptaciones curriculares y metodológicas en el entorno educativo."
        ));
    }
    
    public List<Servicio> getServicios() {
        return servicios;
    }
}
