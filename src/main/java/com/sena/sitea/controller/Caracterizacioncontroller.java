/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.sena.sitea.controller;

import com.sena.sitea.entities.Caracterizacion;
import com.sena.sitea.entities.Estudiante;
import com.sena.sitea.services.CaracterizacionFacadeLocal;
import com.sena.sitea.services.EstudianteFacadeLocal;
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
 * @author bjcab
 */
@Named(value = "caracterizacioncontroller")
@SessionScoped
public class Caracterizacioncontroller implements Serializable {

    Caracterizacion car = new Caracterizacion();
    Estudiante est = new Estudiante();
    List<SelectItem> listaEstudiante;
    
    @EJB
    CaracterizacionFacadeLocal cafl;
    @EJB
    EstudianteFacadeLocal efl;

    public Caracterizacion getCar() {
        return car;
    }

    public void setCar(Caracterizacion car) {
        this.car = car;
    }
    
    public Estudiante getEst() {
        return est;
    }

    public void setEst(Estudiante est) {
        this.est = est;
    }
    
    public List<Caracterizacion> obtenerCaracterizaciones (){
        try {
            return this.cafl.findAll();
        } catch (Exception e) {
        }
        return null;
    }
   
    public Caracterizacioncontroller() {
    }
    
    public List<SelectItem> listaEstudiante(){
        listaEstudiante = new ArrayList<>();
        try {
            for(Estudiante est : this.efl.findAll()){
                SelectItem item = new SelectItem(est.getIdEstudiante(), est.getPrimerNombreEstudiante());
                listaEstudiante.add(item);
            }
            return listaEstudiante;
        } catch (Exception e) {
            return null;
        }
        
    }
    
    public String crearCaracterizacionP1 (){
        car = new Caracterizacion ();
        return "/views/caracterizacion/crearcaracterizacion.xhtml?faces-redirect?true";
    }
    
    public void crearCaracterizacionP2 () {
        car.setEstudianteIdEstudiante(est);
                
        try {
            this.cafl.create(car);
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Caracterización registrada correctamente", "MSG_INFO");
            fc.addMessage(null, fm);
            car = new Caracterizacion();
        } catch (Exception e) {
            
        }
        
    }
    
    public String editarCaracteriacionP1 (Caracterizacion car2){
        this.car = car2;
        this.est.setIdEstudiante(car.getEstudianteIdEstudiante().getIdEstudiante());
        return "/views/caracterizacion/crearcaracterizacion.xhtml?faces-redirect?true";    
    }
    
    public void editarCaracterizacionP2 (){
        try {
            this.car.setEstudianteIdEstudiante(est);
            this.cafl.edit(car);
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Caracterización editada correctamente", "MSG_INFO");
            fc.addMessage(null, fm);
        } catch (Exception e) {
            
        }
    }
    
    public void eliminarCaracterizacion(Caracterizacion car2){
        try {
            this.cafl.remove(car2);
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Caracterización eliminada correctamente", "MSG_INFO");
            fc.addMessage(null, fm);
        } catch (Exception e) {
        }
    }
    
}
