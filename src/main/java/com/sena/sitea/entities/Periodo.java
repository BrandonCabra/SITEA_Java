/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sena.sitea.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author bjcab
 */
@Entity
@Table(name = "periodo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Periodo.findAll", query = "SELECT p FROM Periodo p"),
    @NamedQuery(name = "Periodo.findByIdPeriodo", query = "SELECT p FROM Periodo p WHERE p.idPeriodo = :idPeriodo"),
    @NamedQuery(name = "Periodo.findByNumeroPeriodo", query = "SELECT p FROM Periodo p WHERE p.numeroPeriodo = :numeroPeriodo"),
    @NamedQuery(name = "Periodo.findByFechaInicio", query = "SELECT p FROM Periodo p WHERE p.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "Periodo.findByFechaCierre", query = "SELECT p FROM Periodo p WHERE p.fechaCierre = :fechaCierre")})
public class Periodo implements Serializable {

    @Column(name = "FECHA_CIERRA")
    @Temporal(TemporalType.DATE)
    private Date fechaCierra;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PERIODO")
    private Integer idPeriodo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_PERIODO")
    private int numeroPeriodo;
    @Column(name = "FECHA_INICIO")
    @Temporal(TemporalType.TIME)
    private Date fechaInicio;
    @Column(name = "FECHA_CIERRE")
    @Temporal(TemporalType.TIME)
    private Date fechaCierre;
    @OneToMany(mappedBy = "periodo", fetch = FetchType.LAZY)
    private List<Dba> dbaList;

    public Periodo() {
    }

    public Periodo(Integer idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public Periodo(Integer idPeriodo, int numeroPeriodo) {
        this.idPeriodo = idPeriodo;
        this.numeroPeriodo = numeroPeriodo;
    }

    public Integer getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(Integer idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public int getNumeroPeriodo() {
        return numeroPeriodo;
    }

    public void setNumeroPeriodo(int numeroPeriodo) {
        this.numeroPeriodo = numeroPeriodo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    @XmlTransient
    public List<Dba> getDbaList() {
        return dbaList;
    }

    public void setDbaList(List<Dba> dbaList) {
        this.dbaList = dbaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPeriodo != null ? idPeriodo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Periodo)) {
            return false;
        }
        Periodo other = (Periodo) object;
        if ((this.idPeriodo == null && other.idPeriodo != null) || (this.idPeriodo != null && !this.idPeriodo.equals(other.idPeriodo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sena.sitea.entities.Periodo[ idPeriodo=" + idPeriodo + " ]";
    }

    public Date getFechaCierra() {
        return fechaCierra;
    }

    public void setFechaCierra(Date fechaCierra) {
        this.fechaCierra = fechaCierra;
    }
    
}
