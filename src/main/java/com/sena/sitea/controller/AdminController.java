package com.sena.sitea.controller;

import com.sena.sitea.entities.AuditEstudiante;
import com.sena.sitea.entities.Rol;
import com.sena.sitea.entities.Usuarios;
import com.sena.sitea.entities.TipoDocumento;
import com.sena.sitea.services.AuditEstudianteFacadeLocal;
import com.sena.sitea.services.PasswordHashService;
import com.sena.sitea.services.RolFacadeLocal;
import com.sena.sitea.services.TipoDocumentoFacadeLocal;
import com.sena.sitea.services.UsuariosFacadeLocal;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named(value = "adminController")
@SessionScoped
public class AdminController implements Serializable {

    // --- Servicios EJB ---
    @EJB private UsuariosFacadeLocal ufl;
    @EJB private RolFacadeLocal rfl;
    @EJB private AuditEstudianteFacadeLocal auditFacade;
    @EJB private PasswordHashService passwordService;
    @EJB private TipoDocumentoFacadeLocal tdfl;

    // --- Variables de Vista ---
    private List<Usuarios> listaUsuarios;
    private List<Rol> listaRoles;
    private List<TipoDocumento> listaTiposDoc;
    private List<AuditEstudiante> listaLogs;
    
    private Usuarios usuarioSeleccionado;
    private String accionTitulo;

    @PostConstruct
    public void init() {
        cargarDatos();
        this.usuarioSeleccionado = new Usuarios();
    }

    public void cargarDatos() {
        try {
            this.listaUsuarios = ufl.findAll(); 
            this.listaRoles = rfl.findAll();
            this.listaTiposDoc = tdfl.findAll();
            this.listaLogs = auditFacade.findAll(); 
        } catch (Exception e) {
            System.err.println("Error cargando datos admin: " + e.getMessage());
        }
    }

    // --- Crear Usuario ---
    public void prepararCreacion() {
        this.usuarioSeleccionado = new Usuarios();
        this.usuarioSeleccionado.setEstatus("ACTIVO"); // Valor por defecto string
        this.accionTitulo = "Crear Nuevo Usuario";
    }

    // --- Editar Usuario ---
    public void prepararEdicion(Usuarios u) {
        this.usuarioSeleccionado = u;
        // Usamos primerNombre en lugar de nombreUsuario
        this.accionTitulo = "Editar Usuario: " + u.getPrimerNombre();
    }

    public void guardarUsuario() {
        try {
            if (usuarioSeleccionado.getIdUsuario() == null) {
                // --- CREACIÓN ---
                // Generar contraseña (usando numeroDocumento)
                String passTemporal = usuarioSeleccionado.getNumeroDocumento() + "Sitea2024*";
                // Usamos setPassword en lugar de setPasswordUsuario
                usuarioSeleccionado.setPassword(passwordService.hashPassword(passTemporal));
                
                // Fecha registro (usando el nombre largo de la entidad)
                usuarioSeleccionado.setFechaRegistroIdFechaRegistro(new Date());
                
                ufl.create(usuarioSeleccionado);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Usuario creado. Clave: " + passTemporal));
            } else {
                // --- EDICIÓN ---
                ufl.edit(usuarioSeleccionado);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Usuario actualizado."));
            }
            cargarDatos();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo guardar: " + e.getMessage()));
            e.printStackTrace();
        }
    }

    // --- Actualizar Estado (String) ---
    public void alternarEstado(Usuarios u) {
        // Lógica para alternar String "ACTIVO" <-> "INACTIVO"
        String nuevoEstado = "ACTIVO".equals(u.getEstatus()) ? "INACTIVO" : "ACTIVO";
        u.setEstatus(nuevoEstado);
        
        ufl.edit(u);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Estado", "Usuario ahora está " + nuevoEstado));
    }

    // --- Eliminar (Lógico) ---
    public void eliminarUsuario(Usuarios u) {
        u.setEstatus("ELIMINADO"); 
        ufl.edit(u);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Eliminado", "Usuario marcado como eliminado."));
    }

    // --- Restablecer Contraseña ---
    public void restablecerPassword(Usuarios u) {
        try {
            String nuevaPass = passwordService.generateTemporaryPassword();
            // Corrección: setPassword
            u.setPassword(passwordService.hashPassword(nuevaPass));
            ufl.edit(u);
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Restablecido", "Nueva clave: " + nuevaPass));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo cambiar clave."));
        }
    }
    
    public void descargarReporte(String tipo) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Reporte", "Descargando " + tipo + "..."));
    }

    // --- Getters y Setters ---
    public List<Usuarios> getListaUsuarios() { return listaUsuarios; }
    public List<Rol> getListaRoles() { return listaRoles; }
    public List<TipoDocumento> getListaTiposDoc() { return listaTiposDoc; }
    public List<AuditEstudiante> getListaLogs() { return listaLogs; }
    public Usuarios getUsuarioSeleccionado() { return usuarioSeleccionado; }
    public void setUsuarioSeleccionado(Usuarios usuarioSeleccionado) { this.usuarioSeleccionado = usuarioSeleccionado; }
    public String getAccionTitulo() { return accionTitulo; }
}