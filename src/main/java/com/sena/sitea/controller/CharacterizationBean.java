package com.sena.sitea.controller;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

@Named("characterizationBean")
@ViewScoped
public class CharacterizationBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /* ==============================
       ===      ENTIDADES        ===
       ============================== */

    public static class Student {
        private String id;
        private String firstName;
        private String lastName;
        private String expediente;

        public String getFullName() {
            return firstName + " " + lastName;
        }

        // Getters / Setters
        public String getId() { return id; }
        public void setId(String id) { this.id = id; }

        public String getFirstName() { return firstName; }
        public void setFirstName(String firstName) { this.firstName = firstName; }

        public String getLastName() { return lastName; }
        public void setLastName(String lastName) { this.lastName = lastName; }

        public String getExpediente() { return expediente; }
        public void setExpediente(String expediente) { this.expediente = expediente; }
    }

    public static class Dimension {
        private String id;
        private String name;
        private String description;

        // Getters / Setters
        public String getId() { return id; }
        public void setId(String id) { this.id = id; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }

    public static class Assessment {
        private String id;
        private String dimensionId;
        private int progress;     // 0 - 100
        private Date completedAt;
        private String observations;
        private List<String> strengths;    // Fortalezas
        private List<String> needs;        // Necesidades
        private List<String> barriers;     // Barreras

        public String getCompletedAtDisplay() {
            if (completedAt == null) return "En proceso";
            return new SimpleDateFormat("yyyy-MM-dd").format(completedAt);
        }

        // Getters / Setters
        public String getId() { return id; }
        public void setId(String id) { this.id = id; }

        public String getDimensionId() { return dimensionId; }
        public void setDimensionId(String dimensionId) { this.dimensionId = dimensionId; }

        public int getProgress() { return progress; }
        public void setProgress(int progress) { this.progress = progress; }

        public Date getCompletedAt() { return completedAt; }
        public void setCompletedAt(Date completedAt) { this.completedAt = completedAt; }

        public String getObservations() { return observations; }
        public void setObservations(String observations) { this.observations = observations; }

        public List<String> getStrengths() { return strengths; }
        public void setStrengths(List<String> strengths) { this.strengths = strengths; }

        public List<String> getNeeds() { return needs; }
        public void setNeeds(List<String> needs) { this.needs = needs; }

        public List<String> getBarriers() { return barriers; }
        public void setBarriers(List<String> barriers) { this.barriers = barriers; }
    }


    /* ==============================
       ===      ATRIBUTOS        ===
       ============================== */

    private Student student;
    private Date startDate;
    private Date estimatedEndDate;

    private List<Dimension> dimensions;
    private List<Assessment> assessments;

    private Date familyMeetingDate;
    private String familyMeetingNotes;


    /* ==============================
       ===     INICIALIZACIÓN    ===
       ============================== */

    @PostConstruct
    public void init() {

        /* ==== Estudiante simulado ==== */
        student = new Student();
        student.setId("S001");
        student.setFirstName("Juan");
        student.setLastName("Pérez");
        student.setExpediente("EXP-2025-001");

        /* ==== Fechas ==== */
        startDate = new Date();

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 30);
        estimatedEndDate = cal.getTime();

        /* ==== Dimensiones MEN ==== */
        dimensions = new ArrayList<>();

        String[] names = {
                "Contexto Familiar",
                "Habilidades Intelectuales",
                "Bienestar Emocional",
                "Conducta Adaptativa",
                "Salud Física",
                "Participación Social",
                "Control del Entorno",
                "Dimensión Pedagógica"
        };

        for (int i = 0; i < names.length; i++) {
            Dimension d = new Dimension();
            d.setId("D" + (i + 1));
            d.setName(names[i]);
            d.setDescription("Descripción breve de " + names[i]);
            dimensions.add(d);
        }

        /* ==== Valoraciones simuladas ==== */
        assessments = new ArrayList<>();

        String[][] strengthsData = {
            {"Apoyo familiar consistente", "Ambiente familiar estable"},
            {"Razonamiento lógico avanzado", "Memoria excepcional"},
            {"Autoestima alta", "Resiliencia emocional"},
            {"Responsabilidad destacada", "Autonomía en tareas"},
            {"Estado nutricional óptimo", "Coordinación motora excelente"},
            {"Liderazgo natural", "Integración grupal exitosa"},
            {"Capacidad de toma de decisiones", "Independencia en acciones"},
            {"Desempeño académico sobresaliente", "Motivación intrínseca"}
        };

        String[][] needsData = {
            {"Comunicación familiar más fluida", "Mayor participación en decisiones"},
            {"Desarrollo de habilidades sociales", "Estrategias para resolver conflictos"},
            {"Manejo del estrés académico", "Técnicas de relajación"},
            {"Mejora en control impulsivo", "Regulación emocional"},
            {"Fortalecimiento óseo", "Actividad física complementaria"},
            {"Mayor inclusión en actividades grupales", "Desarrollo de habilidades de colaboración"},
            {"Autonomía en planificación", "Gestión del tiempo"},
            {"Profundización en contenidos avanzados", "Enriquecimiento curricular"}
        };

        String[][] barriersData = {
            {"Situación económica limitada", "Acceso a recursos educativos"},
            {"Dificultades en lectura comprensiva", "Procesamiento lento de información"},
            {"Ansiedad generalizada", "Miedos específicos"},
            {"Dificultad en seguimiento de instrucciones", "Impulsividad en respuestas"},
            {"Antecedentes de enfermedad crónica", "Limitaciones en movilidad"},
            {"Tímidez extrema", "Fobia social moderada"},
            {"Dependencia de figuras de autoridad", "Baja autoconfianza"},
            {"Falta de recursos tecnológicos", "Brecha digital"}
        };

        for (int i = 0; i < dimensions.size(); i++) {
            Dimension d = dimensions.get(i);
            Assessment a = new Assessment();
            a.setId(UUID.randomUUID().toString());
            a.setDimensionId(d.getId());

            // Datos de ejemplo
            if (i < 3) {
                a.setProgress(100);
                a.setCompletedAt(new Date());
            } else {
                a.setProgress(0);
            }

            a.setObservations("Evaluación en proceso para " + d.getName());
            a.setStrengths(Arrays.asList(strengthsData[i]));
            a.setNeeds(Arrays.asList(needsData[i]));
            a.setBarriers(Arrays.asList(barriersData[i]));

            assessments.add(a);
        }

        /* ==== Reunión con familia ==== */
        familyMeetingDate = new Date();
        familyMeetingNotes = "Se realizó reunión con la familia. Seguimiento mensual.";
    }


    /* =====================================
       =====     GETTERS PRINCIPALES    =====
       ===================================== */

    public Student getStudent() { return student; }
    public List<Dimension> getDimensions() { return dimensions; }
    public List<Assessment> getAssessments() { return assessments; }

    public Date getStartDate() { return startDate; }
    public Date getEstimatedEndDate() { return estimatedEndDate; }
    public Date getFamilyMeetingDate() { return familyMeetingDate; }
    public String getFamilyMeetingNotes() { return familyMeetingNotes; }
    
    public String getFamilyMeetingDateDisplay() {
        if (familyMeetingDate == null) return "No registrada";
        return new SimpleDateFormat("yyyy-MM-dd").format(familyMeetingDate);
    }


    /* =====================================
       =====  LÓGICA DE PROGRESO / ESTADO ===
       ===================================== */

    public int getOverallProgress() {
        int sum = 0;
        for (Assessment a : assessments) sum += a.getProgress();
        return Math.round((float) sum / Math.max(1, assessments.size()));
    }

    public int getCompletedCount() {
        int c = 0;
        for (Assessment a : assessments)
            if (a.getProgress() >= 100) c++;
        return c;
    }

    public int getPercentageComplete() {
        return Math.round((getCompletedCount() * 100f) / dimensions.size());
    }

    public String getStartDateDisplay() {
        return new SimpleDateFormat("yyyy-MM-dd").format(startDate);
    }

    public String getEstimatedEndDateDisplay() {
        return new SimpleDateFormat("yyyy-MM-dd").format(estimatedEndDate);
    }

    public int getProgressForDimension(String dimensionId) {
        for (Assessment a : assessments)
            if (a.getDimensionId().equals(dimensionId))
                return a.getProgress();
        return 0;
    }

    public String getStatusForDimension(String dimensionId) {
        int p = getProgressForDimension(dimensionId);

        if (p >= 100) return "Completada";
        if (p >= 60) return "Avanzada";
        if (p >= 20) return "En proceso";
        return "Pendiente";
    }

    public String getDimensionName(String dimensionId) {
        for (Dimension d : dimensions)
            if (d.getId().equals(dimensionId))
                return d.getName();
        return dimensionId;
    }

    public List<String> getStrengthsForDimension(String dimensionId) {
        for (Assessment a : assessments)
            if (a.getDimensionId().equals(dimensionId) && a.getStrengths() != null)
                return a.getStrengths();
        return new ArrayList<>();
    }

    public List<String> getNeedsForDimension(String dimensionId) {
        for (Assessment a : assessments)
            if (a.getDimensionId().equals(dimensionId) && a.getNeeds() != null)
                return a.getNeeds();
        return new ArrayList<>();
    }

    public List<String> getBarriersForDimension(String dimensionId) {
        for (Assessment a : assessments)
            if (a.getDimensionId().equals(dimensionId) && a.getBarriers() != null)
                return a.getBarriers();
        return new ArrayList<>();
    }


    /* =====================================
       =====        ACCIONES JSF         =====
       ===================================== */

    public String goBack() {
        return "/characterization/list.xhtml?faces-redirect=true";
    }

    public void openDimension(String dimensionId) {
        FacesMessage msg = new FacesMessage(
                FacesMessage.SEVERITY_INFO,
                "Abrir dimensión",
                "Abrir valoración para: " + getDimensionName(dimensionId)
        );
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String generateReport() {
        if (getOverallProgress() < 100) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, 
                            "Informe", "Debe completar todas las dimensiones.")
            );
            return null;
        }

        FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Informe", "Informe generado correctamente.")
        );
        return null;
    }

    public String goToPiar() {
        if (getOverallProgress() < 95) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, 
                            "PIAR", "Debe completar al menos el 95% de la caracterización.")
            );
            return null;
        }

        return "/piar/create.xhtml?faces-redirect=true";
    }
}
