/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sena.sitea.entities;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

/**
 *
 * @author bjcab
 */
@Entity
@Table(name = "grado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grado.findAll", query = "SELECT g FROM Grado g"),
    @NamedQuery(name = "Grado.findByIdGrado", query = "SELECT g FROM Grado g WHERE g.idGrado = :idGrado"),
    @NamedQuery(name = "Grado.findByNivelGrado", query = "SELECT g FROM Grado g WHERE g.nivelGrado = :nivelGrado"),
    @NamedQuery(name = "Grado.findByCicloGrado", query = "SELECT g FROM Grado g WHERE g.cicloGrado = :cicloGrado")})
public class Grado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_GRADO")
    private Integer idGrado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "NIVEL_GRADO")
    private String nivelGrado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "CICLO_GRADO")
    private String cicloGrado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gradoIdGrado", fetch = FetchType.LAZY)
    private List<GradoMateria> gradoMateriaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grado", fetch = FetchType.LAZY)
    private List<Dba> dbaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gradoIdGrado", fetch = FetchType.LAZY)
    private List<Curso> cursoList;

    public Grado() {
    }

    public Grado(Integer idGrado) {
        this.idGrado = idGrado;
    }

    public Grado(Integer idGrado, String nivelGrado, String cicloGrado) {
        this.idGrado = idGrado;
        this.nivelGrado = nivelGrado;
        this.cicloGrado = cicloGrado;
    }

    public Integer getIdGrado() {
        return idGrado;
    }

    public void setIdGrado(Integer idGrado) {
        this.idGrado = idGrado;
    }

    public String getNivelGrado() {
        return nivelGrado;
    }

    public void setNivelGrado(String nivelGrado) {
        this.nivelGrado = nivelGrado;
    }

    public String getCicloGrado() {
        return cicloGrado;
    }

    public void setCicloGrado(String cicloGrado) {
        this.cicloGrado = cicloGrado;
    }

    @XmlTransient
    public List<GradoMateria> getGradoMateriaList() {
        return gradoMateriaList;
    }

    public void setGradoMateriaList(List<GradoMateria> gradoMateriaList) {
        this.gradoMateriaList = gradoMateriaList;
    }

    @XmlTransient
    public List<Dba> getDbaList() {
        return dbaList;
    }

    public void setDbaList(List<Dba> dbaList) {
        this.dbaList = dbaList;
    }

    @XmlTransient
    public List<Curso> getCursoList() {
        return cursoList;
    }

    public void setCursoList(List<Curso> cursoList) {
        this.cursoList = cursoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGrado != null ? idGrado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grado)) {
            return false;
        }
        Grado other = (Grado) object;
        if ((this.idGrado == null && other.idGrado != null) || (this.idGrado != null && !this.idGrado.equals(other.idGrado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sena.sitea.entities.Grado[ idGrado=" + idGrado + " ]";
    }
    
}
