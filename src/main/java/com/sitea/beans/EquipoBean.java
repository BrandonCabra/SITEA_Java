package com.sitea.beans;

import com.sitea.models.Miembro;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class EquipoBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Miembro> equipo;
    
    @PostConstruct
    public void init() {
        equipo = new ArrayList<>();
        
        equipo.add(new Miembro("Dr. Juan Pérez", "Psicopedagogo",
            "Especialista en evaluación y diagnóstico del Trastorno Específico del Aprendizaje con más de 15 años de experiencia.",
            "👨‍⚕️"));
        
        equipo.add(new Miembro("Dra. María González", "Psicóloga Educativa",
            "Experta en intervención psicopedagógica y desarrollo de estrategias de aprendizaje.",
            "👩‍⚕️"));
        
        equipo.add(new Miembro("Lic. Carlos Rodríguez", "Especialista en Dislexia",
            "Especializado en intervención de lectoescritura y rehabilitación de la dislexia.",
            "👨‍🏫"));
        
        equipo.add(new Miembro("Lic. Ana Martínez", "Especialista en Discalculia",
            "Especialista en intervención matemática y rehabilitación de la discalculia.",
            "👩‍🏫"));
        
        equipo.add(new Miembro("Dra. Laura Torres", "Neuropsicóloga",
            "Evaluación neuropsicológica y programas de estimulación cognitiva para el aprendizaje.",
            "👩‍💼"));
    }
    
    public List<Miembro> getEquipo() {
        return equipo;
    }
}
