/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sena.sitea.entities;

import java.io.Serializable;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bjcab
 */
@Entity
@Table(name = "evaluacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evaluacion.findAll", query = "SELECT e FROM Evaluacion e"),
    @NamedQuery(name = "Evaluacion.findByIdEvaluacion", query = "SELECT e FROM Evaluacion e WHERE e.idEvaluacion = :idEvaluacion"),
    @NamedQuery(name = "Evaluacion.findByCualitativaEvaluacion", query = "SELECT e FROM Evaluacion e WHERE e.cualitativaEvaluacion = :cualitativaEvaluacion"),
    @NamedQuery(name = "Evaluacion.findByCuantitativaEvaluacion", query = "SELECT e FROM Evaluacion e WHERE e.cuantitativaEvaluacion = :cuantitativaEvaluacion"),
    @NamedQuery(name = "Evaluacion.findByCriteriosEvaluacion", query = "SELECT e FROM Evaluacion e WHERE e.criteriosEvaluacion = :criteriosEvaluacion"),
    @NamedQuery(name = "Evaluacion.findByEvaluacionEstrategia", query = "SELECT e FROM Evaluacion e WHERE e.evaluacionEstrategia = :evaluacionEstrategia")})
public class Evaluacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_EVALUACION")
    private Integer idEvaluacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "CUALITATIVA_EVALUACION")
    private String cualitativaEvaluacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "CUANTITATIVA_EVALUACION")
    private String cuantitativaEvaluacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "CRITERIOS EVALUACION")
    private String criteriosEvaluacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "EVALUACION_ESTRATEGIA")
    private String evaluacionEstrategia;
    @JoinColumn(name = "PIAR_SEGUIMIENTO_EVALUATIVO", referencedColumnName = "SEGUIMIENTO_EVALUATIVO")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Piar piarSeguimientoEvaluativo;
    @JoinColumn(name = "BOLETIN_ACADEMICO_ID_BOLETIN_ACADEMICO", referencedColumnName = "ID_BOLETIN_ACADEMICO")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private BoletinAcademico boletinAcademicoIdBoletinAcademico;

    public Evaluacion() {
    }

    public Evaluacion(Integer idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public Evaluacion(Integer idEvaluacion, String cualitativaEvaluacion, String cuantitativaEvaluacion, String criteriosEvaluacion, String evaluacionEstrategia) {
        this.idEvaluacion = idEvaluacion;
        this.cualitativaEvaluacion = cualitativaEvaluacion;
        this.cuantitativaEvaluacion = cuantitativaEvaluacion;
        this.criteriosEvaluacion = criteriosEvaluacion;
        this.evaluacionEstrategia = evaluacionEstrategia;
    }

    public Integer getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(Integer idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public String getCualitativaEvaluacion() {
        return cualitativaEvaluacion;
    }

    public void setCualitativaEvaluacion(String cualitativaEvaluacion) {
        this.cualitativaEvaluacion = cualitativaEvaluacion;
    }

    public String getCuantitativaEvaluacion() {
        return cuantitativaEvaluacion;
    }

    public void setCuantitativaEvaluacion(String cuantitativaEvaluacion) {
        this.cuantitativaEvaluacion = cuantitativaEvaluacion;
    }

    public String getCriteriosEvaluacion() {
        return criteriosEvaluacion;
    }

    public void setCriteriosEvaluacion(String criteriosEvaluacion) {
        this.criteriosEvaluacion = criteriosEvaluacion;
    }

    public String getEvaluacionEstrategia() {
        return evaluacionEstrategia;
    }

    public void setEvaluacionEstrategia(String evaluacionEstrategia) {
        this.evaluacionEstrategia = evaluacionEstrategia;
    }

    public Piar getPiarSeguimientoEvaluativo() {
        return piarSeguimientoEvaluativo;
    }

    public void setPiarSeguimientoEvaluativo(Piar piarSeguimientoEvaluativo) {
        this.piarSeguimientoEvaluativo = piarSeguimientoEvaluativo;
    }

    public BoletinAcademico getBoletinAcademicoIdBoletinAcademico() {
        return boletinAcademicoIdBoletinAcademico;
    }

    public void setBoletinAcademicoIdBoletinAcademico(BoletinAcademico boletinAcademicoIdBoletinAcademico) {
        this.boletinAcademicoIdBoletinAcademico = boletinAcademicoIdBoletinAcademico;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEvaluacion != null ? idEvaluacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evaluacion)) {
            return false;
        }
        Evaluacion other = (Evaluacion) object;
        if ((this.idEvaluacion == null && other.idEvaluacion != null) || (this.idEvaluacion != null && !this.idEvaluacion.equals(other.idEvaluacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sena.sitea.entities.Evaluacion[ idEvaluacion=" + idEvaluacion + " ]";
    }
    
}
