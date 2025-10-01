/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sena.sitea.entities;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author bjcab
 */
@Entity
@Table(name = "curso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Curso.findAll", query = "SELECT c FROM Curso c"),
    @NamedQuery(name = "Curso.findByIdCurso", query = "SELECT c FROM Curso c WHERE c.idCurso = :idCurso"),
    @NamedQuery(name = "Curso.findByCodigoCurso", query = "SELECT c FROM Curso c WHERE c.codigoCurso = :codigoCurso"),
    @NamedQuery(name = "Curso.findByCantidadEstudiante", query = "SELECT c FROM Curso c WHERE c.cantidadEstudiante = :cantidadEstudiante")})
public class Curso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_CURSO")
    private Integer idCurso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_CURSO")
    private int codigoCurso;
    @Column(name = "CANTIDAD_ESTUDIANTE")
    private Integer cantidadEstudiante;
    @OneToMany(mappedBy = "cursoIdCurso", fetch = FetchType.LAZY)
    private List<Estudiante> estudianteList;
    @JoinColumn(name = "GRADO_ID_GRADO", referencedColumnName = "ID_GRADO")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Grado gradoIdGrado;
    @JoinColumn(name = "ID_INSTITUCION_ID", referencedColumnName = "ID_INSTITUCION")
    @ManyToOne(fetch = FetchType.LAZY)
    private Institucion idInstitucionId;

    public Curso() {
    }

    public Curso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public Curso(Integer idCurso, int codigoCurso) {
        this.idCurso = idCurso;
        this.codigoCurso = codigoCurso;
    }

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public int getCodigoCurso() {
        return codigoCurso;
    }

    public void setCodigoCurso(int codigoCurso) {
        this.codigoCurso = codigoCurso;
    }

    public Integer getCantidadEstudiante() {
        return cantidadEstudiante;
    }

    public void setCantidadEstudiante(Integer cantidadEstudiante) {
        this.cantidadEstudiante = cantidadEstudiante;
    }

    @XmlTransient
    public List<Estudiante> getEstudianteList() {
        return estudianteList;
    }

    public void setEstudianteList(List<Estudiante> estudianteList) {
        this.estudianteList = estudianteList;
    }

    public Grado getGradoIdGrado() {
        return gradoIdGrado;
    }

    public void setGradoIdGrado(Grado gradoIdGrado) {
        this.gradoIdGrado = gradoIdGrado;
    }

    public Institucion getIdInstitucionId() {
        return idInstitucionId;
    }

    public void setIdInstitucionId(Institucion idInstitucionId) {
        this.idInstitucionId = idInstitucionId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCurso != null ? idCurso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Curso)) {
            return false;
        }
        Curso other = (Curso) object;
        if ((this.idCurso == null && other.idCurso != null) || (this.idCurso != null && !this.idCurso.equals(other.idCurso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sena.sitea.entities.Curso[ idCurso=" + idCurso + " ]";
    }
    
}
