/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sena.sitea.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author bjcab
 */
@Entity
@Table(name = "piar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Piar.findAll", query = "SELECT p FROM Piar p"),
    @NamedQuery(name = "Piar.findByIdPiar", query = "SELECT p FROM Piar p WHERE p.idPiar = :idPiar"),
    @NamedQuery(name = "Piar.findByCodigoPiar", query = "SELECT p FROM Piar p WHERE p.codigoPiar = :codigoPiar"),
    @NamedQuery(name = "Piar.findByPeriodo", query = "SELECT p FROM Piar p WHERE p.periodo = :periodo"),
    @NamedQuery(name = "Piar.findByAreaIdArea", query = "SELECT p FROM Piar p WHERE p.areaIdArea = :areaIdArea"),
    @NamedQuery(name = "Piar.findByBarraDeAprendizaje", query = "SELECT p FROM Piar p WHERE p.barraDeAprendizaje = :barraDeAprendizaje"),
    @NamedQuery(name = "Piar.findByFlexibilizacion", query = "SELECT p FROM Piar p WHERE p.flexibilizacion = :flexibilizacion"),
    @NamedQuery(name = "Piar.findByEvaluacion", query = "SELECT p FROM Piar p WHERE p.evaluacion = :evaluacion"),
    @NamedQuery(name = "Piar.findBySeguimientoEvaluativo", query = "SELECT p FROM Piar p WHERE p.seguimientoEvaluativo = :seguimientoEvaluativo")})
public class Piar implements Serializable {

    private static final long serialVersionUID = 1L;
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
    @Size(max = 45)
    @Column(name = "PERIODO")
    private String periodo;
    @Column(name = "AREA_ID_AREA")
    private Integer areaIdArea;
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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "EVALUACION")
    private String evaluacion;
    @Column(name = "SEGUIMIENTO_EVALUATIVO")
    private Integer seguimientoEvaluativo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "piarCodigoPiar", fetch = FetchType.LAZY)
    private List<ActividadClase> actividadClaseList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "piarSeguimientoEvaluativo", fetch = FetchType.LAZY)
    private List<Evaluacion> evaluacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "piarIdPiar", fetch = FetchType.LAZY)
    private List<PiarHasEstrategiasEducativas> piarHasEstrategiasEducativasList;
    @JoinColumn(name = "ESTUDIANTE_ID_ESTUDIANTE", referencedColumnName = "ID_ESTUDIANTE")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Estudiante estudianteIdEstudiante;
    @JoinColumn(name = "DBA_ID_DBA", referencedColumnName = "ID_DBA")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Dba dbaIdDba;

    public Piar() {
    }

    public Piar(Integer idPiar) {
        this.idPiar = idPiar;
    }

    public Piar(Integer idPiar, String codigoPiar, String barraDeAprendizaje, String flexibilizacion, String evaluacion) {
        this.idPiar = idPiar;
        this.codigoPiar = codigoPiar;
        this.barraDeAprendizaje = barraDeAprendizaje;
        this.flexibilizacion = flexibilizacion;
        this.evaluacion = evaluacion;
    }

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

    public Integer getAreaIdArea() {
        return areaIdArea;
    }

    public void setAreaIdArea(Integer areaIdArea) {
        this.areaIdArea = areaIdArea;
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

    public String getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(String evaluacion) {
        this.evaluacion = evaluacion;
    }

    public Integer getSeguimientoEvaluativo() {
        return seguimientoEvaluativo;
    }

    public void setSeguimientoEvaluativo(Integer seguimientoEvaluativo) {
        this.seguimientoEvaluativo = seguimientoEvaluativo;
    }

    @XmlTransient
    public List<ActividadClase> getActividadClaseList() {
        return actividadClaseList;
    }

    public void setActividadClaseList(List<ActividadClase> actividadClaseList) {
        this.actividadClaseList = actividadClaseList;
    }

    @XmlTransient
    public List<Evaluacion> getEvaluacionList() {
        return evaluacionList;
    }

    public void setEvaluacionList(List<Evaluacion> evaluacionList) {
        this.evaluacionList = evaluacionList;
    }

    @XmlTransient
    public List<PiarHasEstrategiasEducativas> getPiarHasEstrategiasEducativasList() {
        return piarHasEstrategiasEducativasList;
    }

    public void setPiarHasEstrategiasEducativasList(List<PiarHasEstrategiasEducativas> piarHasEstrategiasEducativasList) {
        this.piarHasEstrategiasEducativasList = piarHasEstrategiasEducativasList;
    }

    public Estudiante getEstudianteIdEstudiante() {
        return estudianteIdEstudiante;
    }

    public void setEstudianteIdEstudiante(Estudiante estudianteIdEstudiante) {
        this.estudianteIdEstudiante = estudianteIdEstudiante;
    }

    public Dba getDbaIdDba() {
        return dbaIdDba;
    }

    public void setDbaIdDba(Dba dbaIdDba) {
        this.dbaIdDba = dbaIdDba;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPiar != null ? idPiar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Piar)) {
            return false;
        }
        Piar other = (Piar) object;
        if ((this.idPiar == null && other.idPiar != null) || (this.idPiar != null && !this.idPiar.equals(other.idPiar))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sena.sitea.entities.Piar[ idPiar=" + idPiar + " ]";
    }
    
}
