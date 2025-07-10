package com.sena.sitea.controller;

import com.sena.sitea.entities.Rol;
import com.sena.sitea.entities.TipoDocumento;
import com.sena.sitea.entities.Usuarios;
import com.sena.sitea.services.RolFacadeLocal;
import com.sena.sitea.services.TipoDocumentoFacadeLocal;
import com.sena.sitea.services.UsuariosFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.mindrot.jbcrypt.BCrypt;
import javax.validation.ConstraintViolation;
/*import javax.enterprise.context.ViewScoped;*/
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named(value = "registro")
@ViewScoped
public class Registro implements Serializable {

    private Usuarios con = new Usuarios(); // Usuario para registro

    @EJB
    private UsuariosFacadeLocal rfl;

    @EJB
    private TipoDocumentoFacadeLocal tipoDocumentoFacade;

    @EJB
    private RolFacadeLocal rolFacade;

    public Registro() {
    }

    // Getters y setters
    public Usuarios getCon() {
        return con;
    }

    public void setCon(Usuarios con) {
        this.con = con;
    }

    public List<TipoDocumento> getListaTipoDocumentos() {
        return tipoDocumentoFacade.findAll();
    }

    public List<Rol> getListaRoles() {
        return rolFacade.findAll();
    }

    public String crearP1() {
        this.con = new Usuarios();
        return "/TemplateRegistro.xhtml";
    }

    public String crearP2() {
        System.out.println("🟢 Entró al método crearP2()");
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        System.out.println("📦 Sesión activa: " + (session != null));

        try {
            if (con.getPassword() == null || con.getPassword().isEmpty()
                    || con.getPrimerNombre() == null || con.getPrimerApellido() == null
                    || con.getRolIdRol() == null || con.getTipoDocumentoIdTipoDocumento() == null) {

                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Faltan campos obligatorios", "Completa todos los campos antes de registrar."));
                return null;
            }

            String hashed = BCrypt.hashpw(con.getPassword(), BCrypt.gensalt());
            con.setPassword(hashed);

            rfl.create(con);
            System.out.println("✅ Usuario registrado correctamente: " + con.getPrimerNombre());

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Registro exitoso!", "El usuario fue registrado correctamente."));
            this.con = new Usuarios();

            return null;

        } catch (javax.validation.ConstraintViolationException ex) {
            for (ConstraintViolation<?> cv : ex.getConstraintViolations()) {
                System.err.println("⚠️ Campo: " + cv.getPropertyPath());
                System.err.println("💬 Mensaje: " + cv.getMessage());
            }
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Datos inválidos", "Verifica todos los campos obligatorios."));
            return null;

        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en el sistema", "No se pudo registrar el usuario."));
            return null;
        }
    }
}
