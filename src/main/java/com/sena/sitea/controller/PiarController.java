/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.sena.sitea.controller;

import com.sena.sitea.entities.Estudiante;
import com.sena.sitea.entities.Piar;
import com.sena.sitea.services.EstudianteFacadeLocal;
import com.sena.sitea.services.PiarFacadeLocal;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author User
 */
@Named(value = "piarController")
@SessionScoped
public class PiarController implements Serializable {

    private Piar pi = new Piar();
    private Estudiante est = new Estudiante();
    private List<SelectItem> listaEstudiantes;
    
    @EJB
    private PiarFacadeLocal pfl;
    @EJB
    private EstudianteFacadeLocal efl;

    public Estudiante getEst() {
        return est;
    }

    public void setEst(Estudiante est) {
        this.est = est;
    }

    public Piar getPi() {
        return pi;
    }

    public void setPi(Piar pi) {
        this.pi = pi;
    }

    public List<Piar> obtenerpiar() {

        return this.pfl.findAll();
    }

    public PiarController() {
    }

    public List<SelectItem> listarEstudiantes() {
        listaEstudiantes = new ArrayList<>();
        try {
            for (Estudiante est : this.efl.findAll()) {
                SelectItem item = new SelectItem(est.getIdEstudiante(), est.getnumero_documento_estudiante());
                listaEstudiantes.add(item);
            }
            return listaEstudiantes;
        } catch (Exception e) {
        }
        return null;
    }

    public String crearP1() {
        this.pi = new Piar();
        return "/views/Piar/crearact.xhtml?faces-redirect=true";
    }

    public void crearP2() {
        this.pi.setIdEstudiante(est);
        try {
            this.pfl.create(pi);
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "PIAR Registrado", "MSG_INFO");
            fc.addMessage(null, fm);
            this.pi = new Piar();
        } catch (Exception e) {
        }
    }

    public String editarPiarP1(Piar pi2) {
        this.pi = pi2;
        this.est.setIdEstudiante(est.getIdEstudiante());
        return "/views/caracterizacion/crearestudiantetea.xhtml?faces-redirect?true";
    }

    public void editarPiarP2() {
        try {
            this.pi.setIdEstudiante(est);
            this.pfl.edit(pi);
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Estudiante editado correctamente", "MSG_INFO");
            fc.addMessage(null, fm);
        } catch (Exception e) {

        }
    }

    public void eliminarPiar(Piar pi2) {
        try {
            this.pfl.remove(pi);
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Estudiante eliminado correctamente", "MSG_INFO");
            fc.addMessage(null, fm);
        } catch (Exception e) {
        }
    }
}
