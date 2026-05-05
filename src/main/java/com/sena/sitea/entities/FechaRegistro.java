/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sena.sitea.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

/**
 *
 * @author bjcab
 */
@Entity
@Table(name = "fecha_registro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FechaRegistro.findAll", query = "SELECT f FROM FechaRegistro f"),
    @NamedQuery(name = "FechaRegistro.findByIdFechaRegistro", query = "SELECT f FROM FechaRegistro f WHERE f.idFechaRegistro = :idFechaRegistro"),
    @NamedQuery(name = "FechaRegistro.findByCreacionRegistro", query = "SELECT f FROM FechaRegistro f WHERE f.creacionRegistro = :creacionRegistro"),
    @NamedQuery(name = "FechaRegistro.findByActualizacionRegistro", query = "SELECT f FROM FechaRegistro f WHERE f.actualizacionRegistro = :actualizacionRegistro")})
public class FechaRegistro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_FECHA_REGISTRO")
    private Integer idFechaRegistro;
    @Column(name = "CREACION_REGISTRO")
    @Temporal(TemporalType.DATE)
    private Date creacionRegistro;
    @Column(name = "ACTUALIZACION_REGISTRO")
    @Temporal(TemporalType.DATE)
    private Date actualizacionRegistro;
    @OneToMany(mappedBy = "fechaRegistroIdFechaRegistro", fetch = FetchType.LAZY)
    private List<Matricula> matriculaList;

    public FechaRegistro() {
    }

    public FechaRegistro(Integer idFechaRegistro) {
        this.idFechaRegistro = idFechaRegistro;
    }

    public Integer getIdFechaRegistro() {
        return idFechaRegistro;
    }

    public void setIdFechaRegistro(Integer idFechaRegistro) {
        this.idFechaRegistro = idFechaRegistro;
    }

    public Date getCreacionRegistro() {
        return creacionRegistro;
    }

    public void setCreacionRegistro(Date creacionRegistro) {
        this.creacionRegistro = creacionRegistro;
    }

    public Date getActualizacionRegistro() {
        return actualizacionRegistro;
    }

    public void setActualizacionRegistro(Date actualizacionRegistro) {
        this.actualizacionRegistro = actualizacionRegistro;
    }

    @XmlTransient
    public List<Matricula> getMatriculaList() {
        return matriculaList;
    }

    public void setMatriculaList(List<Matricula> matriculaList) {
        this.matriculaList = matriculaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFechaRegistro != null ? idFechaRegistro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FechaRegistro)) {
            return false;
        }
        FechaRegistro other = (FechaRegistro) object;
        if ((this.idFechaRegistro == null && other.idFechaRegistro != null) || (this.idFechaRegistro != null && !this.idFechaRegistro.equals(other.idFechaRegistro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sena.sitea.entities.FechaRegistro[ idFechaRegistro=" + idFechaRegistro + " ]";
    }
    
}
