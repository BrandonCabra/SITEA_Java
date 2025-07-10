/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sena.sitea.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author bjcab
 */
@Entity
@Table(name = "boletin_academico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BoletinAcademico.findAll", query = "SELECT b FROM BoletinAcademico b"),
    @NamedQuery(name = "BoletinAcademico.findByIdBoletinAcademico", query = "SELECT b FROM BoletinAcademico b WHERE b.idBoletinAcademico = :idBoletinAcademico"),
    @NamedQuery(name = "BoletinAcademico.findByFechaBoletin", query = "SELECT b FROM BoletinAcademico b WHERE b.fechaBoletin = :fechaBoletin"),
    @NamedQuery(name = "BoletinAcademico.findByPeriodoBoletin", query = "SELECT b FROM BoletinAcademico b WHERE b.periodoBoletin = :periodoBoletin"),
    @NamedQuery(name = "BoletinAcademico.findByObservacionProf", query = "SELECT b FROM BoletinAcademico b WHERE b.observacionProf = :observacionProf"),
    @NamedQuery(name = "BoletinAcademico.findByEstadoBoletin", query = "SELECT b FROM BoletinAcademico b WHERE b.estadoBoletin = :estadoBoletin"),
    @NamedQuery(name = "BoletinAcademico.findByPromedio", query = "SELECT b FROM BoletinAcademico b WHERE b.promedio = :promedio"),
    @NamedQuery(name = "BoletinAcademico.findByRecomendaciones", query = "SELECT b FROM BoletinAcademico b WHERE b.recomendaciones = :recomendaciones")})
public class BoletinAcademico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_BOLETIN_ACADEMICO")
    private Integer idBoletinAcademico;
    @Column(name = "FECHA_BOLETIN")
    @Temporal(TemporalType.DATE)
    private Date fechaBoletin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "PERIODO_BOLETIN")
    private String periodoBoletin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "OBSERVACION_PROF")
    private String observacionProf;
    @Size(max = 45)
    @Column(name = "ESTADO_BOLETIN")
    private String estadoBoletin;
    @Size(max = 45)
    @Column(name = "PROMEDIO")
    private String promedio;
    @Size(max = 45)
    @Column(name = "RECOMENDACIONES")
    private String recomendaciones;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "boletinAcademicoIdBoletinAcademico", fetch = FetchType.LAZY)
    private List<Evaluacion> evaluacionList;
    @JoinColumn(name = "NOVEDADES_REPORTES_ID_NOVEDADES_REPORTES", referencedColumnName = "ID_NOVEDADES_REPORTES")
    @ManyToOne(fetch = FetchType.LAZY)
    private NovedadesReportes novedadesReportesIdNovedadesReportes;
    @JoinColumn(name = "ESTUDIANTE_ID_ESTUDIANTE", referencedColumnName = "ID_ESTUDIANTE")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Estudiante estudianteIdEstudiante;

    public BoletinAcademico() {
    }

    public BoletinAcademico(Integer idBoletinAcademico) {
        this.idBoletinAcademico = idBoletinAcademico;
    }

    public BoletinAcademico(Integer idBoletinAcademico, String periodoBoletin, String observacionProf) {
        this.idBoletinAcademico = idBoletinAcademico;
        this.periodoBoletin = periodoBoletin;
        this.observacionProf = observacionProf;
    }

    public Integer getIdBoletinAcademico() {
        return idBoletinAcademico;
    }

    public void setIdBoletinAcademico(Integer idBoletinAcademico) {
        this.idBoletinAcademico = idBoletinAcademico;
    }

    public Date getFechaBoletin() {
        return fechaBoletin;
    }

    public void setFechaBoletin(Date fechaBoletin) {
        this.fechaBoletin = fechaBoletin;
    }

    public String getPeriodoBoletin() {
        return periodoBoletin;
    }

    public void setPeriodoBoletin(String periodoBoletin) {
        this.periodoBoletin = periodoBoletin;
    }

    public String getObservacionProf() {
        return observacionProf;
    }

    public void setObservacionProf(String observacionProf) {
        this.observacionProf = observacionProf;
    }

    public String getEstadoBoletin() {
        return estadoBoletin;
    }

    public void setEstadoBoletin(String estadoBoletin) {
        this.estadoBoletin = estadoBoletin;
    }

    public String getPromedio() {
        return promedio;
    }

    public void setPromedio(String promedio) {
        this.promedio = promedio;
    }

    public String getRecomendaciones() {
        return recomendaciones;
    }

    public void setRecomendaciones(String recomendaciones) {
        this.recomendaciones = recomendaciones;
    }

    @XmlTransient
    public List<Evaluacion> getEvaluacionList() {
        return evaluacionList;
    }

    public void setEvaluacionList(List<Evaluacion> evaluacionList) {
        this.evaluacionList = evaluacionList;
    }

    public NovedadesReportes getNovedadesReportesIdNovedadesReportes() {
        return novedadesReportesIdNovedadesReportes;
    }

    public void setNovedadesReportesIdNovedadesReportes(NovedadesReportes novedadesReportesIdNovedadesReportes) {
        this.novedadesReportesIdNovedadesReportes = novedadesReportesIdNovedadesReportes;
    }

    public Estudiante getEstudianteIdEstudiante() {
        return estudianteIdEstudiante;
    }

    public void setEstudianteIdEstudiante(Estudiante estudianteIdEstudiante) {
        this.estudianteIdEstudiante = estudianteIdEstudiante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBoletinAcademico != null ? idBoletinAcademico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BoletinAcademico)) {
            return false;
        }
        BoletinAcademico other = (BoletinAcademico) object;
        if ((this.idBoletinAcademico == null && other.idBoletinAcademico != null) || (this.idBoletinAcademico != null && !this.idBoletinAcademico.equals(other.idBoletinAcademico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sena.sitea.entities.BoletinAcademico[ idBoletinAcademico=" + idBoletinAcademico + " ]";
    }
    
}
