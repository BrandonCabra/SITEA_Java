/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sena.sitea.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "piar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Piar.findAll", query = "SELECT p FROM Piar p")
})
public class Piar implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PIAR")
    private Integer idPiar;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "CODIGO_PIAR")
    private String codigoPiar;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "PERIODO")
    private String periodo;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "EVALUACION")
    private String evaluacion;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "DBA")
    private String dba;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "BARRA_DE_APRENDIZAJE")
    private String barraDeAprendizaje;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "FLEXIBILIZACION")
    private String flexibilizacion;

    @Column(name = "SEGUIMIENTO_EVALUATIVO")
    private Integer seguimientoEvaluativo;

    @JoinColumn(name = "ESTUDIANTE_ID_ESTUDIANTE", referencedColumnName = "ID_ESTUDIANTE")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Estudiante estudianteIdEstudiante;

    public Piar() {
    }

    // Getters y Setters
    public Integer getIdPiar() {
        return idPiar;
    }

    public void setIdPiar(Integer idPiar) {
        this.idPiar = idPiar;
    }

    public String getCodigoPiar() {
        return codigoPiar;
    }

    public void setCodigoPiar(String codigoPiar) {
        this.codigoPiar = codigoPiar;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(String evaluacion) {
        this.evaluacion = evaluacion;
    }

    public String getDba() {
        return dba;
    }

    public void setDba(String dba) {
        this.dba = dba;
    }

    public String getBarraDeAprendizaje() {
        return barraDeAprendizaje;
    }

    public void setBarraDeAprendizaje(String barraDeAprendizaje) {
        this.barraDeAprendizaje = barraDeAprendizaje;
    }

    public String getFlexibilizacion() {
        return flexibilizacion;
    }

    public void setFlexibilizacion(String flexibilizacion) {
        this.flexibilizacion = flexibilizacion;
    }

    public Integer getSeguimientoEvaluativo() {
        return seguimientoEvaluativo;
    }

    public void setSeguimientoEvaluativo(Integer seguimientoEvaluativo) {
        this.seguimientoEvaluativo = seguimientoEvaluativo;
    }

    public Estudiante getEstudianteIdEstudiante() {
        return estudianteIdEstudiante;
    }

    public void setEstudianteIdEstudiante(Estudiante estudianteIdEstudiante) {
        this.estudianteIdEstudiante = estudianteIdEstudiante;
    }

    // Alias opcional para usar pi.idEstudiante en JSF
    public Estudiante getIdEstudiante() {
        return estudianteIdEstudiante;
    }

    public void setIdEstudiante(Estudiante est) {
        this.estudianteIdEstudiante = est;
    }
}
