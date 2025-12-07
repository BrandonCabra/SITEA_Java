package com.sena.sitea.controller;

import com.sena.sitea.entities.AuditEstudiante;
import com.sena.sitea.entities.Usuarios;
import com.sena.sitea.services.AuditEstudianteFacadeLocal;
import com.sena.sitea.services.PasswordHashService;
import com.sena.sitea.services.UsuariosFacadeLocal;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import javax.imageio.ImageIO;

@Named(value = "perfilBean")
@SessionScoped

public class PerfilBean implements Serializable {

    private Usuarios usuario;
    private Part uploadedPhoto;
    private Date filterFrom;
    private Date filterTo;
    private List<AuditEstudiante> actividadesFiltradas = new ArrayList<>();
    
    @EJB
private PasswordHashService passwordService; // <--- AGREGA ESTO

    @EJB
    private UsuariosFacadeLocal ufl;

    @EJB
    private AuditEstudianteFacadeLocal auditFacade;

    @PostConstruct
    public void init() {
        cargarUsuarioDesdeSesion();
        cargarActividades();
    }

    private void cargarUsuarioDesdeSesion() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Object o = fc.getExternalContext().getSessionMap().get("login");
        if (o instanceof Login) {
            Login l = (Login) o;
            this.usuario = l.getUsuario();
        }
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public Part getUploadedPhoto() {
        return uploadedPhoto;
    }

    public void setUploadedPhoto(Part uploadedPhoto) {
        this.uploadedPhoto = uploadedPhoto;
    }

    public Date getFilterFrom() {
        return filterFrom;
    }

    public void setFilterFrom(Date filterFrom) {
        this.filterFrom = filterFrom;
    }

    public Date getFilterTo() {
        return filterTo;
    }

    public void setFilterTo(Date filterTo) {
        this.filterTo = filterTo;
    }

    public List<AuditEstudiante> getActividadesFiltradas() {
        return actividadesFiltradas;
    }

    public void guardarPerfil() {
        if (usuario == null) return;
        try {
            ufl.edit(usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Perfil actualizado correctamente", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al actualizar perfil: " + e.getMessage(), null));
        }
    }

    public void subirFoto() {
        if (uploadedPhoto == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Seleccione una imagen para subir", null));
            return;
        }

        String contentType = uploadedPhoto.getContentType();
        if (!("image/png".equals(contentType) || "image/jpeg".equals(contentType))) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Tipo de archivo no permitido. Use PNG o JPG.", null));
            return;
        }

        try {
            long size = uploadedPhoto.getSize();
            if (size > 2L * 1024L * 1024L) { // 2MB
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El archivo excede 2MB.", null));
                return;
            }

            FacesContext fc = FacesContext.getCurrentInstance();
            ServletContext ctx = (ServletContext) fc.getExternalContext().getContext();
            String uploadsRelative = "/resources/uploads/perfiles";
            String realPath = ctx.getRealPath(uploadsRelative);
            File uploadsDir = new File(realPath);
            if (!uploadsDir.exists()) uploadsDir.mkdirs();

            String extension = contentType.equals("image/png") ? ".png" : ".jpg";
            String filename = "perfil_" + (usuario != null && usuario.getIdUsuario()!=null?usuario.getIdUsuario():"anon") + "_" + System.currentTimeMillis() + extension;
            File file = new File(uploadsDir, filename);

            // resize to 300x300 square
            BufferedImage inputImage = ImageIO.read(uploadedPhoto.getInputStream());
            if (inputImage == null) throw new IOException("Imagen inválida");
            BufferedImage resized = new BufferedImage(300, 300, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = resized.createGraphics();
            g2.drawImage(inputImage, 0, 0, 300, 300, null);
            g2.dispose();
            ImageIO.write(resized, extension.equals(".png")?"png":"jpg", file);

            String relativeUrl = uploadsRelative + "/" + filename;
            if (usuario != null) {
                // remove previous file if it was under resources/uploads/perfiles
                try {
                    if (usuario.getFotoPerfil()!=null && usuario.getFotoPerfil().startsWith(uploadsRelative)) {
                        File old = new File(ctx.getRealPath(usuario.getFotoPerfil()));
                        if (old.exists()) old.delete();
                    }
                } catch (Exception ex) {
                    // ignore cleanup errors
                }

                usuario.setFotoPerfil(relativeUrl);
                ufl.edit(usuario);
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Foto de perfil actualizada", null));

        } catch (IOException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error procesando la imagen: " + ex.getMessage(), null));
        }
    }
    
    // En PerfilBean.java

public String getFotoPerfil() {
    if (usuario != null && usuario.getFotoPerfil()!= null && !usuario.getFotoPerfil().isEmpty()) {
        // Asumiendo que guardas la ruta relativa o el nombre del archivo
        // Ajusta "/resources/images/perfiles/" según donde guardes las fotos reales
        return "/resources/images/perfiles/" + usuario.getFotoPerfil();
    }
    // Retorna una imagen por defecto si no tiene foto
    return "/resources/images/perfiles/usuario_default.jpg"; 
}

    public void cargarActividades() {
        actividadesFiltradas.clear();
        if (usuario == null) return;
        try {
            List<AuditEstudiante> all = auditFacade.findAll();
            List<AuditEstudiante> byUser = all.stream()
                    .filter(a -> a.getChangedBy() != null && a.getChangedBy().equals(usuario.getIdUsuario()))
                    .collect(Collectors.toList());
            actividadesFiltradas.addAll(byUser);
        } catch (Exception e) {
            // if backend not available, ignore
        }
    }

    public void filtrarActividades() {
        cargarActividades();
        if (filterFrom == null && filterTo == null) return;
        actividadesFiltradas = actividadesFiltradas.stream().filter(a -> {
            Date d = a.getChangedAt();
            if (d == null) return false;
            if (filterFrom != null && d.before(filterFrom)) return false;
            if (filterTo != null && d.after(filterTo)) return false;
            return true;
        }).collect(Collectors.toList());
    }

    // small helper to refresh data when ajax triggers
    public void onFilterChange(AjaxBehaviorEvent e) {
        filtrarActividades();
    }
    

    
    // Variables TEMPORALES (no se guardan en BD, solo sirven para el formulario)
private String currentPassword;
private String newPassword;
private String confirmPassword;

// Getters y Setters
public String getCurrentPassword() { return currentPassword; }
public void setCurrentPassword(String currentPassword) { this.currentPassword = currentPassword; }

public String getNewPassword() { return newPassword; }
public void setNewPassword(String newPassword) { this.newPassword = newPassword; }

public String getConfirmPassword() { return confirmPassword; }
public void setConfirmPassword(String confirmPassword) { this.confirmPassword = confirmPassword; }

// Método Lógico
public void cambiarPassword() {
    try {
        // 1. Validar que las nuevas coincidan
        if (newPassword == null || !newPassword.equals(confirmPassword)) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Las nuevas contraseñas no coinciden."));
            return;
        }

        // 2. Validar contraseña actual
        // CORRECCIÓN: Usamos 'passwordService' (la instancia inyectada) y el método 'verifyPassword'
        boolean passwordCorrecta = passwordService.verifyPassword(currentPassword, usuario.getPasswordUsuario());
        
        if (!passwordCorrecta) {
             FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La contraseña actual es incorrecta."));
             return;
        }

        // 3. Hashear nueva y guardar
        // CORRECCIÓN: Pasamos 'newPassword' como argumento
        String nuevoHash = passwordService.hashPassword(newPassword);
        
        usuario.setPasswordUsuario(nuevoHash);
        ufl.edit(usuario); // Guardar en BD

        // 4. Limpiar campos y notificar
        this.currentPassword = null;
        this.newPassword = null;
        this.confirmPassword = null;

        FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Tu contraseña ha sido actualizada correctamente."));

    } catch (Exception e) {
        FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se pudo actualizar: " + e.getMessage()));
        e.printStackTrace();
    }
}

// --- Lógica para Doble Factor de Autenticación (2FA) ---

    // 1. Variable para controlar el estado (false = desactivado, true = activado)
    private boolean autenticacion2FA;

    // 2. Getters y Setters (OBLIGATORIOS para que la vista lo encuentre)
    public boolean isAutenticacion2FA() {
        return autenticacion2FA;
    }

    public void setAutenticacion2FA(boolean autenticacion2FA) {
        this.autenticacion2FA = autenticacion2FA;
    }

    // 3. Método para activar/desactivar (El botón llama a esto)
    public void toggle2FA() {
        // Invertimos el valor actual
        this.autenticacion2FA = !this.autenticacion2FA;
        
        String estado = this.autenticacion2FA ? "ACTIVADO" : "DESACTIVADO";
        String severidad = this.autenticacion2FA ? "INFO" : "WARN";
        
        // NOTA: Aquí deberías guardar este estado en la base de datos si quieres que sea permanente.
        // Ejemplo: usuario.setTwoFactorEnabled(this.autenticacion2FA); ufl.edit(usuario);
        
        FacesMessage.Severity severityInfo = this.autenticacion2FA ? FacesMessage.SEVERITY_INFO : FacesMessage.SEVERITY_WARN;
        
        FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(severityInfo, "Seguridad 2FA", "El doble factor ha sido " + estado));
    }

}
