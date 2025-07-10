/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.sena.sitea.controller;

import com.sena.sitea.entities.Curso;
import com.sena.sitea.entities.Estudiante;
import com.sena.sitea.entities.TipoDocumento;
import com.sena.sitea.services.CursoFacadeLocal;
import com.sena.sitea.services.EstudianteFacadeLocal;
import com.sena.sitea.services.TipoDocumentoFacadeLocal;
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
@Named(value = "estudianteTeaController")
@SessionScoped
public class EstudianteTeaController implements Serializable {
    

    private Estudiante con = new Estudiante();
    Curso cur = new Curso();
    List<SelectItem>listaCurso;
    
    TipoDocumento td = new TipoDocumento();
    List<SelectItem>listaTipoDocumento;
    
    
    @EJB
    private EstudianteFacadeLocal efl;
    @EJB
    CursoFacadeLocal cfl;
    @EJB
    TipoDocumentoFacadeLocal tfl;
    
    

    public Estudiante getCon() {
        return con;
    }

    public void setCon(Estudiante con) {
        this.con = con;
    }

    public Curso getCur() {
        return cur;
    }

    public void setCur(Curso cur) {
        this.cur = cur;
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
    
    public List<SelectItem> listaCurso (){
        listaCurso = new ArrayList<>();
        try {
            for(Curso cur : this.cfl.findAll()){
                String label = String.valueOf(cur.getCodigoCurso());

                SelectItem item = new SelectItem(cur.getIdCurso(), label);
                listaCurso.add(item);
                
            }
            return listaCurso;
        } catch (Exception e) {
            
        }
        return null;
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
    
    public void crearEstudianteP2 () {
        con.setCursoIdCurso(cur);
        con.setTipoDocumentoIdTipoDocumento(td);
        
        try {
            this.efl.create(con);
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Estudiante registrado correctamente", "MSG_INFO");
            fc.addMessage(null, fm);
            con = new Estudiante();
        } catch (Exception e) {
            
        }
        
    }
    
    public String editarEstudianteP1 (Estudiante con2){
        this.con = con2;
        this.cur.setIdCurso(con.getCursoIdCurso() .getIdCurso());
        this.td.setIdTipoDocumento(con.getTipoDocumentoIdTipoDocumento().getIdTipoDocumento());
        return "/views/caracterizacion/crearestudiantetea.xhtml?faces-redirect?true";    
    }
    
    public void editarEstudianteP2 (){
        try {
            this.con.setCursoIdCurso(cur);
            this.con.setTipoDocumentoIdTipoDocumento(td);
            this.efl.edit(con);
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Estudiante editado correctamente", "MSG_INFO");
            fc.addMessage(null, fm);
        } catch (Exception e) {
            
        }
    }
    
    public void eliminarEstudiante(Estudiante con2){
        try {
            this.efl.remove(con2);
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Estudiante eliminado correctamente", "MSG_INFO");
            fc.addMessage(null, fm);
        } catch (Exception e) {
        }
    }
    
}
