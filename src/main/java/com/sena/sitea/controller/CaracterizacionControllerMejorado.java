package com.sena.sitea.controller;

import com.sena.sitea.entities.Caracterizacion;
import com.sena.sitea.entities.DimensionValoracion;
import com.sena.sitea.entities.Estudiante;
import com.sena.sitea.entities.ObservacionSistematica;
import com.sena.sitea.services.CaracterizacionFacadeLocal;
import com.sena.sitea.services.DimensionValoracionFacadeLocal;
import com.sena.sitea.services.EstudianteFacadeLocal;
import com.sena.sitea.services.ObservacionSistematicaFacadeLocal;
import javax.inject.Named;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 * Controlador mejorado para el módulo de caracterización
 * Implementa RF-001 a RF-027 y HU-001 a HU-011
 * 
 * @author SITEA
 */
@Named(value = "caracterizacionControllerMejorado")
@SessionScoped
public class CaracterizacionControllerMejorado implements Serializable {

    private Caracterizacion caracterizacion = new Caracterizacion();
    private Estudiante estudiante = new Estudiante();
    private DimensionValoracion dimensionActual = new DimensionValoracion();
    private ObservacionSistematica observacionActual = new ObservacionSistematica();
    
    private List<SelectItem> listaEstudiantes;
    private List<DimensionValoracion> dimensiones;
    private List<ObservacionSistematica> observaciones;
    private String filtroEstado;
    private String filtroBusqueda;
    
    // Las 8 dimensiones según el MEN
    private static final String[] DIMENSIONES_MEN = {
        "Contexto y vida familiar",
        "Habilidades intelectuales",
        "Bienestar emocional",
        "Conducta adaptativa y desarrollo personal",
        "Salud y bienestar físico",
        "Participación e inclusión social",
        "Control del propio entorno",
        "Dimensión pedagógica"
    };
    
    @EJB
    private CaracterizacionFacadeLocal caracterizacionFacade;
    @EJB
    private EstudianteFacadeLocal estudianteFacade;
    @EJB
    private com.sena.sitea.services.ExpedienteService expedienteService;
    @EJB
    private DimensionValoracionFacadeLocal dimensionFacade;
    @EJB
    private ObservacionSistematicaFacadeLocal observacionFacade;

    public CaracterizacionControllerMejorado() {
    }
    @PostConstruct
    public void init() {
        if (this.filtroEstado == null || this.filtroEstado.isEmpty()) {
            this.filtroEstado = "TODOS";
        }
    }


    /**
     * RF-001: Crear pre-registro de estudiante
     * HU-001: Registro de estudiantes con TEA
     */
    public String crearPreRegistro() {
        try {
            // Validar duplicidad (RF-006)
            if (validarDuplicidad()) {
                addMessage(FacesMessage.SEVERITY_WARN, 
                    "Este estudiante ya se encuentra registrado. Expediente: " + estudiante.getExpedienteId());
                return null;
            }
            
            // Generar expediente único (RF-005)
            estudiante.setExpedienteId(generarExpedienteId());
            estudiante.setEstadoRegistro("PRE_REGISTRO");
            estudiante.setFechaRegistro(new Date());
            estudiante.setCreatedAt(new Date());
            estudiante.setUpdatedAt(new Date());
            
            // Obtener usuario actual del contexto
            Login login = (Login) FacesContext.getCurrentInstance()
                    .getExternalContext().getSessionMap().get("login");
            if (login != null && login.getUsuario() != null) {
                estudiante.setCreatedBy(login.getUsuario().getIdUsuario());
            }
            
            estudianteFacade.create(estudiante);
            
            addMessage(FacesMessage.SEVERITY_INFO, 
                "Estudiante registrado exitosamente. Expediente: " + estudiante.getExpedienteId() + 
                ". Ahora puede iniciar el proceso de caracterización pedagógica");
            
            estudiante = new Estudiante();
            return "/views/caracterizacion/dashboardRegistro.xhtml?faces-redirect=true";
            
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, 
                "Error al registrar estudiante: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * RF-006: Validar duplicidad de expedientes
     */
    private boolean validarDuplicidad() {
        try {
            List<Estudiante> existentes = estudianteFacade.findAll();
            for (Estudiante est : existentes) {
                if (est.getNumeroDocumentoEstudiante().equals(estudiante.getNumeroDocumentoEstudiante())) {
                    estudiante = est; // Cargar el existente
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * RF-005: Generar expediente único formato EXP-TEA-YYYY-####
     */
    private String generarExpedienteId() {
        try {
            // Delegar generación al servicio de expedientes (transaccional y seguro)
            return expedienteService.generateExpediente(false);
        } catch (Exception e) {
            e.printStackTrace();
            return "EXP-TEA-" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        }
    }

    /**
     * RF-008: Iniciar proceso de caracterización
     * HU-002: Iniciar proceso de caracterización
     */
    public String iniciarCaracterizacion(Estudiante est) {
        try {
            // Verificar si ya tiene caracterización activa
            if (est.getCaracterizacionList() != null && !est.getCaracterizacionList().isEmpty()) {
                for (Caracterizacion car : est.getCaracterizacionList()) {
                    if ("EN_PROCESO".equals(car.getEstadoCaracterizacion()) || 
                        "INICIADA".equals(car.getEstadoCaracterizacion())) {
                        addMessage(FacesMessage.SEVERITY_WARN, 
                            "Este estudiante ya tiene una caracterización en proceso. Expediente: " + 
                            car.getExpedienteCaracterizacion());
                        caracterizacion = car;
                        return "/views/caracterizacion/dashboard.xhtml?faces-redirect=true";
                    }
                }
            }
            
            // Crear nueva caracterización
            caracterizacion = new Caracterizacion();
            caracterizacion.setEstudianteIdEstudiante(est);
            caracterizacion.setExpedienteCaracterizacion(generarExpedienteCaracterizacion());
            caracterizacion.setEstadoCaracterizacion("INICIADA");
            caracterizacion.setFechaInicio(new Date());
            caracterizacion.setCreatedAt(new Date());
            caracterizacion.setUpdatedAt(new Date());
            
            // Obtener usuario actual
            Login login = (Login) FacesContext.getCurrentInstance()
                    .getExternalContext().getSessionMap().get("login");
            if (login != null && login.getUsuario() != null) {
                caracterizacion.setCreatedBy(login.getUsuario().getIdUsuario());
            }
            
            caracterizacionFacade.create(caracterizacion);
            
            // RF-009: Inicializar las 8 dimensiones
            inicializarDimensiones();
            
            addMessage(FacesMessage.SEVERITY_INFO, 
                "Caracterización iniciada exitosamente. Expediente: " + 
                caracterizacion.getExpedienteCaracterizacion());
            
            return "/views/caracterizacion/dashboard.xhtml?faces-redirect=true";
            
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, 
                "Error al iniciar caracterización: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Generar expediente de caracterización formato CHAR-TEA-YYYY-####
     */
    private String generarExpedienteCaracterizacion() {
        try {
            // Usar expedienteService para generación consistente por año
            String code = expedienteService.generateExpediente(false);
            // Exp. del tipo EXP-TEA-YYYY-#### generado por el servicio. Para caracterización,
            // convertimos el prefijo a CHAR-TEA manteniendo el consecutivo y año.
            if (code != null && code.startsWith("EXP-TEA-")) {
                String tail = code.substring("EXP-TEA-".length()); // YYYY-####
                return "CHAR-TEA-" + tail;
            }
            // Fallback
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            return "CHAR-TEA-" + year + "-0001";
        } catch (Exception e) {
            e.printStackTrace();
            return "CHAR-TEA-" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        }
    }

    /**
     * RF-009: Inicializar las 8 dimensiones del MEN
     */
    private void inicializarDimensiones() {
        try {
            for (String nombreDimension : DIMENSIONES_MEN) {
                DimensionValoracion dimension = new DimensionValoracion();
                dimension.setCaracterizacionId(caracterizacion);
                dimension.setNombreDimension(nombreDimension);
                dimension.setEstado("PENDIENTE");
                dimension.setCreatedAt(new Date());
                dimension.setUpdatedAt(new Date());
                dimensionFacade.create(dimension);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * RF-004: Consultar y filtrar expedientes
     */
    public List<Caracterizacion> obtenerCaracterizacionesFiltradas() {
        try {
            List<Caracterizacion> todas = caracterizacionFacade.findAll();
            List<Caracterizacion> filtradas = new ArrayList<>();
            
            for (Caracterizacion car : todas) {
                boolean cumpleFiltro = true;
                
                // Filtro por estado
                if (filtroEstado != null && !filtroEstado.isEmpty() && 
                    !"TODOS".equals(filtroEstado)) {
                    cumpleFiltro = filtroEstado.equals(car.getEstadoCaracterizacion());
                }
                
                // Filtro por búsqueda (nombre, documento, etc.)
                if (cumpleFiltro && filtroBusqueda != null && !filtroBusqueda.isEmpty()) {
                    Estudiante est = car.getEstudianteIdEstudiante();
                    String busqueda = filtroBusqueda.toLowerCase();
                    cumpleFiltro = (est.getPrimerNombreEstudiante().toLowerCase().contains(busqueda) ||
                                   est.getPrimerApellidoEstudiante().toLowerCase().contains(busqueda) ||
                                   est.getNumeroDocumentoEstudiante().contains(busqueda) ||
                                   (car.getExpedienteCaracterizacion() != null && 
                                    car.getExpedienteCaracterizacion().contains(busqueda)));
                }
                
                if (cumpleFiltro) {
                    filtradas.add(car);
                }
            }
            
            return filtradas;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Cargar dimensiones de una caracterización
     */
    public void cargarDimensiones() {
        try {
            if (caracterizacion != null && caracterizacion.getIdCaracterizacion() != null) {
                dimensiones = dimensionFacade.findByCaracterizacion(
                    caracterizacion.getIdCaracterizacion());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Guardar valoración de dimensión
     */
    public void guardarDimension() {
        try {
            dimensionActual.setUpdatedAt(new Date());
            dimensionActual.setFechaValoracion(new Date());
            dimensionActual.setEstado("COMPLETADA");
            
            dimensionFacade.edit(dimensionActual);
            
            addMessage(FacesMessage.SEVERITY_INFO, 
                "Dimensión valorada correctamente");
            
            cargarDimensiones();
            dimensionActual = new DimensionValoracion();
            
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, 
                "Error al guardar dimensión: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * RF-015: Registrar observación sistemática
     * HU-004: Gestión de observación sistemática
     */
    public void registrarObservacion() {
        try {
            observacionActual.setCaracterizacionId(caracterizacion);
            observacionActual.setFechaObservacion(new Date());
            observacionActual.setCreatedAt(new Date());
            
            // Obtener usuario actual como observador
            Login login = (Login) FacesContext.getCurrentInstance()
                    .getExternalContext().getSessionMap().get("login");
            if (login != null) {
                observacionActual.setObservador(login.getNombreUsuario());
            }
            
            observacionFacade.create(observacionActual);
            
            addMessage(FacesMessage.SEVERITY_INFO, 
                "Observación registrada correctamente");
            
            cargarObservaciones();
            observacionActual = new ObservacionSistematica();
            
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, 
                "Error al registrar observación: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Cargar observaciones de una caracterización
     */
    public void cargarObservaciones() {
        try {
            if (caracterizacion != null && caracterizacion.getIdCaracterizacion() != null) {
                observaciones = observacionFacade.findByCaracterizacion(
                    caracterizacion.getIdCaracterizacion());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Lista de estudiantes sin caracterización o con pre-registro
     */
    public List<SelectItem> listaEstudiantesSinCaracterizacion() {
        listaEstudiantes = new ArrayList<>();
        try {
            for (Estudiante est : estudianteFacade.findAll()) {
                boolean tieneCaracterizacionActiva = false;
                
                if (est.getCaracterizacionList() != null) {
                    for (Caracterizacion car : est.getCaracterizacionList()) {
                        if ("EN_PROCESO".equals(car.getEstadoCaracterizacion()) || 
                            "INICIADA".equals(car.getEstadoCaracterizacion())) {
                            tieneCaracterizacionActiva = true;
                            break;
                        }
                    }
                }
                
                if (!tieneCaracterizacionActiva) {
                    SelectItem item = new SelectItem(est.getIdEstudiante(), 
                        est.getNumeroDocumentoEstudiante() + " - " + 
                        est.getPrimerNombreEstudiante() + " " + 
                        est.getPrimerApellidoEstudiante());
                    listaEstudiantes.add(item);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaEstudiantes;
    }

    /**
     * Calcular porcentaje de avance de la caracterización
     */
    public int calcularPorcentajeAvance() {
        try {
            if (dimensiones == null || dimensiones.isEmpty()) {
                cargarDimensiones();
            }
            
            if (dimensiones == null || dimensiones.isEmpty()) {
                return 0;
            }
            
            int completadas = 0;
            for (DimensionValoracion dim : dimensiones) {
                if ("COMPLETADA".equals(dim.getEstado())) {
                    completadas++;
                }
            }
            
            return (completadas * 100) / dimensiones.size();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Cargar dimensiones actuales para visualización en dashboard
     */
    public List<DimensionValoracion> getDimensionesActuales() {
        try {
            if (caracterizacion != null && caracterizacion.getIdCaracterizacion() != null) {
                return dimensionFacade.findByCaracterizacion(
                    caracterizacion.getIdCaracterizacion());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * Inicializar dimensiones (versión public para usarse desde el formulario)
     */
    public String inicializarDimensionesFormulario() {
        try {
            if (caracterizacion == null || caracterizacion.getIdCaracterizacion() == null) {
                addMessage(FacesMessage.SEVERITY_ERROR, 
                    "Debe seleccionar una caracterización primero");
                return null;
            }
            
            // Verificar si ya existen dimensiones
            List<DimensionValoracion> existentes = 
                dimensionFacade.findByCaracterizacion(caracterizacion.getIdCaracterizacion());
            
            if (existentes != null && !existentes.isEmpty()) {
                addMessage(FacesMessage.SEVERITY_WARN, 
                    "Las dimensiones ya han sido inicializadas para esta caracterización");
                return null;
            }
            
            // Crear las 8 dimensiones
            for (String nombreDimension : DIMENSIONES_MEN) {
                DimensionValoracion dimension = new DimensionValoracion();
                dimension.setCaracterizacionId(caracterizacion);
                dimension.setNombreDimension(nombreDimension);
                dimension.setDescripcion(obtenerDescripcionDimension(nombreDimension));
                dimension.setEstado("PENDIENTE");
                dimension.setCreatedAt(new Date());
                dimension.setUpdatedAt(new Date());
                dimensionFacade.create(dimension);
            }
            
            addMessage(FacesMessage.SEVERITY_INFO, 
                "Las 8 dimensiones MEN han sido inicializadas correctamente");
            
            return "/views/caracterizacion/dashboard_dimensiones.xhtml?faces-redirect=true";
            
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, 
                "Error al inicializar dimensiones: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Obtener descripción detallada de cada dimensión MEN
     */
    private String obtenerDescripcionDimension(String nombreDimension) {
        switch(nombreDimension) {
            case "Contexto y vida familiar":
                return "Evaluación del entorno familiar, relaciones afectivas, situación socioeconómica y apoyo familiar";
            case "Habilidades intelectuales":
                return "Capacidades cognitivas, procesos de aprendizaje, razonamiento y habilidades académicas";
            case "Bienestar emocional":
                return "Estabilidad emocional, autoestima, manejo del estrés y bienestar psicológico";
            case "Conducta adaptativa y desarrollo personal":
                return "Independencia, habilidades sociales, autorregulación y adaptación a normas";
            case "Salud y bienestar físico":
                return "Estado de salud general, desarrollo motor, nutrición y acceso a servicios de salud";
            case "Participación e inclusión social":
                return "Interacción social, integración en grupos, oportunidades de participación";
            case "Control del propio entorno":
                return "Capacidad de influir en el entorno, toma de decisiones y autonomía";
            case "Dimensión pedagógica":
                return "Desempeño académico, motivación por el aprendizaje y logros educativos";
            default:
                return "Dimensión de evaluación";
        }
    }

    /**
     * Navegar a la vista de valoración de una dimensión específica
     */
    public String irAValoracionDimension(Integer dimensionId) {
        try {
            if (dimensionId != null) {
                dimensionActual = dimensionFacade.find(dimensionId);
                if (dimensionActual != null) {
                    return "/views/caracterizacion/valorar_dimension.xhtml?faces-redirect=true";
                }
            }
            addMessage(FacesMessage.SEVERITY_ERROR, "Dimensión no encontrada");
            return null;
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, 
                "Error al cargar dimensión: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Guardar valoración de una dimensión individual
     */
    public String guardarValoracion() {
        try {
            if (dimensionActual == null || dimensionActual.getIdDimension() == null) {
                addMessage(FacesMessage.SEVERITY_ERROR, 
                    "Debe seleccionar una dimensión para valorar");
                return null;
            }
            
            // Validaciones
            if (dimensionActual.getPuntuacion() == null || 
                dimensionActual.getPuntuacion() < 1 || 
                dimensionActual.getPuntuacion() > 5) {
                addMessage(FacesMessage.SEVERITY_WARN, 
                    "Debe seleccionar una puntuación entre 1 y 5");
                return null;
            }
            
            if (dimensionActual.getFortalezas() == null || 
                dimensionActual.getFortalezas().trim().isEmpty()) {
                addMessage(FacesMessage.SEVERITY_WARN, 
                    "Debe describir las fortalezas identificadas");
                return null;
            }
            
            if (dimensionActual.getAreasApoyo() == null || 
                dimensionActual.getAreasApoyo().trim().isEmpty()) {
                addMessage(FacesMessage.SEVERITY_WARN, 
                    "Debe describir las áreas que requieren apoyo");
                return null;
            }
            
            // Actualizar campos de auditoría
            dimensionActual.setEstado("COMPLETADA");
            dimensionActual.setFechaValoracion(new Date());
            dimensionActual.setUpdatedAt(new Date());
            
            // Obtener usuario actual
            Login login = (Login) FacesContext.getCurrentInstance()
                    .getExternalContext().getSessionMap().get("login");
            if (login != null && login.getUsuario() != null) {
                // Guardar usuario que realizó la valoración si la entidad lo permite
            }
            
            // Persistir cambios
            dimensionFacade.edit(dimensionActual);
            
            addMessage(FacesMessage.SEVERITY_INFO, 
                "✓ Dimensión '" + dimensionActual.getNombreDimension() + "' valorada correctamente");
            
            return "/views/caracterizacion/dashboard_dimensiones.xhtml?faces-redirect=true";
            
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, 
                "Error al guardar valoración: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Volver al dashboard de dimensiones sin guardar
     */
    public String irAlDashboardDimensiones() {
        dimensionActual = new DimensionValoracion();
        return "/views/caracterizacion/dashboard_dimensiones.xhtml?faces-redirect=true";
    }

    /**
     * Contar dimensiones por estado
     */
    public long contarDimensiones(String estado) {
        try {
            List<DimensionValoracion> todas = getDimensionesActuales();
            if (todas == null) {
                return 0;
            }
            return todas.stream()
                .filter(d -> estado.equals(d.getEstado()))
                .count();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Generar informe de valoración cuando todas las dimensiones están completadas
     */
    public String generarInformeValoracion() {
        try {
            if (caracterizacion == null || caracterizacion.getIdCaracterizacion() == null) {
                addMessage(FacesMessage.SEVERITY_ERROR, 
                    "Debe seleccionar una caracterización");
                return null;
            }
            
            // Verificar que todas las dimensiones estén completadas
            long pendientes = contarDimensiones("PENDIENTE");
            long enProceso = contarDimensiones("EN_PROCESO");
            
            if (pendientes > 0 || enProceso > 0) {
                addMessage(FacesMessage.SEVERITY_WARN, 
                    "Debe completar todas las dimensiones antes de generar el informe");
                return null;
            }
            
            // Marcar caracterización como completada
            caracterizacion.setEstadoCaracterizacion("COMPLETADA");
            caracterizacion.setFechaFinalizacion(new Date());
            caracterizacion.setUpdatedAt(new Date());
            caracterizacionFacade.edit(caracterizacion);
            
            addMessage(FacesMessage.SEVERITY_INFO, 
                "Informe de valoración generado correctamente");
            
            // Redireccionar a vista de informe (próxima fase)
            return "/views/caracterizacion/informe_valoracion.xhtml?faces-redirect=true";
            
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, 
                "Error al generar informe: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Selecciona una caracterización existente y navega al dashboard de caracterización
     */
    public String seleccionarCaracterizacion(Integer idCaracterizacion) {
        try {
            if (idCaracterizacion != null) {
                Caracterizacion car = caracterizacionFacade.find(idCaracterizacion);
                if (car != null) {
                    this.caracterizacion = car;
                    cargarDimensiones();
                    return "/views/caracterizacion/dashboard.xhtml?faces-redirect=true";
                }
            }
            addMessage(FacesMessage.SEVERITY_ERROR, "Caracterización no encontrada");
            return null;
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error al seleccionar caracterización: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Lista de objetos Estudiante que no tienen una caracterización activa (INICIADA/EN_PROCESO)
     */
    public List<Estudiante> listaEstudiantesParaIniciar() {
        List<Estudiante> resultados = new ArrayList<>();
        try {
            for (Estudiante est : estudianteFacade.findAll()) {
                boolean tieneActiva = false;
                if (est.getCaracterizacionList() != null) {
                    for (Caracterizacion c : est.getCaracterizacionList()) {
                        if ("EN_PROCESO".equals(c.getEstadoCaracterizacion()) || "INICIADA".equals(c.getEstadoCaracterizacion())) {
                            tieneActiva = true;
                            break;
                        }
                    }
                }
                if (!tieneActiva) {
                    resultados.add(est);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultados;
    }

    /**
     * Inicia caracterización por id de estudiante (útil desde la vista)
     */
    public String iniciarCaracterizacionPorId(Integer estudianteId) {
        try {
            if (estudianteId != null) {
                Estudiante est = estudianteFacade.find(estudianteId);
                if (est != null) {
                    return iniciarCaracterizacion(est);
                }
            }
            addMessage(FacesMessage.SEVERITY_ERROR, "Estudiante no encontrado");
            return null;
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error al iniciar caracterización: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Método auxiliar para agregar mensajes
     */
    private void addMessage(FacesMessage.Severity severity, String message) {
        FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(severity, message, ""));
    }

    // Getters y Setters
    public Caracterizacion getCaracterizacion() {
        return caracterizacion;
    }

    public void setCaracterizacion(Caracterizacion caracterizacion) {
        this.caracterizacion = caracterizacion;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public DimensionValoracion getDimensionActual() {
        return dimensionActual;
    }

    public void setDimensionActual(DimensionValoracion dimensionActual) {
        this.dimensionActual = dimensionActual;
    }

    public ObservacionSistematica getObservacionActual() {
        return observacionActual;
    }

    public void setObservacionActual(ObservacionSistematica observacionActual) {
        this.observacionActual = observacionActual;
    }

    public List<DimensionValoracion> getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(List<DimensionValoracion> dimensiones) {
        this.dimensiones = dimensiones;
    }

    public List<ObservacionSistematica> getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(List<ObservacionSistematica> observaciones) {
        this.observaciones = observaciones;
    }

    public String getFiltroEstado() {
        return filtroEstado;
    }

    public void setFiltroEstado(String filtroEstado) {
        this.filtroEstado = filtroEstado;
    }

    public String getFiltroBusqueda() {
        return filtroBusqueda;
    }

    public void setFiltroBusqueda(String filtroBusqueda) {
        this.filtroBusqueda = filtroBusqueda;
    }
}
