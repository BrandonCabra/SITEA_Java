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
@Table(name = "grado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grado.findAll", query = "SELECT g FROM Grado g"),
    @NamedQuery(name = "Grado.findByIdGrado", query = "SELECT g FROM Grado g WHERE g.idGrado = :idGrado"),
    @NamedQuery(name = "Grado.findByCodigoGrado", query = "SELECT g FROM Grado g WHERE g.codigoGrado = :codigoGrado"),
    @NamedQuery(name = "Grado.findByCantidadEstudiantes", query = "SELECT g FROM Grado g WHERE g.cantidadEstudiantes = :cantidadEstudiantes")})
public class Grado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_GRADO")
    private Integer idGrado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "CODIGO_GRADO")
    private String codigoGrado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANTIDAD_ESTUDIANTES")
    private int cantidadEstudiantes;
    @OneToMany(mappedBy = "gradoIdGrado", fetch = FetchType.LAZY)
    private List<Estudiante> estudianteList;
    @JoinColumn(name = "INSTITUCION_ID_INSTITUCION", referencedColumnName = "ID_INSTITUCION")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Institucion institucionIdInstitucion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gradoIdGrado", fetch = FetchType.LAZY)
    private List<GradoMateria> gradoMateriaList;

    public Grado() {
    }

    public Grado(Integer idGrado) {
        this.idGrado = idGrado;
    }

    public Grado(Integer idGrado, String codigoGrado, int cantidadEstudiantes) {
        this.idGrado = idGrado;
        this.codigoGrado = codigoGrado;
        this.cantidadEstudiantes = cantidadEstudiantes;
    }

    public Integer getIdGrado() {
        return idGrado;
    }

    public void setIdGrado(Integer idGrado) {
        this.idGrado = idGrado;
    }

    public String getCodigoGrado() {
        return codigoGrado;
    }

    public void setCodigoGrado(String codigoGrado) {
        this.codigoGrado = codigoGrado;
    }

    public int getCantidadEstudiantes() {
        return cantidadEstudiantes;
    }

    public void setCantidadEstudiantes(int cantidadEstudiantes) {
        this.cantidadEstudiantes = cantidadEstudiantes;
    }

    @XmlTransient
    public List<Estudiante> getEstudianteList() {
        return estudianteList;
    }

    public void setEstudianteList(List<Estudiante> estudianteList) {
        this.estudianteList = estudianteList;
    }

    public Institucion getInstitucionIdInstitucion() {
        return institucionIdInstitucion;
    }

    public void setInstitucionIdInstitucion(Institucion institucionIdInstitucion) {
        this.institucionIdInstitucion = institucionIdInstitucion;
    }

    @XmlTransient
    public List<GradoMateria> getGradoMateriaList() {
        return gradoMateriaList;
    }

    public void setGradoMateriaList(List<GradoMateria> gradoMateriaList) {
        this.gradoMateriaList = gradoMateriaList;
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
