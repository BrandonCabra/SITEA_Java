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
import java.util.Date;
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
                if(est.getCaracterizacionList().isEmpty()){
                
                SelectItem item = new SelectItem(est.getIdEstudiante(), est.getNumeroDocumentoEstudiante() + " " + est.getPrimerNombreEstudiante() + " " + est.getPrimerApellidoEstudiante());
                listaEstudiante.add(item);
                }
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
        try {
            // Buscar el estudiante seleccionado por ID
            if (est != null && est.getIdEstudiante() != null) {
                Estudiante estudianteSeleccionado = this.efl.find(est.getIdEstudiante());
                car.setEstudianteIdEstudiante(estudianteSeleccionado);
            } else {
                FacesContext fc = FacesContext.getCurrentInstance();
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe seleccionar un estudiante", "MSG_ERROR");
                fc.addMessage(null, fm);
                return;
            }
            
            // Establecer fechas de auditoría
            car.setCreatedAt(new Date());
            car.setUpdatedAt(new Date());
            car.setFechaInicio(new Date());
            car.setEstadoCaracterizacion("EN_PROCESO");
            
            this.cafl.create(car);
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Caracterización registrada correctamente", "MSG_INFO");
            fc.addMessage(null, fm);
            car = new Caracterizacion();
            est = new Estudiante();
        } catch (Exception e) {
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al registrar caracterización: " + e.getMessage(), "MSG_ERROR");
            fc.addMessage(null, fm);
            e.printStackTrace();
        }
        
    }
    
    public String editarCaracteriacionP1 (Caracterizacion car2){
        this.car = car2;
        this.est.setIdEstudiante(car.getEstudianteIdEstudiante().getIdEstudiante());
        return "/views/caracterizacion/crearcaracterizacion.xhtml?faces-redirect?true";    
    }
    
    public void editarCaracterizacionP2 (){
        try {
            // Buscar el estudiante seleccionado por ID si cambió
            if (est != null && est.getIdEstudiante() != null) {
                Estudiante estudianteSeleccionado = this.efl.find(est.getIdEstudiante());
                this.car.setEstudianteIdEstudiante(estudianteSeleccionado);
            }
            
            // Actualizar fecha de modificación
            this.car.setUpdatedAt(new Date());
            
            this.cafl.edit(car);
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Caracterización editada correctamente", "MSG_INFO");
            fc.addMessage(null, fm);
        } catch (Exception e) {
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al editar caracterización: " + e.getMessage(), "MSG_ERROR");
            fc.addMessage(null, fm);
            e.printStackTrace();
        }
    }
    
    public void eliminarCaracterizacion(Caracterizacion car2){
        try {
            this.cafl.remove(car2);
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Caracterización eliminada correctamente", "MSG_INFO");
            fc.addMessage(null, fm);
        } catch (Exception e) {
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al eliminar caracterización: " + e.getMessage(), "MSG_ERROR");
            fc.addMessage(null, fm);
            e.printStackTrace();
        }
    }
    
}
