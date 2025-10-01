/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.sena.sitea.controller;

import com.sena.sitea.entities.Curso;
import com.sena.sitea.entities.Estudiante;
import com.sena.sitea.entities.TipoDocumento;
import com.sena.sitea.services.CursoFacadeLocal;
import com.sena.sitea.services.EstudianteFacadeLocal;
import com.sena.sitea.services.TipoDocumentoFacadeLocal;
import javax.inject.Named;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author bjcab
 */
@Named(value = "estudianteTeaController")
@SessionScoped
public class EstudianteTeaController implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    
//======== Atributos ==========    

    private Estudiante con = new Estudiante();
    Curso cur = new Curso();
    List<SelectItem>listaCurso;
    
    TipoDocumento td = new TipoDocumento();
    List<SelectItem>listaTipoDocumento;
    
    
// ========== FACADES EJB ==========    
    
    @EJB
    private EstudianteFacadeLocal efl;
    @EJB
    CursoFacadeLocal cfl;
    @EJB
    TipoDocumentoFacadeLocal tfl;
    
   /** Aqui van las otras tablas que van a ser creadas y llamadas la de señales y la de contacto
     * @return o*/
    
    
//========= Getters y Setters ==========    
    
    

    public Estudiante getCon() {
        return con;
    }

    public void setCon(Estudiante con) {
        this.con = con;
    }

    public Curso getCur() {
        return cur;
    }

    public void setCur(Curso cur) {
        this.cur = cur;
    }
    

    public TipoDocumento getTd() {
        return td;
    }

    public void setTd(TipoDocumento td) {
        this.td = td;
    }
    
  
// ========== MÉTODOS DE DATOS EXISTENTES ==========   
    
    public List<Estudiante> obtenerEstudiantes (){
        try{
            return this.efl.findAll();  
        } catch (Exception e) {
        }
        return null;
    }
            
    public List<SelectItem> listaCurso (){
        listaCurso = new ArrayList<>();
        try {
            for(Curso cur : this.cfl.findAll()){
                String label = String.valueOf(cur.getCodigoCurso());

                SelectItem item = new SelectItem(cur.getIdCurso(), label);
                listaCurso.add(item);
                
            }
            return listaCurso;
        } catch (Exception e) {
            
        }
        return null;
    }
    
    public List<SelectItem> listaTipoDocumento(){
        listaTipoDocumento = new ArrayList<>();
        try {
            for(TipoDocumento td : this.tfl.findAll()){
                SelectItem item = new SelectItem(td.getIdTipoDocumento(), td.getNombreTipoDocumento());
                listaTipoDocumento.add(item);
            }
            return listaTipoDocumento;
        } catch (Exception e) {
            return null;
        }
    }
    
    
              
    
// ========== NAVEGACIÓN Y PREPARACIÓN ==========
    
       
    public String crearEstudianteP1() {
    con = new Estudiante();
    cur = new Curso();
    td = new TipoDocumento();
    // Corregir la navegación - faltaba un "=" 
    return "/views/caracterizacion/crearestudiantetea.xhtml?faces-redirect=true";
}

    
    
        public String editarEstudianteP1 (Estudiante con2){
        this.con = con2;
        this.cur.setIdCurso(con.getCursoIdCurso() .getIdCurso());
        this.td.setIdTipoDocumento(con.getTipoDocumentoIdTipoDocumento().getIdTipoDocumento());
        return "/views/caracterizacion/crearestudiantetea.xhtml?faces-redirect?true";    
    }
    
// ========== MÉTODOS PRINCIPALES DE REGISTRO ==========
    // Método de creación
    public void crearEstudianteP2() {
    try {
        // Establecer relaciones
        con.setCursoIdCurso(cur);
        con.setTipoDocumentoIdTipoDocumento(td);
        
        // Establecer fechas obligatorias
        java.util.Date ahora = new java.util.Date();
        con.setFechaRegistro(ahora);
        con.setCreatedAt(ahora);
        con.setUpdatedAt(ahora);
        
        // Establecer información adicional
        con.setExpedienteId(expedienteIdTemp);
        
        // Establecer usuario que registra (puedes obtenerlo de la sesión)
        if (usuarioQueRegistra != null) {
            con.setCreatedBy(usuarioQueRegistra);
            con.setUpdatedBy(usuarioQueRegistra);
        }
        
        // Valores por defecto para campos obligatorios
        if (con.getEstadoRegistro() == null || con.getEstadoRegistro().trim().isEmpty()) {
            con.setEstadoRegistro("ACTIVO");
        }
        
        // Validar campos obligatorios antes de guardar
        if (validarCamposObligatorios()) {
            
            if ("sospecha".equals(tipoRegistroSeleccionado)) {
                // Procesar señales de alerta seleccionadas + personalizada
                String alertasCombinadas = procesarSenalesAlerta();
                con.setAlerta(alertasCombinadas);
                con.setContextoObservacion(contextoObservacion);
                con.setDiagnosticoCertificado(false);
                
                // Limpiar campos de diagnóstico
                con.setTipoTea(null);
                con.setFechaDiagnostico(null);
                con.setProfesionalDiagnostico(null);
                con.setObservacionesDiagnostico(null);
                
            } else if ("diagnostico".equals(tipoRegistroSeleccionado)) {
                // Asignar campos del diagnóstico médico al estudiante
                con.setTipoTea(tipoTEA);
                con.setFechaDiagnostico(fechaDiagnostico);
                con.setProfesionalDiagnostico(profesionalDiagnostico);
                con.setObservacionesDiagnostico(observacionesDiagnostico);
                con.setDiagnosticoCertificado(true);
                
                // Limpiar campos de sospecha
                con.setAlerta(null);
                con.setContextoObservacion(null);
            }

            efl.create(con);

            // Mensajes y estados para panel éxito
            this.mensajeExito = "Estudiante registrado correctamente. ¡Bienvenido!";
            this.procesoCompletado = true;

            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro exitoso", mensajeExito));
        }
        
    } catch (Exception e) {
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al registrar: " + e.getMessage()));
        this.procesoCompletado = false;
        this.mensajeExito = null;
        e.printStackTrace(); // Para debug
    }
}


/**
 * Validar campos obligatorios según las anotaciones @NotNull de la entidad
 */
private boolean validarCamposObligatorios() {
    boolean valido = true;
    FacesContext fc = FacesContext.getCurrentInstance();
    
    // Validar campos obligatorios según tu entidad
    if (con.getNumeroDocumentoEstudiante() == null || con.getNumeroDocumentoEstudiante().trim().isEmpty()) {
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Número de documento es obligatorio"));
        valido = false;
    }
    
    if (con.getPrimerNombreEstudiante() == null || con.getPrimerNombreEstudiante().trim().isEmpty()) {
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Primer nombre es obligatorio"));
        valido = false;
    }
    
    if (con.getPrimerApellidoEstudiante() == null || con.getPrimerApellidoEstudiante().trim().isEmpty()) {
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Primer apellido es obligatorio"));
        valido = false;
    }
    
    if (con.getFechaNacimiento() == null) {
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Fecha de nacimiento es obligatoria"));
        valido = false;
    }
    
    if (con.getDireccionEstudiante() == null || con.getDireccionEstudiante().trim().isEmpty()) {
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Dirección es obligatoria"));
        valido = false;
    }
    
    if (con.getCorreoInstitucionalEstudiante() == null || con.getCorreoInstitucionalEstudiante().trim().isEmpty()) {
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Correo institucional es obligatorio"));
        valido = false;
    }
    
    if (con.getTipoDocumentoIdTipoDocumento() == null) {
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Tipo de documento es obligatorio"));
        valido = false;
    }
    
    if (con.getCursoIdCurso() == null) {
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Curso es obligatorio"));
        valido = false;
    }
    
    // Validaciones específicas por tipo de registro
    if ("sospecha".equals(tipoRegistroSeleccionado)) {
        // Validar que al menos haya una señal de alerta (seleccionada o personalizada)
        boolean tieneSenal = (senalesAlertaSeleccionadas != null && !senalesAlertaSeleccionadas.isEmpty()) ||
                           (otraSenalAlerta != null && !otraSenalAlerta.trim().isEmpty());
        
        if (!tieneSenal) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "Error", "Debe seleccionar al menos una señal de alerta o describir una personalizada"));
            valido = false;
        }
        
    } else if ("diagnostico".equals(tipoRegistroSeleccionado)) {
        // Validaciones para diagnóstico
        if (tipoTEA == null || tipoTEA.trim().isEmpty()) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "Error", "El tipo de TEA es obligatorio para diagnóstico confirmado"));
            valido = false;
        }
        
        if (fechaDiagnostico == null) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "Error", "La fecha de diagnóstico es obligatoria"));
            valido = false;
        }
    }
    
    // Validaciones de contactos familiares
    if (con.getAcudientePrincipal() == null || con.getAcudientePrincipal().trim().isEmpty()) {
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
            "Error", "El nombre del acudiente principal es obligatorio"));
        valido = false;
    }
    
    if (con.getRelacionAcudiente() == null || con.getRelacionAcudiente().trim().isEmpty()) {
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
            "Error", "La relación del acudiente es obligatoria"));
        valido = false;
    }
    
    if (con.getTelefonoAlternativo() == null || con.getTelefonoAlternativo().trim().isEmpty()) {
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
            "Error", "El teléfono de contacto es obligatorio"));
        valido = false;
    }
    
    if (con.getCorreoContacto() == null || con.getCorreoContacto().trim().isEmpty()) {
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
            "Error", "El correo de contacto es obligatorio"));
        valido = false;
    }
    
    
    return valido;
}

    
//===========OTROS METODOS=============    
    
   // Método de editar
    public void editarEstudianteP2 (){
        try {
            this.con.setCursoIdCurso(cur);
            this.con.setTipoDocumentoIdTipoDocumento(td);
            this.efl.edit(con);
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Estudiante editado correctamente", "MSG_INFO");
            fc.addMessage(null, fm);
        } catch (Exception e) {
            
        }
    }
    
    public void eliminarEstudiante(Estudiante con2){
        try {
            this.efl.remove(con2);
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Estudiante eliminado correctamente", "MSG_INFO");
            fc.addMessage(null, fm);
        } catch (Exception e) {
        }
    }
    
    
// ========== MÉTODO DE VALIDACIÓN DE NO DUPLICIDAD (POR DOCUMENTO) / AJAX ==========
    
    public void validarDocumentoExistente() {
    FacesContext fc = FacesContext.getCurrentInstance();
    try {
        if (td != null && td.getIdTipoDocumento() != null && 
            con.getNumeroDocumentoEstudiante() != null && 
            !con.getNumeroDocumentoEstudiante().trim().isEmpty()) {
            
            Estudiante existente = efl.findByDocumento(td.getIdTipoDocumento(), 
                                                      con.getNumeroDocumentoEstudiante());
            if (existente != null && (con.getIdEstudiante() == null || 
                !existente.getIdEstudiante().equals(con.getIdEstudiante()))) {
                
                fc.addMessage("preRegistroForm:numeroDocumentoEstudiante",
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Advertencia", 
                        "Este documento ya está registrado para el estudiante con Id: " + existente.getIdEstudiante()));
                System.out.println("validarDocumentoExistente llamado para documento: " + con.getNumeroDocumentoEstudiante());

                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
    "Advertencia GLOBAL", 
    "Este documento ya está registrado para el estudiante con Id: " + existente.getIdEstudiante()));
            }
        }
    } catch (Exception e) {
        // Opcional: Log o manejo de error silencioso para no interrumpir flujo
    }
}

  
    
//========= Estado de proceso completado==================
private boolean procesoCompletado = false;
private String mensajeExito;

// Getter y Setter
public boolean isProcesoCompletado() {
    return procesoCompletado;
}

public void setProcesoCompletado(boolean procesoCompletado) {
    this.procesoCompletado = procesoCompletado;
}

public String getMensajeExito() {
    return mensajeExito;
}

public void setMensajeExito(String mensajeExito) {
    this.mensajeExito = mensajeExito;
}

//==========Métodos para navegación usados en los commandButton del panel éxito=============
public String irACaracterizacion() {
    // Aquí defines la ruta a la caracterización, por ejemplo:
    return "/views/caracterizacion/iniciarcaracterizacion.xhtml?faces-redirect=true";
}

public String irARutaDiagnostico() {
    // Aquí defines la ruta a la ruta de diagnóstico, por ejemplo:
    return "/views/caracterizacion/rutadiagnostico.xhtml?faces-redirect=true";
}

public String nuevoRegistro() {
    // Resetea el formulario y estado para un nuevo registro
    this.con = new Estudiante();
    this.procesoCompletado = false;
    this.mensajeExito = null;
    this.cur = new Curso();
    this.td = new TipoDocumento();

    return null; // Quedarse en la misma vista para empezar de nuevo
}

//=============================
/**
 * Limpia el formulario para nuevo registro
 */
public void limpiarFormulario() {
    con = new Estudiante();
    cur = new Curso();
    td = new TipoDocumento();
    procesoCompletado = false;
    mensajeExito = null;
    tipoRegistroSeleccionado = null;
    otraSenalAlerta = null;
    contextoObservacion = null;
    
    // Limpiar campos de diagnóstico
    tipoTEA = null;
    fechaDiagnostico = null;
    profesionalDiagnostico = null;
    observacionesDiagnostico = null;
    documentacionDisponible = null;
    
    // Limpiar campos adicionales
    expedienteIdTemp = null;
    observacionesGenerales = null;
    usuarioQueRegistra = null;
    
    // Limpiar señales de alerta
    if (senalesAlertaSeleccionadas != null) {
        senalesAlertaSeleccionadas.clear();
    } else {
        senalesAlertaSeleccionadas = new ArrayList<>();
    }
}


//================TIPO DE REGISTRO DIAGNOSTICO/SOSPECHA========
    

// Atributo para control de tipo registro seleccionado
private String tipoRegistroSeleccionado;

// Getter y Setter
public String getTipoRegistroSeleccionado() {
    return tipoRegistroSeleccionado;
}

public void setTipoRegistroSeleccionado(String tipoRegistroSeleccionado) {
    this.tipoRegistroSeleccionado = tipoRegistroSeleccionado;
}

private String otraSenalAlerta;
private String contextoObservacion;

public String getOtraSenalAlerta() { return otraSenalAlerta; }
public void setOtraSenalAlerta(String otraSenalAlerta) { this.otraSenalAlerta = otraSenalAlerta; }

public String getContextoObservacion() { return contextoObservacion; }
public void setContextoObservacion(String contextoObservacion) { this.contextoObservacion = contextoObservacion; }



@PostConstruct
public void init() {
    // Inicializar objetos si es necesario
    if (con == null) {
        con = new Estudiante();
    }
    if (cur == null) {
        cur = new Curso();
    }
    if (td == null) {
        td = new TipoDocumento();
    }
}

// ========== CAMPOS DE DIAGNÓSTICO ==========
private String tipoTEA;
private Date fechaDiagnostico;
private String profesionalDiagnostico;
private String observacionesDiagnostico;
private Boolean documentacionDisponible;

// Getters y Setters
public String getTipoTEA() {
    return tipoTEA;
}

public void setTipoTEA(String tipoTEA) {
    this.tipoTEA = tipoTEA;
}

public Date getFechaDiagnostico() {
    return fechaDiagnostico;
}

public void setFechaDiagnostico(Date fechaDiagnostico) {
    this.fechaDiagnostico = fechaDiagnostico;
}

public String getProfesionalDiagnostico() {
    return profesionalDiagnostico;
}

public void setProfesionalDiagnostico(String profesionalDiagnostico) {
    this.profesionalDiagnostico = profesionalDiagnostico;
}

public String getObservacionesDiagnostico() {
    return observacionesDiagnostico;
}

public void setObservacionesDiagnostico(String observacionesDiagnostico) {
    this.observacionesDiagnostico = observacionesDiagnostico;
}

public Boolean getDocumentacionDisponible() {
    return documentacionDisponible;
}

public void setDocumentacionDisponible(Boolean documentacionDisponible) {
    this.documentacionDisponible = documentacionDisponible;
}



//================DIAGNOSTICO POR SOSPECHA========
// ========== CAMPOS PARA SOSPECHA FUNDADA ==========
private List<String> senalesAlertaSeleccionadas;

// Constructor o inicialización
public EstudianteTeaController() {
    senalesAlertaSeleccionadas = new ArrayList<>();
}

// Getter y Setter
public List<String> getSenalesAlertaSeleccionadas() {
    return senalesAlertaSeleccionadas;
}

public void setSenalesAlertaSeleccionadas(List<String> senalesAlertaSeleccionadas) {
    this.senalesAlertaSeleccionadas = senalesAlertaSeleccionadas;
}

/**
 * Convierte la lista de señales seleccionadas en un string para almacenar en alerta
 */
private String procesarSenalesAlerta() {
    if (senalesAlertaSeleccionadas != null && !senalesAlertaSeleccionadas.isEmpty()) {
        StringBuilder alertas = new StringBuilder();
        
        // Agregar señales seleccionadas
        for (String senal : senalesAlertaSeleccionadas) {
            if (alertas.length() > 0) {
                alertas.append("; ");
            }
            alertas.append(senal);
        }
        
        // Agregar señal personalizada si existe
        if (otraSenalAlerta != null && !otraSenalAlerta.trim().isEmpty()) {
            if (alertas.length() > 0) {
                alertas.append("; ");
            }
            alertas.append("OTRA: ").append(otraSenalAlerta.trim());
        }
        
        return alertas.toString();
    }
    
    // Si no hay señales seleccionadas, solo retornar la personalizada
    return (otraSenalAlerta != null && !otraSenalAlerta.trim().isEmpty()) ? 
           "OTRA: " + otraSenalAlerta.trim() : null;
}

// ========== CAMPOS DE CONTACTOS FAMILIARES ========== 
//metodos para familiar cmplejas



// ========== CAMPOS DE INFORMACIÓN ADICIONAL ==========
private String expedienteIdTemp; // Campo temporal para el formulario
private String observacionesGenerales; // Campo adicional 
private Integer usuarioQueRegistra; // Para createdBy/updatedBy

// Getters y Setters adicionales
public String getExpedienteIdTemp() {
    return expedienteIdTemp;
}

public void setExpedienteIdTemp(String expedienteIdTemp) {
    this.expedienteIdTemp = expedienteIdTemp;
}

public String getObservacionesGenerales() {
    return observacionesGenerales;
}

public void setObservacionesGenerales(String observacionesGenerales) {
    this.observacionesGenerales = observacionesGenerales;
}

public Integer getUsuarioQueRegistra() {
    return usuarioQueRegistra;
}

public void setUsuarioQueRegistra(Integer usuarioQueRegistra) {
    this.usuarioQueRegistra = usuarioQueRegistra;
}

// ========== MÉTODOS AUXILIARES ==========
public String getFechaActual() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    return sdf.format(new Date());
}




    
}
 