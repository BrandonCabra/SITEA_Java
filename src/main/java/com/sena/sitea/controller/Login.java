/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.sena.sitea.controller;

import com.sena.sitea.entities.Permisos;
import com.sena.sitea.entities.Usuarios;
import com.sena.sitea.security.PasswordUtil;
import com.sena.sitea.services.PermisosFacadeLocal;
import com.sena.sitea.services.UsuariosFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author bjcab
 */
@Named(value = "login")
@SessionScoped
public class Login implements Serializable {

    private String NUMERO_DOCUMENTO;
    private String PASSWORD;
    private Usuarios usuario = new Usuarios();
    private List<Permisos> listaPermisos;
    
    private String nombreUsuario;
    String rolActual;
    
    

    @EJB
    private UsuariosFacadeLocal ufl;

    @EJB
    private PermisosFacadeLocal pfl;

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getRolActual() {
        return rolActual;
    }

    public void setRolActual(String rolActual) {
        this.rolActual = rolActual;
    }

    
    
    public List<Permisos> getListaPermisos() {
        return listaPermisos;
    }

    public void setListaPermisos(List<Permisos> listaPermisos) {
        this.listaPermisos = listaPermisos;
    }

    public String getNUMERO_DOCUMENTO() {
        return NUMERO_DOCUMENTO;
    }

    public void setNUMERO_DOCUMENTO(String NUMERO_DOCUMENTO) {
        this.NUMERO_DOCUMENTO = NUMERO_DOCUMENTO;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String iniciarSesion() {
        System.out.print("Hash del password: " + PasswordUtil.hashPassword(PASSWORD));
        this.usuario = this.ufl.iniciarSesion(NUMERO_DOCUMENTO, PASSWORD);
        if (usuario.getNumeroDocumento() != null) {
            HttpSession sesion = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            sesion.setAttribute("NUMERO_DOCUMENTO", NUMERO_DOCUMENTO);
            this.listaPermisos = this.pfl.PermisosByUsuario(usuario);
            
            this.nombreUsuario = usuario.getPrimerNombre() + " " + usuario.getPrimerApellido(); //nombre completo
            if (!usuario.getUsuarioRolList().isEmpty()){
                this.rolActual = usuario.getUsuarioRolList().get(0).getRolIdRol().getNombreRol();
            }
            System.out.println("Rol asignado: " + this.rolActual);
         
            return "/views/inicio.xhtml?faces-redirect=true";
        } else {
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Numero de documento y/o Contraseña incorrectas", "MSG_INFO");
            fc.addMessage(null, fm);
            return null;
        }

    }

    public String formatearDescripcion(String descripcion) {
        if (descripcion == null) {
            return "";
        }

        // Insertar espacio entre letras mayúsculas si la siguiente es minúscula o si hay cambio de palabra
        return descripcion
                .replaceAll("(?<=[A-Z])(?=[A-Z][a-z])", " ")
                .replaceAll("(?<=[a-z])(?=[A-Z])", " ");
    }
    
    public String aplicarTildes(String texto) {
    if (texto == null) return "";

    // Mapeo manual de palabras clave
    Map<String, String> conTildes = new HashMap<>();
    conTildes.put("CARACTERIZACION", "CARACTERIZACIÓN");
    conTildes.put("GESTION", "GESTIÓN");
    conTildes.put("INSTITUCION", "INSTITUCIÓN");
    conTildes.put("MODULOS", "MÓDULOS");
    conTildes.put("PSICOPEDAGOGICA", "PSICOPEDAGÓGICA");
    conTildes.put("BOLETIN", "BOLETÍN");
    // Separar el texto y corregir palabra por palabra
    String[] palabras = texto.split(" ");
    StringBuilder resultado = new StringBuilder();

    for (String p : palabras) {
        String palabraFormateada = conTildes.getOrDefault(p.toUpperCase(), p);
        resultado.append(palabraFormateada).append(" ");
    }

    return resultado.toString().trim();
}

    public Login() {
    }

    public String cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login.xhtml?faces-redirect=true";
    }

}
