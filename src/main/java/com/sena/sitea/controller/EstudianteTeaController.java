/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.sena.sitea.controller;

import com.sena.sitea.entities.Estudiante;
import com.sena.sitea.entities.Grado;
import com.sena.sitea.entities.TipoDocumento;
import com.sena.sitea.services.EstudianteFacadeLocal;
import com.sena.sitea.services.GradoFacadeLocal;
import com.sena.sitea.services.TipoDocumentoFacadeLocal;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.model.SelectItem;

/**
 *
 * @author bjcab
 */
@Named(value = "estudianteTeaController")
@ViewScoped
public class EstudianteTeaController implements Serializable {
    

    private Estudiante con = new Estudiante();
    Grado gra = new Grado();
    List<SelectItem>listaGrado;
    
    TipoDocumento td = new TipoDocumento ();
    List<SelectItem>listaTipoDocumento;
    
    
    @EJB
    private EstudianteFacadeLocal efl;
    @EJB
    GradoFacadeLocal gfl;
    @EJB
    TipoDocumentoFacadeLocal tfl;
    
    

    public Estudiante getCon() {
        return con;
    }

    public void setCon(Estudiante con) {
        this.con = con;
    }

    public Grado getGra() {
        return gra;
    }

    public void setGra(Grado gra) {
        this.gra = gra;
    }

    public TipoDocumento getTd() {
        return td;
    }

    public void setTd(TipoDocumento td) {
        this.td = td;
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
    
    public List<SelectItem> listaGrado (){
        listaGrado = new ArrayList<>();
        try {
            for(Grado gra : this.gfl.findAll()){
                SelectItem item = new SelectItem(gra.getIdGrado(), gra.getCodigoGrado());
                listaGrado.add(item);
            }
            return listaGrado;
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<SelectItem> listaTipoDocumento(){
        listaTipoDocumento = new ArrayList<>();
        try {
            for(TipoDocumento td : this.tfl.findAll()){
                SelectItem item = new SelectItem(td.getIdTipoDocumento(), td.getNombreTipoDocumento());
                listaTipoDocumento.add(item);
            }
            return listaTipoDocumento;
        } catch (Exception e) {
            return null;
        }
    }
    
    public String crearEstudianteP1 (){
        con = new Estudiante ();
        return "/views/caracterizacion/crearestudiantetea.xhtml?faces-redirect?true";
    }
    
}
