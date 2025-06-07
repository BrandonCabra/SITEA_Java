/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.sena.sitea.controller;

import com.sena.sitea.entities.Estudiante;
import com.sena.sitea.services.EstudianteFacadeLocal;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author bjcab
 */
@Named(value = "estudianteTeaController")
@ViewScoped
public class EstudianteTeaController implements Serializable {

    private Estudiante con = new Estudiante();
    @EJB
    private EstudianteFacadeLocal efl;
    

    public Estudiante getCon() {
        return con;
    }

    public void setCon(Estudiante con) {
        this.con = con;
    }
    
    public List<Estudiante> obtenerEstudiantes (){
        try{
            return this.efl.findAll();  
        } catch (Exception e) {
        }
        return null;
    }
            
            
    public EstudianteTeaController() {
    }
    
}
