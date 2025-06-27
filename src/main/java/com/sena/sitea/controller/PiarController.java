/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.sena.sitea.controller;

import com.sena.sitea.entities.Estudiante;
import com.sena.sitea.entities.Piar;
import com.sena.sitea.services.EstudianteFacadeLocal;
import com.sena.sitea.services.PiarFacadeLocal;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named(value = "piarController")
@SessionScoped
public class PiarController implements Serializable {

    private Piar pi;
    private Estudiante est;
    private List<SelectItem> listaEstudiantes;

    @EJB
    private PiarFacadeLocal pfl;

    @EJB
    private EstudianteFacadeLocal efl;

    @PostConstruct
    public void init() {
        System.out.println("🧪 Iniciando PiarController...");
        pi = new Piar();
        est = new Estudiante();
        listaEstudiantes = new ArrayList<>();

        try {
            List<Estudiante> estudiantes = efl.findAll();

            if (estudiantes == null) {
                System.out.println("🚨 La lista de estudiantes es NULL");
            } else {
                System.out.println("📋 Estudiantes encontrados: " + estudiantes.size());

                for (Estudiante e : estudiantes) {
                    System.out.println("👤 Agregando estudiante: " +
                        e.getIdEstudiante() + " - " + e.getnumero_documento_estudiante());
                    listaEstudiantes.add(new SelectItem(
                        e.getIdEstudiante(),
                        e.getnumero_documento_estudiante() + " - " + e.getPrimerNombreEstudiante()
                    ));
                }
            }

        } catch (Exception e) {
            System.out.println("❌ Error en init(): " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Getters y acciones
    public Piar getPi() {
        return pi;
    }

    public void setPi(Piar pi) {
        this.pi = pi;
    }

    public Estudiante getEst() {
        return est;
    }

    public void setEst(Estudiante est) {
        this.est = est;
    }

    public List<SelectItem> getListaEstudiantes() {
        return listaEstudiantes;
    }

    public List<Piar> obtenerpiar() {
        return pfl.findAll();
    }

    public String crearP1() {
        pi = new Piar();
        est = new Estudiante();
        return "/views/Piar/crearact.xhtml?faces-redirect=true";
    }

    public void crearP2() {
        try {
            Estudiante estudianteSeleccionado = efl.find(est.getIdEstudiante());
            pi.setEstudianteIdEstudiante(estudianteSeleccionado);
            pfl.create(pi);
            mostrarMensaje("✅ PIAR registrado correctamente", FacesMessage.SEVERITY_INFO);
            pi = new Piar();
            est = new Estudiante();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarMensaje("❌ Error al registrar PIAR", FacesMessage.SEVERITY_ERROR);
        }
    }

    public String editarPiarP1(Piar pi2) {
        this.pi = pi2;
        this.est.setIdEstudiante(pi2.getEstudianteIdEstudiante().getIdEstudiante());
        return "/views/caracterizacion/crearestudiantetea.xhtml?faces-redirect=true";
    }

    public void editarPiarP2() {
        try {
            Estudiante estudianteSeleccionado = efl.find(est.getIdEstudiante());
            pi.setEstudianteIdEstudiante(estudianteSeleccionado);
            pfl.edit(pi);
            mostrarMensaje("✔️ PIAR editado correctamente", FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            mostrarMensaje("❌ Error al editar PIAR", FacesMessage.SEVERITY_ERROR);
        }
    }

    public void eliminarPiar(Piar pi2) {
        try {
            pfl.remove(pi2);
            mostrarMensaje("🗑️ PIAR eliminado", FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            mostrarMensaje("❌ Error al eliminar PIAR", FacesMessage.SEVERITY_ERROR);
        }
    }

    private void mostrarMensaje(String texto, FacesMessage.Severity tipo) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(tipo, texto, null));
    }
}