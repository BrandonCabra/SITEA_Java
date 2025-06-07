/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.sena.sitea.controller;

import com.sena.sitea.entities.Usuarios;
import com.sena.sitea.services.UsuariosFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
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
    private Usuarios usuario= new Usuarios();
    @EJB
    private UsuariosFacadeLocal ufl;

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
    
    public String iniciarSesion (){
        this.usuario = this.ufl.iniciarSesion(NUMERO_DOCUMENTO, PASSWORD);
        if (usuario.getNumeroDocumento()!=null){
            HttpSession sesion = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            sesion.setAttribute("NUMERO_DOCUMENTO", NUMERO_DOCUMENTO);
            return "modulos.xhtml?faces-redirect=true";
        }else{
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Numero de documento y/o Contraseña incorrectas", "MSG_INFO");
            fc.addMessage(null, fm);
            return null;
        }
        
    }
     
   
    public Login() {
    }
    
    
    
}
