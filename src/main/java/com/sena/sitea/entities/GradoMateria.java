/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sena.sitea.entities;

import java.io.Serializable;
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
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bjcab
 */
@Entity
@Table(name = "grado_materia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GradoMateria.findAll", query = "SELECT g FROM GradoMateria g"),
    @NamedQuery(name = "GradoMateria.findByIdGradoMateria", query = "SELECT g FROM GradoMateria g WHERE g.idGradoMateria = :idGradoMateria")})
public class GradoMateria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_GRADO_MATERIA")
    private Integer idGradoMateria;
    @JoinColumn(name = "GRADO_ID_GRADO", referencedColumnName = "ID_GRADO")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Grado gradoIdGrado;
    @JoinColumn(name = "MATERIA_ID_MATERIA", referencedColumnName = "ID_MATERIA")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Materia materiaIdMateria;

    public GradoMateria() {
    }

    public GradoMateria(Integer idGradoMateria) {
        this.idGradoMateria = idGradoMateria;
    }

    public Integer getIdGradoMateria() {
        return idGradoMateria;
    }

    public void setIdGradoMateria(Integer idGradoMateria) {
        this.idGradoMateria = idGradoMateria;
    }

    public Grado getGradoIdGrado() {
        return gradoIdGrado;
    }

    public void setGradoIdGrado(Grado gradoIdGrado) {
        this.gradoIdGrado = gradoIdGrado;
    }

    public Materia getMateriaIdMateria() {
        return materiaIdMateria;
    }

    public void setMateriaIdMateria(Materia materiaIdMateria) {
        this.materiaIdMateria = materiaIdMateria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGradoMateria != null ? idGradoMateria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GradoMateria)) {
            return false;
        }
        GradoMateria other = (GradoMateria) object;
        if ((this.idGradoMateria == null && other.idGradoMateria != null) || (this.idGradoMateria != null && !this.idGradoMateria.equals(other.idGradoMateria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sena.sitea.entities.GradoMateria[ idGradoMateria=" + idGradoMateria + " ]";
    }
    
}
