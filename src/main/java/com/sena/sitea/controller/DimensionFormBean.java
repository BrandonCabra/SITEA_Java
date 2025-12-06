package com.sena.sitea.controller;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;

@Named("dimensionFormBean")
@ViewScoped
public class DimensionFormBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private String studentId;
    private Integer caracterizacionId;
    private String evaluationDate;
    private String observaciones;
    private String recomendaciones;

    // Simple structures to back the view
    public static class Question implements Serializable {
        public String id;
        public String text;
        public Question(String id, String text) { this.id = id; this.text = text; }
        public String getId(){return id;} public String getText(){return text;}
    }
    public static class Section implements Serializable {
        public String title;
        public String guidingQuestion;
        public String answer;
        public Section(String title, String guidingQuestion){this.title=title;this.guidingQuestion=guidingQuestion;}
        public String getTitle(){return title;} public String getGuidingQuestion(){return guidingQuestion;} public String getAnswer(){return answer;} public void setAnswer(String a){this.answer=a;}
    }
    public static class Guide implements Serializable {
        public String title;
        public String guidanceText;
        public String observations;
        public Guide(String t, String g){this.title=t;this.guidanceText=g;}
        public String getTitle(){return title;} public String getGuidanceText(){return guidanceText;} public String getObservations(){return observations;} public void setObservations(String o){this.observations=o;}
    }

    private List<Question> likertQuestions;
    private Map<String,Integer> likertResponses; // clave -> valor (1..5)
    private List<Section> structuredSections;
    private List<Guide> observationGuides;

    private int likertScore;
    private int totalLikertPoints;
    private int maxLikertPoints;

    @PostConstruct
    public void init(){
        // default values
        studentId = "Desconocido";
        evaluationDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        likertQuestions = new ArrayList<>();
        likertResponses = new HashMap<>();
        structuredSections = new ArrayList<>();
        observationGuides = new ArrayList<>();

        // sample likert questions (IDs are arbitrary keys used by views)
        likertQuestions.add(new Question("D1_q1","La familia brinda apoyo emocional al estudiante."));
        likertQuestions.add(new Question("D1_q2","Las condiciones del hogar favorecen el estudio."));
        likertQuestions.add(new Question("D1_q3","Los miembros de la familia participan en la educación del estudiante."));

        // sample structured sections
        structuredSections.add(new Section("Composición Familiar","¿Quiénes viven en el hogar?"));
        structuredSections.add(new Section("Recursos","¿Qué recursos tiene la familia para apoyar el aprendizaje?"));

        // sample observation guides
        observationGuides.add(new Guide("Interacción Familiar","Observar interacciones en la familia: comunicación, roles, apoyo."));
        observationGuides.add(new Guide("Recursos Materiales","Observar disponibilidad de espacios y materiales de estudio."));

        // initialize some response keys default to 0 (not answered)
        // initialize some response keys default to 0 (not answered) for dimensions D1..D8 (3 items each)
        for (int d = 1; d <= 8; d++) {
            String dim = "D" + d;
            for (int q = 1; q <= 3; q++) {
                likertResponses.put(dim + "_q" + q, 0);
            }
        }

        // Intentar obtener caracterizacion actual desde el controlador de sesión si está disponible
        try {
            javax.faces.context.FacesContext fc = javax.faces.context.FacesContext.getCurrentInstance();
            if (fc != null) {
                com.sena.sitea.controller.Caracterizacioncontroller ctrl = fc.getApplication().evaluateExpressionGet(fc, "#{caracterizacioncontroller}", com.sena.sitea.controller.Caracterizacioncontroller.class);
                if (ctrl != null && ctrl.getCar() != null && ctrl.getCar().getIdCaracterizacion() != null) {
                    this.caracterizacionId = ctrl.getCar().getIdCaracterizacion();
                }
            }
        } catch (Exception ex) {
            // no crítico; sólo intentar enlazar si el usuario proviene del flujo de caracterización
        }
    }

    // Getters / Setters
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public String getEvaluationDate() { return evaluationDate; }
    public void setEvaluationDate(String evaluationDate) { this.evaluationDate = evaluationDate; }
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
    public String getRecomendaciones() { return recomendaciones; }
    public void setRecomendaciones(String recomendaciones) { this.recomendaciones = recomendaciones; }

    public List<Question> getLikertQuestions(){ return likertQuestions; }
    public Map<String,Integer> getLikertResponses(){ return likertResponses; }

    // Safe indexed accessors for EL binding: #{dimensionFormBean.likertValue['D1_q1']}
    public Integer getLikertValue(String key) {
        Integer v = likertResponses.get(key);
        if (v == null) {
            likertResponses.put(key, 0);
            return 0;
        }
        return v;
    }

    public void setLikertValue(String key, Integer value) {
        if (value == null) value = 0;
        likertResponses.put(key, value);
    }
    public List<Section> getStructuredSections(){ return structuredSections; }
    public List<Guide> getObservationGuides(){ return observationGuides; }

    public int getLikertScore(){ return likertScore; }
    public int getTotalLikertPoints(){ return totalLikertPoints; }
    public int getMaxLikertPoints(){ return maxLikertPoints; }

    // Actions
    public void calculateLikertScore(){
        int sum=0; int count=0;
        for(Integer v: likertResponses.values()){
            if(v!=null && v>0){ sum += v; count++; }
        }
        totalLikertPoints = sum;
        maxLikertPoints = count * 5;
        likertScore = (maxLikertPoints==0)?0: (int)Math.round(((double)totalLikertPoints/(double)maxLikertPoints)*100);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Puntuación calculada", likertScore+"%"));
    }
    @EJB
    private com.sena.sitea.services.ValoracionInstrumentoService valoracionInstrumentoService;

    public String save(String dimensionId){
        // Validación: todas las preguntas obligatorias para la dimensión deben tener respuesta (>0)
        boolean missing = false;
        for (Map.Entry<String,Integer> e : likertResponses.entrySet()){
            if(e.getKey().startsWith(dimensionId + "_q")){
                Integer v = e.getValue();
                if (v == null || v == 0) {
                    missing = true;
                    break;
                }
            }
        }
        if (missing) {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Faltan respuestas", "Por favor responda todas las preguntas obligatorias de la dimensión " + dimensionId));
            return null; // no navegar
        }

        // Si todo está bien, calcular puntuación, persistir y mostrar confirmación
        calculateLikertScore();

        // Preparar respuestas a enviar al servicio
        Map<String,Integer> respuestasADepositar = new HashMap<>();
        for (Map.Entry<String,Integer> e : likertResponses.entrySet()){
            if (e.getKey().startsWith(dimensionId + "_q")) {
                respuestasADepositar.put(e.getKey(), e.getValue());
            }
        }

        // convertir evaluationDate a Date
        Date fecha = null;
        try {
            fecha = new SimpleDateFormat("yyyy-MM-dd").parse(evaluationDate);
        } catch (ParseException ex) {
            fecha = new Date();
        }

        // Llamar al servicio para persistir
        try {
            valoracionInstrumentoService.saveInstrument(this.caracterizacionId, dimensionId, studentId, fecha, likertScore, observaciones, recomendaciones, respuestasADepositar);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Valoración guardada", "Dimensión " + dimensionId));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al guardar", ex.getMessage()));
        }
        return null; // stay on page
    }

    public String goBack(){
        // Navigate back to main caracterization page
        return "/views/caracterizacion/index_1.xhtml?faces-redirect=true";
    }
}
