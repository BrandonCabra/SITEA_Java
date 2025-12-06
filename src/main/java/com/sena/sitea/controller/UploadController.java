package com.sena.sitea.controller;

import com.sena.sitea.util.UploadServiceClient;
import com.sena.sitea.util.UploadServiceClient.FileInfo;
import com.sena.sitea.util.UploadServiceClient.UploadResponse;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import java.io.Serializable;
import java.util.List;

/**
 * Controlador para la gestión de carga de archivos usando el servicio Python
 */
@ManagedBean(name = "uploadController")
@ViewScoped
public class UploadController implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private List<FileInfo> uploadedFiles;
    private String modulo = "general";
    private String descripcion;
    private boolean serviceAvailable;
    
    public UploadController() {
        checkServiceStatus();
        loadFiles();
    }
    
    /**
     * Verifica si el servicio Python está disponible
     */
    public void checkServiceStatus() {
        serviceAvailable = UploadServiceClient.isServiceAvailable();
        if (!serviceAvailable) {
            addMessage(FacesMessage.SEVERITY_WARN, 
                "Servicio de carga no disponible", 
                "Asegúrate de que el servicio Python esté ejecutándose en el puerto 5000");
        }
    }
    
    /**
     * Maneja la carga de un archivo
     */
    public void handleFileUpload(FileUploadEvent event) {
        if (!serviceAvailable) {
            addMessage(FacesMessage.SEVERITY_ERROR, 
                "Error", 
                "El servicio de carga no está disponible");
            return;
        }
        
        try {
            UploadedFile file = event.getFile();
            
            // Obtener usuario actual (ajustar según tu sistema de autenticación)
            String usuarioId = getCurrentUserId();
            
            // Subir archivo al servicio Python
            UploadResponse response = UploadServiceClient.uploadFile(
                file.getInputStream(),
                file.getFileName(),
                usuarioId,
                modulo,
                descripcion
            );
            
            if (response.isSuccess()) {
                addMessage(FacesMessage.SEVERITY_INFO, 
                    "Éxito", 
                    response.getMessage() + ": " + response.getOriginalFilename());
                
                // Recargar lista de archivos
                loadFiles();
                
                // Limpiar campos
                descripcion = "";
                
            } else {
                addMessage(FacesMessage.SEVERITY_ERROR, 
                    "Error", 
                    response.getError());
            }
            
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, 
                "Error", 
                "Error al procesar el archivo: " + e.getMessage());
        }
    }
    
    /**
     * Carga la lista de archivos subidos
     */
    public void loadFiles() {
        uploadedFiles = UploadServiceClient.listFiles();
    }
    
    /**
     * Elimina un archivo
     */
    public void deleteFile(String filename) {
        boolean success = UploadServiceClient.deleteFile(filename);
        
        if (success) {
            addMessage(FacesMessage.SEVERITY_INFO, 
                "Éxito", 
                "Archivo eliminado correctamente");
            loadFiles();
        } else {
            addMessage(FacesMessage.SEVERITY_ERROR, 
                "Error", 
                "No se pudo eliminar el archivo");
        }
    }
    
    /**
     * Obtiene el ID del usuario actual
     * Ajustar según tu sistema de autenticación
     */
    private String getCurrentUserId() {
        // Ejemplo: obtener desde la sesión
        FacesContext context = FacesContext.getCurrentInstance();
        Object userId = context.getExternalContext().getSessionMap().get("usuarioId");
        return userId != null ? userId.toString() : "unknown";
    }
    
    /**
     * Agrega un mensaje al contexto de JSF
     */
    private void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(severity, summary, detail));
    }
    
    // Getters y Setters
    public List<FileInfo> getUploadedFiles() {
        return uploadedFiles;
    }
    
    public void setUploadedFiles(List<FileInfo> uploadedFiles) {
        this.uploadedFiles = uploadedFiles;
    }
    
    public String getModulo() {
        return modulo;
    }
    
    public void setModulo(String modulo) {
        this.modulo = modulo;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public boolean isServiceAvailable() {
        return serviceAvailable;
    }
}
