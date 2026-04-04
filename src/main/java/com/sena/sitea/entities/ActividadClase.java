/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sena.sitea.entities;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bjcab
 */
@Entity
@Table(name = "actividad_clase")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActividadClase.findAll", query = "SELECT a FROM ActividadClase a"),
    @NamedQuery(name = "ActividadClase.findByIdActividadClase", query = "SELECT a FROM ActividadClase a WHERE a.idActividadClase = :idActividadClase"),
    @NamedQuery(name = "ActividadClase.findByPeriodoActividadClase", query = "SELECT a FROM ActividadClase a WHERE a.periodoActividadClase = :periodoActividadClase"),
    @NamedQuery(name = "ActividadClase.findByFechaActividadClase", query = "SELECT a FROM ActividadClase a WHERE a.fechaActividadClase = :fechaActividadClase"),
    @NamedQuery(name = "ActividadClase.findByDuaActividadClase", query = "SELECT a FROM ActividadClase a WHERE a.duaActividadClase = :duaActividadClase"),
    @NamedQuery(name = "ActividadClase.findByDescripcionActividadClase", query = "SELECT a FROM ActividadClase a WHERE a.descripcionActividadClase = :descripcionActividadClase"),
    @NamedQuery(name = "ActividadClase.findByDiarioPedagogico", query = "SELECT a FROM ActividadClase a WHERE a.diarioPedagogico = :diarioPedagogico")})
public class ActividadClase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ACTIVIDAD_CLASE")
    private Integer idActividadClase;
    @Size(max = 45)
    @Column(name = "PERIODO_ACTIVIDAD_CLASE")
    private String periodoActividadClase;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ACTIVIDAD_CLASE")
    @Temporal(TemporalType.DATE)
    private Date fechaActividadClase;
    @Size(max = 200)
    @Column(name = "DUA_ACTIVIDAD_CLASE")
    private String duaActividadClase;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "DESCRIPCION_ACTIVIDAD_CLASE")
    private String descripcionActividadClase;
    @Size(max = 100)
    @Column(name = "DIARIO_PEDAGOGICO")
    private String diarioPedagogico;
    @JoinColumn(name = "PIAR_CODIGO_PIAR", referencedColumnName = "ID_PIAR")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Piar piarCodigoPiar;

    public ActividadClase() {
    }

    public ActividadClase(Integer idActividadClase) {
        this.idActividadClase = idActividadClase;
    }

    public ActividadClase(Integer idActividadClase, Date fechaActividadClase, String descripcionActividadClase) {
        this.idActividadClase = idActividadClase;
        this.fechaActividadClase = fechaActividadClase;
        this.descripcionActividadClase = descripcionActividadClase;
    }

    public Integer getIdActividadClase() {
        return idActividadClase;
    }

    public void setIdActividadClase(Integer idActividadClase) {
        this.idActividadClase = idActividadClase;
    }

    public String getPeriodoActividadClase() {
        return periodoActividadClase;
    }

    public void setPeriodoActividadClase(String periodoActividadClase) {
        this.periodoActividadClase = periodoActividadClase;
    }

    public Date getFechaActividadClase() {
        return fechaActividadClase;
    }

    public void setFechaActividadClase(Date fechaActividadClase) {
        this.fechaActividadClase = fechaActividadClase;
    }

    public String getDuaActividadClase() {
        return duaActividadClase;
    }

    public void setDuaActividadClase(String duaActividadClase) {
        this.duaActividadClase = duaActividadClase;
    }

    public String getDescripcionActividadClase() {
        return descripcionActividadClase;
    }

    public void setDescripcionActividadClase(String descripcionActividadClase) {
        this.descripcionActividadClase = descripcionActividadClase;
    }

    public String getDiarioPedagogico() {
        return diarioPedagogico;
    }

    public void setDiarioPedagogico(String diarioPedagogico) {
        this.diarioPedagogico = diarioPedagogico;
    }

    public Piar getPiarCodigoPiar() {
        return piarCodigoPiar;
    }

    public void setPiarCodigoPiar(Piar piarCodigoPiar) {
        this.piarCodigoPiar = piarCodigoPiar;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idActividadClase != null ? idActividadClase.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActividadClase)) {
            return false;
        }
        ActividadClase other = (ActividadClase) object;
        if ((this.idActividadClase == null && other.idActividadClase != null) || (this.idActividadClase != null && !this.idActividadClase.equals(other.idActividadClase))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sena.sitea.entities.ActividadClase[ idActividadClase=" + idActividadClase + " ]";
    }
    
}
