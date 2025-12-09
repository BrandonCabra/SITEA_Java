package com.sena.sitea.controller;

import com.sena.sitea.entities.Caracterizacion;

import com.sena.sitea.entities.DimensionValoracion;
import com.sena.sitea.entities.Estudiante;
import com.sena.sitea.entities.ObservacionSistematica;
import com.sena.sitea.entities.ContextoEscolar;
import com.sena.sitea.entities.DimBienestarEmocional;
import com.sena.sitea.entities.DimSaludFisica;
import com.sena.sitea.entities.DimConductaAdaptativa;
import com.sena.sitea.entities.DimParticipacionSocial;
import com.sena.sitea.entities.DimControlEntorno;
import com.sena.sitea.entities.DimPedagogica;
import com.sena.sitea.entities.DimHabilidadesIntelectuales;
import com.sena.sitea.entities.Usuarios;
import com.sena.sitea.entities.Rol;
import com.sena.sitea.entities.TipoDocumento;
import com.sena.sitea.services.CaracterizacionFacadeLocal;
import com.sena.sitea.services.DimensionValoracionFacadeLocal;
import com.sena.sitea.services.EstudianteFacadeLocal;
import com.sena.sitea.services.ObservacionSistematicaFacadeLocal;
import com.sena.sitea.services.UsuariosFacadeLocal;
import com.sena.sitea.services.RolFacadeLocal;
import com.sena.sitea.services.EmailService;
import com.sena.sitea.services.GradoFacadeLocal;
import com.sena.sitea.entities.Grado;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;
import org.primefaces.model.DefaultScheduleEvent;
import com.sena.sitea.security.PasswordUtil;
import javax.inject.Named;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.Date;
import java.util.List;
import java.security.SecureRandom;
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
    private DimPedagogica pedagogicaActual = new DimPedagogica();
    private DimHabilidadesIntelectuales habilidadesActual = new DimHabilidadesIntelectuales();
    
    // Propiedades para captura de contexto familiar
    private com.sena.sitea.entities.ContextoFamiliar contextoFamiliar = new com.sena.sitea.entities.ContextoFamiliar();
    private com.sena.sitea.entities.ContextoEscolar contextoEscolar = new com.sena.sitea.entities.ContextoEscolar();
    
    private List<SelectItem> listaEstudiantes;
    private List<DimensionValoracion> dimensiones;
    private List<ObservacionSistematica> observaciones;
    private String filtroEstado;
    private String filtroBusqueda;
    // Filtros y dashboard
    private Integer filtroGradoId;
    private List<Grado> listaGrados;
    private ScheduleModel eventModel;

    // Métricas
    private long countCompletadas;
    private long countEnProceso;
    private long countSinIniciar;
    
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
    
    // Estilos e inteligencias para valoración de habilidades intelectuales
    private static final String[] ESTILOS = {"Visual", "Auditivo", "Kinestésico"};
    private static final String[] INTELIGENCIAS = {
        "Lingüística", "Lógico-matemática", "Espacial", "Corporal-cinestésica",
        "Musical", "Interpersonal", "Intrapersonal", "Naturalista"
    };

    // Valores temporales para el formulario (1-5)
    private Integer[] estilosPuntuaciones;
    private Integer[] inteligenciasPuntuaciones;
    
    @EJB
    private CaracterizacionFacadeLocal caracterizacionFacade;
    @EJB
    private EstudianteFacadeLocal estudianteFacade;
    @EJB
    private com.sena.sitea.services.ExpedienteService expedienteService;
    @EJB
    private DimensionValoracionFacadeLocal dimensionFacade;
    @EJB
    private com.sena.sitea.services.DimSaludFisicaFacadeLocal dimSaludFacade;
    @EJB
    private com.sena.sitea.services.DimBienestarEmocionalFacadeLocal dimBienestarFacade;
    @EJB
    private com.sena.sitea.services.DimConductaAdaptativaFacadeLocal dimConductaFacade;
    @EJB
    private com.sena.sitea.services.DimParticipacionSocialFacadeLocal dimParticipacionFacade;
    @EJB
    private com.sena.sitea.services.DimControlEntornoFacadeLocal dimControlEntornoFacade;
    @EJB
    private com.sena.sitea.services.DimPedagogicaFacadeLocal dimPedagogicaFacade;
    @EJB
    private com.sena.sitea.services.DimHabilidadesIntelectualesFacadeLocal dimIntelectualFacade;
    @EJB
    private GradoFacadeLocal gradoFacade;
    @EJB
    private ObservacionSistematicaFacadeLocal observacionFacade;
    @EJB
    private com.sena.sitea.services.ContextoEscolarFacadeLocal contextoEscolarFacade;
    @EJB
    private com.sena.sitea.services.ContextoFamiliarFacadeLocal contextoFamiliarFacade;
    @EJB
    private UsuariosFacadeLocal usuariosFacade;
    @EJB
    private RolFacadeLocal rolFacade;
    @EJB
    private EmailService emailService;

    public CaracterizacionControllerMejorado() {
    }
    
    // Exponer los arrays para la vista
    public String[] getEstilos() {
        return ESTILOS;
    }

    public String[] getInteligencias() {
        return INTELIGENCIAS;
    }

    public Integer[] getEstilosPuntuaciones() {
        return estilosPuntuaciones;
    }

    public void setEstilosPuntuaciones(Integer[] estilosPuntuaciones) {
        this.estilosPuntuaciones = estilosPuntuaciones;
    }

    public Integer[] getInteligenciasPuntuaciones() {
        return inteligenciasPuntuaciones;
    }

    public void setInteligenciasPuntuaciones(Integer[] inteligenciasPuntuaciones) {
        this.inteligenciasPuntuaciones = inteligenciasPuntuaciones;
    }
    @PostConstruct
    public void init() {
        if (this.filtroEstado == null || this.filtroEstado.isEmpty()) {
            this.filtroEstado = "TODOS";
        }
        // Inicializaciones adicionales para dimensiones
        if (this.saludFisicaActual == null) {
            this.saludFisicaActual = new DimSaludFisica();
        }
        if (this.bienestarEmocionalActual == null) {
            this.bienestarEmocionalActual = new DimBienestarEmocional();
        }
        if (this.conductaAdaptativaActual == null) {
            this.conductaAdaptativaActual = new DimConductaAdaptativa();
        }
        if (this.participacionSocialActual == null) {
            this.participacionSocialActual = new DimParticipacionSocial();
        }
        if (this.controlEntornoActual == null) {
            this.controlEntornoActual = new DimControlEntorno();
        }
        if (this.pedagogicaActual == null) {
            this.pedagogicaActual = new DimPedagogica();
        }
        if (this.habilidadesActual == null) {
            this.habilidadesActual = new DimHabilidadesIntelectuales();
        }
        try {
            // Inicializar lista de grados y calendario
            this.listaGrados = gradoFacade.findAll();
        } catch (Exception e) {
            this.listaGrados = new ArrayList<>();
        }
        inicializarCalendario();
        calcularMetricasGlobales();
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
            // Asignar código/expediente y estado inicial
            String codigo = generarExpedienteCaracterizacion();
            caracterizacion.setExpedienteCaracterizacion(codigo);
            caracterizacion.setCodigoCaracterizacion(codigo);
            caracterizacion.setEstadoCaracterizacion("INICIADA");
            caracterizacion.setFechaInicio(new Date());
            caracterizacion.setCreatedAt(new Date());
            caracterizacion.setUpdatedAt(new Date());
            // Rellenar campos obligatorios con valores por defecto para pasar validación
            caracterizacion.setContextoAcademico("PENDIENTE");
            caracterizacion.setContextoFamiliar("PENDIENTE");
            caracterizacion.setContextoEscolar("PENDIENTE");
            caracterizacion.setDiagnostico("PENDIENTE");
            caracterizacion.setValoracionPedagogica("PENDIENTE");
            caracterizacion.setBarraDeAprendizaje("PENDIENTE");
            caracterizacion.setRecomendaciones("PENDIENTE");
            caracterizacion.setCorresponsabilidad("PENDIENTE");
            
            // Obtener usuario actual
            Login login = (Login) FacesContext.getCurrentInstance()
                    .getExternalContext().getSessionMap().get("login");
            if (login != null && login.getUsuario() != null) {
                caracterizacion.setCreatedBy(login.getUsuario().getIdUsuario());
            }
            
            // Persistir caracterización inicial
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
            
            return completadas * 100 / dimensiones.size();
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
                // Registro mínimo de auditoría (no intrusivo).
                System.out.println("Usuario que valoró dimensión: " + login.getUsuario().getIdUsuario());
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
     * Selecciona una caracterización y navega al detalle de dimensiones (avance individual)
     */
    public String seleccionarCaracterizacionYAvanzar(Integer idCaracterizacion) {
        try {
            if (idCaracterizacion != null) {
                Caracterizacion car = caracterizacionFacade.find(idCaracterizacion);
                if (car != null) {
                    this.caracterizacion = car;
                    this.estudiante = car.getEstudianteIdEstudiante();
                    cargarDimensiones();
                    return "/views/caracterizacion/dimensiones/dimensionesavance.xhtml?faces-redirect=true";
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
     * Variante: iniciar caracterización y navegar directamente al formulario de Contexto Escolar
     */
    public String iniciarCaracterizacionYRegistrarEscolar(Integer estudianteId) {
        try {
            if (estudianteId != null) {
                Estudiante est = estudianteFacade.find(estudianteId);
                if (est != null) {
                    String res = iniciarCaracterizacion(est);
                    // iniciarCaracterizacion ya creó la caracterizacion y dimensiones
                    // recargar caracterizacion actual y navegar al formulario escolar
                    return "/views/caracterizacion/contexto_escolar.xhtml?faces-redirect=true";
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
     * Iniciar valoración de Habilidades Intelectuales (estilos + inteligencias)
     */
    public String iniciarValoracionHabilidades(Integer dimensionId) {
        try {
            if (dimensionId != null) {
                dimensionActual = dimensionFacade.find(dimensionId);
                if (dimensionActual != null) {
                    // inicializar puntuaciones por defecto (3 = neutro)
                    estilosPuntuaciones = new Integer[ESTILOS.length];
                    for (int i = 0; i < ESTILOS.length; i++) estilosPuntuaciones[i] = 3;
                    inteligenciasPuntuaciones = new Integer[INTELIGENCIAS.length];
                    for (int i = 0; i < INTELIGENCIAS.length; i++) inteligenciasPuntuaciones[i] = 3;

                    return "/views/caracterizacion/valorar_habilidades_intelectuales.xhtml?faces-redirect=true";
                }
            }
            addMessage(FacesMessage.SEVERITY_ERROR, "Dimensión no encontrada");
            return null;
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error al iniciar valoración: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Guardar resultados del formulario de Estilos e Inteligencias
     */
    public String guardarHabilidades() {
        try {
            if (dimensionActual == null || dimensionActual.getIdDimension() == null) {
                addMessage(FacesMessage.SEVERITY_ERROR, "Debe seleccionar una dimensión");
                return null;
            }

            // Asegurar no-nulos
            for (int i = 0; i < ESTILOS.length; i++) if (estilosPuntuaciones[i] == null) estilosPuntuaciones[i] = 0;
            for (int i = 0; i < INTELIGENCIAS.length; i++) if (inteligenciasPuntuaciones[i] == null) inteligenciasPuntuaciones[i] = 0;

            // Determinar estilo predominante
            int idxEst = 0;
            int maxEst = estilosPuntuaciones[0];
            for (int i = 1; i < estilosPuntuaciones.length; i++) {
                if (estilosPuntuaciones[i] > maxEst) {
                    maxEst = estilosPuntuaciones[i];
                    idxEst = i;
                }
            }
            String estiloPred = ESTILOS[idxEst];

            // Determinar 2 inteligencias predominantes
            List<int[]> lista = new ArrayList<>();
            for (int i = 0; i < INTELIGENCIAS.length; i++) {
                lista.add(new int[]{i, inteligenciasPuntuaciones[i]});
            }
            lista.sort((a, b) -> Integer.compare(b[1], a[1]));
            String principales = INTELIGENCIAS[lista.get(0)[0]] + (lista.size() > 1 ? ", " + INTELIGENCIAS[lista.get(1)[0]] : "");

            // Calcular puntuación global como promedio de inteligencias (1-5)
            double avg = 0;
            int sum = 0;
            for (int v : inteligenciasPuntuaciones) sum += v;
            avg = (double) sum / inteligenciasPuntuaciones.length;

            // Guardar resumen en la entidad de dimensión
            dimensionActual.setPuntuacion((int) Math.round(avg));
            String resumen = "Estilo predominante: " + estiloPred + ". Inteligencias predominantes: " + principales + ".";
            dimensionActual.setFortalezas("Resumen: " + resumen);
            dimensionActual.setAreasApoyo("Recomendaciones: Revisar estrategias pedagógicas acordes al estilo " + estiloPred + ".");
            dimensionActual.setEstado("COMPLETADA");
            dimensionActual.setFechaValoracion(new Date());
            dimensionActual.setUpdatedAt(new Date());

            dimensionFacade.edit(dimensionActual);

            addMessage(FacesMessage.SEVERITY_INFO, "Valoración guardada: " + resumen);
            return "/views/caracterizacion/dashboard_dimensiones.xhtml?faces-redirect=true";
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error al guardar valoración: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Guardar datos del Contexto Escolar en la caracterización actual
     */
    public String guardarContextoEscolar() {
        try {
            if (caracterizacion == null || caracterizacion.getIdCaracterizacion() == null) {
                addMessage(FacesMessage.SEVERITY_ERROR, "No hay caracterización seleccionada");
                return null;
            }
            // Validación mínima basada en la entidad ContextoEscolar vinculada al controlador
            if (contextoEscolar == null || contextoEscolar.getInfraestructura() == null || contextoEscolar.getInfraestructura().trim().isEmpty()) {
                addMessage(FacesMessage.SEVERITY_WARN, "Debe ingresar la información del contexto escolar (Infraestructura)");
                return null;
            }

            // Validaciones detalladas de todos los campos requeridos
            StringBuilder erroresValidacion = new StringBuilder();
            
            if (contextoEscolar.getAccesibilidad() == null || contextoEscolar.getAccesibilidad().trim().isEmpty()) {
                erroresValidacion.append("• Debe describir la accesibilidad de las instalaciones\n");
            }
            if (contextoEscolar.getRecursos() == null || contextoEscolar.getRecursos().trim().isEmpty()) {
                erroresValidacion.append("• Debe describir los recursos disponibles\n");
            }
            if (contextoEscolar.getAmbiente() == null || contextoEscolar.getAmbiente().trim().isEmpty()) {
                erroresValidacion.append("• Debe describir el ambiente del contexto escolar\n");
            }
            if (contextoEscolar.getObservacionesDocentes() == null || contextoEscolar.getObservacionesDocentes().trim().isEmpty()) {
                erroresValidacion.append("• Debe ingresar las observaciones de los docentes\n");
            }
            if (contextoEscolar.getBarrerasAprendizaje() == null || contextoEscolar.getBarrerasAprendizaje().trim().isEmpty()) {
                erroresValidacion.append("• Debe describir las barreras de aprendizaje identificadas\n");
            }
            if (contextoEscolar.getRecomendacionesInstitucionales() == null || contextoEscolar.getRecomendacionesInstitucionales().trim().isEmpty()) {
                erroresValidacion.append("• Debe incluir recomendaciones institucionales\n");
            }
            
            // Si hay errores, mostrarlos todos en un mensaje
            if (erroresValidacion.length() > 0) {
                addMessage(FacesMessage.SEVERITY_ERROR, 
                    "Por favor, complete los siguientes campos:\n" + erroresValidacion.toString());
                return null;
            }

            // Buscar contexto escolar existente o crear nuevo
            com.sena.sitea.entities.ContextoEscolar ce = null;
            java.util.List<com.sena.sitea.entities.ContextoEscolar> encontrados = contextoEscolarFacade.findByCaracterizacion(caracterizacion.getIdCaracterizacion());
            if (encontrados != null && !encontrados.isEmpty()) {
                ce = encontrados.get(0);
                addMessage(FacesMessage.SEVERITY_INFO, "Actualizando contexto escolar existente...");
            } else {
                ce = new com.sena.sitea.entities.ContextoEscolar();
                ce.setCaracterizacion(caracterizacion);
                ce.setCreatedAt(new Date());
                // set created_by from session user if available
                Login login = (Login) FacesContext.getCurrentInstance()
                        .getExternalContext().getSessionMap().get("login");
                if (login != null && login.getUsuario() != null) {
                    ce.setCreatedBy(login.getUsuario().getIdUsuario());
                }
            }

            // Mapear campos desde el objeto contextoEscolar del controlador a la entidad persistente
            ce.setInfraestructura(contextoEscolar.getInfraestructura().trim());
            ce.setAccesibilidad(contextoEscolar.getAccesibilidad().trim());
            ce.setRecursos(contextoEscolar.getRecursos().trim());
            ce.setAmbiente(contextoEscolar.getAmbiente().trim());
            ce.setObservacionesDocentes(contextoEscolar.getObservacionesDocentes().trim());
            ce.setBarrerasAprendizaje(contextoEscolar.getBarrerasAprendizaje().trim());
            ce.setRecomendacionesInstitucionales(contextoEscolar.getRecomendacionesInstitucionales().trim());
            ce.setUpdatedAt(new Date());
            
            // Persistir
            if (ce.getIdContextoEscolar() == null) {
                contextoEscolarFacade.create(ce);
                addMessage(FacesMessage.SEVERITY_INFO, 
                    "✓ Contexto escolar guardado correctamente. ID: " + ce.getIdContextoEscolar());
            } else {
                contextoEscolarFacade.edit(ce);
                addMessage(FacesMessage.SEVERITY_INFO, 
                    "✓ Contexto escolar actualizado correctamente");
            }
            
            // después de guardar, ir al formulario de contexto familiar
            // Mantener mensajes en Flash para que se muestren después del redirect
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            return "/views/caracterizacion/contexto_familiar.xhtml?faces-redirect=true";
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error al guardar contexto escolar: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Guardar datos del Contexto Familiar y registrar acudiente
     * Incluye creación de usuario para el acudiente
     */
    public String guardarContextoFamiliarYRegistrarAcudiente() {
        try {
            if (caracterizacion == null || caracterizacion.getIdCaracterizacion() == null) {
                addMessage(FacesMessage.SEVERITY_ERROR, "No hay caracterización seleccionada");
                return null;
            }

            // Validación mínima - al menos nombre del acudiente
            if (contextoFamiliar.getAcudienteNombre() == null || contextoFamiliar.getAcudienteNombre().trim().isEmpty()) {
                addMessage(FacesMessage.SEVERITY_WARN, "Debe ingresar el nombre del acudiente principal");
                return null;
            }

            // Buscar contexto familiar existente o crear nuevo
            com.sena.sitea.entities.ContextoFamiliar cf = null;
            java.util.List<com.sena.sitea.entities.ContextoFamiliar> encontrados = 
                contextoFamiliarFacade.findByCaracterizacion(caracterizacion.getIdCaracterizacion());
            
            if (encontrados != null && !encontrados.isEmpty()) {
                cf = encontrados.get(0);
            } else {
                cf = new com.sena.sitea.entities.ContextoFamiliar();
                cf.setCaracterizacion(caracterizacion);
                cf.setCreatedAt(new Date());
                
                // Set created_by from session user if available
                Login login = (Login) FacesContext.getCurrentInstance()
                        .getExternalContext().getSessionMap().get("login");
                if (login != null && login.getUsuario() != null) {
                    cf.setCreatedBy(login.getUsuario().getIdUsuario());
                }
            }

            // Mapear datos del acudiente desde contextoFamiliar
            cf.setAcudienteNombre(contextoFamiliar.getAcudienteNombre());
            cf.setAcudienteDocumento(contextoFamiliar.getAcudienteDocumento());
            cf.setAcudienteTelefono(contextoFamiliar.getAcudienteTelefono());
            cf.setAcudienteEmail(contextoFamiliar.getAcudienteEmail());
            cf.setAcudienteParentesco(contextoFamiliar.getAcudienteParentesco());

            // Mapear datos de madre, padre y otros familiares
            cf.setMadreNombre(contextoFamiliar.getMadreNombre());
            cf.setMadreDocumento(contextoFamiliar.getMadreDocumento());
            cf.setMadreTelefono(contextoFamiliar.getMadreTelefono());
            cf.setMadreEmail(contextoFamiliar.getMadreEmail());
            cf.setMadreOcupacion(contextoFamiliar.getMadreOcupacion());
            cf.setMadreEscolaridad(contextoFamiliar.getMadreEscolaridad());

            cf.setPadreNombre(contextoFamiliar.getPadreNombre());
            cf.setPadreDocumento(contextoFamiliar.getPadreDocumento());
            cf.setPadreTelefono(contextoFamiliar.getPadreTelefono());
            cf.setPadreEmail(contextoFamiliar.getPadreEmail());
            cf.setPadreOcupacion(contextoFamiliar.getPadreOcupacion());
            cf.setPadreEscolaridad(contextoFamiliar.getPadreEscolaridad());

            // Mapear información familiar, vivienda y observaciones
            cf.setOtrosFamiliares(contextoFamiliar.getOtrosFamiliares());
            cf.setRelacionesFamiliares(contextoFamiliar.getRelacionesFamiliares());
            cf.setComunicacionFamiliar(contextoFamiliar.getComunicacionFamiliar());
            cf.setTipoVivienda(contextoFamiliar.getTipoVivienda());
            cf.setTenenciaVivienda(contextoFamiliar.getTenenciaVivienda());
            cf.setCondicionesVivienda(contextoFamiliar.getCondicionesVivienda());
            cf.setSituacionEconomica(contextoFamiliar.getSituacionEconomica());
            cf.setObservacionesFamilia(contextoFamiliar.getObservacionesFamilia());

            cf.setUpdatedAt(new Date());

            // Persistir
            if (cf.getIdContextoFamiliar() == null) {
                contextoFamiliarFacade.create(cf);
                addMessage(FacesMessage.SEVERITY_INFO, 
                    "✓ Contexto familiar guardado correctamente. ID: " + cf.getIdContextoFamiliar());
            } else {
                contextoFamiliarFacade.edit(cf);
                addMessage(FacesMessage.SEVERITY_INFO, 
                    "✓ Contexto familiar actualizado correctamente");
            }

            // Registrar acudiente como usuario si tiene email
            if (contextoFamiliar.getAcudienteEmail() != null && !contextoFamiliar.getAcudienteEmail().trim().isEmpty()) {
                registrarUsuarioAcudiente(contextoFamiliar.getAcudienteNombre(), 
                                          contextoFamiliar.getAcudienteEmail(),
                                          contextoFamiliar.getAcudienteDocumento());
            }

            // Antes de redirigir, conservar mensajes para que el usuario vea la confirmación
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            // Después de guardar, ir al dashboard de dimensiones para valorar
            return "/views/caracterizacion/dashboard_dimensiones.xhtml?faces-redirect=true";
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, 
                "Error al guardar contexto familiar: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Validar formato de email
     */
    private boolean esEmailValido(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        // Patrón básico de email: usuario@dominio.extensión
        String regex = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+\\.[A-Za-z]{2,})$";
        return email.matches(regex);
    }

    /**
     * Registrar usuario acudiente en el sistema
     * Genera password temporal y envía por email
     */
    private void registrarUsuarioAcudiente(String nombre, String email, String documento) {
        try {
            // Validar inputs básicos
            if (nombre == null || nombre.trim().isEmpty() || 
                email == null || email.trim().isEmpty() || 
                documento == null || documento.trim().isEmpty()) {
                addMessage(FacesMessage.SEVERITY_WARN, 
                    "Datos incompletos del acudiente. Email no será registrado.");
                return;
            }

            // Validar email básico
            if (!email.contains("@")) {
                addMessage(FacesMessage.SEVERITY_WARN, 
                    "Email del acudiente inválido: " + email);
                return;
            }

            // 1. Generar password temporal seguro
            String passwordTemporal = generateSecurePassword();
            String passwordHash = PasswordUtil.hashPassword(passwordTemporal);

            // 2. Crear nuevo usuario
            Usuarios usuarioAcudiente = new Usuarios();
            
            // Dividir nombre en primer nombre y apellido
            String[] partes = nombre.trim().split("\\s+", 2);
            usuarioAcudiente.setPrimerNombre(partes[0]);
            usuarioAcudiente.setPrimerApellido(partes.length > 1 ? partes[1] : "SIN_APELLIDO");
            
            // Llenar campos obligatorios
            usuarioAcudiente.setNumeroDocumento(documento);
            usuarioAcudiente.setCorreoUsuario(email);
            usuarioAcudiente.setPassword(passwordHash);
            usuarioAcudiente.setEstatus("ACTIVO");
            usuarioAcudiente.setDireccionUsuario("NO_ESPECIFICADO"); // Campo obligatorio (@NotNull)
            usuarioAcudiente.setFechaRegistroIdFechaRegistro(new Date());
            
            // 3. Asignar TipoDocumento (por defecto: CC = Cédula de Ciudadanía, id=1)
            TipoDocumento tipoDoc = new TipoDocumento();
            tipoDoc.setIdTipoDocumento(1); // CC es generalmente el tipo 1
            usuarioAcudiente.setTipoDocumentoIdTipoDocumento(tipoDoc);
            
            // 4. Buscar rol "acudiente" y asignar
            // Consultar rol por nombre en la BD
            List<Rol> rolesEncontrados = rolFacade.findAll();
            Rol rolAcudiente = null;
            
            for (Rol rol : rolesEncontrados) {
                if ("acudiente".equalsIgnoreCase(rol.getNombreRol())) {
                    rolAcudiente = rol;
                    break;
                }
            }
            
            // Si no existe rol "acudiente", crear uno o usar rol predeterminado
            if (rolAcudiente == null) {
                // Buscar rol por defecto (padre, tutor, etc.) - id 4 es usualmente para acudientes
                for (Rol rol : rolesEncontrados) {
                    if (rol.getIdRol() == 4) { // Ajustar según tu BD
                        rolAcudiente = rol;
                        break;
                    }
                }
            }
            
            if (rolAcudiente != null) {
                usuarioAcudiente.setRolIdRol(rolAcudiente);
            } else {
                addMessage(FacesMessage.SEVERITY_WARN, 
                    "No se encontró rol 'acudiente' en el sistema. Contacte al administrador.");
                return;
            }
            
            // 5. Persistir usuario
            usuariosFacade.create(usuarioAcudiente);
            
            System.out.println("[SITEA] Nuevo usuario acudiente creado: " + email + 
                             " | Documento: " + documento + " | Usuario ID: " + usuarioAcudiente.getIdUsuario());
            
            addMessage(FacesMessage.SEVERITY_INFO, 
                "✓ Acudiente registrado exitosamente en el sistema con ID: " + usuarioAcudiente.getIdUsuario());

            // 6. Enviar email con credenciales via SendGrid
            String asuntoEmail = "SITEA - Credenciales de Acceso";
            String cuerpoEmail = "Estimado/a " + nombre + ",\n\n" +
                "Le informamos que ha sido registrado/a en la plataforma SITEA como acudiente.\n\n" +
                "CREDENCIALES DE ACCESO:\n" +
                "Usuario (Documento): " + documento + "\n" +
                "Contraseña temporal: " + passwordTemporal + "\n\n" +
                "Por favor, ingrese a la plataforma en la siguiente dirección:\n" +
                "http://localhost:8080/sitea/ (ajustar según tu dominio)\n\n" +
                "IMPORTANTE: Cambie su contraseña en el primer inicio de sesión.\n" +
                "Esta contraseña es temporal y expira en 30 días.\n\n" +
                "Si tiene dudas, contacte al equipo SITEA.\n\n" +
                "Saludos,\n" +
                "Plataforma SITEA";
            
            try {
                emailService.enviarEmailPrueba(email, asuntoEmail, cuerpoEmail);
                
                addMessage(FacesMessage.SEVERITY_INFO, 
                    "✓ Acudiente registrado y credenciales enviadas a: " + email);
                
                System.out.println("[SITEA] Email enviado exitosamente a: " + email);
            } catch (Exception emailEx) {
                // No fallar la creación de usuario si falla el email
                addMessage(FacesMessage.SEVERITY_WARN, 
                    "Acudiente registrado pero no se pudo enviar email: " + emailEx.getMessage() + 
                    ". Contraseña temporal: " + passwordTemporal);
                
                System.err.println("[SITEA ERROR] No se pudo enviar email a " + email + ": " + emailEx.getMessage());
                emailEx.printStackTrace();
            }
            
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, 
                "Error al registrar acudiente: " + e.getMessage());
            
            System.err.println("[SITEA ERROR] Error registrando acudiente: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Genera una contraseña temporal segura
     * @return password de 12 caracteres (mayúsculas, minúsculas, números)
     */
    private String generateSecurePassword() {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$";
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();
        
        for (int i = 0; i < 12; i++) {
            int index = random.nextInt(caracteres.length());
            password.append(caracteres.charAt(index));
        }
        
        return password.toString();
    }

    /**
     * Método auxiliar para agregar mensajes
     */
    private void addMessage(FacesMessage.Severity severity, String message) {
        FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(severity, message, ""));
    }

    // --- Variables y Métodos para Dimensión 8 (Pedagógica) ---
    public DimPedagogica getPedagogicaActual() { return pedagogicaActual; }
    public void setPedagogicaActual(DimPedagogica pedagogicaActual) { this.pedagogicaActual = pedagogicaActual; }

    public String guardarDimensionPedagogica() {
        try {
            if (pedagogicaActual.getFactoresMotivacion() == null || pedagogicaActual.getFactoresMotivacion().trim().isEmpty()) {
                addMessage(FacesMessage.SEVERITY_WARN, "Por favor describa los factores que motivan el aprendizaje.");
                return null;
            }

            // Asociar a la caracterización actual
            if (this.caracterizacion != null && this.caracterizacion.getIdCaracterizacion() != null) {
                pedagogicaActual.setCaracterizacionId(this.caracterizacion);
            }

            if (pedagogicaActual.getIdDimPedagogica() == null) {
                dimPedagogicaFacade.create(pedagogicaActual);
            } else {
                dimPedagogicaFacade.edit(pedagogicaActual);
            }

            // Actualizar dimensión genérica
            if (dimensionActual != null) {
                dimensionActual.setEstado("COMPLETADA");
                dimensionActual.setFechaValoracion(new Date());

                double v1 = (pedagogicaActual.getMotivacionAprender() != null) ? pedagogicaActual.getMotivacionAprender() : 0;
                double v2 = (pedagogicaActual.getActividadesAprendeMejor() != null) ? pedagogicaActual.getActividadesAprendeMejor() : 0;
                double v3 = (pedagogicaActual.getMetodosAdaptan() != null) ? pedagogicaActual.getMetodosAdaptan() : 0;
                double promedio = (v1 + v2 + v3) / 3.0;

                String nivel = promedio >= 3.5 ? "Alta Motivación" : (promedio >= 2.5 ? "Motivación Media" : "Baja Motivación / Riesgo Deserción");
                dimensionActual.setFortalezas("Estilo de Aprendizaje: " + nivel);

                String apoyo = "Motivadores: " + (pedagogicaActual.getFactoresMotivacion().length() > 50 ? 
                        pedagogicaActual.getFactoresMotivacion().substring(0, 47) + "..." : pedagogicaActual.getFactoresMotivacion());
                dimensionActual.setAreasApoyo(apoyo);

                dimensionFacade.edit(dimensionActual);
            }

            addMessage(FacesMessage.SEVERITY_INFO, "Dimensión Pedagógica guardada. ¡Caracterización Finalizada!");
            return "dashboard_dimensiones?faces-redirect=true";
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error: " + e.getMessage());
            return null;
        }
    }

    // --- Variables y Métodos para Dimensión 2 (Habilidades Intelectuales) ---
    public DimHabilidadesIntelectuales getHabilidadesActual() { return habilidadesActual; }
    public void setHabilidadesActual(DimHabilidadesIntelectuales habilidadesActual) { this.habilidadesActual = habilidadesActual; }

    public void cargarDatosIntelectuales() {
        try {
            if (this.caracterizacion != null && this.caracterizacion.getIdCaracterizacion() != null) {
                java.util.List<DimHabilidadesIntelectuales> lista = dimIntelectualFacade.findByCaracterizacion(this.caracterizacion.getIdCaracterizacion());
                if (lista != null && !lista.isEmpty()) {
                    this.habilidadesActual = lista.get(0);
                } else {
                    this.habilidadesActual = new DimHabilidadesIntelectuales();
                    this.habilidadesActual.setCaracterizacionId(this.caracterizacion);
                }
            }
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_WARN, "No se pudieron cargar los datos intelectuales: " + e.getMessage());
        }
    }

    public String guardarDimensionIntelectual() {
        try {
            if (this.caracterizacion != null && this.caracterizacion.getIdCaracterizacion() != null) {
                habilidadesActual.setCaracterizacionId(this.caracterizacion);
            }

            if (habilidadesActual.getIdDimIntelectual() == null) {
                dimIntelectualFacade.create(habilidadesActual);
            } else {
                dimIntelectualFacade.edit(habilidadesActual);
            }

            if (dimensionActual != null) {
                dimensionActual.setEstado("COMPLETADA");
                dimensionActual.setFechaValoracion(new Date());
                String resumen = "Estilo: " + (habilidadesActual.getEstiloAprendizajeDominante()!=null ? habilidadesActual.getEstiloAprendizajeDominante() : "Pendiente") + 
                                 ". Atención: " + (habilidadesActual.getAtencion()!=null?habilidadesActual.getAtencion():"");
                dimensionActual.setFortalezas(resumen);
                dimensionFacade.edit(dimensionActual);
            }

            addMessage(FacesMessage.SEVERITY_INFO, "Habilidades Intelectuales guardadas correctamente.");
            return "dashboard_dimensiones?faces-redirect=true";
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error: " + e.getMessage());
            return null;
        }
    }

    // --- Dashboard: filtros, progreso y calendario ---
    public List<Caracterizacion> obtenerCaracterizacionesDashboard() {
        try {
            List<Caracterizacion> todas = caracterizacionFacade.findAll();
            List<Caracterizacion> filtradas = new ArrayList<>();

            for (Caracterizacion car : todas) {
                boolean match = true;

                // 1. Filtro Texto (Nombre/Doc)
                if (filtroBusqueda != null && !filtroBusqueda.isEmpty()) {
                    String search = filtroBusqueda.toLowerCase();
                    String nombre = (car.getEstudianteIdEstudiante().getPrimerNombreEstudiante() + " " + 
                                   car.getEstudianteIdEstudiante().getPrimerApellidoEstudiante()).toLowerCase();
                    String doc = car.getEstudianteIdEstudiante().getNumeroDocumentoEstudiante();
                    
                    if (!nombre.contains(search) && (doc == null || !doc.contains(search))) {
                        match = false;
                    }
                }

                // 2. Filtro Estado
                if (filtroEstado != null && !"TODOS".equals(filtroEstado)) {
                    if (!filtroEstado.equals(car.getEstadoCaracterizacion())) {
                        match = false;
                    }
                }

                // 3. Filtro Grado (NUEVO)
                if (filtroGradoId != null && filtroGradoId != 0) {
                    com.sena.sitea.entities.Curso curso = car.getEstudianteIdEstudiante().getCursoIdCurso();
                    Grado gradoEst = null;
                    if (curso != null) {
                        gradoEst = curso.getGradoIdGrado();
                    }
                    if (gradoEst == null || !gradoEst.getIdGrado().equals(filtroGradoId)) {
                        match = false;
                    }
                }

                if (match) {
                    filtradas.add(car);
                }
            }
            return filtradas;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public int getProgresoEstudiante(Caracterizacion car) {
        try {
            List<DimensionValoracion> dims = dimensionFacade.findByCaracterizacion(car.getIdCaracterizacion());
            if (dims == null || dims.isEmpty()) return 0;
            
            long completadas = dims.stream().filter(d -> "COMPLETADA".equals(d.getEstado())).count();
            return (int) ((completadas * 100) / dims.size());
        } catch (Exception e) {
            return 0;
        }
    }

    public void calcularMetricasGlobales() {
        List<Caracterizacion> list = caracterizacionFacade.findAll();
        countCompletadas = list.stream().filter(c -> "COMPLETADA".equals(c.getEstadoCaracterizacion())).count();
        countEnProceso = list.stream().filter(c -> "EN_PROCESO".equals(c.getEstadoCaracterizacion())).count();
        countSinIniciar = list.stream().filter(c -> "INICIADA".equals(c.getEstadoCaracterizacion())).count();
    }

    // --- MÉTODOS PARA EL DASHBOARD DE DETALLE (dashboard_dimensiones.xhtml) ---

    // 1. Navegación Inteligente: Decide qué formulario abrir según la dimensión
    public String navegarAFormularioDimension(DimensionValoracion dim) {
        if (dim == null) return null;

        // 1. Establecer la dimensión actual en sesión para que el formulario la use
        this.dimensionActual = dim;

        // 2. Normalizar nombre (minúsculas y sin tildes) para comparaciones robustas
        String nombre = dim.getNombreDimension() != null ? dim.getNombreDimension().toLowerCase() : "";
        nombre = java.text.Normalizer.normalize(nombre, java.text.Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

        // 3. Redirección según contenido del nombre (ajusta outcomes si tus vistas difieren)
        if (nombre.contains("contexto") && nombre.contains("familiar")) {
            return "dimension1_contexto_familiar?faces-redirect=true";
        }
        if (nombre.contains("intelectual") || nombre.contains("cognitiv")) {
            if (habilidadesActual == null) habilidadesActual = new DimHabilidadesIntelectuales();
            return "d2HabilidaesIntelectuales?faces-redirect=true";
        }
        if (nombre.contains("emocional")) {
            if (bienestarEmocionalActual == null) bienestarEmocionalActual = new DimBienestarEmocional();
            return "d3testEmocional?faces-redirect=true";
        }
        if (nombre.contains("adaptativa")) {
            if (conductaAdaptativaActual == null) conductaAdaptativaActual = new DimConductaAdaptativa();
            return "dt4TestConductaAdaptativa?faces-redirect=true";
        }
        if (nombre.contains("salud")) {
            if (saludFisicaActual == null) saludFisicaActual = new DimSaludFisica();
            return "dt5SaludGeneral?faces-redirect=true";
        }
        if (nombre.contains("particip") || nombre.contains("social")) {
            if (participacionSocialActual == null) participacionSocialActual = new DimParticipacionSocial();
            return "d6TestParticipacionSocial?faces-redirect=true";
        }
        if (nombre.contains("control") || nombre.contains("entorno")) {
            if (controlEntornoActual == null) controlEntornoActual = new DimControlEntorno();
            return "d7TestControlEntorno?faces-redirect=true";
        }
        if (nombre.contains("pedagog") || nombre.contains("metas")) {
            if (pedagogicaActual == null) pedagogicaActual = new DimPedagogica();
            return "d8TestPedagogica?faces-redirect=true";
        }

        // Fallback: no se encontró formulario
        addMessage(FacesMessage.SEVERITY_ERROR, "No se encontró el formulario para: " + nombre);
        return null;
    }

    // 2. Métodos de Progreso para la Vista
    public int getPorcentajeGeneral() {
        List<DimensionValoracion> dims = getDimensionesActuales();
        if (dims == null || dims.isEmpty()) return 0;
        long completadas = dims.stream().filter(d -> "COMPLETADA".equals(d.getEstado())).count();
        return (int) ((completadas * 100) / dims.size());
    }
    
    public long getDimensionesCompletadasCount() {
        if (dimensiones == null) return 0;
        return dimensiones.stream().filter(d -> "COMPLETADA".equals(d.getEstado())).count();
    }
    
    // 3. Fechas para las tarjetas
    public String getFechaInicioFormato() {
        if (caracterizacion == null || caracterizacion.getFechaInicio() == null) return "N/A";
        return new SimpleDateFormat("dd/MM/yyyy").format(caracterizacion.getFechaInicio());
    }
    
    public String getFechaEstimadaFin() {
        if (caracterizacion == null || caracterizacion.getFechaInicio() == null) return "N/A";
        Calendar c = Calendar.getInstance();
        c.setTime(caracterizacion.getFechaInicio());
        c.add(Calendar.DATE, 30); 
        return new SimpleDateFormat("dd/MM/yyyy").format(c.getTime());
    }

    // 4. Línea de Tiempo (Dimensiones ordenadas por fecha de valoración)
    public List<DimensionValoracion> getHistorialDimensiones() {
        if (dimensiones == null) return new ArrayList<>();
        return dimensiones.stream()
                .filter(d -> d.getFechaValoracion() != null)
                .sorted(Comparator.comparing(DimensionValoracion::getFechaValoracion).reversed())
                .collect(Collectors.toList());
    }
    
    // 5. Helpers de Estilo para la Vista
    public String getClaseEstado(String estado) {
        if ("COMPLETADA".equals(estado)) return "completada";
        if ("EN_PROCESO".equals(estado)) return "en-proceso";
        return "pendiente";
    }
    
    public String getIconoDimension(String nombre) {
        if (nombre == null) return "fa-star";
        String lower = nombre.toLowerCase();
        if (lower.contains("contexto")) return "fa-home";
        if (lower.contains("intelectual")) return "fa-brain";
        if (lower.contains("emocional")) return "fa-heart";
        if (lower.contains("adaptativa")) return "fa-hands";
        if (lower.contains("salud")) return "fa-heartbeat";
        if (lower.contains("particip")) return "fa-users";
        if (lower.contains("control")) return "fa-compass";
        if (lower.contains("pedagog")) return "fa-chalkboard-teacher";
        return "fa-star";
    }

    public void inicializarCalendario() {
        eventModel = new DefaultScheduleModel();
        
        List<Caracterizacion> list = caracterizacionFacade.findAll();
        for (Caracterizacion c : list) {
            if (c.getFechaInicio() != null) {
                java.time.LocalDateTime startLdt = convertToLocalDateTime(c.getFechaInicio());
                java.time.LocalDateTime endLdt = startLdt.plusHours(1);
                DefaultScheduleEvent event = new DefaultScheduleEvent();
                event.setTitle("Inicio: " + c.getEstudianteIdEstudiante().getPrimerNombreEstudiante());
                event.setStartDate(startLdt);
                event.setEndDate(endLdt);
                event.setAllDay(false);
                event.setDescription("Expediente: " + c.getExpedienteCaracterizacion());
                event.setStyleClass("evento-inicio");
                eventModel.addEvent(event);
            }
            if (c.getFechaFinalizacion() != null) {
                java.time.LocalDateTime startLdt = convertToLocalDateTime(c.getFechaFinalizacion());
                java.time.LocalDateTime endLdt = startLdt.plusHours(1);
                DefaultScheduleEvent event = new DefaultScheduleEvent();
                event.setTitle("Fin: " + c.getEstudianteIdEstudiante().getPrimerNombreEstudiante());
                event.setStartDate(startLdt);
                event.setEndDate(endLdt);
                event.setAllDay(false);
                event.setStyleClass("evento-fin");
                eventModel.addEvent(event);
            }
        }
    }

    private java.time.LocalDateTime convertToLocalDateTime(Date dateToConvert) {
        return dateToConvert.toInstant()
            .atZone(java.time.ZoneId.systemDefault())
          .toLocalDateTime();
    }

    // Se usa directamente java.util.Date para compatibilidad con Schedule de PrimeFaces 12

    // Getters y Setters
    public Integer getFiltroGradoId() { return filtroGradoId; }
    public void setFiltroGradoId(Integer filtroGradoId) { this.filtroGradoId = filtroGradoId; }
    public List<Grado> getListaGrados() { return listaGrados; }
    public ScheduleModel getEventModel() { return eventModel; }
    public long getCountCompletadas() { return countCompletadas; }
    public long getCountEnProceso() { return countEnProceso; }
    public long getCountSinIniciar() { return countSinIniciar; }

    // Getters y Setters para contexto familiar
    public com.sena.sitea.entities.ContextoFamiliar getContextoFamiliar() {
        return contextoFamiliar;
    }

    public void setContextoFamiliar(com.sena.sitea.entities.ContextoFamiliar contextoFamiliar) {
        this.contextoFamiliar = contextoFamiliar;
    }

    public com.sena.sitea.entities.ContextoEscolar getContextoEscolar() {
        return contextoEscolar;
    }

    public void setContextoEscolar(com.sena.sitea.entities.ContextoEscolar contextoEscolar) {
        this.contextoEscolar = contextoEscolar;
    }
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
    
   
    // DIMENSION SALUD

private DimSaludFisica saludFisicaActual;

// Initialization moved to the primary @PostConstruct above

// Getter y Setter para la vista
public DimSaludFisica getSaludFisicaActual() { return saludFisicaActual; }
public void setSaludFisicaActual(DimSaludFisica saludFisicaActual) { this.saludFisicaActual = saludFisicaActual; }

// Método para guardar esta dimensión específica
public String guardarDimensionSalud() {
    try {
        if (caracterizacion == null || caracterizacion.getIdCaracterizacion() == null) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Debe seleccionar una caracterización antes de guardar.");
            return null;
        }

        // Asociar a la caracterización actual
        saludFisicaActual.setCaracterizacionId(caracterizacion);

        // Si ya existe un registro para esta caracterización, actualizar; si no, crear
        java.util.List<DimSaludFisica> existentes = dimSaludFacade.findByCaracterizacion(caracterizacion.getIdCaracterizacion());
        if (existentes != null && !existentes.isEmpty()) {
            DimSaludFisica existente = existentes.get(0);
            saludFisicaActual.setIdDimSalud(existente.getIdDimSalud());
            dimSaludFacade.edit(saludFisicaActual);
        } else {
            dimSaludFacade.create(saludFisicaActual);
        }

        // Actualizar el estado de la dimensión en DimensionValoracion
        java.util.List<DimensionValoracion> dims = dimensionFacade.findByCaracterizacion(caracterizacion.getIdCaracterizacion());
        if (dims != null) {
            for (DimensionValoracion d : dims) {
                if (d.getNombreDimension() != null && d.getNombreDimension().toLowerCase().contains("salud")) {
                    d.setEstado("COMPLETADA");
                    d.setFechaValoracion(new Date());
                    d.setUpdatedAt(new Date());
                    String resumen = "Observaciones: " + (saludFisicaActual.getObservaciones() != null ? saludFisicaActual.getObservaciones() : "-");
                    if (saludFisicaActual.getAlergiasConocidas() != null && !saludFisicaActual.getAlergiasConocidas().isEmpty()) {
                        resumen += " | Alergias: " + saludFisicaActual.getAlergiasConocidas();
                    }
                    d.setFortalezas(resumen);
                    dimensionFacade.edit(d);
                    break;
                }
            }
        }

        addMessage(FacesMessage.SEVERITY_INFO, "Dimensión de Salud Física actualizada.");
        return "/views/caracterizacion/dashboard_dimensiones.xhtml?faces-redirect=true";
    } catch (Exception e) {
        addMessage(FacesMessage.SEVERITY_ERROR, "No se pudo guardar: " + e.getMessage());
        e.printStackTrace();
        return null;
    }
}

    // Agrega estas variables y métodos a tu clase existente

private DimBienestarEmocional bienestarEmocionalActual;

// Initialization moved to the primary @PostConstruct above

// Getter y Setter
public DimBienestarEmocional getBienestarEmocionalActual() { return bienestarEmocionalActual; }
public void setBienestarEmocionalActual(DimBienestarEmocional bienestarEmocionalActual) { this.bienestarEmocionalActual = bienestarEmocionalActual; }

// DIMENSION CONDUCTA ADAPTATIVA

private DimConductaAdaptativa conductaAdaptativaActual;

// Getter y Setter
public DimConductaAdaptativa getConductaAdaptativaActual() { return conductaAdaptativaActual; }
public void setConductaAdaptativaActual(DimConductaAdaptativa conductaAdaptativaActual) { this.conductaAdaptativaActual = conductaAdaptativaActual; }

// Método Guardar para Conducta Adaptativa
public String guardarDimensionAdaptativa() {
    try {
        if (caracterizacion == null || caracterizacion.getIdCaracterizacion() == null) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Debe seleccionar una caracterización antes de guardar.");
            return null;
        }

        // Asociar a la caracterización actual
        conductaAdaptativaActual.setCaracterizacionId(caracterizacion);

        // Crear o actualizar registro
        java.util.List<DimConductaAdaptativa> existentes = dimConductaFacade.findByCaracterizacion(caracterizacion.getIdCaracterizacion());
        if (existentes != null && !existentes.isEmpty()) {
            DimConductaAdaptativa existente = existentes.get(0);
            conductaAdaptativaActual.setIdDimAdaptativa(existente.getIdDimAdaptativa());
            dimConductaFacade.edit(conductaAdaptativaActual);
        } else {
            dimConductaFacade.create(conductaAdaptativaActual);
        }

        // Actualizar DimensionValoracion correspondiente
        java.util.List<DimensionValoracion> dims = dimensionFacade.findByCaracterizacion(caracterizacion.getIdCaracterizacion());
        if (dims != null) {
            for (DimensionValoracion d : dims) {
                if (d.getNombreDimension() != null && d.getNombreDimension().toLowerCase().contains("conducta")) {
                    d.setEstado("COMPLETADA");
                    d.setFechaValoracion(new Date());
                    d.setUpdatedAt(new Date());
                    double promedio = calcularPromedioAdaptativo();
                    String nivel = promedio >= 3.5 ? "Alto (Independiente)" : (promedio >= 2.5 ? "Medio (Apoyo Intermitente)" : "Bajo (Apoyo Extenso)");
                    String resumen = "Nivel de Independencia: " + nivel + ". Obs: " + (conductaAdaptativaActual.getObservaciones() != null ? conductaAdaptativaActual.getObservaciones() : "-");
                    d.setFortalezas(resumen);
                    dimensionFacade.edit(d);
                    break;
                }
            }
        }

        addMessage(FacesMessage.SEVERITY_INFO, "Conducta Adaptativa guardada correctamente.");
        return "/views/caracterizacion/dashboard_dimensiones.xhtml?faces-redirect=true";
    } catch (Exception e) {
        addMessage(FacesMessage.SEVERITY_ERROR, "Error al guardar: " + e.getMessage());
        e.printStackTrace();
        return null;
    }
}

// Auxiliar para cálculo rápido
private double calcularPromedioAdaptativo() {
    try {
        int suma = 0;
        int count = 0;
        Integer[] campos = new Integer[]{conductaAdaptativaActual.getAlimentacion(), conductaAdaptativaActual.getHigiene(), conductaAdaptativaActual.getVestido(), conductaAdaptativaActual.getSeguridad(),
            conductaAdaptativaActual.getComunicacion(), conductaAdaptativaActual.getSeguimientoInstrucciones(), conductaAdaptativaActual.getManejoDineroTiempo(), conductaAdaptativaActual.getLecturaFuncional(),
            conductaAdaptativaActual.getInteraccionPares(), conductaAdaptativaActual.getRespetoNormas(), conductaAdaptativaActual.getManejoCambios(), conductaAdaptativaActual.getAutocontrol()};
        for (Integer v : campos) {
            if (v != null) { suma += v; count++; }
        }
        if (count == 0) return 0.0;
        return ((double) suma) / ((double) count);
    } catch (Exception ex) {
        return 0.0;
    }
}

// DIMENSION PARTICIPACION E INCLUSION SOCIAL

private DimParticipacionSocial participacionSocialActual;

public DimParticipacionSocial getParticipacionSocialActual() { return participacionSocialActual; }
public void setParticipacionSocialActual(DimParticipacionSocial participacionSocialActual) { this.participacionSocialActual = participacionSocialActual; }

// Guardar Participación Social
public String guardarDimensionParticipacion() {
    try {
        if (caracterizacion == null || caracterizacion.getIdCaracterizacion() == null) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Debe seleccionar una caracterización antes de guardar.");
            return null;
        }

        // Validaciones simples: verificar seguridad y ruta de atención (preguntas críticas)
        if (participacionSocialActual.getSeguridadEscolar() == null || participacionSocialActual.getSeguridadEscolar() == 0 ||
            participacionSocialActual.getRutaAtencionExclusion() == null || participacionSocialActual.getRutaAtencionExclusion() == 0) {
            addMessage(FacesMessage.SEVERITY_WARN, "Por favor responda las preguntas sobre Percepción del Entorno Escolar.");
            return null;
        }

        // Asociar y crear/actualizar registro
        participacionSocialActual.setCaracterizacionId(caracterizacion);
        java.util.List<DimParticipacionSocial> existentes = dimParticipacionFacade.findByCaracterizacion(caracterizacion.getIdCaracterizacion());
        if (existentes != null && !existentes.isEmpty()) {
            DimParticipacionSocial existente = existentes.get(0);
            participacionSocialActual.setIdDimParticipacion(existente.getIdDimParticipacion());
            dimParticipacionFacade.edit(participacionSocialActual);
        } else {
            dimParticipacionFacade.create(participacionSocialActual);
        }

        double promedio = calcularPromedioParticipacion();
        String nivel = "";
        if (promedio >= 3.5) nivel = "Alta Inclusión (Se siente seguro y parte del grupo)";
        else if (promedio >= 2.5) nivel = "Inclusión Media (Algunas barreras percibidas)";
        else nivel = "Riesgo de Exclusión (Requiere intervención inmediata)";
        // Alerta si percepción de inseguridad es baja
        if (participacionSocialActual.getSeguridadEscolar() != null && participacionSocialActual.getSeguridadEscolar() <= 2) {
            nivel += " [ALERTA: Percepción de Inseguridad]";
        }

        if (dimensionActual != null) {
            dimensionActual.setEstado("COMPLETADA");
            dimensionActual.setFechaValoracion(new Date());
            dimensionActual.setFortalezas("Resumen: " + nivel);
            dimensionActual.setAreasApoyo("Obs: " + (participacionSocialActual.getObservaciones() != null ? participacionSocialActual.getObservaciones() : "-"));
            dimensionFacade.edit(dimensionActual);
        }

        addMessage(FacesMessage.SEVERITY_INFO, "Dimensión de Participación Social guardada correctamente.");
        return "/views/caracterizacion/dashboard_dimensiones.xhtml?faces-redirect=true";
    } catch (Exception e) {
        addMessage(FacesMessage.SEVERITY_ERROR, "Error al guardar: " + e.getMessage());
        e.printStackTrace();
        return null;
    }
}

private double calcularPromedioParticipacion() {
    try {
        int suma = 0; int count = 0;
        Integer[] campos = new Integer[]{participacionSocialActual.getAceptacionCompaneros(), participacionSocialActual.getSeguridadEscolar(), participacionSocialActual.getRespetoDiversidad(), participacionSocialActual.getPromocionInclusion()};
        for (Integer v : campos) { if (v != null) { suma += v; count++; } }
        if (count == 0) return 0.0;
        return ((double) suma) / ((double) count);
    } catch (Exception ex) {
        return 0.0;
    }
}

// DIMENSION CONTROL DEL PROPIO ENTORNO

private DimControlEntorno controlEntornoActual;

public DimControlEntorno getControlEntornoActual() { return controlEntornoActual; }
public void setControlEntornoActual(DimControlEntorno controlEntornoActual) { this.controlEntornoActual = controlEntornoActual; }

public String guardarDimensionControlEntorno() {
    try {
        if (caracterizacion == null || caracterizacion.getIdCaracterizacion() == null) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Debe seleccionar una caracterización antes de guardar.");
            return null;
        }

        // Validaciones mínimas
        if (controlEntornoActual.getTieneMetas() == null || controlEntornoActual.getTieneMetas() == 0 ||
            controlEntornoActual.getSeguridadDecisiones() == null || controlEntornoActual.getSeguridadDecisiones() == 0) {
            addMessage(FacesMessage.SEVERITY_WARN, "Por favor complete las preguntas clave de Autonomía.");
            return null;
        }

        // Asociar y crear/actualizar
        controlEntornoActual.setCaracterizacionId(caracterizacion);
        java.util.List<DimControlEntorno> existentes = dimControlEntornoFacade.findByCaracterizacion(caracterizacion.getIdCaracterizacion());
        if (existentes != null && !existentes.isEmpty()) {
            DimControlEntorno existente = existentes.get(0);
            controlEntornoActual.setIdDimControl(existente.getIdDimControl());
            dimControlEntornoFacade.edit(controlEntornoActual);
        } else {
            dimControlEntornoFacade.create(controlEntornoActual);
        }

        // Actualizar DimensionValoracion correspondiente
        java.util.List<DimensionValoracion> dims = dimensionFacade.findByCaracterizacion(caracterizacion.getIdCaracterizacion());
        if (dims != null) {
            for (DimensionValoracion d : dims) {
                if (d.getNombreDimension() != null && d.getNombreDimension().toLowerCase().contains("control")) {
                    d.setEstado("COMPLETADA");
                    d.setFechaValoracion(new Date());
                    d.setUpdatedAt(new Date());
                    double promedio = ( (controlEntornoActual.getRequierePocoApoyo()!=null?controlEntornoActual.getRequierePocoApoyo():0) +
                                       (controlEntornoActual.getSeguridadDecisiones()!=null?controlEntornoActual.getSeguridadDecisiones():0) +
                                       (controlEntornoActual.getTieneMetas()!=null?controlEntornoActual.getTieneMetas():0) ) / 3.0;
                    String nivel = promedio >= 3.5 ? "Autonomía Alta" : (promedio >= 2.5 ? "Autonomía en Desarrollo" : "Dependencia Significativa");
                    d.setFortalezas("Nivel de Autodeterminación: " + nivel);
                    d.setAreasApoyo("Obs: " + (controlEntornoActual.getObservaciones() != null ? controlEntornoActual.getObservaciones() : "-"));
                    dimensionFacade.edit(d);
                    break;
                }
            }
        }

        addMessage(FacesMessage.SEVERITY_INFO, "Dimensión 'Control del Propio Entorno' guardada correctamente.");
        return "/views/caracterizacion/dashboard_dimensiones.xhtml?faces-redirect=true";
    } catch (Exception e) {
        addMessage(FacesMessage.SEVERITY_ERROR, "Error: " + e.getMessage());
        e.printStackTrace();
        return null;
    }
}

// Método de Guardado
public String guardarDimensionEmocional() {
    try {
        if (caracterizacion == null || caracterizacion.getIdCaracterizacion() == null) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Debe seleccionar una caracterización antes de guardar.");
            return null;
        }

        // Asociar entidad con la caracterización actual
        bienestarEmocionalActual.setCaracterizacionId(caracterizacion);

        // Crear o actualizar registro
        java.util.List<DimBienestarEmocional> existentes = dimBienestarFacade.findByCaracterizacion(caracterizacion.getIdCaracterizacion());
        if (existentes != null && !existentes.isEmpty()) {
            DimBienestarEmocional existente = existentes.get(0);
            bienestarEmocionalActual.setIdDimEmocional(existente.getIdDimEmocional());
            dimBienestarFacade.edit(bienestarEmocionalActual);
        } else {
            dimBienestarFacade.create(bienestarEmocionalActual);
        }

        // Actualizar DimensionValoracion correspondiente
        java.util.List<DimensionValoracion> dims = dimensionFacade.findByCaracterizacion(caracterizacion.getIdCaracterizacion());
        if (dims != null) {
            for (DimensionValoracion d : dims) {
                if (d.getNombreDimension() != null && d.getNombreDimension().toLowerCase().contains("emocional")) {
                    d.setEstado("COMPLETADA");
                    d.setFechaValoracion(new Date());
                    d.setUpdatedAt(new Date());
                    String resumen = "Autoestima: " + getNivelTexto(bienestarEmocionalActual.getAutoestima()) + 
                                     ". Regulación: " + getNivelTexto(bienestarEmocionalActual.getRegulaImpulsos());
                    java.util.List<String> signos = bienestarEmocionalActual.getSignosAlarmaList();
                    if (signos != null && !signos.isEmpty()) {
                        resumen += ". ALERTA: " + String.join(", ", signos);
                    }
                    d.setFortalezas(resumen);
                    dimensionFacade.edit(d);
                    break;
                }
            }
        }

        addMessage(FacesMessage.SEVERITY_INFO, "Valoración Emocional guardada exitosamente.");
        return "/views/caracterizacion/dashboard_dimensiones.xhtml?faces-redirect=true";
    } catch (Exception e) {
        addMessage(FacesMessage.SEVERITY_ERROR, "Error al guardar: " + e.getMessage());
        e.printStackTrace();
        return null;
    }
}

// Auxiliar para convertir número a texto
private String getNivelTexto(Integer valor) {
    if(valor == null) return "N/A";
    switch(valor) {
        case 1: return "Bajo";
        case 2: return "Medio-Bajo";
        case 3: return "Medio-Alto";
        case 4: return "Alto";
        default: return "N/A";
    }
}
    
    
    
}
