package com.sena.sitea.controller;

import com.mycompany.webserviceconsumer.PerplexityAPIClient; // Tu cliente existente
import com.sena.sitea.entities.*;
import com.sena.sitea.services.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "informeBean")
@ViewScoped
public class InformeBean implements Serializable {

    // Datos Principales
    private Integer idCaracterizacion;
    private Caracterizacion caracterizacion;
    private Estudiante estudiante;
    
    // Dimensiones (Cargadas desde BD)
    private List<DimensionValoracion> dimensionesList;
    private DimSaludFisica dimSalud;
    private DimConductaAdaptativa dimAdaptativa;
    private DimBienestarEmocional dimEmocional;
    private DimPedagogica dimPedagogica;
    
    // Contenido Generado por IA
    private String conclusionIA;
    private String ajustesSugeridosIA;
    private boolean cargandoIA = false;

    // Servicios
    @EJB private CaracterizacionFacadeLocal caracterizacionFacade;
    @EJB private DimensionValoracionFacadeLocal dimensionFacade;
    // ... inyectar otros facades específicos si los creaste como entidades separadas

    public void cargarDatos() {
        try {
            String idParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
            if (idParam != null) {
                this.idCaracterizacion = Integer.parseInt(idParam);
                this.caracterizacion = caracterizacionFacade.find(idCaracterizacion);
                this.estudiante = caracterizacion.getEstudianteIdEstudiante();
                
                // Cargar la lista genérica de dimensiones (resúmenes)
                this.dimensionesList = dimensionFacade.findByCaracterizacion(idCaracterizacion);
                if (this.dimensionesList == null) this.dimensionesList = java.util.Collections.emptyList();
                
                // AQUÍ: Cargarías los objetos específicos de las otras tablas
                // dimSalud = saludFacade.findByCaracterizacion(...) 
                // Para el ejemplo, usaremos los datos de la lista genérica que fuimos guardando en 'fortalezas' y 'areasApoyo'
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // --- INTEGRACIÓN CON PERPLEXITY ---
    public void generarAnalisisIA() {
        this.cargandoIA = true;
        try {
            StringBuilder prompt = new StringBuilder();
            prompt.append("Actúa como un experto en Educación Inclusiva y PIAR (Decreto 1421 Colombia). ");
            // Agregar información de grado/curso de forma segura
            String gradoLabel = getGradoLabel();
            prompt.append("Analiza los siguientes datos de un estudiante de grado ").append(gradoLabel).append(":\n\n");
            
            // Agregar datos de las dimensiones al prompt
            if (dimensionesList != null) {
                for (DimensionValoracion dim : dimensionesList) {
                    prompt.append("- ").append(dim.getNombreDimension() != null ? dim.getNombreDimension() : "(sin nombre)").append(": ");
                    prompt.append(dim.getFortalezas() != null ? dim.getFortalezas() : "-").append(". Apoyos: ").append(dim.getAreasApoyo() != null ? dim.getAreasApoyo() : "-").append("\n");
                }
            }
            
            prompt.append("\nGenera 2 secciones breves en formato HTML simple (usando <p>, <ul>, <li>, <strong>):\n");
            prompt.append("1. PERFIL PEDAGÓGICO: Un párrafo integrando sus fortalezas y barreras.\n");
            prompt.append("2. AJUSTES RAZONABLES SUGERIDOS: Una lista de 3 a 5 estrategias puntuales para el aula.");

            // Llamada a tu clase cliente existente
            String respuesta = PerplexityAPIClient.generarEstrategia(prompt.toString());
            
            // Procesar respuesta (Asumiendo que la IA separa por secciones o formateamos nosotros)
            // Para simplicidad, guardamos todo en una variable o lo dividimos si la IA usa marcas
            this.conclusionIA = respuesta; 
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "IA", "Análisis generado con éxito."));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error IA", e.getMessage()));
        } finally {
            this.cargandoIA = false;
        }
    }
    
    public void guardarInformeFinal() {
        // Guardar el texto generado por la IA en la base de datos (campo 'recomendaciones' de Caracterizacion)
        try {
            caracterizacion.setRecomendaciones(conclusionIA);
            caracterizacion.setFechaFinalizacion(new Date());
            caracterizacionFacade.edit(caracterizacion);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardado", "Informe finalizado."));
        } catch (Exception e) {
             e.printStackTrace();
        }
    }

    // Getters y Setters
    public Caracterizacion getCaracterizacion() { return caracterizacion; }
    public Estudiante getEstudiante() { return estudiante; }
    public List<DimensionValoracion> getDimensionesList() { return dimensionesList; }
    public String getConclusionIA() { return conclusionIA; }
    public void setConclusionIA(String conclusionIA) { this.conclusionIA = conclusionIA; }
    public boolean isCargandoIA() { return cargandoIA; }

    // Utilitario: etiqueta legible del grado/curso para la vista y para el prompt
    public String getGradoLabel() {
        try {
            if (estudiante == null) return "(sin estudiante)";
            if (estudiante.getCursoIdCurso() != null) {
                Curso c = estudiante.getCursoIdCurso();
                Grado g = c.getGradoIdGrado();
                if (g != null) {
                    return g.getNivelGrado() + " " + g.getCicloGrado();
                }
                // fallback to codigoCurso
                return String.valueOf(c.getCodigoCurso());
            }
            return "(sin curso)";
        } catch (Exception e) {
            return "(error leyenda grado)";
        }
    }

    // Calcula la edad aproximada en años a partir de la fecha de nacimiento
    public Integer getEdad() {
        try {
            if (estudiante == null || estudiante.getFechaNacimiento() == null) return null;
            java.time.LocalDate nacimiento = estudiante.getFechaNacimiento().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            java.time.Period p = java.time.Period.between(nacimiento, java.time.LocalDate.now());
            return p.getYears();
        } catch (Exception e) {
            return null;
        }
    }
}